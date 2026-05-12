package com.hotgo.javafinal.entity.client;

import com.hotgo.javafinal.JavaFinal;
import com.hotgo.javafinal.entity.custom.SkineaterEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SkineaterRenderer extends MobRenderer<SkineaterEntity, SkineaterModel<SkineaterEntity>> {
    public SkineaterRenderer(EntityRendererProvider.Context context) {
        super(context, new SkineaterModel<>(context.bakeLayer(SkineaterModel.LAYER_LOCATION)), 0.25f);
    }

    @Override
    public ResourceLocation getTextureLocation(SkineaterEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(JavaFinal.MOD_ID, "textures/entity/skineater/skineater.png");
    }

    @Override
    public void render(SkineaterEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        if(entity.isBaby()) {
            poseStack.scale(0.45f, 0.45f, 0.45f);
        } else {
            poseStack.scale(1f, 1f, 1f);
        }
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }
}
