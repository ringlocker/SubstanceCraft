package com.github.ringlocker.substancecraft.block.entity.entities;

import com.github.ringlocker.substancecraft.block.blocks.GenericMenuBlock;
import com.github.ringlocker.substancecraft.block.entity.ImplementedInventory;
import com.github.ringlocker.substancecraft.block.entity.RecipeList;
import com.github.ringlocker.substancecraft.recipe.MultipleItemInput;
import com.github.ringlocker.substancecraft.recipe.recipes.ByproductRecipe;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
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
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class WorkstationBlockEntity<T extends ByproductRecipe> extends BlockEntity implements ExtendedScreenHandlerFactory<BlockPos>, ImplementedInventory, RecipeList<T> {

    protected final String displayName;
    protected final NonNullList<ItemStack> inventory;

    protected int progress;
    protected int maxProgress;
    protected int selectedRecipeIndex;

    public final RecipeManager.CachedCheck<MultipleItemInput, T> matchGetter;
    private final RecipeType<T> type;
    private final List<RecipeHolder<T>> recipes;

    protected final int FIRST_INPUT_SLOT = 0;
    protected final int OUTPUT_SLOT = 4;
    protected final int FIRST_BYPRODUCT_SLOT = 5;

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
        super(type, pos, blockState);
        this.displayName = displayName;
        this.inventory = NonNullList.withSize(8, ItemStack.EMPTY);
        this.matchGetter = RecipeManager.createCheck(recipeType);
        this.type = recipeType;
        this.recipes = new ArrayList<>();
    }

    @Override
    public void setLevel(Level level) {
        super.setLevel(level);
        setupRecipeList(level);
    }

    @Override
    public List<RecipeHolder<T>> getRecipes() {
        return this.recipes;
    }

    @NotNull
    protected List<RecipeHolder<T>> getRecipeList(RecipeType<T> type, Level level) {
        List<RecipeHolder<T>> allRecipes;
        allRecipes = new ArrayList<>(level.recipeAccess().getSynchronizedRecipes().getAllOfType(type));
        return allRecipes;
    }

    @Override
    public void setItem(int slot, ItemStack stack) {
        ItemStack itemStack = this.inventory.get(slot);
        boolean itemsEqual = !stack.isEmpty() && ItemStack.isSameItem(itemStack, stack);
        this.inventory.set(slot, stack);
        if (slot >= FIRST_INPUT_SLOT && slot < OUTPUT_SLOT && !itemsEqual) {
            this.maxProgress = getCookTime();
            this.progress = 0;
            this.setChanged();
        }
    }

    @Override
    public BlockPos getScreenOpeningData(ServerPlayer player) {
        return worldPosition;
    }

    @Override
    public @NotNull Component getDisplayName() {
        return Component.literal(displayName);
    }

    @Override
    public boolean canPlaceItem(int slot, ItemStack stack) {
        return slot < OUTPUT_SLOT && slot < inputCount();
    }

    @Override
    public boolean canTakeItem(Container target, int slot, ItemStack stack) {
        return slot >= OUTPUT_SLOT;
    }

    @Override
    public NonNullList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public @NotNull CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return this.saveWithFullMetadata(registries);
    }

    @Override
    public void setChanged() {
        if (level != null) {
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
            super.setChanged();
        }
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        ContainerHelper.loadAllItems(input, this.inventory);
        progress = input.getIntOr("Progress", 0);
        maxProgress = input.getIntOr("MaxProgress", 0);
        selectedRecipeIndex = input.getIntOr("SelectedRecipeIndex", 0);
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        ContainerHelper.saveAllItems(output, this.inventory);
        output.putInt("Progress", progress);
        output.putInt("MaxProgress", maxProgress);
        output.putInt("SelectedRecipeIndex", selectedRecipeIndex);
    }

    public ItemStack getRenderStack() {
        if(this.getItem(OUTPUT_SLOT).isEmpty()) {
            return this.getItem(FIRST_INPUT_SLOT);
        } else {
            return this.getItem(OUTPUT_SLOT);
        }
    }

    public int inputCount() {
        return getCurrentRecipe().map(tRecipeHolder -> tRecipeHolder.value().getInputs().size()).orElse(1);
    }

    public int byproductCount() {
        return getCurrentRecipe().map(tRecipeHolder -> tRecipeHolder.value().getByproducts().size()).orElse(0);
    }

    public void setupRecipeList(Level level) {
        this.recipes.clear();
        List<RecipeHolder<T>> allRecipes = getRecipeList(type, level);
        recipes.addAll(allRecipes);
        recipes.sort(Comparator.comparing(recipe -> recipe.value().getResult().getDisplayName().getString()));
    }

    public void tick(Level level, BlockPos pos, BlockState state) {
        if (level.isClientSide()) return;
        updateState(state, level, pos);
        if (isSlotEmptyOrReceivable(OUTPUT_SLOT)) {
            if (hasRecipe()) {
                T recipe = getRecipes().get(getSelectedRecipeIndex()).value();
                if (recipe.matches(new MultipleItemInput(noAirInputs()), level)) {
                    if ((inventory.get(OUTPUT_SLOT).getCount() == 0) || inventory.get(OUTPUT_SLOT).getItem() == recipe.getResult().getItem()) {
                        progress++;
                        setChanged(level, pos, state);
                        if (progress >= maxProgress) {
                            craftItem(recipe);
                            resetProgress();
                        }
                    } else {
                        resetProgress();
                    }
                }
            } else {
                resetProgress();
            }
        } else {
            resetProgress();
            setChanged(level, pos, state);
        }
    }

    public Optional<RecipeHolder<T>> getCurrentRecipe() {
        List<RecipeHolder<T>> recipes = getRecipes();
        if (recipes.isEmpty()) {
            return Optional.empty();
        } else {
            int index = getSelectedRecipeIndex();
            if (index > -1 && index < recipes.size()) {
                return Optional.of(recipes.get(index));
            } else return Optional.empty();
        }
    }

    public int getSelectedRecipeIndex() {
        return this.selectedRecipeIndex;
    }

    public void setSelectedRecipeIndex(int selectedRecipeIndex) {
        this.selectedRecipeIndex = selectedRecipeIndex;
        onSelectRecipeChange();
        setChanged();
    }

    protected void updateState(BlockState state, Level level, BlockPos pos) {
        if (progress > 0) {
            level.setBlockAndUpdate(pos, state.setValue(GenericMenuBlock.LIT, true));
        } else if (state.getValue(GenericMenuBlock.LIT)) {
            level.setBlockAndUpdate(pos, state.setValue(GenericMenuBlock.LIT, false));
        }
    }

    protected boolean canInsertItemIntoSlot(Item item, int slot) {
        return this.getItem(slot).getItem() == item || getItem(slot).isEmpty();
    }

    protected boolean canInsertAmountIntoSlot(ItemStack result, int slot) {
            return this.getItem(slot).getCount() + result.getCount() <= result.getMaxStackSize();
    }

    protected boolean isSlotEmptyOrReceivable(int slot) {
        return this.getItem(slot).isEmpty() || getItem(slot).getCount() < getItem(slot).getMaxStackSize();
    }

    protected void byproduct(ByproductRecipe recipe) {
        List<ItemStack> byproducts = recipe.getByproducts();
        if (byproducts.isEmpty()) { return; }
        int index = 0;
        for (ItemStack byproduct : byproducts) {
            if (getLevel() == null) return;
            if (getLevel().random.nextInt(100) > byproduct.getCount() << 1) {
                index++;
                continue;
            }
            int slot = 5 + index;
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

    private void onSelectRecipeChange() {
        if (level == null || level.isClientSide()) return;
        ByproductRecipe recipe = getRecipes().get(getSelectedRecipeIndex()).value();
        int inputs = recipe.getInputs().size();
        int byproducts = recipe.getByproducts().size();
        for (int i = inputs; i < 4; i++) {
            moveOrDropItem(i, inputs, byproducts);
        }
        for (int i = 5 + byproducts; i < 8; i++) {
            moveOrDropItem(i, inputs, byproducts);
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
        for (int i = 4 + recipeByproducts; i > 3; i--) {
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

    private void resetProgress() {
        this.progress = 0;
    }

    private void craftItem(T recipe) {
        for (int i = 0; i < 4; i++) {
            this.removeItem(FIRST_INPUT_SLOT + i, 1);
        }
        ItemStack result = recipe.getResult();
        if (canInsertAmountIntoSlot(result, OUTPUT_SLOT)) {
            this.setItem(OUTPUT_SLOT, new ItemStack(result.getItem(), getItem(OUTPUT_SLOT).getCount() + recipe.getResult().getCount()));
        }
        byproduct(recipe);
    }

    private boolean hasRecipe() {
        Optional<RecipeHolder<T>> recipe = getCurrentRecipe();
        return recipe.isPresent() && canInsertAmountIntoSlot(recipe.get().value().getResult(), OUTPUT_SLOT) && canInsertItemIntoSlot(recipe.get().value().getResult().getItem(), OUTPUT_SLOT);
    }

    private int getCookTime() {
        Optional<RecipeHolder<T>> recipe = getCurrentRecipe();
        return recipe.map(tRecipeHolder -> tRecipeHolder.value().time()).orElse(200);
    }

    private List<ItemStack> noAirInputs() {
        return inventory.subList(FIRST_INPUT_SLOT, OUTPUT_SLOT).stream().filter(itemStack -> !itemStack.isEmpty() && !itemStack.getItem().equals(Items.AIR)).collect(Collectors.toList());
    }

}
