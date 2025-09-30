package com.github.ringlocker.substancecraft.items;

import com.github.ringlocker.substancecraft.SubstanceCraft;
import com.github.ringlocker.substancecraft.block.SubstanceCraftBlocks;
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

    public static CreativeModeTab SUBSTANCES_ITEM_GROUP;

    public static final Item MARIJUANA = registerItem("marijuana", Item::new, new Item.Properties());
    public static final Item MARIJUANA_TRIM = registerItem("marijuana_trim", Item::new, new Item.Properties());
    public static final Item HASH = registerItem("hash", Item::new, new Item.Properties());
    public static final Item EMPTY_DAB_RIG = registerItem("empty_dab_rig", DabRig.EmptyDabRigItem::new, new Item.Properties());
    public static final Item DAB_RIG = registerItem("dab_rig", DabRig.DabRigItem::new, new Item.Properties().food(new FoodProperties.Builder().alwaysEdible().nutrition(-1).build()));
    public static final Item DIPHENHYDRAMINE = registerItem("diphenhydramine", SimpleDrugs.Diphenhydramine::new, new Item.Properties().food(new FoodProperties.Builder().alwaysEdible().build()));
    public static final Item KETAMINE = registerItem("ketamine", SimpleDrugs.Ketamine::new, new Item.Properties().food(new FoodProperties.Builder().alwaysEdible().build()));
    public static final Item OIL = registerItem("oil", properties -> new SubstanceItem(properties, SubstanceTintColors.THICK_BLACK_LIQUID), new Item.Properties());
    public static final Item OIL_SHALE = registerItem("oil_shale", Item::new, new Item.Properties());
    public static final Item PETROLEUM_NAPHTHA = registerItem("petroleum_naphtha", properties -> new SubstanceItem(properties, SubstanceTintColors.PISS_YELLOW_LIQUID), new Item.Properties());
    public static final Item KEROSENE = registerItem("kerosene", properties -> new SubstanceItem(properties, SubstanceTintColors.LIGHT_BLUE_LIQUID), new Item.Properties());
    public static final Item GASOLINE = registerItem("gasoline",  properties -> new SubstanceItem(properties, SubstanceTintColors.YELLOW_LIQUID), new Item.Properties());
    public static final Item METHANOL = registerItem("methanol", properties -> new SubstanceItem(properties, SubstanceTintColors.CLEAR_LIQUID), new Item.Properties());
    public static final Item BENZENE = registerItem("benzene", properties -> new SubstanceItem(properties, SubstanceTintColors.VERY_LIGHT_YELLOW_LIQUID), new Item.Properties());
    public static final Item CHLOROFORM = registerItem("chloroform", properties -> new SubstanceItem(properties, SubstanceTintColors.CLEAR_LIQUID), new Item.Properties());
    public static final Item FORMALDEHYDE = registerItem("formaldehyde", properties -> new SubstanceItem(properties, SubstanceTintColors.CLEAR_LIQUID), new Item.Properties());
    public static final Item TOLUENE = registerItem("toluene", properties -> new SubstanceItem(properties, SubstanceTintColors.CLEAR_LIQUID), new Item.Properties());
    public static final Item HALITE = registerItem("halite", Item::new, new Item.Properties());
    public static final Item SALT = registerItem("salt", Item::new, new Item.Properties());
    public static final Item BRINE = registerItem("brine", properties -> new SubstanceItem(properties, SubstanceTintColors.LIGHT_TEAL_LIQUID), new Item.Properties());
    public static final Item SODIUM_HYDROXIDE = registerItem("sodium_hydroxide", Item::new, new Item.Properties());
    public static final Item HYDROGEN = registerItem("hydrogen", properties -> new SubstanceItem(properties, SubstanceTintColors.CLEAR_GAS), new Item.Properties());
    public static final Item CHLORINE = registerItem("chlorine", properties -> new SubstanceItem(properties, SubstanceTintColors.LIGHT_YELLOW_GAS), new Item.Properties());
    public static final Item METHANE = registerItem("methane", properties -> new SubstanceItem(properties, SubstanceTintColors.CLEAR_GAS), new Item.Properties());
    public static final Item NITROGEN = registerItem("nitrogen", properties -> new SubstanceItem(properties, SubstanceTintColors.CLEAR_GAS), new Item.Properties());
    public static final Item OXYGEN = registerItem("oxygen", properties -> new SubstanceItem(properties, SubstanceTintColors.CLEAR_GAS), new Item.Properties());
    public static final Item NATURAL_GAS = registerItem("natural_gas", properties -> new SubstanceItem(properties, SubstanceTintColors.CLEAR_GAS), new Item.Properties());
    public static final Item ETHANE = registerItem("ethane", properties -> new SubstanceItem(properties, SubstanceTintColors.CLEAR_GAS), new Item.Properties());
    public static final Item PROPANE = registerItem("propane", properties -> new SubstanceItem(properties, SubstanceTintColors.CLEAR_GAS), new Item.Properties());
    public static final Item BUTANE = registerItem("butane", properties -> new SubstanceItem(properties, SubstanceTintColors.CLEAR_GAS), new Item.Properties());
    public static final Item METHYLAMINE = registerItem("methylamine", properties -> new SubstanceItem(properties, SubstanceTintColors.CLEAR_LIQUID), new Item.Properties());
    public static final Item ETHYLENE = registerItem("ethylene", properties -> new SubstanceItem(properties, SubstanceTintColors.CLEAR_GAS), new Item.Properties());
    public static final Item PROPYLENE = registerItem("propylene", properties -> new SubstanceItem(properties, SubstanceTintColors.CLEAR_GAS), new Item.Properties());
    public static final Item DIESEL = registerItem("diesel", properties -> new SubstanceItem(properties, SubstanceTintColors.RED_LIQUID), new Item.Properties());
    public static final Item AMMONIA = registerItem("ammonia", properties -> new SubstanceItem(properties, SubstanceTintColors.CLEAR_GAS), new Item.Properties());
    public static final Item CORN = registerItem("corn", Item::new, new Item.Properties());
    public static final Item ETHANOL = registerItem("ethanol", properties -> new SubstanceItem(properties, SubstanceTintColors.CLEAR_LIQUID), new Item.Properties());
    public static final Item YEAST = registerItem("yeast", Item::new, new Item.Properties());
    public static final Item HYDROCHLORIC_ACID = registerItem("hydrochloric_acid", properties -> new SubstanceItem(properties, SubstanceTintColors.VERY_LIGHT_YELLOW_GAS), new Item.Properties());
    public static final Item ERGOT = registerItem("ergot", Item::new, new Item.Properties());
    public static final Item BENZALDEHYDE = registerItem("benzaldehyde", properties -> new SubstanceItem(properties, SubstanceTintColors.CLEAR_LIQUID), new Item.Properties());
    public static final Item NITRIC_ACID = registerItem("nitric_acid", properties -> new SubstanceItem(properties, SubstanceTintColors.VERY_LIGHT_YELLOW_GAS), new Item.Properties());
    public static final Item NITROMETHANE = registerItem("nitromethane", properties -> new SubstanceItem(properties, SubstanceTintColors.CLEAR_LIQUID), new Item.Properties());
    public static final Item ACETIC_ACID = registerItem("acetic_acid", properties -> new SubstanceItem(properties, SubstanceTintColors.CLEAR_LIQUID), new Item.Properties());
    public static final Item AMMONIUM_ACETATE = registerItem("ammonium_acetate", Item::new, new Item.Properties());
    public static final Item CARBON_MONOXIDE = registerItem("carbon_monoxide", properties -> new SubstanceItem(properties, SubstanceTintColors.CLEAR_GAS), new Item.Properties());
    public static final Item COKE = registerItem("coke", Item::new, new Item.Properties());
    public static final Item BETA_NITROSTYRENE = registerItem("beta_nitrostyrene", properties -> new SubstanceItem(properties, SubstanceTintColors.CLEAR_LIQUID), new Item.Properties());
    public static final Item MALEIC_ANHYDRIDE = registerItem("maleic_anhydride", Item::new, new Item.Properties());
    public static final Item TETRAHYDROFURAN = registerItem("tetrahydrofuran", properties -> new SubstanceItem(properties, SubstanceTintColors.CLEAR_LIQUID), new Item.Properties());
    public static final Item TWO_C_H = registerItem("2c_h", Item::new, new Item.Properties());
    public static final Item BROMINE = registerItem("bromine", properties -> new SubstanceItem(properties, SubstanceTintColors.FOGGY_ORANGE_LIQUID), new Item.Properties());
    public static final Item BROMIDE = registerItem("bromide", properties -> new SubstanceItem(properties, SubstanceTintColors.LIGHT_FOGGY_ORANGE_LIQUID), new Item.Properties());
    public static final Item TWO_C_B = registerItem("2c_b", Item::new, new Item.Properties());
    public static final Item DISTILLED_WATER = registerItem("distilled_water", properties -> new SubstanceItem(properties, SubstanceTintColors.DARK_BLUE_LIQUID), new Item.Properties());
    public static final Item P2P = registerItem("p2p", properties -> new SubstanceItem(properties, SubstanceTintColors.VERY_LIGHT_YELLOW_LIQUID), new Item.Properties());
    public static final Item P2NP = registerItem("p2np", Item::new, new Item.Properties());
    public static final Item NITROETHANE = registerItem("nitroethane", properties -> new SubstanceItem(properties, SubstanceTintColors.CLEAR_LIQUID), new Item.Properties());
    public static final Item FORMIC_ACID = registerItem("formic_acid", properties -> new SubstanceItem(properties, SubstanceTintColors.CLEAR_LIQUID), new Item.Properties());
    public static final Item METHYL_FORMATE = registerItem("methyl_formate", properties -> new SubstanceItem(properties, SubstanceTintColors.CLEAR_LIQUID), new Item.Properties());
    public static final Item AMPHETAMINE = registerItem("amphetamine", SimpleDrugs.Amphetamine::new, new Item.Properties().food(new FoodProperties.Builder().alwaysEdible().build()));
    public static final Item COCA_LEAVES = registerItem("coca_leaves", Item::new, new Item.Properties());
    public static final Item RAW_SULFUR = registerItem("raw_sulfur", Item::new, new Item.Properties());
    public static final Item SULFUR = registerItem("sulfur", Item::new, new Item.Properties());

    public static Item registerItem(String name, Function<Item.Properties, Item> factory, Item.Properties properties) {
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, name));
        Item item = factory.apply(properties.setId(key));
        return Registry.register(BuiltInRegistries.ITEM, key, item);
    }

    public static void registerItems() {
        registerItemGroups();
    }

    private static void registerItemGroups() {
        SUBSTANCES_ITEM_GROUP = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
                ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "substances"),
                FabricItemGroup.builder().title(Component.translatable("itemgroup.substancecraft.substances"))
                        .icon(() -> new ItemStack(Items.OMINOUS_BOTTLE)).displayItems((displayContext, entries) -> {
                            entries.accept(SubstanceCraftItems.MARIJUANA);
                            entries.accept(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.MARIJUANA_PLANT));
                            entries.accept(SubstanceCraftItems.MARIJUANA_TRIM);
                            entries.accept(SubstanceCraftItems.HASH);
                            entries.accept(SubstanceCraftItems.EMPTY_DAB_RIG);
                            entries.accept(SubstanceCraftItems.DAB_RIG);
                            entries.accept(SubstanceCraftItems.CORN);
                            entries.accept(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.CORN_CROP));
                            entries.accept(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.COCA_CROP));
                            entries.accept(SubstanceCraftItems.COCA_LEAVES);
                            entries.accept(SubstanceCraftItems.YEAST);
                            entries.accept(SubstanceCraftItems.ERGOT);
                            entries.accept(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.HASH_PRESS));
                            entries.accept(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.REFINERY));
                            entries.accept(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.CATALYTIC_REFORMER));
                            entries.accept(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.ELECTROLYSIS_MACHINE));
                            entries.accept(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.OXIDATION_MACHINE));
                            entries.accept(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.EXTRACTOR));
                            entries.accept(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.MIXER));
                            entries.accept(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.HEATED_MIXER));
                            entries.accept(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.FERMENTATION_TANK));
                            entries.accept(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.OIL_SHALE));
                            entries.accept(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.HALITE));
                            entries.accept(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.SYLVITE));
                            entries.accept(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.SULFUR_ORE));
                            entries.accept(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.DEEPSLATE_SULFUR_ORE));
                            entries.accept(SubstanceCraftItems.RAW_SULFUR);
                            entries.accept(SubstanceCraftItems.DIPHENHYDRAMINE);
                            entries.accept(SubstanceCraftItems.KETAMINE);
                            entries.accept(SubstanceCraftItems.TWO_C_H);
                            entries.accept(SubstanceCraftItems.TWO_C_B);
                            entries.accept(SubstanceCraftItems.HALITE);
                            entries.accept(SubstanceCraftItems.SALT);
                            entries.accept(SubstanceCraftItems.SODIUM_HYDROXIDE);
                            entries.accept(SubstanceCraftItems.COKE);
                            entries.accept(SubstanceCraftItems.AMMONIUM_ACETATE);
                            entries.accept(SubstanceCraftItems.MALEIC_ANHYDRIDE);
                            entries.accept(SubstanceCraftItems.P2NP);
                            entries.accept(SubstanceCraftItems.AMPHETAMINE);
                            entries.accept(SubstanceCraftItems.OIL);
                            entries.accept(SubstanceCraftItems.OIL_SHALE);
                            entries.accept(SubstanceCraftItems.PETROLEUM_NAPHTHA);
                            entries.accept(SubstanceCraftItems.KEROSENE);
                            entries.accept(SubstanceCraftItems.GASOLINE);
                            entries.accept(SubstanceCraftItems.METHANOL);
                            entries.accept(SubstanceCraftItems.FORMALDEHYDE);
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
                            entries.accept(SubstanceCraftItems.ETHYLENE);
                            entries.accept(SubstanceCraftItems.PROPYLENE);
                            entries.accept(SubstanceCraftItems.DIESEL);
                            entries.accept(SubstanceCraftItems.ETHANOL);
                            entries.accept(SubstanceCraftItems.AMMONIA);
                            entries.accept(SubstanceCraftItems.HYDROCHLORIC_ACID);
                            entries.accept(SubstanceCraftItems.BENZALDEHYDE);
                            entries.accept(SubstanceCraftItems.NITRIC_ACID);
                            entries.accept(SubstanceCraftItems.NITROMETHANE);
                            entries.accept(SubstanceCraftItems.ACETIC_ACID);
                            entries.accept(SubstanceCraftItems.CARBON_MONOXIDE);
                            entries.accept(SubstanceCraftItems.BETA_NITROSTYRENE);
                            entries.accept(SubstanceCraftItems.TETRAHYDROFURAN);
                            entries.accept(SubstanceCraftItems.BROMINE);
                            entries.accept(SubstanceCraftItems.BROMIDE);
                            entries.accept(SubstanceCraftItems.DISTILLED_WATER);
                            entries.accept(SubstanceCraftItems.P2P);
                            entries.accept(SubstanceCraftItems.NITROETHANE);
                            entries.accept(SubstanceCraftItems.METHYL_FORMATE);
                            entries.accept(SubstanceCraftItems.FORMIC_ACID);
                            entries.accept(SubstanceCraftItems.SULFUR);
                        }).build());

    }

}
