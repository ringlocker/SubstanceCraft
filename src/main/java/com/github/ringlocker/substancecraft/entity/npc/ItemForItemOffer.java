package com.github.ringlocker.substancecraft.entity.npc;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.villager.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.Nullable;

public class ItemForItemOffer implements VillagerTrades.ItemListing {

    private final static RandomSource random = RandomSource.create();

    private final ItemCost buyFromMerchant;
    private final ItemStack sellingToMerchant;
    private final int maxUses;
    private final int villagerXp;
    private final float priceMultiplier;

    public ItemForItemOffer(ItemLike buyFromMerchant, int quantity, ItemStack sellingToMerchant, int maxUses, int villagerXp) {
        this(new ItemCost(buyFromMerchant.asItem(), quantity), sellingToMerchant, maxUses, villagerXp);
    }

    public ItemForItemOffer(ItemLike buyFromMerchant, int minQuantity, int maxQuantity, ItemStack sellingToMerchant, int maxUses, int villagerXp) {
        this(new ItemCost(buyFromMerchant.asItem(), minQuantity + random.nextInt(maxQuantity - minQuantity + 1)), sellingToMerchant, maxUses, villagerXp);
    }

    public ItemForItemOffer(ItemCost buyFromMerchant, ItemStack sellingToMerchant, int maxUses, int xp) {
        this.buyFromMerchant = buyFromMerchant;
        this.sellingToMerchant = sellingToMerchant;
        this.maxUses = maxUses;
        this.villagerXp = xp;
        this.priceMultiplier = 0.05F;
    }

    @Override
    public @Nullable MerchantOffer getOffer(@NotNull ServerLevel serverLevel, @NotNull Entity entity, @NotNull RandomSource randomSource) {
        return new MerchantOffer(this.buyFromMerchant, sellingToMerchant, this.maxUses, this.villagerXp, this.priceMultiplier);
    }
}
