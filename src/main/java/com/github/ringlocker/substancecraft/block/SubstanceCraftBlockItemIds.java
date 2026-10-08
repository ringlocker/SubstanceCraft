package com.github.ringlocker.substancecraft.block;


import com.github.ringlocker.substancecraft.SubstanceCraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.references.BlockItemId;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockItemTagId;
import net.minecraft.tags.TagKey;

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
    public static final BlockItemId TRONA_ORE = create("trona_ore");
    public static final BlockItemId DEEPSLATE_TRONA_ORE = create("deepslate_trona_ore");
    public static final BlockItemId PYROLUSITE_ORE = create("pyrolusite_ore");
    public static final BlockItemId DEEPSLATE_PYROLUSITE_ORE = create("deepslate_pyrolusite_ore");
    public static final BlockItemId LIMESTONE = create("limestone_block");
    public static final BlockItemId PHOSPHORITE = create("phosphorite_block");
    public static final BlockItemId GRAPEVINE = create("grapevine");
    public static final BlockItemId PSILOCYBIN = create("psilocybin");
    public static final BlockItemId PEYOTE_CACTUS = create("peyote_cactus");
    public static final BlockItemId PALE_PSILOCYBIN = create("pale_psilocybin");
    public static final BlockItemId CHEMIST_WORKSTATION = create("chemist_station");
    public static final BlockItemId PLANT_RESEARCH_STATION = create("plant_research_station");
    public static final BlockItemId MIMOSA_HOSTILIS_LOG = create("mimosa_hostilis_log");
    public static final BlockItemId MIMOSA_HOSTILIS_WOOD = create("mimosa_hostilis_wood");
    public static final BlockItemId MIMOSA_HOSTILIS_PLANKS = create("mimosa_hostilis_planks");
    public static final BlockItemId MIMOSA_HOSTILIS_STAIRS = create("mimosa_hostilis_stairs");
    public static final BlockItemId MIMOSA_HOSTILIS_SLAB = create("mimosa_hostilis_slab");
    public static final BlockItemId MIMOSA_HOSTILIS_FENCE = create("mimosa_hostilis_fence");
    public static final BlockItemId MIMOSA_HOSTILIS_FENCE_GATE = create("mimosa_hostilis_fence_gate");
    public static final BlockItemId MIMOSA_HOSTILIS_PRESSURE_PLATE = create("mimosa_hostilis_pressure_plate");
    public static final BlockItemId MIMOSA_HOSTILIS_BUTTON = create("mimosa_hostilis_button");
    public static final BlockItemId MIMOSA_HOSTILIS_WALL = create("mimosa_hostilis_wall");
    public static final BlockItemId STRIPPED_MIMOSA_HOSTILIS_WOOD = create("stripped_mimosa_hostilis_wood");
    public static final BlockItemId MIMOSA_HOSTILIS_LEAVES = create("mimosa_hostilis_leaves");
    public static final BlockItemId STRIPPED_MIMOSA_HOSTILIS_LOG = create("stripped_mimosa_hostilis_log");
    public static final BlockItemId MIMOSA_HOSTILIS_SAPLING = create("mimosa_hostilis_sapling");
    public static final BlockItemId POTTED_MIMOSA_HOSTILIS_SAPLING = create("potted_mimosa_hostilis_sapling");
    public static final BlockItemId MIMOSA_HOSTILIS_ROOT = create("mimosa_hostilis_root");
    public static final BlockItemId STRIPPED_MIMOSA_HOSTILIS_ROOT = create("stripped_mimosa_hostilis_root");
    public static final BlockItemId BAUXITE_ORE = create("bauxite_ore");
    public static final BlockItemId DEEPSLATE_BAUXITE_ORE = create("deepslate_bauxite_ore");
    public static final BlockItemId PALLADIUM_ORE = create("palladium_ore");
    public static final BlockItemId DEEPSLATE_PALLADIUM_ORE = create("deepslate_palladium_ore");
    public static final BlockItemId CRYOLITE_ORE = create("cryolite_ore");
    public static final BlockItemId DEEPSLATE_CRYOLITE_ORE = create("deepslate_cryolite_ore");
    public static final BlockItemId VANADINITE_ORE = create("vanadinite_ore");
    public static final BlockItemId DEEPSLATE_VANADINITE_ORE = create("deepslate_vanadinite_ore");
    public static final BlockItemId ANISE_PLANT = create("anise");
    public static final BlockItemId CHROMITITE = create("chromitite_block");

    public static final BlockItemTagId MIMOSA_HOSTILIS_LOGS = createTag("mimosa_hostilis_logs");
    public static final BlockItemTagId SULFUR_ORES = createTag("sulfur_ores");
    public static final BlockItemTagId PYROLUSITE_ORES = createTag("pyrolusite_ores");
    public static final BlockItemTagId TRONA_ORES = createTag("trona_ores");
    public static final BlockItemTagId VANADINITE_ORES = createTag("vanadinite_ores");
    public static final BlockItemTagId CRYOLITE_ORES = createTag("cryolite_ores");
    public static final BlockItemTagId PALLADIUM_ORES = createTag("palladium_ores");
    public static final BlockItemTagId BAUXITE_ORES = createTag("bauxite_ores");

    private static BlockItemId create(String name) {
        return new BlockItemId(
                ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID, name)),
                ResourceKey.create(Registries.ITEM,  Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID, name))
        );
    }

    private static BlockItemTagId createTag(String name) {
        return new BlockItemTagId(
                TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID, name)),
                TagKey.create(Registries.ITEM,  Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID, name))
        );
    }
    
    
}
