package com.github.ringlocker.substancecraft.client.datagen;

import com.github.ringlocker.substancecraft.block.SubstanceCraftBlocks;
import com.github.ringlocker.substancecraft.block.blocks.CocaCrop;
import com.github.ringlocker.substancecraft.block.blocks.CornCrop;
import com.github.ringlocker.substancecraft.block.blocks.MarijuanaPlant;
import com.github.ringlocker.substancecraft.item.SubstanceCraftItems;
import com.github.ringlocker.substancecraft.client.item.SubstanceTintColor;
import com.github.ringlocker.substancecraft.item.SubstanceItem;
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
import net.minecraft.resources.ResourceLocation;
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
        itemModelGenerator.generateFlatItem(SubstanceCraftItems.MARIJUANA_TRIM, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(SubstanceCraftItems.MARIJUANA, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(SubstanceCraftItems.HASH, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(SubstanceCraftItems.DAB_RIG, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(SubstanceCraftItems.EMPTY_DAB_RIG, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(SubstanceCraftItems.DIPHENHYDRAMINE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(SubstanceCraftItems.KETAMINE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(SubstanceCraftItems.OIL_SHALE, ModelTemplates.FLAT_ITEM);
        generateSubstanceItem(SubstanceCraftItems.OIL, itemModelGenerator);
        generateSubstanceItem(SubstanceCraftItems.PETROLEUM_NAPHTHA, itemModelGenerator);
        generateSubstanceItem(SubstanceCraftItems.KEROSENE, itemModelGenerator);
        generateSubstanceItem(SubstanceCraftItems.GASOLINE, itemModelGenerator);
        generateSubstanceItem(SubstanceCraftItems.METHANOL, itemModelGenerator);
        generateSubstanceItem(SubstanceCraftItems.FORMALDEHYDE, itemModelGenerator);
        generateSubstanceItem(SubstanceCraftItems.CHLOROFORM, itemModelGenerator);
        generateSubstanceItem(SubstanceCraftItems.BENZENE, itemModelGenerator);
        generateSubstanceItem(SubstanceCraftItems.TOLUENE, itemModelGenerator);
        itemModelGenerator.generateFlatItem(SubstanceCraftItems.HALITE, ModelTemplates.FLAT_ITEM);
        generateSubstanceItem(SubstanceCraftItems.SALT, itemModelGenerator);
        generateSubstanceItem(SubstanceCraftItems.BRINE, itemModelGenerator);
        generateSubstanceItem(SubstanceCraftItems.SODIUM_HYDROXIDE, itemModelGenerator);
        generateSubstanceItem(SubstanceCraftItems.METHANE, itemModelGenerator);
        generateSubstanceItem(SubstanceCraftItems.CHLORINE, itemModelGenerator);
        generateSubstanceItem(SubstanceCraftItems.HYDROGEN, itemModelGenerator);
        generateSubstanceItem(SubstanceCraftItems.NITROGEN, itemModelGenerator);
        generateSubstanceItem(SubstanceCraftItems.OXYGEN, itemModelGenerator);
        generateSubstanceItem(SubstanceCraftItems.NATURAL_GAS, itemModelGenerator);
        generateSubstanceItem(SubstanceCraftItems.PROPANE, itemModelGenerator);
        generateSubstanceItem(SubstanceCraftItems.ETHANE, itemModelGenerator);
        generateSubstanceItem(SubstanceCraftItems.BUTANE, itemModelGenerator);
        generateSubstanceItem(SubstanceCraftItems.METHYLAMINE, itemModelGenerator);
        generateSubstanceItem(SubstanceCraftItems.ETHYLENE, itemModelGenerator);
        generateSubstanceItem(SubstanceCraftItems.PROPYLENE, itemModelGenerator);
        generateSubstanceItem(SubstanceCraftItems.DIESEL, itemModelGenerator);
        itemModelGenerator.generateFlatItem(SubstanceCraftItems.CORN, ModelTemplates.FLAT_ITEM);
        generateSubstanceItem(SubstanceCraftItems.AMMONIA, itemModelGenerator);
        generateSubstanceItem(SubstanceCraftItems.ETHANOL, itemModelGenerator);
        itemModelGenerator.generateFlatItem(SubstanceCraftItems.YEAST, ModelTemplates.FLAT_ITEM);
        generateSubstanceItem(SubstanceCraftItems.HYDROCHLORIC_ACID, itemModelGenerator);
        itemModelGenerator.generateFlatItem(SubstanceCraftItems.ERGOT, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(SubstanceCraftItems.TWO_C_B, ModelTemplates.FLAT_ITEM);
        generateSubstanceItem(SubstanceCraftItems.TWO_C_H, itemModelGenerator);
        generateSubstanceItem(SubstanceCraftItems.BROMIDE, itemModelGenerator);
        generateSubstanceItem(SubstanceCraftItems.BROMINE, itemModelGenerator);
        generateSubstanceItem(SubstanceCraftItems.COKE, itemModelGenerator);
        generateSubstanceItem(SubstanceCraftItems.BENZALDEHYDE, itemModelGenerator);
        generateSubstanceItem(SubstanceCraftItems.NITRIC_ACID, itemModelGenerator);
        generateSubstanceItem(SubstanceCraftItems.NITROMETHANE, itemModelGenerator);
        generateSubstanceItem(SubstanceCraftItems.ACETIC_ACID, itemModelGenerator);
        generateSubstanceItem(SubstanceCraftItems.AMMONIUM_ACETATE, itemModelGenerator);
        generateSubstanceItem(SubstanceCraftItems.MALEIC_ANHYDRIDE, itemModelGenerator);
        generateSubstanceItem(SubstanceCraftItems.CARBON_MONOXIDE, itemModelGenerator);
        generateSubstanceItem(SubstanceCraftItems.BETA_NITROSTYRENE, itemModelGenerator);
        generateSubstanceItem(SubstanceCraftItems.TETRAHYDROFURAN, itemModelGenerator);
        generateSubstanceItem(SubstanceCraftItems.DISTILLED_WATER, itemModelGenerator);
        generateSubstanceItem(SubstanceCraftItems.P2P, itemModelGenerator);
        generateSubstanceItem(SubstanceCraftItems.P2NP, itemModelGenerator);
        generateSubstanceItem(SubstanceCraftItems.NITROETHANE, itemModelGenerator);
        generateSubstanceItem(SubstanceCraftItems.FORMIC_ACID, itemModelGenerator);
        generateSubstanceItem(SubstanceCraftItems.METHYL_FORMATE, itemModelGenerator);
        itemModelGenerator.generateFlatItem(SubstanceCraftItems.AMPHETAMINE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(SubstanceCraftItems.COCA_LEAVES, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(SubstanceCraftItems.RAW_SULFUR, ModelTemplates.FLAT_ITEM);
        generateSubstanceItem(SubstanceCraftItems.SULFUR, itemModelGenerator);
        generateSubstanceItem(SubstanceCraftItems.SULFURIC_ACID, itemModelGenerator);
        itemModelGenerator.generateFlatItem(SubstanceCraftItems.TRONA, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(SubstanceCraftItems.SYLVITE, ModelTemplates.FLAT_ITEM);
        generateSubstanceItem(SubstanceCraftItems.SODIUM_CARBONATE, itemModelGenerator);
        generateSubstanceItem(SubstanceCraftItems.SODIUM_CARBONATE_SOLUTION, itemModelGenerator);
        itemModelGenerator.generateFlatItem(SubstanceCraftItems.PYROLUSITE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(SubstanceCraftItems.COCAINE, ModelTemplates.FLAT_ITEM);
        generateSubstanceItem(SubstanceCraftItems.AGUA_RICA, itemModelGenerator);
        generateSubstanceItem(SubstanceCraftItems.POTASSIUM_CHLORIDE, itemModelGenerator);
        generateSubstanceItem(SubstanceCraftItems.POTASSIUM_HYDROXIDE, itemModelGenerator);
        generateSubstanceItem(SubstanceCraftItems.POTASSIUM_CARBONATE, itemModelGenerator);
        generateSubstanceItem(SubstanceCraftItems.POTASSIUM_PERMANGANATE, itemModelGenerator);
        generateSubstanceItem(SubstanceCraftItems.MANGANESE_DIOXIDE, itemModelGenerator);
        generateSubstanceItem(SubstanceCraftItems.COCA_PASTE, itemModelGenerator);
        generateSubstanceItem(SubstanceCraftItems.ACETONE, itemModelGenerator);
        generateSubstanceItem(SubstanceCraftItems.AMMONIA_SOLUTION, itemModelGenerator);
        generateSubstanceItem(SubstanceCraftItems.CARBON_DIOXIDE, itemModelGenerator);
        itemModelGenerator.generateFlatItem(SubstanceCraftItems.CASH, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(SubstanceCraftItems.BAND, ModelTemplates.FLAT_ITEM);
    }

    public final void generateSubstanceItem(Item substance, ItemModelGenerators itemModelGenerator) {
        SubstanceItem item = (SubstanceItem) substance;
        ResourceLocation overlay = item.getState().getOverlayTexture();
        ResourceLocation base = item.getState().getBaseTexture();
        ResourceLocation resourceLocation = ModelTemplates.TWO_LAYERED_ITEM.create(substance, TextureMapping.layered(overlay, base), itemModelGenerator.modelOutput);
        itemModelGenerator.itemModelOutput.accept(substance, ItemModelUtils.tintedModel(resourceLocation, new SubstanceTintColor()));
    }

    private static void createTopBottomSideFrontAndFrontOnTexture(Block block, BlockModelGenerators blockModelGenerators) {
        ResourceLocation texture = TexturedModel.ORIENTABLE.create(block, blockModelGenerators.modelOutput);
        ResourceLocation frontOn = TexturedModel.ORIENTABLE.get(block)
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
