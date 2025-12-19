package com.github.ringlocker.substancecraft.item;

import com.github.ringlocker.substancecraft.SubstanceCraft;
import net.minecraft.resources.Identifier;

public enum MatterState {

    SOLID(Identifier("item/solid"), Identifier("item/solid_overlay")),
    LIQUID(Identifier("item/liquid"), Identifier("item/liquid_overlay")),
    GAS(Identifier("item/substance"), Identifier("item/substance_overlay"));

    private final Identifier baseTexture;
    private final Identifier overlayTexture;

    MatterState(Identifier baseTexture, Identifier overlayTexture) {
        this.baseTexture = baseTexture;
        this.overlayTexture = overlayTexture;
    }

    public Identifier getBaseTexture() {
        return baseTexture;
    }

    public Identifier getOverlayTexture() {
        return overlayTexture;
    }

    private static Identifier Identifier(String name) {
        return Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID, name);
    }
}
