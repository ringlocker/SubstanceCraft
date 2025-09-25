package com.github.ringlocker.substancecraft.block.entity;

import com.github.ringlocker.substancecraft.block.GenericMenuBlock;
import com.github.ringlocker.substancecraft.client.recipe.ClientRecipeInformation;
import com.github.ringlocker.substancecraft.recipe.SubstanceCraftRecipes;
import com.github.ringlocker.substancecraft.recipe.generic.ByproductRecipe;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class InputOutputBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory<BlockPos>, ImplementedInventory {

    protected final String displayName;
    protected final NonNullList<ItemStack> inventory;

    protected int progress;
    protected int maxProgress;
    protected int selectedRecipeIndex;

    protected final SimpleContainerData data = new SimpleContainerData(3) {
        @Override
        public int get(int index) {
            return switch (index) {
                case 0 -> InputOutputBlockEntity.this.progress;
                case 1 -> InputOutputBlockEntity.this.maxProgress;
                case 2 -> InputOutputBlockEntity.this.selectedRecipeIndex;
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0 -> InputOutputBlockEntity.this.progress = value;
                case 1 -> InputOutputBlockEntity.this.maxProgress = value;
                case 2 -> InputOutputBlockEntity.this.selectedRecipeIndex = value;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    };

    public InputOutputBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState blockState, String displayName, int inventorySize) {
        super(type, pos, blockState);
        this.displayName = displayName;
        this.inventory = NonNullList.withSize(inventorySize, ItemStack.EMPTY);
    }

    public abstract int getMaxByproducts();

    public abstract int getNumRecipes();

    public abstract boolean hasRepeatInputRecipes();

    public abstract boolean multipleInput();

    @NotNull
    protected List<RecipeHolder<?>> getRecipeList(RecipeType<?> type, Level level) {
        List<RecipeHolder<?>> allRecipes;
        if (level.isClientSide) {
            allRecipes = ClientRecipeInformation.getAllRecipesFor(type);
        } else {
            allRecipes = SubstanceCraftRecipes.getAllRecipesFor(type);
        }
        return allRecipes;
    }

    protected void updateState(BlockState state, Level level, BlockPos pos) {
        if (progress > 0) {
            level.setBlockAndUpdate(pos, state.setValue(GenericMenuBlock.LIT, true));
        } else if (state.getValue(GenericMenuBlock.LIT)) {
            level.setBlockAndUpdate(pos, state.setValue(GenericMenuBlock.LIT, false));
        }
    }

    public int getSelectedRecipeIndex() {
        return this.selectedRecipeIndex;
    }

    public void setSelectedRecipeIndex(int selectedRecipeIndex) {
        this.selectedRecipeIndex = selectedRecipeIndex;
        setChanged();
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
    public NonNullList<ItemStack> getItems() {
        return inventory;
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

    protected void byproduct(ByproductRecipe recipe, int firstByproductSlot) {
        List<ItemStack> byproducts = recipe.getByproducts();
        if (byproducts.isEmpty()) { return; }
        int index = 0;
        for (ItemStack byproduct : byproducts) {
            if (getLevel() == null) return;
            if (getLevel().random.nextInt(100) > byproduct.getCount() << 1) {
                index++;
                continue;
            }
            int slot = firstByproductSlot + index;
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

}
