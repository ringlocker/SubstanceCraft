package com.github.ringlocker.substancecraft.world;

import com.github.ringlocker.substancecraft.SubstanceCraft;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;

public class SubstanceCraftFeatures {

    public static void registerFeatures() {
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(Biomes.JUNGLE, Biomes.BAMBOO_JUNGLE, Biomes.SPARSE_JUNGLE),
                GenerationStep.Decoration.VEGETAL_DECORATION,
                ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "patch_marijuana"))
        );
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(Biomes.DEEP_COLD_OCEAN, Biomes.DEEP_OCEAN, Biomes.DEEP_FROZEN_OCEAN, Biomes.DEEP_LUKEWARM_OCEAN),
                GenerationStep.Decoration.UNDERGROUND_ORES,
                ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "ore_oil_shale"))
        );
        BiomeModifications.addFeature(
                BiomeSelectors.tag(BiomeTags.IS_OCEAN),
                GenerationStep.Decoration.UNDERGROUND_ORES,
                ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "ore_halite_ocean"))
        );
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(Biomes.DRIPSTONE_CAVES),
                GenerationStep.Decoration.UNDERGROUND_ORES,
                ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "ore_halite_dripstone_cave"))
        );
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(Biomes.DESERT, Biomes.BADLANDS, Biomes.ERODED_BADLANDS, Biomes.WOODED_BADLANDS),
                GenerationStep.Decoration.UNDERGROUND_ORES,
                ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "ore_sylvite"))
        );
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(Biomes.JUNGLE, Biomes.BAMBOO_JUNGLE, Biomes.SPARSE_JUNGLE),
                GenerationStep.Decoration.VEGETAL_DECORATION,
                ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "patch_coca"))
        );
        BiomeModifications.addFeature(
                BiomeSelectors.tag(BiomeTags.IS_OVERWORLD),
                GenerationStep.Decoration.UNDERGROUND_ORES,
                ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "ore_sulfur"))
        );
        BiomeModifications.addFeature(
                BiomeSelectors.tag(BiomeTags.IS_OVERWORLD),
                GenerationStep.Decoration.UNDERGROUND_ORES,
                ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "ore_trona"))
        );
        BiomeModifications.addFeature(
                BiomeSelectors.tag(BiomeTags.IS_OVERWORLD),
                GenerationStep.Decoration.UNDERGROUND_ORES,
                ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "ore_pyrolusite"))
        );
        BiomeModifications.addFeature(
                BiomeSelectors.tag(BiomeTags.IS_OVERWORLD),
                GenerationStep.Decoration.UNDERGROUND_ORES,
                ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "ore_limestone"))
        );
        BiomeModifications.addFeature(
                BiomeSelectors.tag(BiomeTags.IS_OVERWORLD),
                GenerationStep.Decoration.UNDERGROUND_ORES,
                ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "ore_phosphorite"))
        );
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(Biomes.DARK_FOREST),
                GenerationStep.Decoration.VEGETAL_DECORATION,
                ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "patch_psilocybin"))
        );
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(Biomes.PALE_GARDEN),
                GenerationStep.Decoration.VEGETAL_DECORATION,
                ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "patch_pale_psilocybin"))
        );
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(Biomes.BADLANDS),
                GenerationStep.Decoration.VEGETAL_DECORATION,
                ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "patch_peyote"))
        );
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(Biomes.DESERT),
                GenerationStep.Decoration.VEGETAL_DECORATION,
                ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "patch_peyote_desert"))
        );
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(Biomes.JUNGLE, Biomes.SPARSE_JUNGLE),
                GenerationStep.Decoration.VEGETAL_DECORATION,
                ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "tree_mimosa_hostilis"))
        );
        BiomeModifications.addFeature(
                BiomeSelectors.tag(BiomeTags.IS_OVERWORLD),
                GenerationStep.Decoration.UNDERGROUND_ORES,
                ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "ore_cryolite"))
        );
        BiomeModifications.addFeature(
                BiomeSelectors.tag(BiomeTags.IS_OVERWORLD),
                GenerationStep.Decoration.UNDERGROUND_ORES,
                ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "ore_palladium"))
        );
        BiomeModifications.addFeature(
                BiomeSelectors.tag(BiomeTags.IS_OVERWORLD),
                GenerationStep.Decoration.UNDERGROUND_ORES,
                ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "ore_vanadinite"))
        );
        BiomeModifications.addFeature(
                BiomeSelectors.tag(BiomeTags.IS_OVERWORLD),
                GenerationStep.Decoration.UNDERGROUND_ORES,
                ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "ore_bauxite"))
        );
        BiomeModifications.addFeature(
                BiomeSelectors.tag(BiomeTags.IS_OVERWORLD),
                GenerationStep.Decoration.UNDERGROUND_ORES,
                ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "ore_chromitite"))
        );
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(Biomes.FLOWER_FOREST, Biomes.SUNFLOWER_PLAINS, Biomes.OLD_GROWTH_BIRCH_FOREST),
                GenerationStep.Decoration.VEGETAL_DECORATION,
                ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "patch_anise"))
        );
    }

}
