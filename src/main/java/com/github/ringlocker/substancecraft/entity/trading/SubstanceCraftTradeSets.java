package com.github.ringlocker.substancecraft.entity.trading;

import com.github.ringlocker.substancecraft.SubstanceCraft;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.trading.TradeSet;
import net.minecraft.world.item.trading.VillagerTrade;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;

import java.util.Optional;

public class SubstanceCraftTradeSets {

    public static final ResourceKey<TradeSet> BUY_DRUG = resourceKey("buy_drug");
    public static final ResourceKey<TradeSet> BUY_UNOBTAINABLE_DRUG = resourceKey("buy_unobtainable_drug");
    public static final ResourceKey<TradeSet> BUY_RARE_DRUG = resourceKey("buy_rare_drug");


    public static final ResourceKey<TradeSet> SELL_DRUG = resourceKey("sell_drug");
    public static final ResourceKey<TradeSet> SELL_UNOBTAINABLE_DRUG = resourceKey("sell_unobtainable_drug");
    public static final ResourceKey<TradeSet> SELL_RARE_DRUG = resourceKey("sell_rare_drug");

    public static final ResourceKey<TradeSet> SELL_CROP = resourceKey("sell_crop");
    public static final ResourceKey<TradeSet> SELL_RARE_CROP = resourceKey("sell_rare_crop");
    public static final ResourceKey<TradeSet> BUY_HARVEST = resourceKey("buy_harvest");
    public static final ResourceKey<TradeSet> BUY_RARE_HARVEST = resourceKey("buy_rare_harvest");

    public static final ResourceKey<TradeSet> EXOTIC_FARMER_1 = resourceKey("exotic_farmer_1");
    public static final ResourceKey<TradeSet> EXOTIC_FARMER_2 = resourceKey("exotic_farmer_2");
    public static final ResourceKey<TradeSet> EXOTIC_FARMER_3 = resourceKey("exotic_farmer_3");

    public static final ResourceKey<TradeSet> DEALER_1 = resourceKey("dealer_1");
    public static final ResourceKey<TradeSet> DEALER_2 = resourceKey("dealer_2");
    public static final ResourceKey<TradeSet> DEALER_3 = resourceKey("dealer_3");


    private static final float BUY_DRUG_OFFERS = 2.0f;
    private static final float BUY_UNOBTAINABLE_DRUG_OFFERS = 1.0f;
    private static final float BUY_RARE_DRUG_OFFERS = 1.0f;

    private static final float SELL_DRUG_OFFERS = 2.0f;
    private static final float SELL_UNOBTAINABLE_DRUG_OFFERS = 1.0f;
    private static final float SELL_RARE_DRUG_OFFERS = 1.0f;

    private static final float BUY_HARVEST_OFFERS = 1.0f;
    private static final float BUY_RARE_HARVEST_OFFERS = 1.0f;
    private static final float SELL_CROP_OFFERS = 1.0f;
    private static final float SELL_RARE_CROP_OFFERS = 1.0f;

    private static final float EXOTIC_FARMER_1_OFFERS = 2.0f;
    private static final float EXOTIC_FARMER_2_OFFERS = 2.0f;
    private static final float EXOTIC_FARMER_3_OFFERS = 1.0f;
   
    private static final float DEALER_FARMER_1_OFFERS = 2.0f;
    private static final float DEALER_FARMER_2_OFFERS = 2.0f;
    private static final float DEALER_FARMER_3_OFFERS = 2.0f;

    public static void bootstrap(BootstrapContext<TradeSet> context) {
        register(context, BUY_DRUG, SubstanceCraftTradeTags.BUY_DRUG, ConstantValue.exactly(BUY_DRUG_OFFERS));
        register(context, BUY_UNOBTAINABLE_DRUG, SubstanceCraftTradeTags.BUY_UNOBTAINABLE_DRUG, ConstantValue.exactly(BUY_UNOBTAINABLE_DRUG_OFFERS));
        register(context, BUY_RARE_DRUG, SubstanceCraftTradeTags.BUY_RARE_DRUG, ConstantValue.exactly(BUY_RARE_DRUG_OFFERS));

        register(context, SELL_DRUG, SubstanceCraftTradeTags.SELL_DRUG, ConstantValue.exactly(SELL_DRUG_OFFERS));
        register(context, SELL_UNOBTAINABLE_DRUG, SubstanceCraftTradeTags.SELL_UNOBTAINABLE_DRUG, ConstantValue.exactly(SELL_UNOBTAINABLE_DRUG_OFFERS));
        register(context, SELL_RARE_DRUG, SubstanceCraftTradeTags.SELL_RARE_DRUG, ConstantValue.exactly(SELL_RARE_DRUG_OFFERS));

        register(context, BUY_HARVEST, SubstanceCraftTradeTags.BUY_HARVEST, ConstantValue.exactly(BUY_HARVEST_OFFERS));
        register(context, BUY_RARE_HARVEST, SubstanceCraftTradeTags.BUY_RARE_HARVEST, ConstantValue.exactly(BUY_RARE_HARVEST_OFFERS));
        register(context, SELL_CROP, SubstanceCraftTradeTags.SELL_CROP, ConstantValue.exactly(SELL_CROP_OFFERS));
        register(context, SELL_RARE_CROP, SubstanceCraftTradeTags.SELL_RARE_CROP, ConstantValue.exactly(SELL_RARE_CROP_OFFERS));

        register(context, EXOTIC_FARMER_1, SubstanceCraftTradeTags.EXOTIC_FARMER_1, ConstantValue.exactly(EXOTIC_FARMER_1_OFFERS));
        register(context, EXOTIC_FARMER_2, SubstanceCraftTradeTags.EXOTIC_FARMER_2, ConstantValue.exactly(EXOTIC_FARMER_2_OFFERS));
        register(context, EXOTIC_FARMER_3, SubstanceCraftTradeTags.EXOTIC_FARMER_3, ConstantValue.exactly(EXOTIC_FARMER_3_OFFERS));
        
        register(context, DEALER_1, SubstanceCraftTradeTags.DEALER_1, ConstantValue.exactly(DEALER_FARMER_1_OFFERS));
        register(context, DEALER_2, SubstanceCraftTradeTags.DEALER_2, ConstantValue.exactly(DEALER_FARMER_2_OFFERS));
        register(context, DEALER_3, SubstanceCraftTradeTags.DEALER_3, ConstantValue.exactly(DEALER_FARMER_3_OFFERS));
    }

    public static ResourceKey<TradeSet> resourceKey(String path) {
        return ResourceKey.create(Registries.TRADE_SET, Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID, path));
    }

    public static Holder.Reference<TradeSet> register(BootstrapContext<TradeSet> context, ResourceKey<TradeSet> resourceKey, TagKey<VillagerTrade> tradeTag, NumberProvider numberProvider) {
        return context.register(
                resourceKey,
                new TradeSet(context.lookup(Registries.VILLAGER_TRADE).getOrThrow(tradeTag), numberProvider, false, Optional.of(resourceKey.identifier().withPrefix("trade_set/")))
        );
    }



}



