package com.github.ringlocker.substancecraft.item.items;

import com.github.ringlocker.substancecraft.item.MatterState;
import com.github.ringlocker.substancecraft.item.SubstanceItemModelProvider;
import com.github.ringlocker.substancecraft.item.Transparency;
import net.minecraft.world.item.Item;

public class SubstanceItem extends Item {

    private final int color;
    private final SubstanceItemModelProvider modelProvider;

    public SubstanceItem(Item.Properties properties, int color, MatterState matterState, Transparency transparency) { // TODO: drink chloroform
        super(properties);
        this.color = color;
        this.modelProvider = new SubstanceItemModelProvider(matterState, transparency);

    }

    public SubstanceItem(Item.Properties properties, int color, MatterState matterState) {
        this(properties, color, matterState, matterState == MatterState.SOLID ? Transparency.OPAQUE : Transparency.TRANSLUCENT);
    }

    public int getColor() {
        return color;
    }

    public SubstanceItemModelProvider getModelProvider() {
        return modelProvider;
    }

}
