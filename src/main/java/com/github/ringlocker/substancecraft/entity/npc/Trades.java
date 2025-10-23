package com.github.ringlocker.substancecraft.entity.npc;

import com.github.ringlocker.substancecraft.block.SubstanceCraftBlocks;
import com.github.ringlocker.substancecraft.item.SubstanceCraftItems;
import com.google.common.collect.ImmutableList;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.apache.commons.lang3.tuple.Pair;

public class Trades {

    public static final ImmutableList<Pair<VillagerTrades.ItemListing[], Integer>> DEALER_TRADES = ImmutableList.<Pair<VillagerTrades.ItemListing[], Integer>>builder().add(
            Pair.of(new VillagerTrades.ItemListing[]{
                    new ItemForItemOffer(SubstanceCraftItems.TWO_C_B, 1, stack(SubstanceCraftItems.CASH, 2), 32, 2),
                    new ItemForItemOffer(SubstanceCraftItems.KETAMINE, 1, stack(SubstanceCraftItems.CASH, 3), 32, 2),
                    new ItemForItemOffer(SubstanceCraftItems.HASH, 1, stack(SubstanceCraftItems.CASH, 1), 32, 2),
                    new ItemForItemOffer(SubstanceCraftItems.MARIJUANA, 1, stack(SubstanceCraftItems.CASH, 1), 32, 2),
                    new ItemForItemOffer(SubstanceCraftItems.DIPHENHYDRAMINE, 1, stack(SubstanceCraftItems.CASH, 1), 32, 2),
                    new ItemForItemOffer(SubstanceCraftItems.COCAINE, 1, stack(SubstanceCraftItems.CASH, 2), 32, 2),
                    new ItemForItemOffer(SubstanceCraftItems.AMPHETAMINE, 1, stack(SubstanceCraftItems.CASH, 2), 32, 2),
            }, 4)).add(
            Pair.of(new VillagerTrades.ItemListing[]{
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 3, stack(SubstanceCraftItems.TWO_C_B, 1), 64, 2),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 4, stack(SubstanceCraftItems.KETAMINE, 1), 64, 2),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 2, stack(SubstanceCraftItems.HASH, 1), 64, 2),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 2, stack(SubstanceCraftItems.MARIJUANA, 1), 64, 2),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 2, stack(SubstanceCraftItems.DIPHENHYDRAMINE, 1), 64, 2),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 3, stack(SubstanceCraftItems.COCAINE, 1), 64, 2),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 3, stack(SubstanceCraftItems.AMPHETAMINE, 1), 64, 2),
    }, 2)).add(
            Pair.of(new VillagerTrades.ItemListing[]{
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 5, stack(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.MARIJUANA_PLANT), 1), 16, 2),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 4, stack(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.CORN_CROP), 1), 16, 2),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 6, stack(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.COCA_CROP), 1), 16, 2),
    }, 1)).add(
            Pair.of(new VillagerTrades.ItemListing[]{
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 1, 3, stack(SubstanceCraftItems.DISTILLED_WATER, 1), 64, 2),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 1, 3, stack(SubstanceCraftItems.OIL, 1), 64, 2),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 1, 3, stack(SubstanceCraftItems.DIESEL, 1), 64, 2),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 1, 3, stack(SubstanceCraftItems.GASOLINE, 1), 64, 2),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 1, 3, stack(SubstanceCraftItems.KEROSENE, 1), 64, 2),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 1, 3, stack(SubstanceCraftItems.PETROLEUM_NAPHTHA, 1), 64, 2),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 1, 3, stack(SubstanceCraftItems.ETHANOL, 1), 64, 2),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 1, 3, stack(SubstanceCraftItems.METHANOL, 1), 64, 2),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 1, 3, stack(SubstanceCraftItems.CHLOROFORM, 1), 64, 2),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 1, 3, stack(SubstanceCraftItems.TOLUENE, 1), 64, 2),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 1, 3, stack(SubstanceCraftItems.BENZENE, 1), 64, 2),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 1, 3, stack(SubstanceCraftItems.BRINE, 1), 64, 2),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 1, 3, stack(SubstanceCraftItems.CHLORINE, 1), 64, 2),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 1, 3, stack(SubstanceCraftItems.HYDROGEN, 1), 64, 2),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 1, 3, stack(SubstanceCraftItems.METHANE, 1), 64, 2),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 1, 3, stack(SubstanceCraftItems.NITROGEN, 1), 64, 2),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 1, 3, stack(SubstanceCraftItems.OXYGEN, 1), 64, 2),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 1, 3, stack(SubstanceCraftItems.NATURAL_GAS, 1), 64, 2),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 1, 3, stack(SubstanceCraftItems.PROPANE, 1), 64, 2),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 1, 3, stack(SubstanceCraftItems.ETHANE, 1), 64, 2),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 1, 3, stack(SubstanceCraftItems.BUTANE, 1), 64, 2),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 2, 4, stack(SubstanceCraftItems.METHYLAMINE, 1), 64, 2),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 2, 4, stack(SubstanceCraftItems.PROPYLENE, 1), 64, 2),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 2, 4, stack(SubstanceCraftItems.ETHYLENE, 1), 64, 2),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 2, 4, stack(SubstanceCraftItems.FORMALDEHYDE, 1), 64, 2),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 2, 4, stack(SubstanceCraftItems.AMMONIA, 1), 64, 2),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 2, 4, stack(SubstanceCraftItems.BENZALDEHYDE, 1), 64, 2),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 2, 4, stack(SubstanceCraftItems.HYDROCHLORIC_ACID, 1), 64, 2),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 2, 4, stack(SubstanceCraftItems.AMMONIUM_ACETATE, 1), 64, 2),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 2, 4, stack(SubstanceCraftItems.MALEIC_ANHYDRIDE, 1), 64, 2),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 1, 3, stack(SubstanceCraftItems.SALT, 1), 64, 2),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 2, 4, stack(SubstanceCraftItems.ACETIC_ACID, 1), 64, 2),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 2, 4, stack(SubstanceCraftItems.NITRIC_ACID, 1), 64, 2),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 2, 4, stack(SubstanceCraftItems.NITROMETHANE, 1), 64, 2),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 2, 4, stack(SubstanceCraftItems.NITROETHANE, 1), 64, 2),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 2, 4, stack(SubstanceCraftItems.SODIUM_HYDROXIDE, 1), 64, 2),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 1, 3, stack(SubstanceCraftItems.BROMIDE, 1), 64, 2),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 1, 3, stack(SubstanceCraftItems.BROMINE, 1), 64, 2),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 1, 4, stack(SubstanceCraftItems.CARBON_MONOXIDE, 1), 64, 2),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 2, 4, stack(SubstanceCraftItems.METHYL_FORMATE, 1), 64, 2),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 2, 4, stack(SubstanceCraftItems.FORMIC_ACID, 1), 64, 2),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 1, 3, stack(SubstanceCraftItems.COKE, 1), 64, 2),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 2, 4, stack(SubstanceCraftItems.TETRAHYDROFURAN, 1), 64, 2),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 3, 5, stack(SubstanceCraftItems.BETA_NITROSTYRENE, 1), 64, 2),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 3, 5, stack(SubstanceCraftItems.P2P, 1), 64, 2),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 3, 5, stack(SubstanceCraftItems.P2NP, 1), 64, 2),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 1, 3, stack(SubstanceCraftItems.CARBON_DIOXIDE, 1), 64, 2),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 1, 3, stack(SubstanceCraftItems.SULFUR, 1), 64, 2),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 2, 3, stack(SubstanceCraftItems.SULFURIC_ACID, 1), 64, 2),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 2, 4, stack(SubstanceCraftItems.AMMONIA_SOLUTION, 1), 64, 2),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 1, 3, stack(SubstanceCraftItems.SODIUM_CARBONATE, 1), 64, 2),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 1, 3, stack(SubstanceCraftItems.SODIUM_CARBONATE_SOLUTION, 1), 64, 2),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 2, 4, stack(SubstanceCraftItems.AGUA_RICA, 1), 64, 2),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 1, 3, stack(SubstanceCraftItems.POTASSIUM_CHLORIDE, 1), 64, 2),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 1, 3, stack(SubstanceCraftItems.POTASSIUM_HYDROXIDE, 1), 64, 2),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 2, 4, stack(SubstanceCraftItems.POTASSIUM_CARBONATE, 1), 64, 2),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 2, 4, stack(SubstanceCraftItems.MANGANESE_DIOXIDE, 1), 64, 2),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 3, 4, stack(SubstanceCraftItems.POTASSIUM_PERMANGANATE, 1), 64, 2),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 3, 5, stack(SubstanceCraftItems.COCA_PASTE, 1), 64, 2),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 2, 4, stack(SubstanceCraftItems.ACETONE, 1), 64, 2),
    }, 6)).build();

    private static ItemStack stack(Item item, int count) {
        return new ItemStack(item, count);
    }

}
