package com.hotgo.javafinal.entity.client;

import com.google.common.collect.Maps;
import com.hotgo.javafinal.JavaFinal;
import com.hotgo.javafinal.entity.SkineaterVariant;
import com.hotgo.javafinal.entity.custom.SkineaterEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;

public class SkineaterRenderer extends MobRenderer<SkineaterEntity, SkineaterModel<SkineaterEntity>> {
    private static final Map<SkineaterVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(SkineaterVariant.class), map -> {
                map.put(SkineaterVariant.GREEN,
                        ResourceLocation.fromNamespaceAndPath(JavaFinal.MOD_ID, "textures/entity/skineater/skineater_green.png"));
                map.put(SkineaterVariant.BLUE,
                        ResourceLocation.fromNamespaceAndPath(JavaFinal.MOD_ID, "textures/entity/skineater/skineater_blue.png"));
                map.put(SkineaterVariant.RED,
                        ResourceLocation.fromNamespaceAndPath(JavaFinal.MOD_ID, "textures/entity/skineater/skineater_red.png"));
                map.put(SkineaterVariant.ORANGE,
                        ResourceLocation.fromNamespaceAndPath(JavaFinal.MOD_ID, "textures/entity/skineater/skineater_orange.png"));
            });

    public SkineaterRenderer(EntityRendererProvider.Context context) {
        super(context, new SkineaterModel<>(context.bakeLayer(SkineaterModel.LAYER_LOCATION)), 0.25f);
    }

    @Override
    public ResourceLocation getTextureLocation(SkineaterEntity entity) {
        return LOCATION_BY_VARIANT.get(entity.getVariant());
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
