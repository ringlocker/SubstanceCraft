package com.github.ringlocker.substancecraft.gui.menus;

import com.github.ringlocker.substancecraft.block.entity.entities.WorkstationBlockEntity;
import com.github.ringlocker.substancecraft.recipe.recipes.ByproductRecipe;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WorkstationMenu<R extends ByproductRecipe, B extends WorkstationBlockEntity<R>> extends AbstractContainerMenu {

    protected static final int INPUT_SLOT_X = 98;
    protected static final int INPUT_SLOT_Y = 11;

    protected static final int OUTPUT_SLOT_X = 98;
    protected static final int OUTPUT_SLOT_Y = 59;

    protected static final int BYPRODUCT_SLOT_X = 116;
    protected static final int BYPRODUCT_SLOT_Y = 59;

    protected final int INPUT_SLOT_INDEX = 0;
    protected final int OUTPUT_SLOT_INDEX = 4;
    protected final int BYPRODUCT_SLOT_INDEX = 5;

    protected final List<Slot> inputSlots = new ArrayList<>();
    protected final List<Slot> byproductSlots = new ArrayList<>();

    protected final B blockEntity;
    protected final Container blockEntityInventory;
    protected final SimpleContainerData blockEntityData;
    protected final Inventory playerInventory;

    protected WorkstationMenu(MenuType<? extends WorkstationMenu> menu, int syncId, Inventory playerInventory, B entity, SimpleContainerData blockEntityData) {
        super(menu, syncId);
        entity.setupRecipeList(entity.getLevel());
        checkContainerSize(entity, entity.getContainerSize());
        this.blockEntity = entity;
        this.blockEntityInventory = entity;
        blockEntityInventory.startOpen(playerInventory.player);
        this.playerInventory = playerInventory;
        this.blockEntityData = blockEntityData;


        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);

        for (int i = 0; i < 4; i++) {
            createInputSlot(blockEntity, i);
        }
        createOutputSlot(blockEntity);
        for (int i = 0; i < 3; i++) {
            createByproductSlot(blockEntity, i);
        }

        updateSlots();
        addDataSlots(blockEntityData);
    }

    public B getBlockEntity() {
        return blockEntity;
    }

    public boolean isCrafting() {
        return blockEntityData.get(0) > 0;
    }

    public int getNumRecipes() {
        return blockEntity.getRecipes().size();
    }

    public void updateSlots() {
        for (int i = 0; i < 4; i++) {
            if (i < blockEntity.inputCount()) {
                if (this.slots.contains(inputSlots.get(i))) continue;
                this.addSlot(inputSlots.get(i));
            } else {
                if (!this.slots.contains(inputSlots.get(i))) continue;
                this.slots.remove(inputSlots.get(i));
            }
        }
        for (int i = 0; i < 3; i++) {
            if (i < blockEntity.byproductCount()) {
                if (this.slots.contains(byproductSlots.get(i))) continue;
                this.addSlot(byproductSlots.get(i));
            } else {
                if (!this.slots.contains(byproductSlots.get(i))) continue;
                this.slots.remove(byproductSlots.get(i));
            }
        }
    }

    @Override
    public boolean clickMenuButton(Player player, int id) {
        if (this.isValidRecipeIndex(id)) {
            blockEntity.setSelectedRecipeIndex(id);
            blockEntity.setChanged();
            updateSlots();
        }
        return true;
    }

    private boolean isValidRecipeIndex(int recipeIndex) {
        return recipeIndex >= 0 && recipeIndex < blockEntity.getRecipes().size();
    }

    public List<RecipeHolder<R>> getRecipes() {
        return blockEntity.getRecipes();
    }

    public int getScaledProgress() {
        int progress = this.blockEntityData.get(0);
        int maxProgress = this.blockEntityData.get(1);
        int progressArrowSize = 26;
        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }

    private void addPlayerInventory(Container playerInventory) {
        for (int row = 0; row < 3; ++row) {
            for (int i = 0; i < 9; ++i) {
                this.addSlot(new Slot(playerInventory, i + row * 9 + 9, 8 + i * 18, 84 + row * 18));
            }
        }
    }

    private void addPlayerHotbar(Container playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    @Override
    public @NotNull ItemStack quickMoveStack(Player player, int index) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (!slot.hasItem()) return newStack;
        ItemStack originalStack = slot.getItem();
        newStack = originalStack.copy();
        Container source = this.slots.get(index).container;

        if (source == blockEntityInventory) {
            if (!this.moveItemStackTo(originalStack, 0, 36, true)) {
                return ItemStack.EMPTY;
            }
        }

        else if (source == playerInventory) {
            boolean success = false;
            for (Slot inputSlot : inputSlots) {
                if (!this.slots.contains(inputSlot)) break;
                int inputIndex = this.slots.indexOf(inputSlot);
                if (this.moveItemStackTo(originalStack, inputIndex, inputIndex + 1, true)) {
                    success = true;
                    break;
                }
            }
            if (!success) {
                return ItemStack.EMPTY;
            }
        }

        if (originalStack.isEmpty()) {
            slot.set(ItemStack.EMPTY);
        } else {
            slot.setChanged();
        }

        return newStack;
    }

    @Override
    public boolean stillValid(Player player) {
        return this.blockEntityInventory.stillValid(player);
    }

    public void createInputSlot(Container blockEntityInventory, int index) {
        Slot slot = new Slot(blockEntityInventory, INPUT_SLOT_INDEX + index, INPUT_SLOT_X + (18 * index), INPUT_SLOT_Y);
        inputSlots.add(index, slot);
    }

    public void createOutputSlot(Container blockEntityInventory) {
        Slot slot = new Slot(blockEntityInventory, OUTPUT_SLOT_INDEX, OUTPUT_SLOT_X, OUTPUT_SLOT_Y);
        this.addSlot(slot);
    }

    public void createByproductSlot(Container blockEntityInventory, int index) {
        Slot slot =  new Slot(blockEntityInventory, BYPRODUCT_SLOT_INDEX + index, BYPRODUCT_SLOT_X + (18 * index), BYPRODUCT_SLOT_Y);
        byproductSlots.add(index, slot);
    }

}
