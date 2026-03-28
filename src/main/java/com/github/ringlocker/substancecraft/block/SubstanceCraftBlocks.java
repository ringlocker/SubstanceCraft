package com.github.ringlocker.substancecraft.block;

import com.github.ringlocker.substancecraft.SubstanceCraft;
import com.github.ringlocker.substancecraft.block.blocks.CocaCrop;
import com.github.ringlocker.substancecraft.block.blocks.CornCrop;
import com.github.ringlocker.substancecraft.block.blocks.ElectrolysisMachine;
import com.github.ringlocker.substancecraft.block.blocks.Extractor;
import com.github.ringlocker.substancecraft.block.blocks.FermentationTank;
import com.github.ringlocker.substancecraft.block.blocks.Grapevine;
import com.github.ringlocker.substancecraft.block.blocks.HashPress;
import com.github.ringlocker.substancecraft.block.blocks.HeatedMixer;
import com.github.ringlocker.substancecraft.block.blocks.MarijuanaPlant;
import com.github.ringlocker.substancecraft.block.blocks.Mixer;
import com.github.ringlocker.substancecraft.block.blocks.Oxidizer;
import com.github.ringlocker.substancecraft.block.blocks.PeyoteCactus;
import com.github.ringlocker.substancecraft.block.blocks.PsilocybinMushroom;
import com.github.ringlocker.substancecraft.block.blocks.Refinery;
import com.github.ringlocker.substancecraft.item.Drug;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import java.util.HashMap;
import java.util.function.Function;

public class SubstanceCraftBlocks {

    private static final HashMap<Block, Item> BLOCK_ITEMS = new HashMap<>();

    public static final Block MARIJUANA_PLANT = registerBlock("marijuana_plant", MarijuanaPlant::new, BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).randomTicks().noCollision().sound(SoundType.SWEET_BERRY_BUSH).pushReaction(PushReaction.DESTROY));
    public static final Block HASH_PRESS = registerBlock("hash_press", HashPress::new, BlockBehaviour.Properties.of().strength(3.5F));
    public static final Block REFINERY = registerBlock("refinery", Refinery::new, BlockBehaviour.Properties.of().strength(3.5F).sound(SoundType.STONE));
    public static final Block OIL_SHALE = registerBlock("oil_shale_block", Block::new, BlockBehaviour.Properties.of().strength(0.6F).sound(SoundType.GRAVEL));
    public static final Block ELECTROLYSIS_MACHINE = registerBlock("electrolysis", ElectrolysisMachine::new, BlockBehaviour.Properties.of().strength(3.5F).sound(SoundType.STONE));
    public static final Block OXIDATION_MACHINE = registerBlock("oxidation_machine", Oxidizer::new, BlockBehaviour.Properties.of().strength(3.5F).sound(SoundType.STONE));
    public static final Block EXTRACTOR = registerBlock("extractor", Extractor::new, BlockBehaviour.Properties.of().strength(3.5F).sound(SoundType.STONE));
    public static final Block HALITE = registerBlock("salt_block", Block::new, BlockBehaviour.Properties.of().strength(1.5F, 6.0F).sound(SoundType.CALCITE));
    public static final Block MIXER = registerBlock("mixer", Mixer::new, BlockBehaviour.Properties.of().strength(3.5F).sound(SoundType.STONE));
    public static final Block HEATED_MIXER = registerBlock("heated_mixer", HeatedMixer::new, BlockBehaviour.Properties.of().strength(3.5F).sound(SoundType.STONE));
    public static final Block FERMENTATION_TANK = registerBlock("fermentation_tank", FermentationTank::new, BlockBehaviour.Properties.of().strength(3.5F).sound(SoundType.STONE));
    public static final Block CORN_CROP = registerBlock("corn_crop", CornCrop::new, BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT));
    public static final Block COCA_CROP = registerBlock("coca_plant", CocaCrop::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH));
    public static final Block SYLVITE = registerBlock("sylvite_block", Block::new, BlockBehaviour.Properties.of().strength(1.5F, 6.0F).sound(SoundType.CALCITE));
    public static final Block SULFUR_ORE = registerBlock("sulfur_ore", Block::new, BlockBehaviour.Properties.of().strength(1.5F, 6.0F).sound(SoundType.CALCITE));
    public static final Block DEEPSLATE_SULFUR_ORE = registerBlock("deepslate_sulfur_ore", Block::new, BlockBehaviour.Properties.of().strength(4.5F, 3.0F).sound(SoundType.CALCITE));
    public static final Block TRONA = registerBlock("trona_block", Block::new, BlockBehaviour.Properties.of().strength(1.5F, 6.0F).sound(SoundType.STONE));
    public static final Block PYROLUSITE_ORE = registerBlock("pyrolusite_ore", Block::new, BlockBehaviour.Properties.of().strength(3.0F, 3.0F).sound(SoundType.STONE));
    public static final Block DEEPSLATE_PYROLUSITE_ORE = registerBlock("deepslate_pyrolusite_ore", Block::new, BlockBehaviour.Properties.of().strength(4.5F, 3.0F).sound(SoundType.STONE));
    public static final Block LIMESTONE = registerBlock("limestone_block", Block::new, BlockBehaviour.Properties.of().strength(1.5F, 6.0F).sound(SoundType.STONE));
    public static final Block PHOSPHORITE = registerBlock("phosphorite_block", Block::new, BlockBehaviour.Properties.of().strength(1.5F, 6.0F).sound(SoundType.CALCITE));
    public static final Block GRAPEVINE = registerBlock("grapevine", Grapevine::new, BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).randomTicks().noCollision().sound(SoundType.SWEET_BERRY_BUSH).pushReaction(PushReaction.DESTROY));
    public static final Block PSILOCYBIN = registerPlaceableDrugBlock("psilocybin", PsilocybinMushroom::new, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).noCollision().randomTicks().instabreak().sound(SoundType.GRASS).hasPostProcess(Blocks::always).pushReaction(PushReaction.DESTROY), Drug.PSILOCYBIN);
    public static final Block PEYOTE_CACTUS = registerBlock("peyote_cactus", PeyoteCactus::new, BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).randomTicks().strength(0.4F).sound(SoundType.WOOL).pushReaction(PushReaction.DESTROY));

    public static Item getBlockItem(Block block) {
        return BLOCK_ITEMS.get(block);
    }

    private static Block registerBlock(String name, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties properties) {
        ResourceKey<Block> key = key(name);
        Block block = factory.apply(properties.setId(key));
        BLOCK_ITEMS.put(block, registerBlockItem(name, block));
        return Registry.register(BuiltInRegistries.BLOCK, key, block);
    }

    private static Block registerPlaceableDrugBlock(String name, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties properties, Drug drug) {
        ResourceKey<Block> key = key(name);
        ResourceKey<Item> itemKey = itemKey(name);
        Block block = factory.apply(properties.setId(key));
        BLOCK_ITEMS.put(block, Registry.register(BuiltInRegistries.ITEM, itemKey,
                new PlaceableDrugItem(
                        block, new Item.Properties().useBlockDescriptionPrefix().setId(itemKey).food(new FoodProperties.Builder().alwaysEdible().build()), drug)
                )
        );
        return Registry.register(BuiltInRegistries.BLOCK, key, block);
    }

    private static Item registerBlockItem(String name, Block block) {
        ResourceKey<Item> key = itemKey(name);
        return Registry.register(BuiltInRegistries.ITEM, key, new BlockItem(block, new Item.Properties().useBlockDescriptionPrefix().setId(key)));
    }

    private static ResourceKey<Block> key(String name) {
        return ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID, name));
    }

    private static ResourceKey<Item> itemKey(String name) {
        return ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID, name));
    }

    public static void registerBlocks() {
    }

}
