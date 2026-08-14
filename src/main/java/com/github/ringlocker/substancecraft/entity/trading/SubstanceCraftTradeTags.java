package com.github.ringlocker.substancecraft.entity.trading;

import com.github.ringlocker.substancecraft.SubstanceCraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.trading.VillagerTrade;

public class SubstanceCraftTradeTags {

    public static final TagKey<VillagerTrade> BUY_DRUG = create("buy_drug");
    public static final TagKey<VillagerTrade> BUY_UNOBTAINABLE_DRUG = create("buy_unobtainable_drug");
    public static final TagKey<VillagerTrade> BUY_RARE_DRUG = create("buy_rare_drug");
    
    public static final TagKey<VillagerTrade> SELL_DRUG = create("sell_drug");
    public static final TagKey<VillagerTrade> SELL_UNOBTAINABLE_DRUG = create("sell_unobtainable_drug");
    public static final TagKey<VillagerTrade> SELL_RARE_DRUG = create("sell_rare_drug");
    
    public static final TagKey<VillagerTrade> SELL_CROP = create("sell_crop");
    public static final TagKey<VillagerTrade> SELL_RARE_CROP = create("sell_rare_crop");
    public static final TagKey<VillagerTrade> BUY_HARVEST = create("buy_harvest");
    public static final TagKey<VillagerTrade> BUY_RARE_HARVEST = create("buy_rare_harvest");

    public static final TagKey<VillagerTrade> EXOTIC_FARMER_1 = create("exotic_farmer_1");
    public static final TagKey<VillagerTrade> EXOTIC_FARMER_2 = create("exotic_farmer_2");
    public static final TagKey<VillagerTrade> EXOTIC_FARMER_3 = create("exotic_farmer_3");

    public static final TagKey<VillagerTrade> DEALER_1 = create("dealer_1");
    public static final TagKey<VillagerTrade> DEALER_2 = create("dealer_2");
    public static final TagKey<VillagerTrade> DEALER_3 = create("dealer_3");

    private static TagKey<VillagerTrade> create(String name) {
        return TagKey.create(Registries.VILLAGER_TRADE, Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID,name));
    }

    public static void registerVillagerTradeTags() {

    }

}
