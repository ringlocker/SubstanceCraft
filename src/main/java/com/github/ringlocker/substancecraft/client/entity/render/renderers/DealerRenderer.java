package com.github.ringlocker.substancecraft.client.entity.render.renderers;

import com.github.ringlocker.substancecraft.SubstanceCraft;
import com.github.ringlocker.substancecraft.entity.entities.Dealer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.VillagerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.CrossedArmsItemLayer;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.client.renderer.entity.state.HoldingEntityRenderState;
import net.minecraft.client.renderer.entity.state.VillagerRenderState;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

@Environment(EnvType.CLIENT)
public class DealerRenderer extends MobRenderer<Dealer, VillagerRenderState, VillagerModel> {

    private static final ResourceLocation DEALER_TEXTURE = ResourceLocation.fromNamespaceAndPath(SubstanceCraft.MOD_ID, "textures/entity/dealer.png");

    public DealerRenderer(EntityRendererProvider.Context context) {
        super(context, new VillagerModel(context.bakeLayer(ModelLayers.WANDERING_TRADER)), 0.5F);
        this.addLayer(new CustomHeadLayer<>(this, context.getModelSet(), context.getPlayerSkinRenderCache()));
        this.addLayer(new CrossedArmsItemLayer<>(this));
    }

    public @NotNull ResourceLocation getTextureLocation(VillagerRenderState villagerRenderState) {
        return DEALER_TEXTURE;
    }

    public @NotNull VillagerRenderState createRenderState() {
        return new VillagerRenderState();
    }

    public void extractRenderState(Dealer dealer, VillagerRenderState villagerRenderState, float f) {
        super.extractRenderState(dealer, villagerRenderState, f);
        HoldingEntityRenderState.extractHoldingEntityRenderState(dealer, villagerRenderState, this.itemModelResolver);
        villagerRenderState.isUnhappy = dealer.getUnhappyCounter() > 0;
    }

}
