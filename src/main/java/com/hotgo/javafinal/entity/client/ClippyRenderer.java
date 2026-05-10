package com.hotgo.javafinal.entity.client;

import com.hotgo.javafinal.JavaFinal;
import com.hotgo.javafinal.entity.custom.ClippyEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class ClippyRenderer extends MobRenderer<ClippyEntity, ClippyModel<ClippyEntity>> {
    public ClippyRenderer(EntityRendererProvider.Context context) {
        super(context, new ClippyModel<>(context.bakeLayer(ClippyModel.LAYER_LOCATION)), 0.25f);
    }

    @Override
    public ResourceLocation getTextureLocation(ClippyEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(JavaFinal.MOD_ID, "textures/entity/clippy/clippy.png");
    }

    @Override
    public void render(ClippyEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        if(entity.isBaby()) {
            poseStack.scale(0.45f, 0.45f, 0.45f);
        } else {
            poseStack.scale(1f, 1f, 1f);
        }
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }
}
