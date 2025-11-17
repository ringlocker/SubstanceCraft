package com.github.ringlocker.substancecraft.client.entity.render;

import com.github.ringlocker.substancecraft.client.entity.render.renderers.DealerRenderer;
import com.github.ringlocker.substancecraft.entity.SubstanceCraftEntities;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.EntityRenderers;

@Environment(EnvType.CLIENT)
public class SubstanceCraftEntityRenderers {

    public static void registerEntityRenderers() {
        EntityRenderers.register(SubstanceCraftEntities.DEALER, DealerRenderer::new);
    }

}
