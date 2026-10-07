package com.github.ringlocker.substancecraft.item.items;

import com.github.ringlocker.substancecraft.item.SubstanceTexture;
import com.github.ringlocker.substancecraft.item.SubstanceItemModelProvider;
import com.github.ringlocker.substancecraft.item.SubstanceTransparency;
import net.minecraft.world.item.Item;

public class SubstanceItem extends Item {

    private final int color;
    private final SubstanceItemModelProvider modelProvider;

    public SubstanceItem(Item.Properties properties, int color, SubstanceTexture matterState, SubstanceTransparency transparency) { // TODO: drink chloroform
        super(properties);
        this.color = color;
        this.modelProvider = new SubstanceItemModelProvider(matterState, transparency);

    }

    public SubstanceItem(Item.Properties properties, int color, SubstanceTexture matterState) {
        this(properties, color, matterState, (matterState == SubstanceTexture.SOLID || matterState == SubstanceTexture.INGOT) ? SubstanceTransparency.OPAQUE : SubstanceTransparency.TRANSLUCENT);
    }

    public int getColor() {
        return color;
    }

    public SubstanceItemModelProvider getModelProvider() {
        return modelProvider;
    }

}
