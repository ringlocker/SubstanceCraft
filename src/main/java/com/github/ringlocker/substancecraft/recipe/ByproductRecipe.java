package com.github.ringlocker.substancecraft.recipe;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public interface ByproductRecipe {

    List<ItemStack> getByproducts();

    ItemStack getResult();

    Component getTypeString();

}
