package com.github.ringlocker.substancecraft.client.gui.screens;

import com.github.ringlocker.substancecraft.SubstanceCraft;
import com.github.ringlocker.substancecraft.block.entity.entities.WorkstationBlockEntity;
import com.github.ringlocker.substancecraft.gui.menus.WorkstationMenu;
import com.github.ringlocker.substancecraft.recipe.recipes.ByproductRecipe;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Environment(EnvType.CLIENT)
public abstract class WorkstationScreen<
        R extends ByproductRecipe,
        B extends WorkstationBlockEntity<R>,
        M extends WorkstationMenu<R, B>>
        extends AbstractContainerScreen<M> {

    protected static final Identifier SCROLLER_SPRITE = Identifier.withDefaultNamespace("container/stonecutter/scroller");
    protected static final Identifier SCROLLER_DISABLED_SPRITE = Identifier.withDefaultNamespace("container/stonecutter/scroller_disabled");
    protected static final Identifier RECIPE_SELECTED_SPRITE = Identifier.withDefaultNamespace("container/stonecutter/recipe_selected");
    protected static final Identifier RECIPE_HIGHLIGHTED_SPRITE = Identifier.withDefaultNamespace("container/stonecutter/recipe_highlighted");
    protected static final Identifier RECIPE_SPRITE = Identifier.withDefaultNamespace("container/stonecutter/recipe");

    protected static final Identifier SLOT_SPRITE = Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "slot");
    protected static final Identifier CATALYST_SLOT_SPRITE = Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "catalyst_slot");
    protected static final Identifier ARROW = Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "arrow");
    protected static final Identifier SHORT_ARROW = Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "short_arrow");
    protected static final Identifier ARROW_BACKGROUND = Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "arrow_background");
    protected static final Identifier SHORT_ARROW_BACKGROUND = Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "short_arrow_background");

    protected static final int SCROLLER_WIDTH = 12;
    protected static final int SCROLLER_HEIGHT = 15;
    protected static final int RECIPES_COLUMNS = 4;
    protected static final int RECIPES_ROWS = 3;
    protected static final int RECIPES_IMAGE_SIZE_WIDTH = 16;
    protected static final int RECIPES_IMAGE_SIZE_HEIGHT = 18;
    protected static final int SCROLLER_FULL_HEIGHT = 54;
    protected static final int RECIPES_X = 11;
    protected static final int RECIPES_Y = 15;
    protected static final int SCROLLER_X = 78;
    protected static final int SCROLLER_Y = 16;
    protected static final int PROGRESS_ARROW_X = 103;
    protected static final int PROGRESS_ARROW_Y = 30;
    protected static final int PROGRESS_ARROW_SHORT_Y = 48;
    protected static final int PROGRESS_ARROW_BACKGROUND_WIDTH = 7;
    protected static final int PROGRESS_ARROW_HEIGHT = 26;
    protected static final int SHORT_PROGRESS_ARROW_HEIGHT = 8;

    protected static final int SLOT_WIDTH = 18;

    protected static final int SLOT_HEIGHT = 18;
    protected static final int FIRST_INPUT_X = 97;
    protected static final int FIRST_INPUT_Y = 10;
    protected static final int CATALYST_X = 151;
    protected static final int CATALYST_Y = 28;
    protected static final int OUTPUT_X = 97;
    protected static final int OUTPUT_Y = 58;

    protected Identifier BACKGROUND_TEXTURE = Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "textures/gui/workstation.png");

    protected float scrollOffset;
    protected boolean scrolling;
    protected int firstVisibleIndex;

    public WorkstationScreen(M menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

    @Override
    protected void init() {
        super.init();
        titleLabelY = 5;
        titleLabelX = 10;
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick) {
        super.extractRenderState(graphics, mouseX, mouseY, partialTick);
        this.extractTooltip(graphics, mouseX, mouseY);
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick) {
        graphics.blit(RenderPipelines.GUI_TEXTURED, BACKGROUND_TEXTURE, leftPos, topPos, 0, 0, imageWidth, imageHeight, 176, 166);
        Identifier scrollerTexture = this.isScrollBarActive() ? SCROLLER_SPRITE : SCROLLER_DISABLED_SPRITE;
        graphics.blitSprite(RenderPipelines.GUI_TEXTURED, scrollerTexture, leftPos + SCROLLER_X, topPos + SCROLLER_Y + (int) (41.0F * this.scrollOffset), SCROLLER_WIDTH, SCROLLER_HEIGHT);

        renderProgressArrow(graphics, menu.getBlockEntity().getRecipe(), leftPos, topPos);
        renderButtons(graphics, mouseX, mouseY, leftPos + RECIPES_X, topPos + RECIPES_Y, firstVisibleIndex + (RECIPES_ROWS * RECIPES_COLUMNS));
        renderRecipes(graphics, leftPos + RECIPES_X, topPos + RECIPES_Y, firstVisibleIndex + (RECIPES_ROWS * RECIPES_COLUMNS));
        renderSlots(graphics, menu.getBlockEntity().getRecipe());
    }

    @Override
    protected void extractTooltip(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
        super.extractTooltip(graphics, mouseX, mouseY);
        int recipeX = this.leftPos + RECIPES_X;
        int recipeY = this.topPos + RECIPES_Y;
        int lastVisibleIndex = this.firstVisibleIndex + (RECIPES_ROWS * RECIPES_COLUMNS);
        for (int index = this.firstVisibleIndex; index < lastVisibleIndex && index < this.menu.getNumRecipes(); index++) {
            int relativeIndex = index - this.firstVisibleIndex;
            int buttonX = recipeX + (relativeIndex % RECIPES_COLUMNS) * RECIPES_IMAGE_SIZE_WIDTH;
            int buttonY = recipeY + (relativeIndex / RECIPES_COLUMNS) * RECIPES_IMAGE_SIZE_HEIGHT + 2;
            if (isMouseInBox(mouseX, mouseY, buttonX, buttonX + RECIPES_IMAGE_SIZE_WIDTH, buttonY, buttonY + RECIPES_IMAGE_SIZE_HEIGHT)) {
                graphics.setTooltipForNextFrame(this.font, tooltip(index), Optional.empty(), mouseX, mouseY);
            }
        }
    }

    private void renderButtons(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, int recipesX, int recipesY, int lastVisibleElementIndex) {
        for (int index = this.firstVisibleIndex; index < lastVisibleElementIndex && index < this.menu.getNumRecipes(); index++) {
            int relativeIndex = index - this.firstVisibleIndex;
            int row = relativeIndex / RECIPES_COLUMNS;
            int renderX = recipesX + relativeIndex % RECIPES_COLUMNS * RECIPES_IMAGE_SIZE_WIDTH;
            int renderY = recipesY + row * RECIPES_IMAGE_SIZE_HEIGHT + 2;
            Identifier buttonStateTexture = getButtonStateTexture(index, mouseX, mouseY, renderX, renderY);
            guiGraphics.blitSprite(RenderPipelines.GUI_TEXTURED, buttonStateTexture, renderX, renderY - 1, RECIPES_IMAGE_SIZE_WIDTH, RECIPES_IMAGE_SIZE_HEIGHT);
        }
    }

    private void renderSlots(GuiGraphicsExtractor guiGraphics, ByproductRecipe recipe)
    {
        guiGraphics.blitSprite(RenderPipelines.GUI_TEXTURED, SLOT_SPRITE, leftPos + OUTPUT_X, topPos + OUTPUT_Y, SLOT_WIDTH, SLOT_HEIGHT);
        if (recipe == null) return;
        int inputs = recipe.getInputs().size();
        for (int i = 0; i < inputs; i++) {
            guiGraphics.blitSprite(RenderPipelines.GUI_TEXTURED, SLOT_SPRITE, leftPos + FIRST_INPUT_X + (18 * (i % 4)), topPos + FIRST_INPUT_Y + (18 * ((i > 3 ? 1 : 0))), SLOT_WIDTH, SLOT_HEIGHT);
        }
        if (recipe.hasCatalyst()) {
            guiGraphics.blitSprite(RenderPipelines.GUI_TEXTURED, CATALYST_SLOT_SPRITE, leftPos + CATALYST_X, topPos + CATALYST_Y, SLOT_WIDTH, SLOT_HEIGHT);
        }
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double scrollX, double scrollY) {
        if (this.isScrollBarActive()) {
            int offscreenRows = this.getOffscreenRows();
            float scrollChangeAmount = (float) scrollY / (float) offscreenRows;
            this.scrollOffset = Mth.clamp(this.scrollOffset - scrollChangeAmount, 0.0F, 1.0F);
            this.firstVisibleIndex = (int) ((double) (this.scrollOffset * (float) offscreenRows) + 0.5) * RECIPES_COLUMNS;
        }
        return true;
    }

    @Override
    public boolean mouseDragged(MouseButtonEvent mouseButtonEvent, double dragX, double dragY) {
        if (this.scrolling && this.isScrollBarActive()) {
            int recipeY = this.topPos + RECIPES_Y;
            int bottomScrollerY = recipeY + SCROLLER_FULL_HEIGHT;
            this.scrollOffset = ((float) mouseButtonEvent.y() - (float) recipeY - 7.5F) / ((float) (bottomScrollerY - recipeY) - 15.0F);
            this.scrollOffset = Mth.clamp(this.scrollOffset, 0.0F, 1.0F);
            this.firstVisibleIndex = (int) ((double) (this.scrollOffset * (float) this.getOffscreenRows()) + (double) 0.5F) * RECIPES_COLUMNS;
            return true;
        } else {
            return super.mouseDragged(mouseButtonEvent, dragX, dragY);
        }
    }

    @Override
    public boolean mouseClicked(MouseButtonEvent mouseButtonEvent, boolean isDoubleClick) {
        double mouseX = mouseButtonEvent.x();
        double mouseY = mouseButtonEvent.y();
        this.scrolling = false;
        int recipeX = this.leftPos + RECIPES_X;
        int recipeY = this.topPos + RECIPES_Y;
        int maxRecipeIndex = this.firstVisibleIndex + (RECIPES_ROWS * RECIPES_COLUMNS);

        for (int index = this.firstVisibleIndex; index < maxRecipeIndex; index++) {
            int relativeIndex = index - this.firstVisibleIndex;
            double buttonX = mouseX - (double) (recipeX + relativeIndex % RECIPES_COLUMNS * RECIPES_IMAGE_SIZE_WIDTH);
            double buttonY = mouseY - (double) (recipeY + relativeIndex / RECIPES_COLUMNS * RECIPES_IMAGE_SIZE_HEIGHT);

            if (buttonX >= 0.0 && buttonY >= 0.0 && buttonX < RECIPES_IMAGE_SIZE_WIDTH && buttonY < RECIPES_IMAGE_SIZE_HEIGHT && this.menu.clickMenuButton(this.minecraft.player, index)) {
                Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.UI_STONECUTTER_SELECT_RECIPE, 1.0F));
                MultiPlayerGameMode multiPlayerGameMode = this.minecraft.gameMode;
                if (multiPlayerGameMode != null) {
                    multiPlayerGameMode.handleInventoryButtonClick(this.menu.containerId, index);
                }
                return true;
            }
        }
        recipeX = this.leftPos + SCROLLER_X;
        recipeY = this.topPos + SCROLLER_Y;
        if (isMouseInBox((int) mouseX, (int) mouseY, recipeX, recipeX + (RECIPES_ROWS * RECIPES_COLUMNS), recipeY, recipeY + SCROLLER_FULL_HEIGHT)) {
            this.scrolling = true;
        }
        return super.mouseClicked(mouseButtonEvent, isDoubleClick);
    }

    @SuppressWarnings("deprecation")
    protected List<Component> tooltip(int index) {
        ByproductRecipe recipe = this.menu.getRecipes().get(index).value();
        List<Ingredient> inputs = recipe.getInputs();
        ItemStack resultItem = recipe.getResult().create();
        List<Component> tooltip = new ArrayList<>();
        tooltip.add(getItemNameString(resultItem));
        tooltip.add(Component.literal("Requires: "));
        Set<Holder<Item>> itemInputsSet = inputs.stream().flatMap(Ingredient::items).collect(Collectors.toUnmodifiableSet());
        for (Holder<Item> item : itemInputsSet) {
            tooltip.add(getItemNameString(new ItemStack(item.value())));
        }
        Optional<ItemStackTemplate> catalyst = recipe.getCatalyst();
        catalyst.ifPresent(itemStackTemplate -> tooltip.add(Component.literal("Catalyst: " + (getItemNameString(itemStackTemplate.create()).getString()))));
        if (!recipe.getByproducts().isEmpty()) {
            tooltip.add(Component.literal("Byproducts: "));
            List<ItemStackTemplate> byproducts = recipe.getByproducts();
            for (ItemStackTemplate byproduct : byproducts) {
                tooltip.add(getByproductString(byproduct.create(), byproduct.count() << 1));
            }
        }
        return tooltip;
    }

    protected void renderRecipes(GuiGraphicsExtractor guiGraphics, int x, int y, int startIndex) {
        List<RecipeHolder<R>> list = this.menu.getRecipes();
        for (int index = this.firstVisibleIndex; index < startIndex && index < this.menu.getNumRecipes(); index++) {
            int relativeIndex = index - this.firstVisibleIndex;
            int renderX = x + relativeIndex % RECIPES_COLUMNS * RECIPES_IMAGE_SIZE_WIDTH;
            int row = relativeIndex / RECIPES_COLUMNS;
            int renderY = y + row * RECIPES_IMAGE_SIZE_HEIGHT + 2;
            guiGraphics.fakeItem(list.get(index).value().getResult().create(), renderX, renderY);
        }
    }

    private boolean isMouseInBox(int mouseX, int mouseY, int minX, int maxX, int minY, int maxY) {
        return mouseX >= minX && mouseX < maxX && mouseY >= minY && mouseY < maxY;
    }

    private Identifier getButtonStateTexture(int index, int mouseX, int mouseY, int renderX, int renderY) {
        Identifier buttonStateTexture;
        if (index == this.menu.getBlockEntity().getSelectedRecipeIndex()) {
            buttonStateTexture = RECIPE_SELECTED_SPRITE;
        } else if (mouseX >= renderX && mouseY >= renderY && mouseX < renderX + RECIPES_IMAGE_SIZE_WIDTH && mouseY < renderY + RECIPES_IMAGE_SIZE_HEIGHT) {
            buttonStateTexture = RECIPE_HIGHLIGHTED_SPRITE;
        } else {
            buttonStateTexture = RECIPE_SPRITE;
        }
        return buttonStateTexture;
    }

    private void renderProgressArrow(GuiGraphicsExtractor context, ByproductRecipe recipe, int x, int y) {
        if (recipe == null) return;

        if (recipe.getInputs().size() < 5) {
            context.blitSprite(RenderPipelines.GUI_TEXTURED, ARROW_BACKGROUND, x + PROGRESS_ARROW_X, y + PROGRESS_ARROW_Y, PROGRESS_ARROW_BACKGROUND_WIDTH, PROGRESS_ARROW_HEIGHT);
            if (menu.isCrafting()) {
                context.blitSprite(RenderPipelines.GUI_TEXTURED, ARROW, x + PROGRESS_ARROW_X, y + PROGRESS_ARROW_Y, 8, menu.getScaledProgress());
            }
        } else {
            context.blitSprite(RenderPipelines.GUI_TEXTURED, SHORT_ARROW_BACKGROUND, x + PROGRESS_ARROW_X, y + PROGRESS_ARROW_SHORT_Y, PROGRESS_ARROW_BACKGROUND_WIDTH, SHORT_PROGRESS_ARROW_HEIGHT);
            if (menu.isCrafting()) {
                context.blitSprite(RenderPipelines.GUI_TEXTURED, SHORT_ARROW, x + PROGRESS_ARROW_X, y + PROGRESS_ARROW_Y, 8, menu.getScaledProgress());
            }
        }
    }

    private boolean isScrollBarActive() {
        return this.menu.getNumRecipes() > (RECIPES_ROWS * RECIPES_COLUMNS);
    }

    protected int getOffscreenRows() {
        return (this.menu.getNumRecipes() + RECIPES_COLUMNS - 1) / RECIPES_COLUMNS - RECIPES_ROWS;
    }

    protected Component getItemNameString(ItemStack itemStack) {
        if (itemStack == null) return Component.empty();
        if (itemStack.getItem() == Items.POTION) return Component.literal("Water Bottle");
        else return Component.literal(itemStack.getDisplayName().getString().replace("[", "").replace("]", "")
                + (itemStack.getCount() > 1 ? " x" + itemStack.getCount() : ""));
    }

    protected Component getByproductString(ItemStack itemStack, int chance) {
        if (itemStack == null) return Component.empty();
        if (itemStack.getItem() == Items.POTION) return Component.literal("Water Bottle " + chance + "%");
        else return Component.literal(itemStack.getDisplayName().getString().replace("[", "").replace("]", "") + " " + chance + "%");
    }

}
