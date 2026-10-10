package com.github.ringlocker.substancecraft.entity.trading;

import com.github.ringlocker.substancecraft.SubstanceCraft;
import com.github.ringlocker.substancecraft.block.SubstanceCraftBlocks;
import com.github.ringlocker.substancecraft.item.SubstanceCraftItems;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.trading.TradeCost;
import net.minecraft.world.item.trading.VillagerTrade;
import net.minecraft.world.level.storage.loot.providers.number.floats.ContextFloatProviders;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;

public class SubstanceCraftTrades {

    private static final int MAX_DRUG_BUY_QUANTITY = 12;
    private static final int MAX_DRUG_SELL_QUANTITY = 16;
    private static final int MAX_PLANT_BUY_QUANTITY = 8;
    private static final int MAX_PLANT_SELL_QUANTITY = 16;

    private static final int BASE_DRUG_TRADE_XP = 3;
    private static final int UNOBTAINABLE_DRUG_TRADE_XP_MULTIPLIER = 2;
    private static final int RARE_DRUG_TRADE_XP_MULTIPLIER = 3;

    private static final int BASE_PLANT_TRADE_XP = 2;
    private static final int RARE_PLANT_TRADE_XP_MULTIPLIER = 2;

    private static final int SPECIALITY_HARVEST_SELL_QUANTITY = 8;
    private static final int HARVEST_SELL_QUANTITY = 16;
    private static final int BULK_HARVEST_SELL_QUANTITY = 32;

    public static final ResourceKey<VillagerTrade> BUY_MARIJUANA = resourceKey("buy_marijuana");
    public static final ResourceKey<VillagerTrade> BUY_HASH = resourceKey("buy_hash");
    public static final ResourceKey<VillagerTrade> BUY_RESIN = resourceKey("buy_resin");
    public static final ResourceKey<VillagerTrade> BUY_ROSIN = resourceKey("buy_rosin");
    public static final ResourceKey<VillagerTrade> BUY_2CB = resourceKey("buy_2cb");
    public static final ResourceKey<VillagerTrade> BUY_AMPHETAMINE = resourceKey("buy_amphetamine");
    public static final ResourceKey<VillagerTrade> BUY_COCAINE = resourceKey("buy_cocaine");
    public static final ResourceKey<VillagerTrade> BUY_LSD = resourceKey("buy_lsd");
    public static final ResourceKey<VillagerTrade> BUY_PSILOCYBIN = resourceKey("buy_psilocybin");
    public static final ResourceKey<VillagerTrade> BUY_MESCALINE = resourceKey("buy_mescaline");
    public static final ResourceKey<VillagerTrade> BUY_RED_WINE = resourceKey("buy_red_wine");
    public static final ResourceKey<VillagerTrade> BUY_DMT = resourceKey("buy_dmt");

    public static final ResourceKey<VillagerTrade> BUY_DPH = resourceKey("buy_dph");
    public static final ResourceKey<VillagerTrade> BUY_KETAMINE = resourceKey("buy_ketamine");
    public static final ResourceKey<VillagerTrade> BUY_5_MEO_DMT = resourceKey("buy_5_meo_dmt");

    public static final ResourceKey<VillagerTrade> BUY_PALE_PSILOCYBIN = resourceKey("buy_pale_psilocybin");

    public static final ResourceKey<VillagerTrade> SELL_MARIJUANA = resourceKey("sell_marijuana");
    public static final ResourceKey<VillagerTrade> SELL_HASH = resourceKey("sell_hash");
    public static final ResourceKey<VillagerTrade> SELL_RESIN = resourceKey("sell_resin");
    public static final ResourceKey<VillagerTrade> SELL_ROSIN = resourceKey("sell_rosin");
    public static final ResourceKey<VillagerTrade> SELL_2CB = resourceKey("sell_2cb");
    public static final ResourceKey<VillagerTrade> SELL_AMPHETAMINE = resourceKey("sell_amphetamine");
    public static final ResourceKey<VillagerTrade> SELL_COCAINE = resourceKey("sell_cocaine");
    public static final ResourceKey<VillagerTrade> SELL_LSD = resourceKey("sell_lsd");
    public static final ResourceKey<VillagerTrade> SELL_PSILOCYBIN = resourceKey("sell_psilocybin");
    public static final ResourceKey<VillagerTrade> SELL_MESCALINE = resourceKey("sell_mescaline");
    public static final ResourceKey<VillagerTrade> SELL_RED_WINE = resourceKey("sell_red_wine");
    public static final ResourceKey<VillagerTrade> SELL_DMT = resourceKey("sell_dmt");

    public static final ResourceKey<VillagerTrade> SELL_DPH = resourceKey("sell_dph");
    public static final ResourceKey<VillagerTrade> SELL_KETAMINE = resourceKey("sell_ketamine");
    public static final ResourceKey<VillagerTrade> SELL_5_MEO_DMT = resourceKey("sell_5_meo_dmt");

    public static final ResourceKey<VillagerTrade> SELL_PALE_PSILOCYBIN = resourceKey("sell_pale_psilocybin");


    public static final ResourceKey<VillagerTrade> BUY_CORN = resourceKey("buy_corn");
    public static final ResourceKey<VillagerTrade> BUY_GRAPEVINE = resourceKey("buy_grapevine");
    public static final ResourceKey<VillagerTrade> BUY_PEYOTE_CACTUS = resourceKey("buy_peyote_cactus");

    public static final ResourceKey<VillagerTrade> BUY_MARIJUANA_PLANT = resourceKey("buy_marijuana_plant");
    public static final ResourceKey<VillagerTrade> BUY_COCA_PLANT = resourceKey("buy_coca_plant");

    public static final ResourceKey<VillagerTrade> SELL_CORN = resourceKey("sell_corn");
    public static final ResourceKey<VillagerTrade> SELL_GRAPEVINE = resourceKey("sell_grapevine");
    public static final ResourceKey<VillagerTrade> SELL_PEYOTE_CACTUS = resourceKey("sell_peyote_cactus");

    public static final ResourceKey<VillagerTrade> SELL_MARIJUANA_PLANT = resourceKey("sell_marijuana_plant");
    public static final ResourceKey<VillagerTrade> SELL_MARIJUANA_TRIM = resourceKey("sell_marijuana_trim");
    public static final ResourceKey<VillagerTrade> SELL_COCA_PLANT = resourceKey("sell_coca_plant");


    public static ResourceKey<VillagerTrade> resourceKey(String path) {
        return ResourceKey.create(Registries.VILLAGER_TRADE, Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID, path));
    }

    public static void bootstrap(BootstrapContext<VillagerTrade> context) {

        registerBuyAndSellDrug(context, BUY_MARIJUANA, SELL_MARIJUANA,
                SubstanceCraftItems.MARIJUANA, SubstanceCraftItems.CASH, 4, 6);

        registerBuyAndSellDrug(context, BUY_HASH, SELL_HASH,
                SubstanceCraftItems.HASH, SubstanceCraftItems.CASH, 6, 9);

        registerBuyAndSellDrug(context, BUY_RESIN, SELL_RESIN,
                SubstanceCraftItems.LIVE_RESIN, SubstanceCraftItems.CASH, 11, 15);

        registerBuyAndSellDrug(context, BUY_ROSIN, SELL_ROSIN,
                SubstanceCraftItems.ROSIN, SubstanceCraftItems.CASH, 12, 20);

        registerBuyAndSellDrug(context, BUY_2CB, SELL_2CB,
                SubstanceCraftItems.TWO_C_B, SubstanceCraftItems.CASH, 32, 48);

        registerBuyAndSellDrug(context, BUY_AMPHETAMINE, SELL_AMPHETAMINE,
                SubstanceCraftItems.AMPHETAMINE, SubstanceCraftItems.BAND, 5, 8);

        registerBuyAndSellDrug(context, BUY_COCAINE, SELL_COCAINE,
                SubstanceCraftItems.COCAINE, SubstanceCraftItems.BAND, 7, 10);

        registerBuyAndSellDrug(context, BUY_LSD, SELL_LSD,
                SubstanceCraftItems.LYSERGIC_ACID_DIETHYLAMINE_TAB, SubstanceCraftItems.BAND, 10, 12);

        registerBuyAndSellDrug(context, BUY_PSILOCYBIN, SELL_PSILOCYBIN,
                SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.PSILOCYBIN), SubstanceCraftItems.CASH, 12, 18);

        registerBuyAndSellDrug(context, BUY_MESCALINE, SELL_MESCALINE,
                SubstanceCraftItems.MESCALINE, SubstanceCraftItems.BAND, 3, 6);

        registerBuyAndSellDrug(context, BUY_RED_WINE, SELL_RED_WINE,
                SubstanceCraftItems.RED_WINE, SubstanceCraftItems.CASH, 18, 24);

        registerBuyAndSellDrug(context, BUY_DMT, SELL_DMT,
                SubstanceCraftItems.RED_WINE, SubstanceCraftItems.CASH, 18, 24);


        registerBuyAndSellDrug(context, BUY_DPH, SELL_DPH,
                SubstanceCraftItems.DIPHENHYDRAMINE, SubstanceCraftItems.CASH, 12, 16, UNOBTAINABLE_DRUG_TRADE_XP_MULTIPLIER);

        registerBuyAndSellDrug(context, BUY_KETAMINE, SELL_KETAMINE,
                SubstanceCraftItems.KETAMINE, SubstanceCraftItems.BAND, 3, 5, UNOBTAINABLE_DRUG_TRADE_XP_MULTIPLIER);

        registerBuyAndSellDrug(context, BUY_5_MEO_DMT, SELL_5_MEO_DMT,
                SubstanceCraftItems.FIVE_METHOXY_N_N_DIMETHYLTRYPTAMINE, SubstanceCraftItems.BAND, 4, 6, UNOBTAINABLE_DRUG_TRADE_XP_MULTIPLIER);


        registerBuyAndSellRareDrug(context, BUY_PALE_PSILOCYBIN, SELL_PALE_PSILOCYBIN,
                SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.PALE_PSILOCYBIN),
                SubstanceCraftItems.CASH, SubstanceCraftItems.BAND, 48, 64, 24);


        registerBuyAndSellPlant(context, BUY_CORN, SELL_CORN,
                SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.CORN_CROP), SubstanceCraftItems.CORN,
                SubstanceCraftItems.CASH, HARVEST_SELL_QUANTITY, 12);

        registerBuyAndSellPlant(context, BUY_GRAPEVINE, SELL_GRAPEVINE,
                SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.GRAPEVINE), SubstanceCraftItems.GRAPES,
                SubstanceCraftItems.CASH, BULK_HARVEST_SELL_QUANTITY, 16);


        registerBuyAndSellPlant(context, BUY_PEYOTE_CACTUS, SELL_PEYOTE_CACTUS,
                SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.PEYOTE_CACTUS), SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.PEYOTE_CACTUS),
                SubstanceCraftItems.CASH, SPECIALITY_HARVEST_SELL_QUANTITY, 32, RARE_DRUG_TRADE_XP_MULTIPLIER);

        registerBuyAndSellPlant(context, BUY_MARIJUANA_PLANT, SELL_MARIJUANA_PLANT,
                SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.MARIJUANA_PLANT), SubstanceCraftItems.MARIJUANA,
                SubstanceCraftItems.CASH, HARVEST_SELL_QUANTITY, 32, RARE_PLANT_TRADE_XP_MULTIPLIER);


        register(context, SELL_MARIJUANA_TRIM, new VillagerTrade.Builder(
                new TradeCost(SubstanceCraftItems.MARIJUANA_TRIM, ContextIntProviders.exactly(SPECIALITY_HARVEST_SELL_QUANTITY)),
                new ItemStackTemplate(SubstanceCraftItems.CASH, 16),
                ContextIntProviders.exactly(MAX_PLANT_SELL_QUANTITY),
                ContextIntProviders.exactly(BASE_DRUG_TRADE_XP),
                ContextFloatProviders.exactly(0.05F)
        ).build());

        registerBuyAndSellPlant(context, BUY_COCA_PLANT, SELL_COCA_PLANT,
                SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.COCA_CROP), SubstanceCraftItems.COCA_LEAVES,
                SubstanceCraftItems.CASH, HARVEST_SELL_QUANTITY, 24, RARE_DRUG_TRADE_XP_MULTIPLIER);

    }

    private static void registerBuyAndSellDrug(
            BootstrapContext<VillagerTrade> context,
            ResourceKey<VillagerTrade> buyKey,
            ResourceKey<VillagerTrade> sellKey,
            Item drug, Item cashItem,
            int minCost, int maxCost) {

        registerBuyAndSellDrug(context, buyKey, sellKey, drug, cashItem, minCost, maxCost, 1);

    }

    private static void registerBuyAndSellDrug(
            BootstrapContext<VillagerTrade> context,
            ResourceKey<VillagerTrade> buyKey,
            ResourceKey<VillagerTrade> sellKey,
            Item drug, Item cashItem,
            int minCost, int maxCost, int xpMultiplier) {

        register(context, buyKey, new VillagerTrade.Builder(
                new TradeCost(cashItem, ContextIntProviders.between(minCost, maxCost)),
                new ItemStackTemplate(drug),
                        ContextIntProviders.exactly(MAX_DRUG_BUY_QUANTITY),
                        ContextIntProviders.exactly(BASE_DRUG_TRADE_XP * xpMultiplier),
                        ContextFloatProviders.exactly(0.05F)).build()
        );
        register(context, sellKey, new VillagerTrade.Builder(
                new TradeCost(drug, ContextIntProviders.exactly(1)),
                new ItemStackTemplate(cashItem, (int) minCost),
                        ContextIntProviders.exactly(MAX_DRUG_SELL_QUANTITY),
                        ContextIntProviders.exactly(BASE_DRUG_TRADE_XP * xpMultiplier),
                        ContextFloatProviders.exactly(0.05F)).build()
        );

    }

    private static void registerBuyAndSellRareDrug(
            BootstrapContext<VillagerTrade> context,
            ResourceKey<VillagerTrade> buyKey,
            ResourceKey<VillagerTrade> sellKey,
            Item drug, Item sellCashItem, Item buyCashItem,
            int minBuyCost, int maxBuyCost, float sellPrice) {

        register(context, sellKey, new VillagerTrade.Builder(
                new TradeCost(buyCashItem, ContextIntProviders.between(minBuyCost, maxBuyCost)),
                new ItemStackTemplate(drug),
                        ContextIntProviders.exactly(MAX_DRUG_BUY_QUANTITY),
                        ContextIntProviders.exactly(BASE_DRUG_TRADE_XP * RARE_DRUG_TRADE_XP_MULTIPLIER),
                        ContextFloatProviders.exactly(0.05F)).build()
        );
        register(context, buyKey, new VillagerTrade.Builder(
                new TradeCost(drug, 1),
                new ItemStackTemplate(sellCashItem, (int) sellPrice),
                        ContextIntProviders.exactly(MAX_DRUG_SELL_QUANTITY),
                        ContextIntProviders.exactly(BASE_DRUG_TRADE_XP * RARE_DRUG_TRADE_XP_MULTIPLIER),
                        ContextFloatProviders.exactly(0.05F)).build()
        );

    }

    private static void registerBuyAndSellPlant(
            BootstrapContext<VillagerTrade> context,
            ResourceKey<VillagerTrade> buyKey,
            ResourceKey<VillagerTrade> sellKey,
            Item plant, Item harvestItem, Item cashItem,
            int amountOfHarvestToSell, float sellCost) {

        registerBuyAndSellPlant(context, buyKey, sellKey, plant, harvestItem, cashItem, amountOfHarvestToSell, sellCost, 1);

    }

    private static void registerBuyAndSellPlant(
            BootstrapContext<VillagerTrade> context,
            ResourceKey<VillagerTrade> buyKey,
            ResourceKey<VillagerTrade> sellKey,
            Item plant, Item harvestItem, Item cashItem,
            int amountOfHarvestToSell, float sellCost, int xpMultiplier) {

        int buyPrice = (2 * xpMultiplier * (int) sellCost);
        Item buyCashItem = cashItem;
        if (buyPrice > 0 && cashItem == SubstanceCraftItems.CASH) {
            buyCashItem = SubstanceCraftItems.BAND;
            buyPrice = buyPrice / 9;
        }

        register(context, buyKey, new VillagerTrade.Builder(
                new TradeCost(harvestItem, amountOfHarvestToSell),
                new ItemStackTemplate(cashItem, (int) sellCost),
                ContextIntProviders.exactly(MAX_PLANT_SELL_QUANTITY),
                ContextIntProviders.exactly(BASE_PLANT_TRADE_XP * xpMultiplier),
                ContextFloatProviders.exactly(0.05F)).build()
        );

        register(context, sellKey, new VillagerTrade.Builder(
                new TradeCost(buyCashItem, ContextIntProviders.exactly(Math.clamp(buyPrice, -1, 64))),
                new ItemStackTemplate(plant),
                ContextIntProviders.exactly(MAX_PLANT_BUY_QUANTITY),
                ContextIntProviders.exactly(BASE_PLANT_TRADE_XP * xpMultiplier),
                ContextFloatProviders.exactly(0.05F)).build()
        );

    }

    public static Holder.Reference<VillagerTrade> register(BootstrapContext<VillagerTrade> context, ResourceKey<VillagerTrade> resourceKey, VillagerTrade villagerTrade) {
        return context.register(resourceKey, villagerTrade);
    }

}
