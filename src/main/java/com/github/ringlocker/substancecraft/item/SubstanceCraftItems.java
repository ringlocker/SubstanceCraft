package com.github.ringlocker.substancecraft.item;

import com.github.ringlocker.substancecraft.SubstanceCraft;
import com.github.ringlocker.substancecraft.block.SubstanceCraftBlocks;
import com.github.ringlocker.substancecraft.item.items.DabRig;
import com.github.ringlocker.substancecraft.item.items.SimpleDrugs;
import com.github.ringlocker.substancecraft.item.items.SubstanceItem;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.function.Function;

public class SubstanceCraftItems {

    public static CreativeModeTab DRUGS_ITEM_GROUP;
    public static CreativeModeTab SUBSTANCES_ITEM_GROUP;
    public static CreativeModeTab MATERIALS_ITEM_GROUP;
    public static CreativeModeTab BLOCKS_ITEM_GROUP;
    public static CreativeModeTab AGRICULTURE_ITEM_GROUP;
    public static CreativeModeTab ALL_ITEM_GROUP;

    public static final Item MARIJUANA = registerItem("marijuana", Item::new, new Item.Properties());
    public static final Item MARIJUANA_TRIM = registerItem("marijuana_trim", Item::new, new Item.Properties());
    public static final Item HASH = registerItem("hash", Item::new, new Item.Properties());
    public static final Item EMPTY_DAB_RIG = registerItem("empty_dab_rig", DabRig.EmptyDabRigItem::new, new Item.Properties());
    public static final Item DAB_RIG = registerItem("dab_rig", DabRig.DabRigItem::new, new Item.Properties().food(new FoodProperties.Builder().alwaysEdible().nutrition(-1).build()));
    public static final Item DIPHENHYDRAMINE = registerItem("diphenhydramine", SimpleDrugs.Diphenhydramine::new, new Item.Properties().food(new FoodProperties.Builder().alwaysEdible().build()));
    public static final Item KETAMINE = registerItem("ketamine", SimpleDrugs.Ketamine::new, new Item.Properties().food(new FoodProperties.Builder().alwaysEdible().build()));
    public static final Item OIL = registerItem("oil", properties -> new SubstanceItem(properties, SubstanceTintColors.THICK_BLACK_LIQUID, MatterState.LIQUID), new Item.Properties());
    public static final Item OIL_SHALE = registerItem("oil_shale", Item::new, new Item.Properties());
    public static final Item PETROLEUM_NAPHTHA = registerItem("petroleum_naphtha", properties -> new SubstanceItem(properties, SubstanceTintColors.PISS_YELLOW_LIQUID, MatterState.LIQUID), new Item.Properties());
    public static final Item KEROSENE = registerItem("kerosene", properties -> new SubstanceItem(properties, SubstanceTintColors.LIGHT_BLUE_LIQUID, MatterState.LIQUID), new Item.Properties());
    public static final Item GASOLINE = registerItem("gasoline", properties -> new SubstanceItem(properties, SubstanceTintColors.YELLOW_LIQUID, MatterState.LIQUID), new Item.Properties());
    public static final Item METHANOL = registerItem("methanol", properties -> new SubstanceItem(properties, SubstanceTintColors.CLEAR_LIQUID, MatterState.LIQUID), new Item.Properties());
    public static final Item BENZENE = registerItem("benzene", properties -> new SubstanceItem(properties, SubstanceTintColors.VERY_LIGHT_YELLOW_LIQUID, MatterState.LIQUID), new Item.Properties());
    public static final Item CHLOROFORM = registerItem("chloroform", properties -> new SubstanceItem(properties, SubstanceTintColors.CLEAR_LIQUID, MatterState.LIQUID), new Item.Properties());
    public static final Item FORMALDEHYDE = registerItem("formaldehyde", properties -> new SubstanceItem(properties, SubstanceTintColors.CLEAR_GAS, MatterState.GAS), new Item.Properties());
    public static final Item TOLUENE = registerItem("toluene", properties -> new SubstanceItem(properties, SubstanceTintColors.CLEAR_LIQUID, MatterState.LIQUID), new Item.Properties());
    public static final Item HALITE = registerItem("halite", Item::new, new Item.Properties());
    public static final Item SALT = registerItem("salt", properties -> new SubstanceItem(properties, SubstanceTintColors.CLEAR_LIQUID, MatterState.SOLID), new Item.Properties());
    public static final Item BRINE = registerItem("brine", properties -> new SubstanceItem(properties, SubstanceTintColors.LIGHT_TEAL_LIQUID, MatterState.LIQUID), new Item.Properties());
    public static final Item SODIUM_HYDROXIDE = registerItem("sodium_hydroxide", properties -> new SubstanceItem(properties, SubstanceTintColors.CLEAR_LIQUID, MatterState.SOLID), new Item.Properties());
    public static final Item HYDROGEN = registerItem("hydrogen", properties -> new SubstanceItem(properties, SubstanceTintColors.CLEAR_GAS, MatterState.GAS), new Item.Properties());
    public static final Item CHLORINE = registerItem("chlorine", properties -> new SubstanceItem(properties, SubstanceTintColors.LIGHT_YELLOW_GAS, MatterState.GAS), new Item.Properties());
    public static final Item METHANE = registerItem("methane", properties -> new SubstanceItem(properties, SubstanceTintColors.CLEAR_GAS, MatterState.GAS), new Item.Properties());
    public static final Item NITROGEN = registerItem("nitrogen", properties -> new SubstanceItem(properties, SubstanceTintColors.CLEAR_GAS, MatterState.GAS), new Item.Properties());
    public static final Item OXYGEN = registerItem("oxygen", properties -> new SubstanceItem(properties, SubstanceTintColors.CLEAR_GAS, MatterState.GAS), new Item.Properties());
    public static final Item NATURAL_GAS = registerItem("natural_gas", properties -> new SubstanceItem(properties, SubstanceTintColors.CLEAR_GAS, MatterState.GAS), new Item.Properties());
    public static final Item ETHANE = registerItem("ethane", properties -> new SubstanceItem(properties, SubstanceTintColors.CLEAR_GAS, MatterState.GAS), new Item.Properties());
    public static final Item PROPANE = registerItem("propane", properties -> new SubstanceItem(properties, SubstanceTintColors.CLEAR_GAS, MatterState.GAS), new Item.Properties());
    public static final Item BUTANE = registerItem("butane", properties -> new SubstanceItem(properties, SubstanceTintColors.CLEAR_GAS, MatterState.GAS), new Item.Properties());
    public static final Item METHYLAMINE = registerItem("methylamine", properties -> new SubstanceItem(properties, SubstanceTintColors.CLEAR_GAS, MatterState.GAS), new Item.Properties());
    public static final Item ETHYLENE = registerItem("ethylene", properties -> new SubstanceItem(properties, SubstanceTintColors.CLEAR_GAS, MatterState.GAS), new Item.Properties());
    public static final Item PROPYLENE = registerItem("propylene", properties -> new SubstanceItem(properties, SubstanceTintColors.CLEAR_GAS, MatterState.GAS), new Item.Properties());
    public static final Item DIESEL = registerItem("diesel", properties -> new SubstanceItem(properties, SubstanceTintColors.RED_LIQUID, MatterState.LIQUID), new Item.Properties());
    public static final Item AMMONIA = registerItem("ammonia", properties -> new SubstanceItem(properties, SubstanceTintColors.CLEAR_GAS, MatterState.GAS), new Item.Properties());
    public static final Item CORN = registerItem("corn", Item::new, new Item.Properties());
    public static final Item ETHANOL = registerItem("ethanol", properties -> new SubstanceItem(properties, SubstanceTintColors.CLEAR_LIQUID, MatterState.LIQUID), new Item.Properties());
    public static final Item YEAST = registerItem("yeast", Item::new, new Item.Properties());
    public static final Item HYDROCHLORIC_ACID = registerItem("hydrochloric_acid", properties -> new SubstanceItem(properties, SubstanceTintColors.CLEAR_LIQUID, MatterState.LIQUID), new Item.Properties());
    public static final Item ERGOT = registerItem("ergot", Item::new, new Item.Properties());
    public static final Item BENZALDEHYDE = registerItem("benzaldehyde", properties -> new SubstanceItem(properties, SubstanceTintColors.CLEAR_LIQUID, MatterState.LIQUID), new Item.Properties());
    public static final Item NITRIC_ACID = registerItem("nitric_acid", properties -> new SubstanceItem(properties, SubstanceTintColors.VERY_LIGHT_YELLOW_LIQUID, MatterState.LIQUID), new Item.Properties());
    public static final Item NITROMETHANE = registerItem("nitromethane", properties -> new SubstanceItem(properties, SubstanceTintColors.CLEAR_LIQUID, MatterState.LIQUID), new Item.Properties());
    public static final Item ACETIC_ACID = registerItem("acetic_acid", properties -> new SubstanceItem(properties, SubstanceTintColors.CLEAR_LIQUID, MatterState.LIQUID), new Item.Properties());
    public static final Item AMMONIUM_ACETATE = registerItem("ammonium_acetate", properties -> new SubstanceItem(properties, SubstanceTintColors.CLEAR_LIQUID, MatterState.SOLID), new Item.Properties());
    public static final Item CARBON_MONOXIDE = registerItem("carbon_monoxide", properties -> new SubstanceItem(properties, SubstanceTintColors.CLEAR_GAS, MatterState.GAS), new Item.Properties());
    public static final Item COKE = registerItem("coke", properties -> new SubstanceItem(properties, SubstanceTintColors.THICK_BLACK_SOLID, MatterState.SOLID), new Item.Properties());
    public static final Item BETA_NITROSTYRENE = registerItem("beta_nitrostyrene", properties -> new SubstanceItem(properties, SubstanceTintColors.CLEAR_LIQUID, MatterState.SOLID), new Item.Properties());
    public static final Item MALEIC_ANHYDRIDE = registerItem("maleic_anhydride", properties -> new SubstanceItem(properties, SubstanceTintColors.LIGHT_FOGGY_ORANGE_LIQUID, MatterState.SOLID), new Item.Properties());
    public static final Item TETRAHYDROFURAN = registerItem("tetrahydrofuran", properties -> new SubstanceItem(properties, SubstanceTintColors.CLEAR_LIQUID, MatterState.LIQUID), new Item.Properties());
    public static final Item TWO_C_H = registerItem("2c_h", properties -> new SubstanceItem(properties, SubstanceTintColors.CLEAR_LIQUID, MatterState.SOLID), new Item.Properties());
    public static final Item BROMINE = registerItem("bromine", properties -> new SubstanceItem(properties, SubstanceTintColors.FOGGY_ORANGE_LIQUID, MatterState.LIQUID), new Item.Properties());
    public static final Item BROMIDE = registerItem("bromide", properties -> new SubstanceItem(properties, SubstanceTintColors.CLEAR_LIQUID, MatterState.SOLID), new Item.Properties());
    public static final Item TWO_C_B = registerItem("2c_b", SimpleDrugs.TwoCB::new, new Item.Properties().food(new FoodProperties.Builder().alwaysEdible().build()));
    public static final Item DISTILLED_WATER = registerItem("distilled_water", properties -> new SubstanceItem(properties, SubstanceTintColors.DARK_BLUE_LIQUID, MatterState.LIQUID), new Item.Properties());
    public static final Item P2P = registerItem("p2p", properties -> new SubstanceItem(properties, SubstanceTintColors.VERY_LIGHT_YELLOW_LIQUID, MatterState.LIQUID), new Item.Properties());
    public static final Item P2NP = registerItem("p2np", properties -> new SubstanceItem(properties, SubstanceTintColors.YELLOW_LIQUID, MatterState.SOLID), new Item.Properties());
    public static final Item NITROETHANE = registerItem("nitroethane", properties -> new SubstanceItem(properties, SubstanceTintColors.CLEAR_LIQUID, MatterState.LIQUID), new Item.Properties());
    public static final Item FORMIC_ACID = registerItem("formic_acid", properties -> new SubstanceItem(properties, SubstanceTintColors.CLEAR_LIQUID, MatterState.LIQUID), new Item.Properties());
    public static final Item METHYL_FORMATE = registerItem("methyl_formate", properties -> new SubstanceItem(properties, SubstanceTintColors.CLEAR_LIQUID, MatterState.LIQUID), new Item.Properties());
    public static final Item AMPHETAMINE = registerItem("amphetamine", SimpleDrugs.Amphetamine::new, new Item.Properties().food(new FoodProperties.Builder().alwaysEdible().build()));
    public static final Item COCA_LEAVES = registerItem("coca_leaves", Item::new, new Item.Properties());
    public static final Item RAW_SULFUR = registerItem("raw_sulfur", Item::new, new Item.Properties());
    public static final Item SULFUR = registerItem("sulfur", properties -> new SubstanceItem(properties, SubstanceTintColors.YELLOW_SOLID, MatterState.SOLID), new Item.Properties());
    public static final Item SULFURIC_ACID = registerItem("sulfuric_acid", properties -> new SubstanceItem(properties, SubstanceTintColors.VERY_LIGHT_YELLOW_LIQUID, MatterState.LIQUID), new Item.Properties());
    public static final Item TRONA = registerItem("trona", Item::new, new Item.Properties());
    public static final Item SYLVITE = registerItem("sylvite", Item::new, new Item.Properties());
    public static final Item SODIUM_CARBONATE = registerItem("sodium_carbonate", properties -> new SubstanceItem(properties, SubstanceTintColors.WHITE_SOLID, MatterState.SOLID), new Item.Properties());
    public static final Item SODIUM_CARBONATE_SOLUTION = registerItem("sodium_carbonate_solution", properties -> new SubstanceItem(properties, SubstanceTintColors.THICK_WHITE_LIQUID, MatterState.LIQUID), new Item.Properties());
    public static final Item AGUA_RICA = registerItem("agua_rica", properties -> new SubstanceItem(properties, SubstanceTintColors.GREENISH_ORANGE_LIQUID, MatterState.LIQUID), new Item.Properties());
    public static final Item PYROLUSITE = registerItem("pyrolusite", Item::new, new Item.Properties());
    public static final Item POTASSIUM_CHLORIDE = registerItem("potassium_chloride", properties -> new SubstanceItem(properties, SubstanceTintColors.WHITE_SOLID, MatterState.SOLID), new Item.Properties());
    public static final Item POTASSIUM_HYDROXIDE = registerItem("potassium_hydroxide", properties -> new SubstanceItem(properties, SubstanceTintColors.WHITE_SOLID, MatterState.SOLID), new Item.Properties());
    public static final Item POTASSIUM_CARBONATE = registerItem("potassium_carbonate", properties -> new SubstanceItem(properties, SubstanceTintColors.WHITE_SOLID, MatterState.SOLID), new Item.Properties());
    public static final Item MANGANESE_DIOXIDE = registerItem("manganese_dioxide", properties -> new SubstanceItem(properties, SubstanceTintColors.THICK_BLACK_SOLID, MatterState.SOLID), new Item.Properties());
    public static final Item POTASSIUM_PERMANGANATE = registerItem("potassium_permanganate", properties -> new SubstanceItem(properties, SubstanceTintColors.DARK_PURPLE_SOLID, MatterState.SOLID), new Item.Properties());
    public static final Item COCA_PASTE = registerItem("coca_paste", properties -> new SubstanceItem(properties, SubstanceTintColors.VERY_LIGHT_YELLOW_SOLID, MatterState.SOLID), new Item.Properties());
    public static final Item ACETONE = registerItem("acetone", properties -> new SubstanceItem(properties, SubstanceTintColors.CLEAR_LIQUID, MatterState.LIQUID), new Item.Properties());
    public static final Item AMMONIA_SOLUTION = registerItem("ammonia_solution", properties -> new SubstanceItem(properties, SubstanceTintColors.CLEAR_LIQUID, MatterState.LIQUID), new Item.Properties());
    public static final Item COCAINE = registerItem("cocaine", SimpleDrugs.Cocaine::new, new Item.Properties().food(new FoodProperties.Builder().alwaysEdible().build()));
    public static final Item CARBON_DIOXIDE = registerItem("carbon_dioxide", properties -> new SubstanceItem(properties, SubstanceTintColors.CLEAR_GAS, MatterState.GAS), new Item.Properties());
    public static final Item CASH = registerItem("cash", Item::new, new Item.Properties());
    public static final Item BAND = registerItem("band", Item::new, new Item.Properties());

    public static Item registerItem(String name, Function<Item.Properties, Item> factory, Item.Properties properties) {
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, name));
        Item item = factory.apply(properties.setId(key));
        return Registry.register(BuiltInRegistries.ITEM, key, item);
    }

    public static void registerItems() {
        registerItemGroups();
    }

    private static void registerItemGroups() {
        DRUGS_ITEM_GROUP = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
                ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "drugs"),
                FabricItemGroup.builder().title(Component.translatable("itemgroup.substancecraft.drugs"))
                        .icon(() -> new ItemStack(SubstanceCraftItems.MARIJUANA_TRIM)).displayItems((displayContext, entries) -> addDrugItems(entries)).build());

        BLOCKS_ITEM_GROUP = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
                ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "blocks"),
                FabricItemGroup.builder().title(Component.translatable("itemgroup.substancecraft.blocks"))
                        .icon(() -> new ItemStack(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.REFINERY))).displayItems((displayContext, entries) -> addBlockItems(entries)).build());

        MATERIALS_ITEM_GROUP = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
                ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "materials"),
                FabricItemGroup.builder().title(Component.translatable("itemgroup.substancecraft.materials"))
                        .icon(() -> new ItemStack(SubstanceCraftItems.HALITE)).displayItems((displayContext, entries) -> addMaterialItems(entries)).build());

        AGRICULTURE_ITEM_GROUP = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
                ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "agriculture"),
                FabricItemGroup.builder().title(Component.translatable("itemgroup.substancecraft.agriculture"))
                        .icon(() -> new ItemStack(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.MARIJUANA_PLANT))).displayItems((displayContext, entries) -> addAgricultureItems(entries)).build());

        SUBSTANCES_ITEM_GROUP = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
                ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "substances"),
                FabricItemGroup.builder().title(Component.translatable("itemgroup.substancecraft.substances"))
                        .icon(() -> new ItemStack(SubstanceCraftItems.SALT)).displayItems((displayContext, entries) -> addSubstanceItems(entries)).build());

        ALL_ITEM_GROUP = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
                ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "all"),
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
        entries.accept(MARIJUANA);
        entries.accept(MARIJUANA_TRIM);
        entries.accept(HASH);
        entries.accept(EMPTY_DAB_RIG);
        entries.accept(DAB_RIG);
        entries.accept(SubstanceCraftItems.DIPHENHYDRAMINE);
        entries.accept(SubstanceCraftItems.KETAMINE);
        entries.accept(SubstanceCraftItems.TWO_C_B);
        entries.accept(SubstanceCraftItems.AMPHETAMINE);
        entries.accept(SubstanceCraftItems.COCAINE);
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
    }

    private static void addMaterialItems(CreativeModeTab.Output entries) {
        entries.accept(SubstanceCraftItems.OIL_SHALE);
        entries.accept(SubstanceCraftItems.HALITE);
        entries.accept(SubstanceCraftItems.SYLVITE);
        entries.accept(SubstanceCraftItems.TRONA);
        entries.accept(SubstanceCraftItems.RAW_SULFUR);
        entries.accept(SubstanceCraftItems.PYROLUSITE);
    }

    private static void addAgricultureItems(CreativeModeTab.Output entries) {
        entries.accept(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.MARIJUANA_PLANT));
        entries.accept(SubstanceCraftItems.CORN);
        entries.accept(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.CORN_CROP));
        entries.accept(SubstanceCraftItems.YEAST);
        entries.accept(SubstanceCraftItems.ERGOT);
        entries.accept(SubstanceCraftItems.COCA_LEAVES);
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

    }

}
