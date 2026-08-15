package com.github.ringlocker.substancecraft.item;

import com.github.ringlocker.substancecraft.SubstanceCraft;
import net.minecraft.resources.Identifier;

public record SubstanceItemModelProvider(MatterState matterState, Transparency transparency) {

    private static final Identifier SOLID = identifier("item/solid");
    private static final Identifier LIQUID = identifier("item/liquid");
    private static final Identifier GAS = identifier("item/substance");
    
    private static final Identifier SOLID_OPAQUE = identifier("item/opaque_solid_overlay");
    private static final Identifier SOLID_TRANSLUCENT = identifier("item/translucent_solid_overlay");
    private static final Identifier SOLID_TRANSPARENT = identifier("item/transparent_solid_overlay");
    
    private static final Identifier LIQUID_OPAQUE = identifier("item/opaque_liquid_overlay");
    private static final Identifier LIQUID_TRANSLUCENT = identifier("item/translucent_liquid_overlay");
    private static final Identifier LIQUID_TRANSPARENT = identifier("item/transparent_liquid_overlay");
        
    private static final Identifier GAS_OPAQUE = identifier("item/opaque_substance_overlay");
    private static final Identifier GAS_TRANSLUCENT = identifier("item/translucent_substance_overlay");
    private static final Identifier GAS_TRANSPARENT = identifier("item/transparent_substance_overlay");
    
    public Identifier getBaseTexture() {
        return switch (matterState) {
            case SOLID -> SOLID;
            case LIQUID -> LIQUID;
            case GAS -> GAS;
        };
    }

    public Identifier getOverlayTexture() {
        return switch (matterState) {
            case SOLID -> switch (transparency) {
                case OPAQUE -> SOLID_OPAQUE;
                case TRANSLUCENT -> SOLID_TRANSLUCENT;
                case TRANSPARENT -> SOLID_TRANSPARENT;
            };
            case LIQUID -> switch (transparency) {
                case OPAQUE -> LIQUID_OPAQUE;
                case TRANSLUCENT -> LIQUID_TRANSLUCENT;
                case TRANSPARENT -> LIQUID_TRANSPARENT;
            };
            case GAS -> switch (transparency) {
                case OPAQUE -> GAS_OPAQUE;
                case TRANSLUCENT -> GAS_TRANSLUCENT;
                case TRANSPARENT -> GAS_TRANSPARENT;
            };
        };
    }

    private static Identifier identifier(String name) {
        return Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID, name);
    }
    
}
