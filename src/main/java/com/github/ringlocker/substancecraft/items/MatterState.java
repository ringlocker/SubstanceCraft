package com.github.ringlocker.substancecraft.items;

import com.github.ringlocker.substancecraft.SubstanceCraft;
import net.minecraft.resources.ResourceLocation;

public enum MatterState {

    SOLID(resourceLocation("item/solid"), resourceLocation("item/solid_overlay")),
    LIQUID(resourceLocation("item/substance"), resourceLocation("item/substance_overlay")),
    GAS(resourceLocation("item/substance"), resourceLocation("item/substance_overlay"));

    private final ResourceLocation baseTexture;
    private final ResourceLocation overlayTexture;

    MatterState(ResourceLocation baseTexture, ResourceLocation overlayTexture) {
        this.baseTexture = baseTexture;
        this.overlayTexture = overlayTexture;
    }

    public ResourceLocation getBaseTexture() {
        return baseTexture;
    }

    public ResourceLocation getOverlayTexture() {
        return overlayTexture;
    }

    private static ResourceLocation resourceLocation(String name) {
        return ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, name);
    }
}
