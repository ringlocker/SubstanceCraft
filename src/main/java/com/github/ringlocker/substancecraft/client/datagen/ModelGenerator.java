package com.github.ringlocker.substancecraft.client.datagen;

import com.github.ringlocker.substancecraft.SubstanceCraft;
import com.github.ringlocker.substancecraft.block.SubstanceCraftBlocks;
import com.github.ringlocker.substancecraft.block.blocks.CocaCrop;
import com.github.ringlocker.substancecraft.block.blocks.CornCrop;
import com.github.ringlocker.substancecraft.block.blocks.Grapevine;
import com.github.ringlocker.substancecraft.block.blocks.MarijuanaPlant;
import com.github.ringlocker.substancecraft.block.blocks.PeyoteCactus;
import com.github.ringlocker.substancecraft.block.blocks.PsilocybinMushroom;
import com.github.ringlocker.substancecraft.item.SubstanceCraftItems;
import com.github.ringlocker.substancecraft.client.item.SubstanceTintColor;
import com.github.ringlocker.substancecraft.item.items.SubstanceItem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.client.renderer.block.dispatch.Variant;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

import java.util.function.BiFunction;

@Environment(EnvType.CLIENT)
public class ModelGenerator extends FabricModelProvider {

    public ModelGenerator(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
        blockStateModelGenerator.createCrossBlock(SubstanceCraftBlocks.MARIJUANA_PLANT, BlockModelGenerators.PlantType.TINTED, MarijuanaPlant.AGE_PROPERTY, 0, 1, 2, 3, 4, 5, 6, 7);
        createTopBottomSideFrontAndFrontOnTexture(SubstanceCraftBlocks.REFINERY, blockStateModelGenerator);
        blockStateModelGenerator.createTrivialCube(SubstanceCraftBlocks.OIL_SHALE);
        createTopBottomSideFrontAndFrontOnTexture(SubstanceCraftBlocks.ELECTROLYSIS_MACHINE, blockStateModelGenerator);
        createTopBottomSideFrontAndFrontOnTexture(SubstanceCraftBlocks.OXIDATION_MACHINE, blockStateModelGenerator);
        createTopBottomSideFrontAndFrontOnTexture(SubstanceCraftBlocks.EXTRACTOR, blockStateModelGenerator);
        blockStateModelGenerator.createTrivialCube(SubstanceCraftBlocks.HALITE);
        createTopBottomSideFrontAndFrontOnTexture(SubstanceCraftBlocks.MIXER, blockStateModelGenerator);
        createTopBottomSideFrontAndFrontOnTexture(SubstanceCraftBlocks.HEATED_MIXER, blockStateModelGenerator);
        createTopBottomSideFrontAndFrontOnTexture(SubstanceCraftBlocks.FERMENTATION_TANK, blockStateModelGenerator);
        blockStateModelGenerator.createCrossBlock(SubstanceCraftBlocks.CORN_CROP, BlockModelGenerators.PlantType.TINTED, CornCrop.AGE, 0, 1, 2, 3, 4, 5, 6, 7);
        blockStateModelGenerator.createCrossBlock(SubstanceCraftBlocks.COCA_CROP, BlockModelGenerators.PlantType.TINTED, CocaCrop.AGE_PROPERTY, 0, 1, 2, 3, 4, 5);
        blockStateModelGenerator.createTrivialCube(SubstanceCraftBlocks.SYLVITE);
        blockStateModelGenerator.createTrivialCube(SubstanceCraftBlocks.SULFUR_ORE);
        blockStateModelGenerator.createTrivialCube(SubstanceCraftBlocks.DEEPSLATE_SULFUR_ORE);
        blockStateModelGenerator.createTrivialCube(SubstanceCraftBlocks.TRONA);
        blockStateModelGenerator.createTrivialCube(SubstanceCraftBlocks.PYROLUSITE_ORE);
        blockStateModelGenerator.createTrivialCube(SubstanceCraftBlocks.DEEPSLATE_PYROLUSITE_ORE);
        blockStateModelGenerator.createTrivialCube(SubstanceCraftBlocks.LIMESTONE);
        blockStateModelGenerator.createTrivialCube(SubstanceCraftBlocks.PHOSPHORITE);
        blockStateModelGenerator.createCrossBlock(SubstanceCraftBlocks.GRAPEVINE, BlockModelGenerators.PlantType.TINTED, Grapevine.AGE_PROPERTY, 0, 1, 2, 3, 4, 5, 6, 7);
        blockStateModelGenerator.createCrossBlock(SubstanceCraftBlocks.PSILOCYBIN, BlockModelGenerators.PlantType.TINTED, PsilocybinMushroom.AGE, 0, 1, 2);
        blockStateModelGenerator.createCrossBlock(SubstanceCraftBlocks.PALE_PSILOCYBIN, BlockModelGenerators.PlantType.TINTED, PsilocybinMushroom.AGE, 0, 1, 2);
        createSeaPickleLike(blockStateModelGenerator, SubstanceCraftBlocks.PEYOTE_CACTUS, PeyoteCactus.AGE, "peyote_stage");
        createSmithingTable(SubstanceCraftBlocks.CHEMIST_WORKSTATION, blockStateModelGenerator);
        createCraftingTableLike(SubstanceCraftBlocks.PLANT_RESEARCH_STATION, Blocks.ACACIA_PLANKS, TextureMapping::fletchingTable, blockStateModelGenerator);
        blockStateModelGenerator.woodProvider(SubstanceCraftBlocks.MIMOSA_HOSTILIS_LOG).logWithHorizontal(SubstanceCraftBlocks.MIMOSA_HOSTILIS_LOG).wood(SubstanceCraftBlocks.MIMOSA_HOSTILIS_WOOD);
        blockStateModelGenerator.woodProvider(SubstanceCraftBlocks.STRIPPED_MIMOSA_HOSTILIS_LOG).logWithHorizontal(SubstanceCraftBlocks.STRIPPED_MIMOSA_HOSTILIS_LOG).wood(SubstanceCraftBlocks.STRIPPED_MIMOSA_HOSTILIS_WOOD);
        blockStateModelGenerator.createTintedLeaves(SubstanceCraftBlocks.MIMOSA_HOSTILIS_LEAVES, TexturedModel.LEAVES, -12012264);
        blockStateModelGenerator.createPlantWithDefaultItem(SubstanceCraftBlocks.MIMOSA_HOSTILIS_SAPLING, SubstanceCraftBlocks.POTTED_MIMOSA_HOSTILIS_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);
        blockStateModelGenerator.family(SubstanceCraftBlocks.MIMOSA_WOOD.getBaseBlock()).generateFor(SubstanceCraftBlocks.MIMOSA_WOOD);
        createSideTop(blockStateModelGenerator, SubstanceCraftBlocks.MIMOSA_HOSTILIS_ROOT);
        createSideTop(blockStateModelGenerator, SubstanceCraftBlocks.STRIPPED_MIMOSA_HOSTILIS_ROOT);
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        SubstanceCraftItems.substances.forEach(substance -> generateSubstanceItem(substance, itemModelGenerator));
        SubstanceCraftItems.flatTextureItems.forEach(flatTextureItem -> itemModelGenerator.generateFlatItem(flatTextureItem, ModelTemplates.FLAT_ITEM));
    }

    private void createSeaPickleLike(BlockModelGenerators blockStateModelGenerator, Block block, IntegerProperty ages, String modelName) {
        blockStateModelGenerator.registerSimpleFlatItemModel(SubstanceCraftBlocks.getBlockItem(block));
        PropertyDispatch.C1<MultiVariant, Integer> variants = PropertyDispatch.initial(ages);
        for (int i = 0; i <= ages.getPossibleValues().getLast(); i++) {
            variants.select(i, BlockModelGenerators.createRotatedVariants(BlockModelGenerators.plainModel(Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "block/" + modelName + i))));
        }
        blockStateModelGenerator.blockStateOutput
                .accept(
                        MultiVariantGenerator.dispatch(block)
                                .with(
                                        variants
                                )
                );
    }

    private void generateSubstanceItem(SubstanceItem substance, ItemModelGenerators itemModelGenerator) {
        Identifier baseIdentifier = substance.getState().getBaseTexture();
        Identifier overlayIdentifier = substance.getState().getOverlayTexture();
        Material base = new Material(baseIdentifier, true);
        Material overlay = new Material(overlayIdentifier, true);
        Identifier resourceLocation = ModelTemplates.TWO_LAYERED_ITEM.create(substance, TextureMapping.layered(overlay, base), itemModelGenerator.modelOutput);
        itemModelGenerator.itemModelOutput.accept(substance, ItemModelUtils.tintedModel(resourceLocation, new SubstanceTintColor()));
    }

    private void createTopBottomSideFrontAndFrontOnTexture(Block block, BlockModelGenerators blockModelGenerators) {
        Identifier texture = TexturedModel.ORIENTABLE.create(block, blockModelGenerators.modelOutput);
        Identifier frontOn = TexturedModel.ORIENTABLE.get(block)
                .updateTextures(textureMapping -> textureMapping.put(TextureSlot.FRONT, TextureMapping.getBlockTexture(block, "_front_on")))
                .createWithSuffix(block, "_on", blockModelGenerators.modelOutput);

        MultiVariant offVariant = new MultiVariant(WeightedList.of(new Variant(texture)));
        MultiVariant onVariant = new MultiVariant(WeightedList.of(new Variant(frontOn)));
        blockModelGenerators.blockStateOutput.accept(
                MultiVariantGenerator.dispatch(block)
                        .with(PropertyDispatch.initial(BlockStateProperties.LIT)
                                .select(true, onVariant).select(false, offVariant)
                        )
                        .with(PropertyDispatch.modify(BlockStateProperties.HORIZONTAL_FACING)
                                .select(Direction.EAST, BlockModelGenerators.Y_ROT_90)
                                .select(Direction.SOUTH, BlockModelGenerators.Y_ROT_180)
                                .select(Direction.WEST, BlockModelGenerators.Y_ROT_270)
                                .select(Direction.NORTH, BlockModelGenerators.NOP)
                        )
        );
    }

    private void createSmithingTable(Block block, BlockModelGenerators blockModelGenerators) {
        TextureMapping mapping = new TextureMapping()
                .put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(block, "_front"))
                .put(TextureSlot.DOWN, TextureMapping.getBlockTexture(block, "_bottom"))
                .put(TextureSlot.UP, TextureMapping.getBlockTexture(block, "_top"))
                .put(TextureSlot.NORTH, TextureMapping.getBlockTexture(block, "_front"))
                .put(TextureSlot.SOUTH, TextureMapping.getBlockTexture(block, "_front"))
                .put(TextureSlot.EAST, TextureMapping.getBlockTexture(block, "_side"))
                .put(TextureSlot.WEST, TextureMapping.getBlockTexture(block, "_side"));
        blockModelGenerators.blockStateOutput.accept(
                BlockModelGenerators.createSimpleBlock(
                        block,
                        BlockModelGenerators.plainVariant(ModelTemplates.CUBE.create(block, mapping, blockModelGenerators.modelOutput))
                )
        );
    }

    private void createCraftingTableLike(Block block, Block bottomBlock, BiFunction<Block, Block, TextureMapping> mappingProvider, BlockModelGenerators blockModelGenerators) {
        TextureMapping mapping = mappingProvider.apply(block, bottomBlock);
        blockModelGenerators.blockStateOutput.accept(
                BlockModelGenerators.createSimpleBlock(block,
                        BlockModelGenerators.plainVariant(ModelTemplates.CUBE.create(block, mapping, blockModelGenerators.modelOutput))));
    }

    private void createSideTop(BlockModelGenerators blockModelGenerator, Block block) {
        TextureMapping textures = TextureMapping.column(
                TextureMapping.getBlockTexture(block, "_side"), TextureMapping.getBlockTexture(block, "_top")
        );
        MultiVariant model = BlockModelGenerators.plainVariant(ModelTemplates.CUBE_COLUMN.create(block, textures, blockModelGenerator.modelOutput));
        blockModelGenerator.blockStateOutput.accept(BlockModelGenerators.createAxisAlignedPillarBlock(block, model));
    }

}
