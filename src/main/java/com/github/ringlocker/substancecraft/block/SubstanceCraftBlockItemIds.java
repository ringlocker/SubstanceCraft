package com.github.ringlocker.substancecraft.block;


import com.github.ringlocker.substancecraft.SubstanceCraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.references.BlockItemId;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;

public class SubstanceCraftBlockItemIds {

    public static final BlockItemId MARIJUANA_PLANT = create("marijuana_plant");
    public static final BlockItemId HASH_PRESS = create("hash_press");
    public static final BlockItemId REFINERY = create("refinery");
    public static final BlockItemId OIL_SHALE = create("oil_shale_block");
    public static final BlockItemId ELECTROLYSIS_MACHINE = create("electrolysis");
    public static final BlockItemId OXIDATION_MACHINE = create("oxidation_machine");
    public static final BlockItemId EXTRACTOR = create("extractor");
    public static final BlockItemId HALITE = create("salt_block");
    public static final BlockItemId MIXER = create("mixer");
    public static final BlockItemId HEATED_MIXER = create("heated_mixer");
    public static final BlockItemId FERMENTATION_TANK = create("fermentation_tank");
    public static final BlockItemId CORN_CROP = create("corn_crop");
    public static final BlockItemId COCA_CROP = create("coca_plant");
    public static final BlockItemId SYLVITE = create("sylvite_block");
    public static final BlockItemId SULFUR_ORE = create("sulfur_ore");
    public static final BlockItemId DEEPSLATE_SULFUR_ORE = create("deepslate_sulfur_ore");
    public static final BlockItemId TRONA = create("trona_block");
    public static final BlockItemId PYROLUSITE_ORE = create("pyrolusite_ore");
    public static final BlockItemId DEEPSLATE_PYROLUSITE_ORE = create("deepslate_pyrolusite_ore");
    public static final BlockItemId LIMESTONE = create("limestone_block");
    public static final BlockItemId PHOSPHORITE = create("phosphorite_block");
    public static final BlockItemId GRAPEVINE = create("grapevine");
    public static final BlockItemId PSILOCYBIN = create("psilocybin");
    public static final BlockItemId PEYOTE_CACTUS = create("peyote_cactus");
    public static final BlockItemId PALE_PSILOCYBIN = create("pale_psilocybin");

    private static BlockItemId create(String name) {
        return new BlockItemId(
                ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID, name)),
                ResourceKey.create(Registries.ITEM,  Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID, name))
        );
    }
    
    
}
