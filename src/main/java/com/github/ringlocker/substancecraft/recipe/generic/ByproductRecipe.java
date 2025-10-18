package com.github.ringlocker.substancecraft.recipe.generic;

import net.minecraft.world.item.ItemStack;

import java.util.List;

public interface ByproductRecipe {

    List<ItemStack> getByproducts();

    ItemStack getResult();

}
