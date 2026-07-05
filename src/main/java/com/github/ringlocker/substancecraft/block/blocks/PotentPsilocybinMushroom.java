package com.github.ringlocker.substancecraft.block.blocks;

import com.github.ringlocker.substancecraft.block.SubstanceCraftBlocks;
import net.minecraft.world.item.Item;

public class PotentPsilocybinMushroom extends PsilocybinMushroom {

    public PotentPsilocybinMushroom(Properties properties) {
        super(properties);
    }

    @Override
    public Item getDropItem() {
        return SubstanceCraftBlocks.getBlockItem(SubstanceCraftBlocks.PALE_PSILOCYBIN);
    }


}
