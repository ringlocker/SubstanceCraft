package com.github.ringlocker.substancecraft.datagen.tag;

import com.github.ringlocker.substancecraft.entity.trading.SubstanceCraftTradeTags;
import com.github.ringlocker.substancecraft.entity.trading.SubstanceCraftTrades;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.trading.VillagerTrade;

import java.util.concurrent.CompletableFuture;

public class VillagerTradeProvider extends FabricTagsProvider<VillagerTrade> {

    public VillagerTradeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, Registries.VILLAGER_TRADE, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        tag(SubstanceCraftTradeTags.BUY_DRUG)
                .add(SubstanceCraftTrades.BUY_MARIJUANA)
                .add(SubstanceCraftTrades.BUY_HASH)
                .add(SubstanceCraftTrades.BUY_RESIN)
                .add(SubstanceCraftTrades.BUY_ROSIN)
                .add(SubstanceCraftTrades.BUY_2CB)
                .add(SubstanceCraftTrades.BUY_AMPHETAMINE)
                .add(SubstanceCraftTrades.BUY_COCAINE)
                .add(SubstanceCraftTrades.BUY_LSD)
                .add(SubstanceCraftTrades.BUY_PSILOCYBIN)
                .add(SubstanceCraftTrades.BUY_MESCALINE)
                .add(SubstanceCraftTrades.BUY_RED_WINE)
                .add(SubstanceCraftTrades.BUY_DMT);

        tag(SubstanceCraftTradeTags.SELL_DRUG)
                .add(SubstanceCraftTrades.SELL_MARIJUANA)
                .add(SubstanceCraftTrades.SELL_HASH)
                .add(SubstanceCraftTrades.SELL_RESIN)
                .add(SubstanceCraftTrades.SELL_ROSIN)
                .add(SubstanceCraftTrades.SELL_2CB)
                .add(SubstanceCraftTrades.SELL_AMPHETAMINE)
                .add(SubstanceCraftTrades.SELL_COCAINE)
                .add(SubstanceCraftTrades.SELL_LSD)
                .add(SubstanceCraftTrades.SELL_PSILOCYBIN)
                .add(SubstanceCraftTrades.SELL_MESCALINE)
                .add(SubstanceCraftTrades.SELL_RED_WINE)
                .add(SubstanceCraftTrades.SELL_DMT);

        tag(SubstanceCraftTradeTags.BUY_UNOBTAINABLE_DRUG)
                .add(SubstanceCraftTrades.BUY_DPH)
                .add(SubstanceCraftTrades.BUY_KETAMINE)
                .add(SubstanceCraftTrades.BUY_5_MEO_DMT);

        tag(SubstanceCraftTradeTags.SELL_UNOBTAINABLE_DRUG)
                .add(SubstanceCraftTrades.SELL_DPH)
                .add(SubstanceCraftTrades.SELL_KETAMINE)
                .add(SubstanceCraftTrades.SELL_5_MEO_DMT);

        tag(SubstanceCraftTradeTags.BUY_RARE_DRUG)
                .add(SubstanceCraftTrades.BUY_PALE_PSILOCYBIN);

        tag(SubstanceCraftTradeTags.SELL_RARE_DRUG)
                .add(SubstanceCraftTrades.SELL_PALE_PSILOCYBIN);
        
        tag(SubstanceCraftTradeTags.BUY_HARVEST)
                .add(SubstanceCraftTrades.BUY_CORN)
                .add(SubstanceCraftTrades.BUY_GRAPEVINE)
                .add(SubstanceCraftTrades.BUY_PEYOTE_CACTUS);
        
        tag(SubstanceCraftTradeTags.BUY_RARE_HARVEST)
                .add(SubstanceCraftTrades.BUY_MARIJUANA_PLANT)
                .add(SubstanceCraftTrades.BUY_COCA_PLANT);
            
        tag(SubstanceCraftTradeTags.SELL_CROP)
                .add(SubstanceCraftTrades.SELL_CORN)
                .add(SubstanceCraftTrades.SELL_GRAPEVINE)
                .add(SubstanceCraftTrades.SELL_PEYOTE_CACTUS);
        
        tag(SubstanceCraftTradeTags.SELL_RARE_CROP)
                .add(SubstanceCraftTrades.SELL_MARIJUANA_PLANT)
                .add(SubstanceCraftTrades.SELL_COCA_PLANT);

        tag(SubstanceCraftTradeTags.DEALER_1)
                .addTag(SubstanceCraftTradeTags.BUY_DRUG)
                .addTag(SubstanceCraftTradeTags.SELL_DRUG);

        tag(SubstanceCraftTradeTags.DEALER_2)
                .addTag(SubstanceCraftTradeTags.BUY_DRUG)
                .addTag(SubstanceCraftTradeTags.SELL_DRUG)
                .addTag(SubstanceCraftTradeTags.BUY_UNOBTAINABLE_DRUG)
                .addTag(SubstanceCraftTradeTags.SELL_UNOBTAINABLE_DRUG);

        tag(SubstanceCraftTradeTags.DEALER_3)
                .addTag(SubstanceCraftTradeTags.BUY_UNOBTAINABLE_DRUG)
                .addTag(SubstanceCraftTradeTags.SELL_UNOBTAINABLE_DRUG)
                .addTag(SubstanceCraftTradeTags.BUY_RARE_DRUG);

        tag(SubstanceCraftTradeTags.EXOTIC_FARMER_1)
                .addTag(SubstanceCraftTradeTags.BUY_HARVEST)
                .addTag(SubstanceCraftTradeTags.SELL_CROP);

        tag(SubstanceCraftTradeTags.EXOTIC_FARMER_2)
                .addTag(SubstanceCraftTradeTags.BUY_HARVEST)
                .addTag(SubstanceCraftTradeTags.BUY_RARE_HARVEST)
                .addTag(SubstanceCraftTradeTags.SELL_CROP);

        tag(SubstanceCraftTradeTags.EXOTIC_FARMER_3)
                .addTag(SubstanceCraftTradeTags.SELL_RARE_CROP);

    }
}
