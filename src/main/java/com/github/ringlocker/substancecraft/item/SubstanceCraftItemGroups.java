package com.github.ringlocker.substancecraft.item;

import com.github.ringlocker.substancecraft.SubstanceCraft;
import com.github.ringlocker.substancecraft.block.SubstanceCraftBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
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
        entries.accept(SubstanceCraftItems.MARIJUANA);
        entries.accept(SubstanceCraftItems.MARIJUANA_TRIM);
        entries.accept(SubstanceCraftItems.HASH);
        entries.accept(SubstanceCraftItems.EMPTY_DAB_RIG);
        entries.accept(SubstanceCraftItems.DAB_RIG);
        entries.accept(SubstanceCraftItems.DIPHENHYDRAMINE);
        entries.accept(SubstanceCraftItems.KETAMINE);
        entries.accept(SubstanceCraftItems.TWO_C_B);
        entries.accept(SubstanceCraftItems.AMPHETAMINE);
        entries.accept(SubstanceCraftItems.COCAINE);
        entries.accept(SubstanceCraftItems.LYSERGIC_ACID_DIETHYLAMINE);
        entries.accept(SubstanceCraftItems.PSILOCYBIN);
        entries.accept(SubstanceCraftItems.MESCALINE);
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
        entries.accept(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.FLOUROAPATITE));
    }

    private static void addMaterialItems(CreativeModeTab.Output entries) {
        entries.accept(SubstanceCraftItems.OIL_SHALE);
        entries.accept(SubstanceCraftItems.HALITE);
        entries.accept(SubstanceCraftItems.SYLVITE);
        entries.accept(SubstanceCraftItems.TRONA);
        entries.accept(SubstanceCraftItems.RAW_SULFUR);
        entries.accept(SubstanceCraftItems.PYROLUSITE);
        entries.accept(SubstanceCraftItems.LIMESTONE);
        entries.accept(SubstanceCraftItems.FLOUROAPATITE);
    }

    private static void addAgricultureItems(CreativeModeTab.Output entries) {
        entries.accept(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.MARIJUANA_PLANT));
        entries.accept(SubstanceCraftItems.CORN);
        entries.accept(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.CORN_CROP));
        entries.accept(SubstanceCraftItems.YEAST);
        entries.accept(SubstanceCraftItems.ERGOT);
        entries.accept(SubstanceCraftItems.COCA_LEAVES);
        entries.accept(SubstanceCraftItems.WINE_LEES);
    }

    private static void addSubstanceItems(CreativeModeTab.Output entries) {
        entries.accept(SubstanceCraftItems.DISTILLED_WATER);
        entries.accept(SubstanceCraftItems.OIL);
        entries.accept(SubstanceCraftItems.DIESEL);
        entries.accept(SubstanceCraftItems.GASOLINE);
        entries.accept(SubstanceCraftItems.KEROSENE);
        entries.accept(SubstanceCraftItems.PETROLEUM_NAPHTHA);
        entries.accept(SubstanceCraftItems.ETHANOL);
        entries.accept(SubstanceCraftItems.METHANOL);
        entries.accept(SubstanceCraftItems.CHLOROFORM);
        entries.accept(SubstanceCraftItems.TOLUENE);
        entries.accept(SubstanceCraftItems.BENZENE);
        entries.accept(SubstanceCraftItems.BRINE);
        entries.accept(SubstanceCraftItems.CHLORINE);
        entries.accept(SubstanceCraftItems.HYDROGEN);
        entries.accept(SubstanceCraftItems.METHANE);
        entries.accept(SubstanceCraftItems.NITROGEN);
        entries.accept(SubstanceCraftItems.OXYGEN);
        entries.accept(SubstanceCraftItems.NATURAL_GAS);
        entries.accept(SubstanceCraftItems.PROPANE);
        entries.accept(SubstanceCraftItems.ETHANE);
        entries.accept(SubstanceCraftItems.BUTANE);
        entries.accept(SubstanceCraftItems.METHYLAMINE);
        entries.accept(SubstanceCraftItems.PROPYLENE);
        entries.accept(SubstanceCraftItems.ETHYLENE);
        entries.accept(SubstanceCraftItems.FORMALDEHYDE);
        entries.accept(SubstanceCraftItems.AMMONIA);
        entries.accept(SubstanceCraftItems.BENZALDEHYDE);
        entries.accept(SubstanceCraftItems.HYDROCHLORIC_ACID);
        entries.accept(SubstanceCraftItems.AMMONIUM_ACETATE);
        entries.accept(SubstanceCraftItems.MALEIC_ANHYDRIDE);
        entries.accept(SubstanceCraftItems.SALT);
        entries.accept(SubstanceCraftItems.ACETIC_ACID);
        entries.accept(SubstanceCraftItems.NITRIC_ACID);
        entries.accept(SubstanceCraftItems.NITROMETHANE);
        entries.accept(SubstanceCraftItems.NITROETHANE);
        entries.accept(SubstanceCraftItems.SODIUM_HYDROXIDE);
        entries.accept(SubstanceCraftItems.BROMIDE);
        entries.accept(SubstanceCraftItems.BROMINE);
        entries.accept(SubstanceCraftItems.CARBON_MONOXIDE);
        entries.accept(SubstanceCraftItems.METHYL_FORMATE);
        entries.accept(SubstanceCraftItems.FORMIC_ACID);
        entries.accept(SubstanceCraftItems.COKE);
        entries.accept(SubstanceCraftItems.TETRAHYDROFURAN);
        entries.accept(SubstanceCraftItems.BETA_NITROSTYRENE);
        entries.accept(SubstanceCraftItems.TWO_C_H);
        entries.accept(SubstanceCraftItems.P2P);
        entries.accept(SubstanceCraftItems.P2NP);
        entries.accept(SubstanceCraftItems.CARBON_DIOXIDE);
        entries.accept(SubstanceCraftItems.SULFUR);
        entries.accept(SubstanceCraftItems.SULFURIC_ACID);
        entries.accept(SubstanceCraftItems.AMMONIA_SOLUTION);
        entries.accept(SubstanceCraftItems.SODIUM_CARBONATE);
        entries.accept(SubstanceCraftItems.SODIUM_CARBONATE_SOLUTION);
        entries.accept(SubstanceCraftItems.AGUA_RICA);
        entries.accept(SubstanceCraftItems.POTASSIUM_CHLORIDE);
        entries.accept(SubstanceCraftItems.POTASSIUM_HYDROXIDE);
        entries.accept(SubstanceCraftItems.POTASSIUM_CARBONATE);
        entries.accept(SubstanceCraftItems.MANGANESE_DIOXIDE);
        entries.accept(SubstanceCraftItems.POTASSIUM_PERMANGANATE);
        entries.accept(SubstanceCraftItems.COCA_PASTE);
        entries.accept(SubstanceCraftItems.ACETONE);
        entries.accept(SubstanceCraftItems.POTASSIUM_BITARTRATE);
        entries.accept(SubstanceCraftItems.CALCIUM_OXIDE);
        entries.accept(SubstanceCraftItems.CALCIUM_HYDROXIDE);
        entries.accept(SubstanceCraftItems.CALCIUM_TARTRATE);
        entries.accept(SubstanceCraftItems.TARTARIC_ACID);
        entries.accept(SubstanceCraftItems.ERGOTAMINE);
        entries.accept(SubstanceCraftItems.ERGOTAMINE_TARTRATE);
        entries.accept(SubstanceCraftItems.DIETHYL_ETHER);
        entries.accept(SubstanceCraftItems.ERGOTAMINE_SULFATE);
        entries.accept(SubstanceCraftItems.SILICA);
        entries.accept(SubstanceCraftItems.WHITE_PHOSPHORUS);
        entries.accept(SubstanceCraftItems.RED_PHOSPHORUS);
        entries.accept(SubstanceCraftItems.PHOSPHORUS_TRICHLORIDE);
        entries.accept(SubstanceCraftItems.PHOSPHORYL_CHLORIDE);
        entries.accept(SubstanceCraftItems.DIETHYLENE);
        entries.accept(SubstanceCraftItems.D_LYSERGIC_ACID_HYDRATE);
        entries.accept(SubstanceCraftItems.MAGNESIUM);
        entries.accept(SubstanceCraftItems.MAGNESIUM_SULFATE);
    }

}
