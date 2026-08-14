package com.github.ringlocker.substancecraft.entity.npc;

import com.github.ringlocker.substancecraft.SubstanceCraft;
import com.github.ringlocker.substancecraft.item.SubstanceCraftItems;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.trading.TradeCost;
import net.minecraft.world.item.trading.VillagerTrade;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.List;
import java.util.Optional;

public class SubstanceCraftTrades {

    private static final int MAX_DRUG_BUY_QUANTITY = 16;
    private static final int MAX_DRUG_SELL_QUANTITY = 16;
    private static final int MAX_PLANT_BUY_QUANTITY = 4;
    private static final int MAX_PLANT_BULK_SELL_QUANTITY = 16;

    public static final ResourceKey<VillagerTrade> DEALER_BUY_2CB = resourceKey("dealer/buy_2cb");
    public static final ResourceKey<VillagerTrade> DEALER_BUY_HASH = resourceKey("dealer/buy_hash");

    public static ResourceKey<VillagerTrade> resourceKey(String path) {
        return ResourceKey.create(Registries.VILLAGER_TRADE, Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID, path));
    }

    public static void bootstrap(BootstrapContext<VillagerTrade> context) {
        register(context, DEALER_BUY_2CB, new VillagerTrade(
                new TradeCost(SubstanceCraftItems.BAND, UniformGenerator.between(8.0F, 10.0F)),
                new ItemStackTemplate(SubstanceCraftItems.TWO_C_B),
                MAX_DRUG_BUY_QUANTITY,
                2, 0.05F, Optional.empty(), List.of()
        ));
        register(context, DEALER_BUY_HASH, new VillagerTrade(
                new TradeCost(SubstanceCraftItems.CASH, UniformGenerator.between(10.0F, 10.0F)),
                new ItemStackTemplate(SubstanceCraftItems.HASH),
                MAX_DRUG_BUY_QUANTITY,
                2, 0.05F, Optional.empty(), List.of()
        ));
    }

    public static Holder.Reference<VillagerTrade> register(BootstrapContext<VillagerTrade> context, ResourceKey<VillagerTrade> resourceKey, VillagerTrade villagerTrade) {
        return context.register(resourceKey, villagerTrade);
    }


    /*
    public static final ImmutableList<@NotNull Pair<VillagerTrades.ItemListing[], Integer>> DEALER_TRADES = ImmutableList.<Pair<VillagerTrades.ItemListing[], Integer>>builder()
            // Buys 3 unique synthesizable drugs from player
            .add(Pair.of(new VillagerTrades.ItemListing[]{
                    new ItemForItemOffer(SubstanceCraftItems.TWO_C_B, 1, stack(SubstanceCraftItems.BAND, 9), MAX_DRUG_SELL_QUANTITY, 2),
                    new ItemForItemOffer(SubstanceCraftItems.HASH, 1, stack(SubstanceCraftItems.CASH, 8), MAX_DRUG_SELL_QUANTITY, 2),
                    new ItemForItemOffer(SubstanceCraftItems.COCAINE, 1, stack(SubstanceCraftItems.BAND, 9), MAX_DRUG_SELL_QUANTITY, 2),
                    new ItemForItemOffer(SubstanceCraftItems.AMPHETAMINE, 1, stack(SubstanceCraftItems.BAND, 6), MAX_DRUG_SELL_QUANTITY, 2),
                    new ItemForItemOffer(SubstanceCraftItems.LYSERGIC_ACID_DIETHYLAMINE_TAB, 1, stack(SubstanceCraftItems.BAND, 10), MAX_DRUG_SELL_QUANTITY, 2),
                    new ItemForItemOffer(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.PSILOCYBIN), 1, stack(SubstanceCraftItems.BAND, 2), MAX_DRUG_SELL_QUANTITY, 2),
                    new ItemForItemOffer(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.PALE_PSILOCYBIN), 1, stack(SubstanceCraftItems.BAND, 4), MAX_DRUG_SELL_QUANTITY, 4),
                    new ItemForItemOffer(SubstanceCraftItems.MESCALINE, 1, stack(SubstanceCraftItems.BAND, 2), MAX_DRUG_SELL_QUANTITY, 2),
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

            // Sells 2 synthesizable drug
            .add(Pair.of(new VillagerTrades.ItemListing[]{
                    new ItemForItemOffer(SubstanceCraftItems.BAND, 9, 11, stack(SubstanceCraftItems.TWO_C_B, 1), MAX_DRUG_BUY_QUANTITY, 2),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 8, 12, stack(SubstanceCraftItems.HASH, 1), MAX_DRUG_BUY_QUANTITY, 2),
                    new ItemForItemOffer(SubstanceCraftItems.BAND, 9, 10, stack(SubstanceCraftItems.COCAINE, 1), MAX_DRUG_BUY_QUANTITY, 2),
                    new ItemForItemOffer(SubstanceCraftItems.BAND, 6, 7, stack(SubstanceCraftItems.AMPHETAMINE, 1), MAX_DRUG_BUY_QUANTITY, 2),
                    new ItemForItemOffer(SubstanceCraftItems.BAND, 10, 13, stack(SubstanceCraftItems.LYSERGIC_ACID_DIETHYLAMINE_TAB, 2), MAX_DRUG_BUY_QUANTITY, 2),
                    new ItemForItemOffer(SubstanceCraftItems.BAND, 2, 3, stack(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.PSILOCYBIN), 1), MAX_DRUG_BUY_QUANTITY, 2),
                    new ItemForItemOffer(SubstanceCraftItems.BAND, 14, 21, stack(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.PALE_PSILOCYBIN), 1), MAX_DRUG_BUY_QUANTITY, 2),
                    new ItemForItemOffer(SubstanceCraftItems.BAND, 2, 3, stack(SubstanceCraftItems.MESCALINE, 1), MAX_DRUG_BUY_QUANTITY, 2),
            }, 2))

            // Sells 1 crop seed
            .add(Pair.of(new VillagerTrades.ItemListing[]{
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 6, 10, stack(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.MARIJUANA_PLANT), 1), MAX_PLANT_BUY_QUANTITY, 1),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 4, 6, stack(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.CORN_CROP), 1), MAX_PLANT_BUY_QUANTITY, 1),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 8, 12, stack(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.COCA_CROP), 1), MAX_PLANT_BUY_QUANTITY, 1),
                    new ItemForItemOffer(SubstanceCraftItems.CASH, 7, 10, stack(SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.GRAPEVINE), 1), MAX_PLANT_BUY_QUANTITY, 1),
            }, 1))

            // Buys plant harvests
            .add(Pair.of(new VillagerTrades.ItemListing[]{
                    new ItemForItemOffer(SubstanceCraftItems.MARIJUANA, 8, stack(SubstanceCraftItems.BAND, 1), MAX_PLANT_BULK_SELL_QUANTITY, 1),
                    new ItemForItemOffer(SubstanceCraftItems.MARIJUANA_TRIM, 8, stack(SubstanceCraftItems.CASH, 3), MAX_PLANT_BULK_SELL_QUANTITY, 1),
                    new ItemForItemOffer(SubstanceCraftItems.COCA_LEAVES, 8, stack(SubstanceCraftItems.BAND, 1), MAX_PLANT_BULK_SELL_QUANTITY, 1),
                    new ItemForItemOffer(SubstanceCraftItems.CORN, 8, stack(SubstanceCraftItems.BAND, 1), MAX_PLANT_BULK_SELL_QUANTITY, 1),
                    new ItemForItemOffer(SubstanceCraftItems.GRAPES, 8, stack(SubstanceCraftItems.BAND, 1), MAX_PLANT_BULK_SELL_QUANTITY, 1),
            }, 2)).build();

    private static ItemStack stack(Item item, int count) {
        return new ItemStack(item, count);
    }

     */

}
