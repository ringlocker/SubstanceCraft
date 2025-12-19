package com.github.ringlocker.substancecraft.client.datagen;

import com.github.ringlocker.substancecraft.block.SubstanceCraftBlocks;
import com.github.ringlocker.substancecraft.block.blocks.CocaCrop;
import com.github.ringlocker.substancecraft.block.blocks.CornCrop;
import com.github.ringlocker.substancecraft.block.blocks.MarijuanaPlant;
import com.github.ringlocker.substancecraft.item.SubstanceCraftItems;
import com.github.ringlocker.substancecraft.client.item.SubstanceTintColor;
import com.github.ringlocker.substancecraft.item.items.SubstanceItem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
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
import net.minecraft.client.renderer.block.model.Variant;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

@Environment(EnvType.CLIENT)
public class ModelGenerator extends FabricModelProvider {

    public ModelGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
        blockStateModelGenerator.createCrossBlock(SubstanceCraftBlocks.MARIJUANA_PLANT, BlockModelGenerators.PlantType.TINTED, MarijuanaPlant.AGE, 0, 1, 2, 3, 4, 5, 6, 7);
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
        blockStateModelGenerator.createCrossBlock(SubstanceCraftBlocks.COCA_CROP, BlockModelGenerators.PlantType.TINTED, CocaCrop.AGE, 0, 1, 2, 3, 4, 5);
        blockStateModelGenerator.createTrivialCube(SubstanceCraftBlocks.SYLVITE);
        blockStateModelGenerator.createTrivialCube(SubstanceCraftBlocks.SULFUR_ORE);
        blockStateModelGenerator.createTrivialCube(SubstanceCraftBlocks.DEEPSLATE_SULFUR_ORE);
        blockStateModelGenerator.createTrivialCube(SubstanceCraftBlocks.TRONA);
        blockStateModelGenerator.createTrivialCube(SubstanceCraftBlocks.PYROLUSITE_ORE);
        blockStateModelGenerator.createTrivialCube(SubstanceCraftBlocks.DEEPSLATE_PYROLUSITE_ORE);
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        SubstanceCraftItems.substances.forEach(substance -> generateSubstanceItem(substance, itemModelGenerator));
        SubstanceCraftItems.flatTextureItems.forEach(flatTextureItem -> itemModelGenerator.generateFlatItem(flatTextureItem, ModelTemplates.FLAT_ITEM));
    }

    public final void generateSubstanceItem(SubstanceItem substance, ItemModelGenerators itemModelGenerator) {
        Identifier overlay = substance.getState().getOverlayTexture();
        Identifier base = substance.getState().getBaseTexture();
        Identifier resourceLocation = ModelTemplates.TWO_LAYERED_ITEM.create(substance, TextureMapping.layered(overlay, base), itemModelGenerator.modelOutput);
        itemModelGenerator.itemModelOutput.accept(substance, ItemModelUtils.tintedModel(resourceLocation, new SubstanceTintColor()));
    }

    private static void createTopBottomSideFrontAndFrontOnTexture(Block block, BlockModelGenerators blockModelGenerators) {
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

}
