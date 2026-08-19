package com.github.ringlocker.substancecraft.block.entity;

import com.github.ringlocker.substancecraft.block.GenericMenuBlock;
import com.github.ringlocker.substancecraft.recipe.MultipleItemInput;
import com.github.ringlocker.substancecraft.recipe.recipes.ByproductRecipe;
import net.fabricmc.fabric.api.menu.v1.ExtendedMenuProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.Containers;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class WorkstationBlockEntity<T extends ByproductRecipe> extends RecipeTypedBlockEntity<T> implements ExtendedMenuProvider<BlockPos>, ImplementedInventory {

    protected final String displayName;
    protected final NonNullList<ItemStack> inventory;

    protected int progress;
    protected int maxProgress;

    protected final int FIRST_INPUT_SLOT = 0;
    protected final int CATALYST_SLOT = 7;
    protected final int OUTPUT_SLOT = 8;
    protected final int FIRST_BYPRODUCT_SLOT = 9;

    protected final SimpleContainerData data = new SimpleContainerData(3) {
        @Override
        public int get(int index) {
            return switch (index) {
                case 0 -> WorkstationBlockEntity.this.progress;
                case 1 -> WorkstationBlockEntity.this.maxProgress;
                case 2 -> WorkstationBlockEntity.this.selectedRecipeIndex;
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0 -> WorkstationBlockEntity.this.progress = value;
                case 1 -> WorkstationBlockEntity.this.maxProgress = value;
                case 2 -> WorkstationBlockEntity.this.selectedRecipeIndex = value;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    };

    public WorkstationBlockEntity(BlockEntityType<?> type, RecipeType<T> recipeType, BlockPos pos, BlockState blockState, String displayName) {
        super(type, pos, blockState, recipeType);
        this.displayName = displayName;
        this.inventory = NonNullList.withSize(12, ItemStack.EMPTY);
    }

    @Override
    public BlockPos getScreenOpeningData(ServerPlayer player) {
        return worldPosition;
    }

    @Override
    public @NotNull CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveWithFullMetadata(registries);
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        ContainerHelper.loadAllItems(input, inventory);
        progress = input.getIntOr("Progress", 0);
        maxProgress = input.getIntOr("MaxProgress", 0);
        selectedRecipeIndex = input.getIntOr("SelectedRecipeIndex", 0);
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        ContainerHelper.saveAllItems(output, inventory);
        output.putInt("Progress", progress);
        output.putInt("MaxProgress", maxProgress);
        output.putInt("SelectedRecipeIndex", selectedRecipeIndex);
    }

    @Override
    protected boolean recipeMatches() {
        boolean ingredientsMatch = getRecipe().matches(new MultipleItemInput(noAirInputs()), level);
        if (!ingredientsMatch) return false;
        T recipe = getRecipe();
        Optional<ItemStackTemplate> catalyst = recipe.getCatalyst();
        return catalyst.map(itemStackTemplate -> itemStackTemplate.is(getItem(CATALYST_SLOT).getItem())).orElse(true);
    }

    @Override
    protected void onSelectRecipeChange() {
        if (level == null || level.isClientSide()) return;
        ByproductRecipe recipe = getRecipes().get(getSelectedRecipeIndex()).value();
        int inputs = recipe.getInputs().size();
        int byproducts = recipe.getByproducts().size();
        for (int i = inputs; i < 7; i++) {
            moveOrDropItem(i, inputs, byproducts);
        }
        if (!recipe.hasCatalyst()) moveOrDropItem(CATALYST_SLOT, inputs, byproducts);
        for (int i = FIRST_BYPRODUCT_SLOT + byproducts; i < 12; i++) {
            moveOrDropItem(i, inputs, byproducts);
        }
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.literal(displayName);
    }

    @Override
    public void setItem(int slot, ItemStack stack) {
        ItemStack itemStack = inventory.get(slot);
        boolean itemsEqual = !stack.isEmpty() && ItemStack.isSameItem(itemStack, stack);
        inventory.set(slot, stack);
        if (slot >= FIRST_INPUT_SLOT && slot < OUTPUT_SLOT && !itemsEqual) {
            maxProgress = getCookTime();
            progress = 0;
            setChanged();
        }
    }

    @Override
    public boolean canPlaceItem(int slot, ItemStack stack) {
        return (slot < OUTPUT_SLOT && slot < inputCount()) || ( getRecipe().hasCatalyst() && slot == CATALYST_SLOT);
    }

    @Override
    public boolean canTakeItem(Container target, int slot, ItemStack stack) {
        return slot >= OUTPUT_SLOT;
    }

    @Override
    public NonNullList<ItemStack> getItems() {
        return inventory;
    }

    public ItemStack getRenderStack() {
        if(this.getItem(OUTPUT_SLOT).isEmpty()) {
            return this.getItem(FIRST_INPUT_SLOT);
        } else {
            return this.getItem(OUTPUT_SLOT);
        }
    }

    public void tick(Level level, BlockPos pos, BlockState state) {
        if (level.isClientSide()) return;
        updateState(state, level, pos);

        if (getRecipe() == null) {
            progress = 0;
            return;
        }

        if (!isOutputSlotEmptyOrReceivable() || !areByproductSlotsEmptyOrReceivable()) {
            progress = 0;
            return;
        }

        if (!recipeMatches()) {
            progress = 0;
            return;
        }

        progress++;
        setChanged(level, pos, state);
        if (progress >= maxProgress) {
            craftItem();
            progress = 0;
        }

    }

    protected void updateState(BlockState state, Level level, BlockPos pos) {
        if (progress > 0) {
            if (!state.getValue(GenericMenuBlock.LIT)) level.setBlockAndUpdate(pos, state.setValue(GenericMenuBlock.LIT, true));
        } else if (state.getValue(GenericMenuBlock.LIT)) {
            if (state.getValue(GenericMenuBlock.LIT)) level.setBlockAndUpdate(pos, state.setValue(GenericMenuBlock.LIT, false));
        }
    }

    protected boolean canInsertItemIntoSlot(Item item, int slot) {
        return getItem(slot).getItem() == item || getItem(slot).isEmpty();
    }

    protected boolean canInsertAmountIntoSlot(ItemStack result, int slot) {
            return getItem(slot).getCount() + result.getCount() <= result.getMaxStackSize();
    }

    protected boolean isOutputSlotEmptyOrReceivable() {
        return getItem(OUTPUT_SLOT).isEmpty() || getItem(OUTPUT_SLOT).getCount() < getItem(OUTPUT_SLOT).getMaxStackSize();
    }

    protected boolean areByproductSlotsEmptyOrReceivable() {
        if (byproductCount() == 0) return true;
        List<ItemStackTemplate> byproducts = getRecipe().getByproducts();
        for (int i = 0; i < byproductCount(); i++) {
            if (!canInsertAmountIntoSlot(byproducts.get(i).create(), i + FIRST_BYPRODUCT_SLOT))  return false;
        }
        return true;
    }

    protected void byproduct(ByproductRecipe recipe) {
        List<ItemStackTemplate> byproducts = recipe.getByproducts();
        if (byproducts.isEmpty()) { return; }
        int index = 0;
        for (ItemStackTemplate byproductTemplate : byproducts) {
            ItemStack byproduct = byproductTemplate.create();
            if (getLevel() == null) return;
            if (getLevel().getRandom().nextInt(100) > byproduct.getCount() << 1) {
                index++;
                continue;
            }
            int slot = FIRST_BYPRODUCT_SLOT + index;
            if (!canInsertItemIntoSlot(byproduct.getItem(), slot)) {
                index++;
                continue;
            }
            if (!canInsertAmountIntoSlot(byproduct, slot)) {
                index++;
                continue;
            }
            setItem(slot, new ItemStack(byproduct.getItem(), getItem(slot).getCount() + 1));
            index++;
        }
    }

    private void moveOrDropItem(int fromIndex, int recipeInputs, int recipeByproducts) {
        if (inventory.get(fromIndex) == ItemStack.EMPTY) return;
        if (moveItemToEmptySlot(fromIndex, recipeInputs, recipeByproducts)) return;
        if (level == null) return;
        BlockPos pos = getBlockPos();
        Containers.dropItemStack(level, pos.getX(), pos.getY() + 1, pos.getZ(), inventory.get(fromIndex));
        inventory.set(fromIndex, ItemStack.EMPTY);
    }

    private boolean moveItemToEmptySlot(int fromIndex, int recipeInputs, int recipeByproducts) {
        for (int i = recipeInputs - 1; i >= 0; i--) {
            if (i == fromIndex) continue;
            if (inventory.get(i) != ItemStack.EMPTY) continue;
            moveToSlot(fromIndex, i);
            return true;
        }
        for (int i = FIRST_BYPRODUCT_SLOT + recipeByproducts; i >= FIRST_BYPRODUCT_SLOT; i--) {
            if (i == fromIndex) continue;
            if (inventory.get(i) != ItemStack.EMPTY) continue;
            moveToSlot(fromIndex, i);
            return true;
        }
        return false;
    }

    private void moveToSlot(int fromIndex, int toIndex) {
        inventory.set(toIndex, inventory.get(fromIndex));
        inventory.set(fromIndex, ItemStack.EMPTY);
    }

    private void craftItem() {
        T recipe = getRecipe();
        for (int i = 0; i < 4; i++) {
            removeItem(FIRST_INPUT_SLOT + i, 1);
        }
        ItemStack result = recipe.getResult().create();
        if (canInsertAmountIntoSlot(result, OUTPUT_SLOT)) {
            setItem(OUTPUT_SLOT, new ItemStack(result.getItem(), getItem(OUTPUT_SLOT).getCount() + recipe.getResult().create().getCount()));
        }
        byproduct(recipe);
    }

    private int getCookTime() {
        Optional<RecipeHolder<T>> recipe = getSelectedRecipe();
        return recipe.map(tRecipeHolder -> tRecipeHolder.value().time()).orElse(200);
    }

    private List<ItemStack> noAirInputs() {
        return inventory.subList(FIRST_INPUT_SLOT, CATALYST_SLOT).stream().filter(itemStack -> !itemStack.isEmpty() && !itemStack.getItem().equals(Items.AIR)).collect(Collectors.toList());
    }

}
