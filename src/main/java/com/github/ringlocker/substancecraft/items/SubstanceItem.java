package com.github.ringlocker.substancecraft.items;

import net.minecraft.world.item.Item;

public class SubstanceItem extends Item {

    private final int color;

    public SubstanceItem(Item.Properties properties, int color) {
        super(properties);
        this.color = color;
    }

    public int getColor() {
        return color;
    }
}
