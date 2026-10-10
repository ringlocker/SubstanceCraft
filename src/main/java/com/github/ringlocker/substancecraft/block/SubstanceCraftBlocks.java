package com.github.ringlocker.substancecraft.block;

import com.github.ringlocker.substancecraft.SubstanceCraft;
import com.github.ringlocker.substancecraft.block.blocks.*;
import com.github.ringlocker.substancecraft.item.Drug;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.BlockFamily;
import net.minecraft.references.BlockItemId;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.TintedParticleLeavesBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import java.util.HashMap;
import java.util.function.Function;

public class SubstanceCraftBlocks {

    private static final HashMap<Block, Item> BLOCK_ITEMS = new HashMap<>();

    private static final TreeGrower MIMOSA_HOSTILIS = new TreeGrower("mimosa_hostilis_tree_grower",
            WeightedList.of(ResourceKey.create(Registries.FEATURE, Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "mimosa_hostilis"))),
            WeightedList.of(),
            WeightedList.of(),
            null);
    public static final BlockSetType MIMOSA = new BlockSetType("mimosa");

    public static final Block MARIJUANA_PLANT = register(SubstanceCraftBlockItemIds.MARIJUANA_PLANT, MarijuanaPlant::new, genericBush());
    public static final Block HASH_PRESS = register(SubstanceCraftBlockItemIds.HASH_PRESS, HashPress::new, BlockBehaviour.Properties.of().strength(3.5F));
    public static final Block REFINERY = register(SubstanceCraftBlockItemIds.REFINERY, Refinery::new, BlockBehaviour.Properties.of().strength(3.5F).sound(SoundType.STONE));
    public static final Block OIL_SHALE = register(SubstanceCraftBlockItemIds.OIL_SHALE, Block::new, genericRock());
    public static final Block ELECTROLYSIS_MACHINE = register(SubstanceCraftBlockItemIds.ELECTROLYSIS_MACHINE, ElectrolysisMachine::new, BlockBehaviour.Properties.of().strength(3.5F).sound(SoundType.STONE));
    public static final Block OXIDATION_MACHINE = register(SubstanceCraftBlockItemIds.OXIDATION_MACHINE, Oxidizer::new, BlockBehaviour.Properties.of().strength(3.5F).sound(SoundType.STONE));
    public static final Block EXTRACTOR = register(SubstanceCraftBlockItemIds.EXTRACTOR, Extractor::new, BlockBehaviour.Properties.of().strength(3.5F).sound(SoundType.STONE));
    public static final Block HALITE = register(SubstanceCraftBlockItemIds.HALITE, Block::new, genericRock(MapColor.TERRACOTTA_WHITE, SoundType.CALCITE));
    public static final Block MIXER = register(SubstanceCraftBlockItemIds.MIXER, Mixer::new, BlockBehaviour.Properties.of().strength(3.5F).sound(SoundType.STONE));
    public static final Block HEATED_MIXER = register(SubstanceCraftBlockItemIds.HEATED_MIXER, HeatedMixer::new, BlockBehaviour.Properties.of().strength(3.5F).sound(SoundType.STONE));
    public static final Block FERMENTATION_TANK = register(SubstanceCraftBlockItemIds.FERMENTATION_TANK, FermentationTank::new, BlockBehaviour.Properties.of().strength(3.5F).sound(SoundType.STONE));
    public static final Block CORN_CROP = register(SubstanceCraftBlockItemIds.CORN_CROP, CornCrop::new, genericCrop());
    public static final Block COCA_CROP = register(SubstanceCraftBlockItemIds.COCA_CROP, CocaCrop::new, genericCrop());
    public static final Block SYLVITE = register(SubstanceCraftBlockItemIds.SYLVITE, Block::new, genericRock(MapColor.TERRACOTTA_ORANGE, SoundType.CALCITE));
    public static final Block SULFUR_ORE = register(SubstanceCraftBlockItemIds.SULFUR_ORE, Block::new, genericOre());
    public static final Block DEEPSLATE_SULFUR_ORE = register(SubstanceCraftBlockItemIds.DEEPSLATE_SULFUR_ORE, Block::new, genericDeepslateOre());
    public static final Block TRONA_ORE = register(SubstanceCraftBlockItemIds.TRONA_ORE, Block::new, genericOre());
    public static final Block DEEPSLATE_TRONA_ORE = register(SubstanceCraftBlockItemIds.DEEPSLATE_TRONA_ORE, Block::new, genericDeepslateOre());
    public static final Block PYROLUSITE_ORE = register(SubstanceCraftBlockItemIds.PYROLUSITE_ORE, Block::new, genericOre());
    public static final Block DEEPSLATE_PYROLUSITE_ORE = register(SubstanceCraftBlockItemIds.DEEPSLATE_PYROLUSITE_ORE, Block::new, genericDeepslateOre());
    public static final Block LIMESTONE = register(SubstanceCraftBlockItemIds.LIMESTONE, Block::new, genericRock(MapColor.SAND));
    public static final Block PHOSPHORITE = register(SubstanceCraftBlockItemIds.PHOSPHORITE, Block::new, genericRock());
    public static final Block GRAPEVINE = register(SubstanceCraftBlockItemIds.GRAPEVINE, Grapevine::new, genericBush());
    public static final Block PSILOCYBIN = registerPlaceableDrug(SubstanceCraftBlockItemIds.PSILOCYBIN, PsilocybinMushroom::new, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).noCollision().randomTicks().instabreak().sound(SoundType.GRASS).postProcess(SubstanceCraftBlocks::postProcessSelf).pushReaction(PushReaction.POPPED), Drug.PSILOCYBIN_1);
    public static final Block PEYOTE_CACTUS = register(SubstanceCraftBlockItemIds.PEYOTE_CACTUS, PeyoteCactus::new, BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).randomTicks().strength(0.4F).sound(SoundType.WOOL).pushReaction(PushReaction.POPPED));
    public static final Block PALE_PSILOCYBIN = registerPlaceableDrug(SubstanceCraftBlockItemIds.PALE_PSILOCYBIN, PotentPsilocybinMushroom::new, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).noCollision().randomTicks().instabreak().sound(SoundType.GRASS).postProcess(SubstanceCraftBlocks::postProcessSelf).pushReaction(PushReaction.POPPED), Drug.PSILOCYBIN_2);
    public static final Block CHEMIST_WORKSTATION = register(SubstanceCraftBlockItemIds.CHEMIST_WORKSTATION, Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SMITHING_TABLE));
    public static final Block PLANT_RESEARCH_STATION = register(SubstanceCraftBlockItemIds.PLANT_RESEARCH_STATION, Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.FLETCHING_TABLE));
    public static final Block MIMOSA_HOSTILIS_LOG = register(SubstanceCraftBlockItemIds.MIMOSA_HOSTILIS_LOG, RotatedPillarBlock::new, logProperties(MapColor.PODZOL, MapColor.COLOR_BROWN, SoundType.WOOD));
    public static final Block MIMOSA_HOSTILIS_WOOD = register(SubstanceCraftBlockItemIds.MIMOSA_HOSTILIS_WOOD, RotatedPillarBlock::new, logProperties(MapColor.PODZOL, MapColor.COLOR_BROWN, SoundType.WOOD));
    public static final Block MIMOSA_HOSTILIS_PLANKS = register(SubstanceCraftBlockItemIds.MIMOSA_HOSTILIS_PLANKS, Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
    public static final Block MIMOSA_HOSTILIS_STAIRS = registerStair(SubstanceCraftBlockItemIds.MIMOSA_HOSTILIS_STAIRS, MIMOSA_HOSTILIS_PLANKS);
    public static final Block MIMOSA_HOSTILIS_SLAB = registerSlab(SubstanceCraftBlockItemIds.MIMOSA_HOSTILIS_SLAB, MIMOSA_HOSTILIS_PLANKS);
    public static final Block MIMOSA_HOSTILIS_BUTTON = register(SubstanceCraftBlockItemIds.MIMOSA_HOSTILIS_BUTTON, p -> new ButtonBlock(MIMOSA, 30, p), BlockBehaviour.Properties.of().noCollision().strength(0.5F).pushReaction(PushReaction.POPPED));
    public static final Block MIMOSA_HOSTILIS_FENCE = register(SubstanceCraftBlockItemIds.MIMOSA_HOSTILIS_FENCE, FenceBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE).mapColor(MIMOSA_HOSTILIS_PLANKS.defaultMapColor()));
    public static final Block MIMOSA_HOSTILIS_FENCE_GATE = register(SubstanceCraftBlockItemIds.MIMOSA_HOSTILIS_FENCE_GATE,  p -> new FenceGateBlock(WoodType.OAK, p), BlockBehaviour.Properties.of().mapColor(MIMOSA_HOSTILIS_PLANKS.defaultMapColor()).forceSolidOn().instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).ignitedByLava());
    public static final Block MIMOSA_HOSTILIS_WALL = registerWall(SubstanceCraftBlockItemIds.MIMOSA_HOSTILIS_WALL, MIMOSA_HOSTILIS_PLANKS);
    public static final Block MIMOSA_HOSTILIS_PRESSURE_PLATE = register(SubstanceCraftBlockItemIds.MIMOSA_HOSTILIS_PRESSURE_PLATE,  p -> new PressurePlateBlock(BlockSetType.CRIMSON, p), BlockBehaviour.Properties.of().mapColor(MIMOSA_HOSTILIS_PLANKS.defaultMapColor()).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollision().strength(0.5F).pushReaction(PushReaction.POPPED));
    public static final Block STRIPPED_MIMOSA_HOSTILIS_WOOD = register(SubstanceCraftBlockItemIds.STRIPPED_MIMOSA_HOSTILIS_WOOD, RotatedPillarBlock::new, logProperties(MapColor.PODZOL, MapColor.COLOR_BROWN, SoundType.WOOD));
    public static final Block MIMOSA_HOSTILIS_LEAVES = register(SubstanceCraftBlockItemIds.MIMOSA_HOSTILIS_LEAVES, properties -> new TintedParticleLeavesBlock(0.01F, properties), BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(Blocks::ocelotOrParrot).isSuffocating(Blocks::never).ignitedByLava().pushReaction(PushReaction.POPPED).isRedstoneConductor(Blocks::never));
    public static final Block STRIPPED_MIMOSA_HOSTILIS_LOG = register(SubstanceCraftBlockItemIds.STRIPPED_MIMOSA_HOSTILIS_LOG, RotatedPillarBlock::new, logProperties(MapColor.PODZOL, MapColor.COLOR_BROWN, SoundType.WOOD));
    public static final Block MIMOSA_HOSTILIS_SAPLING = register(SubstanceCraftBlockItemIds.MIMOSA_HOSTILIS_SAPLING, properties -> new SaplingBlock(MIMOSA_HOSTILIS, properties), BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollision().randomTicks().instabreak().sound(SoundType.GRASS).pushReaction(PushReaction.POPPED));
    public static final Block POTTED_MIMOSA_HOSTILIS_SAPLING = register(SubstanceCraftBlockItemIds.POTTED_MIMOSA_HOSTILIS_SAPLING, properties -> new FlowerPotBlock(MIMOSA_HOSTILIS_SAPLING, properties), BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.POPPED));
    public static final Block MIMOSA_HOSTILIS_ROOT = register(SubstanceCraftBlockItemIds.MIMOSA_HOSTILIS_ROOT, MimosaHostilisRoot::new, BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_ROOTS));
    public static final Block STRIPPED_MIMOSA_HOSTILIS_ROOT = register(SubstanceCraftBlockItemIds.STRIPPED_MIMOSA_HOSTILIS_ROOT, RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_ROOTS));
    public static final Block BAUXITE_ORE = register(SubstanceCraftBlockItemIds.BAUXITE_ORE, Block::new, genericOre());
    public static final Block DEEPSLATE_BAUXITE_ORE = register(SubstanceCraftBlockItemIds.DEEPSLATE_BAUXITE_ORE, Block::new, genericDeepslateOre());
    public static final Block PALLADIUM_ORE = register(SubstanceCraftBlockItemIds.PALLADIUM_ORE, Block::new, genericOre());
    public static final Block DEEPSLATE_PALLADIUM_ORE = register(SubstanceCraftBlockItemIds.DEEPSLATE_PALLADIUM_ORE, Block::new, genericDeepslateOre());
    public static final Block CRYOLITE_ORE = register(SubstanceCraftBlockItemIds.CRYOLITE_ORE, Block::new, genericOre());
    public static final Block DEEPSLATE_CRYOLITE_ORE = register(SubstanceCraftBlockItemIds.DEEPSLATE_CRYOLITE_ORE, Block::new, genericDeepslateOre());
    public static final Block VANADINITE_ORE = register(SubstanceCraftBlockItemIds.VANADINITE_ORE, Block::new, genericOre());
    public static final Block DEEPSLATE_VANADINITE_ORE = register(SubstanceCraftBlockItemIds.DEEPSLATE_VANADINITE_ORE, Block::new, genericDeepslateOre());
    public static final Block ANISE_PLANT = register(SubstanceCraftBlockItemIds.ANISE_PLANT, AnisePlant::new, genericFlower());
    public static final Block CHROMITITE = register(SubstanceCraftBlockItemIds.CHROMITITE, Block::new, genericRock());

    public static final BlockFamily MIMOSA_WOOD = new BlockFamily.Builder(MIMOSA_HOSTILIS_PLANKS)
            .log(MIMOSA_HOSTILIS_LOG)
            .strippedLog(STRIPPED_MIMOSA_HOSTILIS_LOG)
            .stairs(MIMOSA_HOSTILIS_STAIRS)
            .slab(MIMOSA_HOSTILIS_SLAB)
            .button(MIMOSA_HOSTILIS_BUTTON)
            .fence(MIMOSA_HOSTILIS_FENCE)
            .fenceGate(MIMOSA_HOSTILIS_FENCE_GATE)
            .wall(MIMOSA_HOSTILIS_WALL)
            .pressurePlate(MIMOSA_HOSTILIS_PRESSURE_PLATE)
            .recipeGroupPrefix("wooden")
            .recipeUnlockedBy("has_planks")
            .getFamily();

    public static Item getBlockItem(Block block) {
        return BLOCK_ITEMS.get(block);
    }

    private static Block register(BlockItemId id, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties properties) {
        Block block = factory.apply(properties.setId(id.block()));
        BLOCK_ITEMS.put(block, registerBlockItem(id.item(), block));
        return Registry.register(BuiltInRegistries.BLOCK, id.block(), block);
    }

    private static Block registerPlaceableDrug(BlockItemId id, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties properties, Drug drug) {
        Block block = factory.apply(properties.setId(id.block()));
        BLOCK_ITEMS.put(block, Registry.register(BuiltInRegistries.ITEM, id.item(),
                        new PlaceableDrugItem(
                                block, new Item.Properties().useBlockDescriptionPrefix().setId(id.item()).food(new FoodProperties.Builder().alwaysEdible().build()), drug)
                )
        );
        return Registry.register(BuiltInRegistries.BLOCK, id.block(), block);
    }

    private static Item registerBlockItem(ResourceKey<Item> key, Block block) {
        return Registry.register(BuiltInRegistries.ITEM, key, new BlockItem(block, new Item.Properties().useBlockDescriptionPrefix().setId(key)));
    }

    private static BlockPos postProcessSelf(final BlockState state, final BlockGetter blockGetter, final BlockPos blockPos) {
        return blockPos;
    }

    private static Block registerStair(BlockItemId id, Block base) {
        return register(id, p -> new StairBlock(base.defaultBlockState(), p), BlockBehaviour.Properties.ofFullCopy(base));
    }

    private static Block registerSlab(BlockItemId id, Block base) {
        return register(id, SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(base));
    }

    private static Block registerWall(BlockItemId id, Block base) {
        return register(id, WallBlock::new, BlockBehaviour.Properties.ofFullCopy(base).forceSolidOn());
    }

    private static BlockBehaviour.Properties logProperties(MapColor topColor, MapColor sideColor, SoundType soundType) {
        return BlockBehaviour.Properties.of().mapColor(state -> state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? topColor : sideColor).instrument(NoteBlockInstrument.BASS).strength(2.0F).sound(soundType).ignitedByLava();
    }

    private static BlockBehaviour.Properties genericDeepslateOre() {
        return BlockBehaviour.Properties.of().mapColor(MapColor.DEEPSLATE).strength(4.5F, 3.0F).sound(SoundType.DEEPSLATE).requiresCorrectToolForDrops();
    }

    private static BlockBehaviour.Properties genericOre() {
        return BlockBehaviour.Properties.of().strength(3.0F, 3.0F).sound(SoundType.STONE).requiresCorrectToolForDrops().mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM);
    }

    private static BlockBehaviour.Properties genericRock(MapColor mapColor) {
        return genericRock(mapColor, SoundType.STONE);
    }

    private static BlockBehaviour.Properties genericRock(MapColor mapColor, SoundType soundType) {
        return BlockBehaviour.Properties.of().mapColor(mapColor).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(soundType);
    }

    private static BlockBehaviour.Properties genericRock() {
        return genericRock(MapColor.STONE);
    }

    private static BlockBehaviour.Properties genericCrop() {
        return BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollision().randomTicks().instabreak().sound(SoundType.CROP).pushReaction(PushReaction.POPPED);
    }

    private static BlockBehaviour.Properties genericBush() {
        return BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollision().randomTicks().instabreak().sound(SoundType.SWEET_BERRY_BUSH).pushReaction(PushReaction.POPPED);
    }

    private static BlockBehaviour.Properties genericFlower() {
        return BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollision().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).ignitedByLava().pushReaction(PushReaction.POPPED);
    }

    public static void registerBlocks() {
    }

}
