package com.github.ringlocker.substancecraft.entity.npc;

import com.github.ringlocker.substancecraft.SubstanceCraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.trading.VillagerTrade;

public class SubstanceCraftTradeTags {

    public static final TagKey<VillagerTrade> DEALER_BUY_DRUG = create("dealer/buy_drug");
    public static final TagKey<VillagerTrade> DEALER_BUY_UNOBTAINABLE_DRUG = create("dealer/buy_unobtainable_drug");
    public static final TagKey<VillagerTrade> DEALER_SELL_DRUG = create("dealer/sell_drug");
    public static final TagKey<VillagerTrade> DEALER_SELL_UNOBTAINABLE_DRUG = create("dealer/sell_unobtainable_drug");
    public static final TagKey<VillagerTrade> DEALER_SELL_CROP = create("dealer/sell_crop");
    public static final TagKey<VillagerTrade> DEALER_BUY_HARVEST = create("dealer/buy_harvest");

    private static TagKey<VillagerTrade> create(String name) {
        return TagKey.create(Registries.VILLAGER_TRADE, Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID,name));
    }

    public static void registerVillagerTradeTags() {
    }

}
