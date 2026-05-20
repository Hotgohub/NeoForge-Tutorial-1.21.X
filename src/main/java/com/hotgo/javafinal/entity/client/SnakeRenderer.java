package com.hotgo.javafinal.entity.client;

import com.hotgo.javafinal.entity.custom.SnakeEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

import java.util.List;
import java.util.Map;

public class SnakeRenderer extends EntityRenderer<SnakeEntity> {

    private static final float SCALE = 0.02f;
    private static final float Y_OFFSET = 0f;

    private static final Map<String, ResourceLocation> MATERIAL_TEXTURES = Map.ofEntries(
            Map.entry("Upper_Arms",        tex("sna_arm1")),
            Map.entry("Lower_Arms",        tex("sna_arm2")),
            Map.entry("Upper_Holster_Leg", tex("sna_leg4")),
            Map.entry("Backside",          tex("sna_leg2")),
            Map.entry("Lower_Legs",        tex("sna_leg3")),
            Map.entry("Feet",              tex("sna_boot")),
            Map.entry("Upper_Leg",         tex("sna_leg1")),
            Map.entry("Head_Sides",        tex("sna_face2")),
            Map.entry("Head_Front",        tex("sna_face")),
            Map.entry("Head_Back",         tex("sna_face3")),
            Map.entry("Head",              tex("sna_hed")),
            Map.entry("Ear_Fronts",        tex("sna_ear1")),
            Map.entry("Ear_Sides",         tex("sna_ear2")),
            Map.entry("Chest",             tex("sna_chest1")),
            Map.entry("Chest_Sides",       tex("sna_chest3")),
            Map.entry("Back",              tex("sna_chest2")),
            Map.entry("Finger_Fronts",     tex("sna_fin")),
            Map.entry("Finger_Backs",      tex("sna_fin3")),
            Map.entry("Finger_Sides",      tex("sna_fin2")),
            Map.entry("Hand_Palms",        tex("sna_hand2")),
            Map.entry("Hand_Fronts",       tex("sna_hand")),
            Map.entry("Collar_Top",        tex("sna_collar1")),
            Map.entry("Collar_Sides",      tex("sna_collar2")),
            Map.entry("Waist",             tex("sna_hip1")),
            Map.entry("Waist_Back",        tex("sna_hip2")),
            Map.entry("Waist_Sides",       tex("sna_chest4")),
            Map.entry("Neck_Front",        tex("sna_neck2")),
            Map.entry("Neck_Back",         tex("sna_neck"))
    );

    private static ResourceLocation tex(String name) {
        return ResourceLocation.fromNamespaceAndPath("javafinal", "textures/entity/snake/" + name + ".png");
    }

    private final ObjModel model;

    public SnakeRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = ObjModel.load("models/entity/snake.obj");
    }

    @Override
    public void render(SnakeEntity entity, float entityYaw, float partialTick,
                       PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {

        poseStack.pushPose();
        poseStack.mulPose(Axis.YP.rotationDegrees(-entityYaw + 180f));
        poseStack.scale(SCALE, SCALE, SCALE);

        // In 1.21.1, we pass PoseStack.Pose directly — not separate Matrix4f/Matrix3f
        PoseStack.Pose pose = poseStack.last();

        for (Map.Entry<String, List<ObjModel.Triangle>> entry : model.groups.entrySet()) {
            ResourceLocation texture = MATERIAL_TEXTURES.getOrDefault(entry.getKey(), tex("sna_hed"));
            VertexConsumer consumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(texture));

            for (ObjModel.Triangle tri : entry.getValue()) {
                submitVertex(consumer, pose, tri.v0(), tri.t0(), tri.n0(), packedLight);
                submitVertex(consumer, pose, tri.v1(), tri.t1(), tri.n1(), packedLight);
                submitVertex(consumer, pose, tri.v2(), tri.t2(), tri.n2(), packedLight);
            }
        }

        poseStack.popPose();
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }

    private void submitVertex(VertexConsumer consumer, PoseStack.Pose pose,
                              ObjModel.Vertex v, ObjModel.TexCoord t, ObjModel.Normal n, int packedLight) {
        consumer.addVertex(pose.pose(), v.x(), v.y(), v.z())
                .setColor(255, 255, 255, 255)
                .setUv(t.u(), t.v())
                .setOverlay(OverlayTexture.NO_OVERLAY)
                .setLight(packedLight)
                .setNormal(pose, n.x(), n.y(), n.z()); // 1.21.1 takes PoseStack.Pose, not Matrix3f
    }

    @Override
    public ResourceLocation getTextureLocation(SnakeEntity entity) {
        return tex("sna_hed");
    }
}