package com.github.ringlocker.substancecraft.item.items;

import com.github.ringlocker.substancecraft.item.MatterState;
import net.minecraft.world.item.Item;

public class SubstanceItem extends Item {

    private final int color;
    private final MatterState state;

    public SubstanceItem(Item.Properties properties, int color, MatterState state) { // TODO: drink chloroform
        super(properties);
        this.color = color;
        this.state = state;
    }

    public int getColor() {
        return color;
    }

    public MatterState getState() {
        return state;
    }

}
