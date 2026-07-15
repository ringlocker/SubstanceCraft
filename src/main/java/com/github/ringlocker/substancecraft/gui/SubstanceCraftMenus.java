package com.github.ringlocker.substancecraft.gui;


import com.github.ringlocker.substancecraft.SubstanceCraft;
import com.github.ringlocker.substancecraft.gui.menus.ElectrolysisMachineMenu;
import com.github.ringlocker.substancecraft.gui.menus.ExtractorMenu;
import com.github.ringlocker.substancecraft.gui.menus.FermentationTankMenu;
import com.github.ringlocker.substancecraft.gui.menus.HashPressMenu;
import com.github.ringlocker.substancecraft.gui.menus.HeatedMixerMenu;
import com.github.ringlocker.substancecraft.gui.menus.MixerMenu;
import com.github.ringlocker.substancecraft.gui.menus.OxidizerMenu;
import com.github.ringlocker.substancecraft.gui.menus.RefineryMenu;
import net.fabricmc.fabric.api.menu.v1.ExtendedMenuType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.inventory.MenuType;

public class SubstanceCraftMenus {

    public static final MenuType<HashPressMenu> HASH_PRESS = Registry.register(
            BuiltInRegistries.MENU,
            Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "hash_press"),
            new ExtendedMenuType<>(HashPressMenu::new, BlockPos.STREAM_CODEC.cast())
    );

    public static final MenuType<RefineryMenu> REFINERY = Registry.register(
            BuiltInRegistries.MENU,
            Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "refinery"),
            new ExtendedMenuType<>(RefineryMenu::new, BlockPos.STREAM_CODEC.cast())
    );

    public static final MenuType<OxidizerMenu> OXIDIZER = Registry.register(
            BuiltInRegistries.MENU,
            Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "oxidizer"),
            new ExtendedMenuType<>(OxidizerMenu::new, BlockPos.STREAM_CODEC.cast())
    );

    public static final MenuType<ElectrolysisMachineMenu> ELECTROLYSIS_MACHINE = Registry.register(
            BuiltInRegistries.MENU,
            Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "electrolysis_machine"),
            new ExtendedMenuType<>(ElectrolysisMachineMenu::new, BlockPos.STREAM_CODEC.cast())
    );

    public static final MenuType<ExtractorMenu> EXTRACTOR = Registry.register(
            BuiltInRegistries.MENU,
            Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "extractor"),
            new ExtendedMenuType<>(ExtractorMenu::new, BlockPos.STREAM_CODEC.cast())
    );

    public static final MenuType<MixerMenu> MIXER = Registry.register(
            BuiltInRegistries.MENU,
            Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "mixer"),
            new ExtendedMenuType<>(MixerMenu::new, BlockPos.STREAM_CODEC.cast())
    );

    public static final MenuType<HeatedMixerMenu> HEATED_MIXER = Registry.register(
            BuiltInRegistries.MENU,
            Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "heated_mixer"),
            new ExtendedMenuType<>(HeatedMixerMenu::new, BlockPos.STREAM_CODEC.cast())
    );

    public static final MenuType<FermentationTankMenu> FERMENTATION_TANK = Registry.register(
            BuiltInRegistries.MENU,
            Identifier.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "fermentation_tank"),
            new ExtendedMenuType<>(FermentationTankMenu::new, BlockPos.STREAM_CODEC.cast())
    );

    public static void registerMenus() {
    }

}
