package com.github.ringlocker.substancecraft.item;

import com.github.ringlocker.substancecraft.SubstanceCraft;
import com.github.ringlocker.substancecraft.block.SubstanceCraftBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class SubstanceCraftItemGroups {

    public static CreativeModeTab DRUGS_ITEM_GROUP;
    public static CreativeModeTab SUBSTANCES_ITEM_GROUP;
    public static CreativeModeTab MATERIALS_ITEM_GROUP;
    public static CreativeModeTab BLOCKS_ITEM_GROUP;
    public static CreativeModeTab AGRICULTURE_ITEM_GROUP;
    public static CreativeModeTab ALL_ITEM_GROUP;

    public static void registerItemGroups() {
        DRUGS_ITEM_GROUP = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
                Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "drugs"),
                FabricItemGroup.builder().title(Component.translatable("itemgroup.substancecraft.drugs"))
                        .icon(() -> new ItemStack(SubstanceCraftItems.MARIJUANA_TRIM)).displayItems((displayContext, entries) -> addDrugItems(entries)).build());

        BLOCKS_ITEM_GROUP = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
                Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "blocks"),
                FabricItemGroup.builder().title(Component.translatable("itemgroup.substancecraft.blocks"))
                        .icon(() -> new ItemStack(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.REFINERY))).displayItems((displayContext, entries) -> addBlockItems(entries)).build());

        MATERIALS_ITEM_GROUP = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
                Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "materials"),
                FabricItemGroup.builder().title(Component.translatable("itemgroup.substancecraft.materials"))
                        .icon(() -> new ItemStack(SubstanceCraftItems.HALITE)).displayItems((displayContext, entries) -> addMaterialItems(entries)).build());

        AGRICULTURE_ITEM_GROUP = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
                Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "agriculture"),
                FabricItemGroup.builder().title(Component.translatable("itemgroup.substancecraft.agriculture"))
                        .icon(() -> new ItemStack(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.MARIJUANA_PLANT))).displayItems((displayContext, entries) -> addAgricultureItems(entries)).build());

        SUBSTANCES_ITEM_GROUP = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
                Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "substances"),
                FabricItemGroup.builder().title(Component.translatable("itemgroup.substancecraft.substances"))
                        .icon(() -> new ItemStack(SubstanceCraftItems.SALT)).displayItems((displayContext, entries) -> addSubstanceItems(entries)).build());

        ALL_ITEM_GROUP = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
                Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "all"),
                FabricItemGroup.builder().title(Component.translatable("itemgroup.substancecraft.all"))
                        .icon(() -> new ItemStack(Items.OMINOUS_BOTTLE)).displayItems((displayContext, entries) -> {
                            addDrugItems(entries);
                            addBlockItems(entries);
                            addMaterialItems(entries);
                            addAgricultureItems(entries);
                            addSubstanceItems(entries);
                        }).build());
    }

    private static void addDrugItems(CreativeModeTab.Output entries) {
        entries.accept(SubstanceCraftItems.MARIJUANA_TRIM);
        entries.accept(SubstanceCraftItems.MARIJUANA);
        entries.accept(SubstanceCraftItems.HASH);
        entries.accept(SubstanceCraftItems.LIVE_RESIN);
        entries.accept(SubstanceCraftItems.ROSIN);
        entries.accept(SubstanceCraftItems.EMPTY_DAB_RIG);
        entries.accept(SubstanceCraftItems.DAB_RIG);
        entries.accept(SubstanceCraftItems.EMPTY_BONG);
        entries.accept(SubstanceCraftItems.BONG);
        entries.accept(SubstanceCraftItems.JOINT);
        entries.accept(SubstanceCraftItems.EDIBLE);
        entries.accept(SubstanceCraftItems.PEN_BATTERY);
        entries.accept(SubstanceCraftItems.EMPTY_CART);
        entries.accept(SubstanceCraftItems.RESIN_CART);
        entries.accept(SubstanceCraftItems.ROSIN_CART);
        entries.accept(SubstanceCraftItems.DMT_CART);
        entries.accept(SubstanceCraftItems.RESIN_PEN);
        entries.accept(SubstanceCraftItems.ROSIN_PEN);
        entries.accept(SubstanceCraftItems.DMT_PEN);
        entries.accept(SubstanceCraftItems.DIPHENHYDRAMINE);
        entries.accept(SubstanceCraftItems.KETAMINE);
        entries.accept(SubstanceCraftItems.TWO_C_B);
        entries.accept(SubstanceCraftItems.AMPHETAMINE);
        entries.accept(SubstanceCraftItems.COCAINE);
        entries.accept(SubstanceCraftItems.LYSERGIC_ACID_DIETHYLAMINE_TAB);
        entries.accept(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.PSILOCYBIN));
        entries.accept(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.PALE_PSILOCYBIN));
        entries.accept(SubstanceCraftItems.MESCALINE);
        entries.accept(SubstanceCraftItems.N_N_DIMETHYLTRYPTAMINE);
        entries.accept(SubstanceCraftItems.RED_WINE);
        entries.accept(SubstanceCraftItems.CASH);
        entries.accept(SubstanceCraftItems.BAND);
    }

    private static void addBlockItems(CreativeModeTab.Output entries) {
        entries.accept(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.REFINERY));
        entries.accept(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.ELECTROLYSIS_MACHINE));
        entries.accept(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.OXIDATION_MACHINE));
        entries.accept(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.EXTRACTOR));
        entries.accept(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.MIXER));
        entries.accept(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.HEATED_MIXER));
        entries.accept(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.FERMENTATION_TANK));
        entries.accept(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.HASH_PRESS));
        entries.accept(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.OIL_SHALE));
        entries.accept(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.HALITE));
        entries.accept(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.SYLVITE));
        entries.accept(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.SULFUR_ORE));
        entries.accept(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.DEEPSLATE_SULFUR_ORE));
        entries.accept(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.TRONA));
        entries.accept(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.PYROLUSITE_ORE));
        entries.accept(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.DEEPSLATE_PYROLUSITE_ORE));
        entries.accept(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.LIMESTONE));
        entries.accept(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.PHOSPHORITE));
    }

    private static void addMaterialItems(CreativeModeTab.Output entries) {
        entries.accept(SubstanceCraftItems.OIL_SHALE);
        entries.accept(SubstanceCraftItems.HALITE);
        entries.accept(SubstanceCraftItems.SYLVITE);
        entries.accept(SubstanceCraftItems.TRONA);
        entries.accept(SubstanceCraftItems.RAW_SULFUR);
        entries.accept(SubstanceCraftItems.PYROLUSITE);
        entries.accept(SubstanceCraftItems.LIMESTONE);
        entries.accept(SubstanceCraftItems.PHOSPHORITE);
        entries.accept(SubstanceCraftItems.FLOUROAPATITE);
    }

    private static void addAgricultureItems(CreativeModeTab.Output entries) {
        entries.accept(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.MARIJUANA_PLANT));
        entries.accept(SubstanceCraftItems.CORN);
        entries.accept(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.CORN_CROP));
        entries.accept(SubstanceCraftItems.YEAST);
        entries.accept(SubstanceCraftItems.ERGOT);
        entries.accept(SubstanceCraftItems.COCA_LEAVES);
        entries.accept(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.GRAPEVINE));
        entries.accept(SubstanceCraftItems.GRAPES);
        entries.accept(SubstanceCraftItems.WINE_LEES);
        entries.accept(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.PEYOTE_CACTUS));
    }

    private static void addSubstanceItems(CreativeModeTab.Output entries) {
        for (Item i : SubstanceCraftItems.substances) {
            entries.accept(i);
        }
    }

}
