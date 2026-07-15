package com.github.ringlocker.substancecraft.entity.npc;

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

    public static final ResourceKey<TradeSet> DEALER_BUY_DRUG = resourceKey("dealer/buy_drug");
    public static final ResourceKey<TradeSet> DEALER_BUY_UNOBTAINABLE_DRUG = resourceKey("dealer/buy_unobtainable_drug");
    public static final ResourceKey<TradeSet> DEALER_SELL_DRUG = resourceKey("dealer/sell_drug");
    public static final ResourceKey<TradeSet> DEALER_SELL_UNOBTAINABLE_DRUG = resourceKey("dealer/sell_unobtainable_drug");
    public static final ResourceKey<TradeSet> DEALER_SELL_CROP = resourceKey("dealer/sell_crop");
    public static final ResourceKey<TradeSet> DEALER_BUY_HARVEST = resourceKey("dealer/buy_harvest");

    public static void bootstrap(BootstrapContext<TradeSet> context) {
        register(context, DEALER_BUY_DRUG, SubstanceCraftTradeTags.DEALER_BUY_DRUG);
        register(context, DEALER_BUY_UNOBTAINABLE_DRUG, SubstanceCraftTradeTags.DEALER_BUY_UNOBTAINABLE_DRUG);
        register(context, DEALER_SELL_DRUG, SubstanceCraftTradeTags.DEALER_SELL_DRUG);
        register(context, DEALER_SELL_UNOBTAINABLE_DRUG, SubstanceCraftTradeTags.DEALER_SELL_UNOBTAINABLE_DRUG);
        register(context, DEALER_SELL_CROP, SubstanceCraftTradeTags.DEALER_SELL_CROP);
        register(context, DEALER_BUY_HARVEST, SubstanceCraftTradeTags.DEALER_BUY_HARVEST);
    }

    public static ResourceKey<TradeSet> resourceKey(String path) {
        return ResourceKey.create(Registries.TRADE_SET, Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID, path));
    }

    public static Holder.Reference<TradeSet> register(BootstrapContext<TradeSet> context, ResourceKey<TradeSet> resourceKey, TagKey<VillagerTrade> tradeTag) {
        return register(context, resourceKey, tradeTag, ConstantValue.exactly(2.0F));
    }

    public static Holder.Reference<TradeSet> register(BootstrapContext<TradeSet> context, ResourceKey<TradeSet> resourceKey, TagKey<VillagerTrade> tradeTag, NumberProvider numberProvider) {
        return context.register(
                resourceKey,
                new TradeSet(context.lookup(Registries.VILLAGER_TRADE).getOrThrow(tradeTag), numberProvider, false, Optional.of(resourceKey.identifier().withPrefix("trade_set/")))
        );
    }



}



