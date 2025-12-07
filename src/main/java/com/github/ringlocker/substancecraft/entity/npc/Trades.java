package com.github.ringlocker.substancecraft.entity.npc;

import com.github.ringlocker.substancecraft.block.SubstanceCraftBlocks;
import com.github.ringlocker.substancecraft.item.SubstanceCraftItems;
import com.google.common.collect.ImmutableList;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.apache.commons.lang3.tuple.Pair;

public class Trades {

    private static final int MAX_DRUG_BUY_QUANTITY = 8;
    private static final int MAX_DRUG_SELL_QUANTITY = 16;
    private static final int MAX_PLANT_BUY_QUANTITY = 8;
    private static final int MAX_PLANT_BULK_SELL_QUANTITY = 8;
    private static final int MAX_CHEMICAL_BUY_QUANTITY = 16;
    
    public static final ImmutableList<Pair<VillagerTrades.ItemListing[], Integer>> DEALER_TRADES = ImmutableList.<Pair<VillagerTrades.ItemListing[], Integer>>builder()
            // Buys 3 unique synthesizable drugs from player
            .add(Pair.of(new VillagerTrades.ItemListing[]{
                    new ItemForItemOffer(SubstanceCraftItems.TWO_C_B, 1, stack(SubstanceCraftItems.BAND, 9), MAX_DRUG_SELL_QUANTITY, 2),
                    new ItemForItemOffer(SubstanceCraftItems.HASH, 1, stack(SubstanceCraftItems.CASH, 8), MAX_DRUG_SELL_QUANTITY, 2),
                    new ItemForItemOffer(SubstanceCraftItems.COCAINE, 1, stack(SubstanceCraftItems.BAND, 9), MAX_DRUG_SELL_QUANTITY, 2),
                    new ItemForItemOffer(SubstanceCraftItems.AMPHETAMINE, 1, stack(SubstanceCraftItems.BAND, 6), MAX_DRUG_SELL_QUANTITY, 2),
            }, 3))

            // Buys 1 unsynthesizable drug from player
            .add(Pair.of(new VillagerTrades.ItemListing[]{
                    new ItemForItemOffer(SubstanceCraftItems.KETAMINE, 1, stack(SubstanceCraftItems.BAND, 4), MAX_DRUG_SELL_QUANTITY, 2),
                    new ItemForItemOffer(SubstanceCraftItems.DIPHENHYDRAMINE, 1, stack(SubstanceCraftItems.BAND, 2), MAX_DRUG_SELL_QUANTITY, 2),
            }, 1))

            // Sells 1 unsynthesizable drug
            .add(Pair.of(new VillagerTrades.ItemListing[]{
                    new ItemForItemOffer(SubstanceCraftItems.BAND, 4, 6, stack(SubstanceCraftItems.KETAMINE, 1), MAX_DRUG_BUY_QUANTITY, 2),
                    new ItemForItemOffer(SubstanceCraftItems.BAND, 2, 3, stack(SubstanceCraftItems.DIPHENHYDRAMINE, 1), MAX_DRUG_BUY_QUANTITY, 2),
            }, 1))

            // Sells 1 synthesizable drug
            .add(Pair.of(new VillagerTrades.ItemListing[]{
                    new ItemForItemOffer(SubstanceCraftItems.BAND, 9, 11, stack(SubstanceCraftItems.TWO_C_B, 1), MAX_DRUG_BUY_QUANTITY, 2),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 8, 12, stack(SubstanceCraftItems.HASH, 1), MAX_DRUG_BUY_QUANTITY, 2),
                    new ItemForItemOffer(SubstanceCraftItems.BAND, 9, 10, stack(SubstanceCraftItems.COCAINE, 1), MAX_DRUG_BUY_QUANTITY, 2),
                    new ItemForItemOffer(SubstanceCraftItems.BAND, 6, 7, stack(SubstanceCraftItems.AMPHETAMINE, 1), MAX_DRUG_BUY_QUANTITY, 2),
            }, 1))

            // Sells 1 crop seed
            .add(Pair.of(new VillagerTrades.ItemListing[]{
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 6, 10, stack(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.MARIJUANA_PLANT), 1), MAX_PLANT_BUY_QUANTITY, 1),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 4, 6, stack(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.CORN_CROP), 1), MAX_PLANT_BUY_QUANTITY, 1),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 8, 12, stack(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.COCA_CROP), 1), MAX_PLANT_BUY_QUANTITY, 1),
            }, 1))

            // Buys plant harvests
            .add(Pair.of(new VillagerTrades.ItemListing[]{
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 5, 10, stack(SubstanceCraftItems.MARIJUANA, 16), MAX_PLANT_BUY_QUANTITY, 1),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 6, 12, stack(SubstanceCraftItems.COCA_LEAVES, 16), MAX_PLANT_BUY_QUANTITY, 1),
            }, 2))

            // Sells 6 substances
            .add(Pair.of(new VillagerTrades.ItemListing[]{
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 2, 5, stack(SubstanceCraftItems.DISTILLED_WATER, 1), MAX_CHEMICAL_BUY_QUANTITY, 1),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 3, 8, stack(SubstanceCraftItems.OIL, 1), MAX_CHEMICAL_BUY_QUANTITY, 1),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 4, 10, stack(SubstanceCraftItems.DIESEL, 1), MAX_CHEMICAL_BUY_QUANTITY, 1),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 4, 10, stack(SubstanceCraftItems.GASOLINE, 1), MAX_CHEMICAL_BUY_QUANTITY, 1),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 4, 10, stack(SubstanceCraftItems.KEROSENE, 1), MAX_CHEMICAL_BUY_QUANTITY, 1),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 4, 10, stack(SubstanceCraftItems.PETROLEUM_NAPHTHA, 1), MAX_CHEMICAL_BUY_QUANTITY, 1),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 8, 13, stack(SubstanceCraftItems.ETHANOL, 1), MAX_CHEMICAL_BUY_QUANTITY, 1),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 8, 13, stack(SubstanceCraftItems.METHANOL, 1), MAX_CHEMICAL_BUY_QUANTITY, 1),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 10, 15, stack(SubstanceCraftItems.CHLOROFORM, 1), MAX_CHEMICAL_BUY_QUANTITY, 1),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 6, 12, stack(SubstanceCraftItems.TOLUENE, 1), MAX_CHEMICAL_BUY_QUANTITY, 1),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 6, 12, stack(SubstanceCraftItems.BENZENE, 1), MAX_CHEMICAL_BUY_QUANTITY, 1),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 4, 9, stack(SubstanceCraftItems.BRINE, 1), MAX_CHEMICAL_BUY_QUANTITY, 1),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 13, 17, stack(SubstanceCraftItems.CHLORINE, 1), MAX_CHEMICAL_BUY_QUANTITY, 1),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 4, 9, stack(SubstanceCraftItems.HYDROGEN, 1), MAX_CHEMICAL_BUY_QUANTITY, 1),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 6, 11, stack(SubstanceCraftItems.METHANE, 1), MAX_CHEMICAL_BUY_QUANTITY, 1),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 2, 4, stack(SubstanceCraftItems.NITROGEN, 1), MAX_CHEMICAL_BUY_QUANTITY, 1),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 5, 10, stack(SubstanceCraftItems.OXYGEN, 1), MAX_CHEMICAL_BUY_QUANTITY, 1),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 4, 9, stack(SubstanceCraftItems.NATURAL_GAS, 1), MAX_CHEMICAL_BUY_QUANTITY, 1),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 7, 11, stack(SubstanceCraftItems.PROPANE, 1), MAX_CHEMICAL_BUY_QUANTITY, 1),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 7, 12, stack(SubstanceCraftItems.ETHANE, 1), MAX_CHEMICAL_BUY_QUANTITY, 1),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 7, 11, stack(SubstanceCraftItems.BUTANE, 1), MAX_CHEMICAL_BUY_QUANTITY, 1),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 20, 28, stack(SubstanceCraftItems.METHYLAMINE, 1), MAX_CHEMICAL_BUY_QUANTITY, 1),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 10, 15, stack(SubstanceCraftItems.PROPYLENE, 1), MAX_CHEMICAL_BUY_QUANTITY, 1),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 9, 15, stack(SubstanceCraftItems.ETHYLENE, 1), MAX_CHEMICAL_BUY_QUANTITY, 1),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 11, 18, stack(SubstanceCraftItems.FORMALDEHYDE, 1), MAX_CHEMICAL_BUY_QUANTITY, 1),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 11, 17, stack(SubstanceCraftItems.AMMONIA, 1), MAX_CHEMICAL_BUY_QUANTITY, 1),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 9, 15, stack(SubstanceCraftItems.BENZALDEHYDE, 1), MAX_CHEMICAL_BUY_QUANTITY, 1),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 13, 20, stack(SubstanceCraftItems.HYDROCHLORIC_ACID, 1), MAX_CHEMICAL_BUY_QUANTITY, 1),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 21, 30, stack(SubstanceCraftItems.AMMONIUM_ACETATE, 1), MAX_CHEMICAL_BUY_QUANTITY, 1),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 12, 18, stack(SubstanceCraftItems.MALEIC_ANHYDRIDE, 1), MAX_CHEMICAL_BUY_QUANTITY, 1),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 2, 4, stack(SubstanceCraftItems.SALT, 1), MAX_CHEMICAL_BUY_QUANTITY, 1),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 14, 19, stack(SubstanceCraftItems.ACETIC_ACID, 1), MAX_CHEMICAL_BUY_QUANTITY, 1),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 14, 21, stack(SubstanceCraftItems.NITRIC_ACID, 1), MAX_CHEMICAL_BUY_QUANTITY, 1),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 21, 30, stack(SubstanceCraftItems.NITROMETHANE, 1), MAX_CHEMICAL_BUY_QUANTITY, 1),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 20, 31, stack(SubstanceCraftItems.NITROETHANE, 1), MAX_CHEMICAL_BUY_QUANTITY, 1),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 7, 11, stack(SubstanceCraftItems.SODIUM_HYDROXIDE, 1), MAX_CHEMICAL_BUY_QUANTITY, 1),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 8, 14, stack(SubstanceCraftItems.BROMIDE, 1), MAX_CHEMICAL_BUY_QUANTITY, 1),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 14, 19, stack(SubstanceCraftItems.BROMINE, 1), MAX_CHEMICAL_BUY_QUANTITY, 1),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 9, 12, stack(SubstanceCraftItems.CARBON_MONOXIDE, 1), MAX_CHEMICAL_BUY_QUANTITY, 1),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 19, 25, stack(SubstanceCraftItems.METHYL_FORMATE, 1), MAX_CHEMICAL_BUY_QUANTITY, 1),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 23, 28, stack(SubstanceCraftItems.FORMIC_ACID, 1), MAX_CHEMICAL_BUY_QUANTITY, 1),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 3, 6, stack(SubstanceCraftItems.COKE, 1), MAX_CHEMICAL_BUY_QUANTITY, 1),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 17, 21, stack(SubstanceCraftItems.TETRAHYDROFURAN, 1), MAX_CHEMICAL_BUY_QUANTITY, 1),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 38, 50, stack(SubstanceCraftItems.BETA_NITROSTYRENE, 1), MAX_CHEMICAL_BUY_QUANTITY, 1),
                    new ItemForItemOffer(SubstanceCraftItems.BAND, 7, 8, stack(SubstanceCraftItems.TWO_C_H, 1), MAX_CHEMICAL_BUY_QUANTITY, 1),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 30, 40, stack(SubstanceCraftItems.P2P, 1), MAX_CHEMICAL_BUY_QUANTITY, 1),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 33, 45, stack(SubstanceCraftItems.P2NP, 1), MAX_CHEMICAL_BUY_QUANTITY, 1),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 4, 7, stack(SubstanceCraftItems.CARBON_DIOXIDE, 1), MAX_CHEMICAL_BUY_QUANTITY, 1),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 4, 8, stack(SubstanceCraftItems.SULFUR, 1), MAX_CHEMICAL_BUY_QUANTITY, 1),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 13, 21, stack(SubstanceCraftItems.SULFURIC_ACID, 1), MAX_CHEMICAL_BUY_QUANTITY, 1),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 14, 21, stack(SubstanceCraftItems.AMMONIA_SOLUTION, 1), MAX_CHEMICAL_BUY_QUANTITY, 1),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 3, 7, stack(SubstanceCraftItems.SODIUM_CARBONATE, 1), MAX_CHEMICAL_BUY_QUANTITY, 1),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 5, 9, stack(SubstanceCraftItems.SODIUM_CARBONATE_SOLUTION, 1), MAX_CHEMICAL_BUY_QUANTITY, 1),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 30, 41, stack(SubstanceCraftItems.AGUA_RICA, 1), MAX_CHEMICAL_BUY_QUANTITY, 1),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 3, 6, stack(SubstanceCraftItems.POTASSIUM_CHLORIDE, 1), MAX_CHEMICAL_BUY_QUANTITY, 1),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 5, 9, stack(SubstanceCraftItems.POTASSIUM_HYDROXIDE, 1), MAX_CHEMICAL_BUY_QUANTITY, 1),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 12, 16, stack(SubstanceCraftItems.POTASSIUM_CARBONATE, 1), MAX_CHEMICAL_BUY_QUANTITY, 1),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 3, 7, stack(SubstanceCraftItems.MANGANESE_DIOXIDE, 1), MAX_CHEMICAL_BUY_QUANTITY, 1),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 18, 25, stack(SubstanceCraftItems.POTASSIUM_PERMANGANATE, 1), MAX_CHEMICAL_BUY_QUANTITY, 1),
                    new ItemForItemOffer(SubstanceCraftItems.BAND, 6, 7, stack(SubstanceCraftItems.COCA_PASTE, 1), MAX_CHEMICAL_BUY_QUANTITY, 1),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 9, 15, stack(SubstanceCraftItems.ACETONE, 1), MAX_CHEMICAL_BUY_QUANTITY, 1),
            }, 6)).build();

    private static ItemStack stack(Item item, int count) {
        return new ItemStack(item, count);
    }

}
