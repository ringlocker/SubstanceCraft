package com.github.ringlocker.substancecraft.item;

import com.github.ringlocker.substancecraft.SubstanceCraft;
import com.github.ringlocker.substancecraft.item.items.DrugConsumerItem;
import com.github.ringlocker.substancecraft.item.items.DrugItem;
import com.github.ringlocker.substancecraft.item.items.PenjaminItem;
import com.github.ringlocker.substancecraft.item.items.SubstanceItem;
import com.github.ringlocker.substancecraft.item.items.WaterFillableItem;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class SubstanceCraftItems {

    public static List<SubstanceItem> substances = new ArrayList<>();
    public static List<Item> flatTextureItems = new ArrayList<>();

    public static final Item MARIJUANA = registerItem("marijuana", Item::new, new Item.Properties());
    public static final Item MARIJUANA_TRIM = registerItem("marijuana_trim", Item::new, new Item.Properties());
    public static final Item HASH = registerItem("hash", Item::new, new Item.Properties());
    public static final Item DAB_RIG = registerItem("dab_rig", properties -> new DrugConsumerItem(properties, List.of(consumable(HASH, Drug.HASH)), smoke(1.0F, 5, 0, 3)), alwaysEatProperties());
    public static final Item EMPTY_DAB_RIG = registerItem("empty_dab_rig", properties -> new WaterFillableItem(properties, DAB_RIG), new Item.Properties());
    public static final Item DIPHENHYDRAMINE = registerItem("diphenhydramine", properties -> new DrugItem(properties, Drug.DIPHENHYDRAMINE), alwaysEatProperties());
    public static final Item KETAMINE = registerItem("ketamine", properties -> new DrugItem(properties, Drug.KETAMINE), alwaysEatProperties());
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
    public static final Item TWO_C_B = registerItem("2c_b", properties -> new DrugItem(properties, Drug.TWO_CB), alwaysEatProperties());
    public static final Item DISTILLED_WATER = registerItem("distilled_water", properties -> new SubstanceItem(properties, SubstanceTintColors.DARK_BLUE_LIQUID, MatterState.LIQUID), new Item.Properties());
    public static final Item P2P = registerItem("p2p", properties -> new SubstanceItem(properties, SubstanceTintColors.VERY_LIGHT_YELLOW_LIQUID, MatterState.LIQUID), new Item.Properties());
    public static final Item P2NP = registerItem("p2np", properties -> new SubstanceItem(properties, SubstanceTintColors.YELLOW_LIQUID, MatterState.SOLID), new Item.Properties());
    public static final Item NITROETHANE = registerItem("nitroethane", properties -> new SubstanceItem(properties, SubstanceTintColors.CLEAR_LIQUID, MatterState.LIQUID), new Item.Properties());
    public static final Item FORMIC_ACID = registerItem("formic_acid", properties -> new SubstanceItem(properties, SubstanceTintColors.CLEAR_LIQUID, MatterState.LIQUID), new Item.Properties());
    public static final Item METHYL_FORMATE = registerItem("methyl_formate", properties -> new SubstanceItem(properties, SubstanceTintColors.CLEAR_LIQUID, MatterState.LIQUID), new Item.Properties());
    public static final Item AMPHETAMINE = registerItem("amphetamine", properties -> new DrugItem(properties, Drug.AMPHETAMINE), alwaysEatProperties());
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
    public static final Item COCAINE = registerItem("cocaine", properties -> new DrugItem(properties, Drug.COCAINE), alwaysEatProperties());
    public static final Item CARBON_DIOXIDE = registerItem("carbon_dioxide", properties -> new SubstanceItem(properties, SubstanceTintColors.CLEAR_GAS, MatterState.GAS), new Item.Properties());
    public static final Item CASH = registerItem("cash", Item::new, new Item.Properties());
    public static final Item BAND = registerItem("band", Item::new, new Item.Properties());
    public static final Item GRAPES = registerItem("grapes", Item::new, new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationModifier(0.2F).build()));
    public static final Item RED_WINE = registerItem("red_wine", properties -> new DrugItem(properties, Drug.WINE), alwaysEatProperties());
    public static final Item WINE_LEES = registerItem("wine_lees", Item::new, new Item.Properties());
    public static final Item POTASSIUM_BITARTRATE = registerItem("potassium_bitartrate", properties -> new SubstanceItem(properties, SubstanceTintColors.WHITE_SOLID, MatterState.SOLID), new Item.Properties());
    public static final Item LIMESTONE = registerItem("limestone", Item::new, new Item.Properties());
    public static final Item CALCIUM_OXIDE = registerItem("calcium_oxide", properties -> new SubstanceItem(properties, SubstanceTintColors.WHITE_SOLID, MatterState.SOLID), new Item.Properties());
    public static final Item CALCIUM_HYDROXIDE = registerItem("calcium_hydroxide", properties -> new SubstanceItem(properties, SubstanceTintColors.THICK_WHITE_LIQUID, MatterState.LIQUID), new Item.Properties());
    public static final Item CALCIUM_TARTRATE = registerItem("calcium_tartrate", properties -> new SubstanceItem(properties, SubstanceTintColors.WHITE_SOLID, MatterState.SOLID), new Item.Properties());
    public static final Item TARTARIC_ACID = registerItem("tartaric_acid", properties -> new SubstanceItem(properties, SubstanceTintColors.WHITE_SOLID, MatterState.SOLID), new Item.Properties());
    public static final Item ERGOTAMINE = registerItem("ergotamine", properties -> new SubstanceItem(properties, SubstanceTintColors.LIGHT_YELLOW_SOLID, MatterState.SOLID), new Item.Properties());
    public static final Item ERGOTAMINE_TARTRATE = registerItem("ergotamine_tartrate", properties -> new SubstanceItem(properties, SubstanceTintColors.LIGHT_YELLOW_SOLID, MatterState.SOLID), new Item.Properties());
    public static final Item DIETHYL_ETHER = registerItem("diethyl_ether", properties -> new SubstanceItem(properties, SubstanceTintColors.CLEAR_LIQUID, MatterState.LIQUID), new Item.Properties());
    public static final Item ERGOTAMINE_SULFATE = registerItem("ergotamine_sulfate", properties -> new SubstanceItem(properties, SubstanceTintColors.VERY_LIGHT_YELLOW_SOLID, MatterState.SOLID), new Item.Properties());
    public static final Item SILICA = registerItem("silica", properties -> new SubstanceItem(properties, SubstanceTintColors.OPAQUE_WHITE_SOLID, MatterState.SOLID), new Item.Properties());
    public static final Item PHOSPHORITE = registerItem("phosphorite", Item::new, new Item.Properties());
    public static final Item FLOUROAPATITE = registerItem("flouroapatite", Item::new, new Item.Properties());
    public static final Item WHITE_PHOSPHORUS = registerItem("white_phosphorus", properties -> new SubstanceItem(properties, SubstanceTintColors.WHITE_SOLID, MatterState.SOLID), new Item.Properties());
    public static final Item RED_PHOSPHORUS = registerItem("red_phosphorus", properties -> new SubstanceItem(properties, SubstanceTintColors.RED_SOLID, MatterState.SOLID), new Item.Properties());
    public static final Item PHOSPHORUS_TRICHLORIDE = registerItem("phosphorus_trichloride", properties -> new SubstanceItem(properties, SubstanceTintColors.VERY_LIGHT_YELLOW_LIQUID, MatterState.LIQUID), new Item.Properties());
    public static final Item PHOSPHORYL_CHLORIDE = registerItem("phosphoryl_chloride", properties -> new SubstanceItem(properties, SubstanceTintColors.VERY_LIGHT_YELLOW_LIQUID, MatterState.LIQUID), new Item.Properties());
    public static final Item DIETHYLENE = registerItem("diethylene", properties -> new SubstanceItem(properties, SubstanceTintColors.CLEAR_LIQUID, MatterState.LIQUID), new Item.Properties());
    public static final Item D_LYSERGIC_ACID_HYDRATE = registerItem("d_lysergic_acid_hydrate", properties -> new SubstanceItem(properties, SubstanceTintColors.CLEAR_LIQUID, MatterState.LIQUID), new Item.Properties());
    public static final Item MAGNESIUM = registerItem("magnesium", properties -> new SubstanceItem(properties, SubstanceTintColors.METALLIC_SOLID, MatterState.SOLID), new Item.Properties());
    public static final Item MAGNESIUM_SULFATE = registerItem("magnesium_sulfate", properties -> new SubstanceItem(properties, SubstanceTintColors.WHITE_SOLID, MatterState.SOLID), new Item.Properties());
    public static final Item LYSERGIC_ACID_DIETHYLAMINE = registerItem("lysergic_acid_diethylamine", properties -> new SubstanceItem(properties, SubstanceTintColors.CLEAR_LIQUID, MatterState.LIQUID), alwaysEatProperties());
    public static final Item LYSERGIC_ACID_DIETHYLAMINE_TAB = registerItem("lysergic_acid_diethylamine_tab", properties -> new DrugItem(properties, Drug.LSD), alwaysEatProperties());
    public static final Item XYLENE = registerItem("xylene", properties -> new SubstanceItem(properties, SubstanceTintColors.CLEAR_LIQUID, MatterState.LIQUID), new Item.Properties());
    public static final Item MESCALINE = registerItem("mescaline", properties -> new DrugItem(properties, Drug.MESCALINE), alwaysEatProperties());
    public static final Item EDIBLE = registerItem("edible", properties -> new DrugItem(properties, Drug.EDIBLE), alwaysEatProperties());
    public static final Item JOINT = registerItem("joint", properties -> new DrugItem(properties, Drug.JOINT, smoke(0.8F, 5, 0, 2)), alwaysEatProperties());
    public static final Item BONG = registerItem("bong", properties -> new DrugConsumerItem(properties, List.of(consumable(MARIJUANA, Drug.FLOWER)), smoke(0.8F, 4, 1, 2)), alwaysEatProperties());
    public static final Item EMPTY_BONG = registerItem("empty_bong", properties -> new WaterFillableItem(properties, BONG), new Item.Properties());
    public static final Item LIVE_RESIN = registerItem("live_resin", Item::new, new Item.Properties());
    public static final Item ROSIN = registerItem("rosin",  Item::new, new Item.Properties());
    public static final Item EMPTY_CART = registerItem("empty_cart", Item::new, new Item.Properties().stacksTo(1));
    public static final Item PROPYLENE_OXIDE = registerItem("propylene_oxide", properties -> new SubstanceItem(properties, SubstanceTintColors.CLEAR_LIQUID, MatterState.LIQUID), new Item.Properties());
    public static final Item PROPYLENE_GLYCOL = registerItem("propylene_glycol", properties -> new SubstanceItem(properties, SubstanceTintColors.CLEAR_LIQUID, MatterState.LIQUID), new Item.Properties());
    public static final Item PEN_BATTERY = registerItem("pen_battery", Item::new, new Item.Properties().stacksTo(1));
    public static final Item RESIN_PEN = registerItem("resin_pen", properties -> new PenjaminItem(properties, Drug.LIVE_RESIN, smoke(0.8F, 4, 0, 1)), alwaysEatProperties().durability(10).stacksTo(1));
    public static final Item RESIN_CART = registerItem("resin_cart", Item::new, new Item.Properties());
    public static final Item ROSIN_PEN = registerItem("rosin_pen", properties -> new PenjaminItem(properties, Drug.ROSIN, smoke(0.8F, 4, 0, 1)), alwaysEatProperties().durability(10).stacksTo(1));
    public static final Item ROSIN_CART = registerItem("rosin_cart", Item::new, new Item.Properties());
    public static final Item DMT_PEN = registerItem("dmt_pen", properties -> new PenjaminItem(properties, Drug.DMT, smoke(0.8F, 4, 0, 1)), alwaysEatProperties().durability(10).stacksTo(1));
    public static final Item DMT_CART = registerItem("dmt_cart", Item::new, new Item.Properties());
    // TODO: battery (lithium or lead or redstone?), empty cart, empty cart * 8 of (dmt, rosin, etc) = full cart, durability system
    public static final Item VINEGAR = registerItem("vinegar", properties -> new SubstanceItem(properties, SubstanceTintColors.CLEAR_LIQUID, MatterState.LIQUID), new Item.Properties());
    public static final Item MIMOSA_TENUIFLORA_ROOT_BARK = registerItem("mimosa_tenuiflora_root_bark", Item::new, new Item.Properties());
    public static final Item N_N_DIMETHYLTRYPTAMINE = registerItem("n_n_dimethyltryptamine", properties -> new DrugItem(properties, Drug.DMT), new Item.Properties());

    public static Item registerItem(String name, Function<Item.Properties, Item> factory, Item.Properties properties) {
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID, name));
        Item item = factory.apply(properties.setId(key));
        Item registered = Registry.register(BuiltInRegistries.ITEM, key, item);
        if (registered instanceof SubstanceItem) substances.add((SubstanceItem) registered);
        else flatTextureItems.add(registered);
        return registered;
    }

    private static Item.Properties alwaysEatProperties() {
        return new Item.Properties().food(new FoodProperties.Builder().alwaysEdible().build());
    }

    private static DrugConsumerItem.Consumable consumable(Item item, Drug drug) {
        return new DrugConsumerItem.Consumable(item, drug);
    }

    private static DrugConsumerItem.SmokeSettings smoke(float smokeVelocityMultiplier, int widthInEighthBlocks, int minRollsPerSection, int maxRollsPerSection) {
        return new DrugConsumerItem.SmokeSettings(smokeVelocityMultiplier, widthInEighthBlocks, minRollsPerSection, maxRollsPerSection);
    }

    public static void registerItems() {

    }


}
