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

import java.util.*;

public class SnakeRenderer extends EntityRenderer<SnakeEntity> {

    private static final float SCALE = 0.02f;

    // Pivot points in model space — tune these if limbs rotate from the wrong point
    private static final float SHOULDER_Y = 77f;
    private static final float SHOULDER_X = 10f;
    private static final float SHOULDER_Z = -3f;
    private static final float HIP_Y      = 50f;
    private static final float HIP_X      = 4f;
    private static final float HIP_Z      = -3f;

    // Which material groups belong to arms/legs vs static body
    private static final Set<String> ARM_GROUPS = Set.of(
            "Upper_Arms", "Lower_Arms",
            "Finger_Fronts", "Finger_Backs", "Finger_Sides",
            "Hand_Palms", "Hand_Fronts"
    );
    private static final Set<String> LEG_GROUPS = Set.of(
            "Upper_Leg", "Lower_Legs", "Feet", "Upper_Holster_Leg"
    );

    private record LimbPart(List<ObjModel.Triangle> triangles, ResourceLocation texture) {}

    private final List<LimbPart> leftArm  = new ArrayList<>();
    private final List<LimbPart> rightArm = new ArrayList<>();
    private final List<LimbPart> leftLeg  = new ArrayList<>();
    private final List<LimbPart> rightLeg = new ArrayList<>();
    private final Map<String, List<ObjModel.Triangle>> staticGroups = new LinkedHashMap<>();

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

    public SnakeRenderer(EntityRendererProvider.Context context) {
        super(context);
        ObjModel model = ObjModel.load("models/entity/snake.obj");

        for (Map.Entry<String, List<ObjModel.Triangle>> entry : model.groups.entrySet()) {
            String name = entry.getKey();
            List<ObjModel.Triangle> tris = entry.getValue();
            ResourceLocation texture = MATERIAL_TEXTURES.getOrDefault(name, tex("sna_hed"));

            if (ARM_GROUPS.contains(name)) {
                var split = splitByX(tris);
                leftArm.add(new LimbPart(split[0], texture));
                rightArm.add(new LimbPart(split[1], texture));
            } else if (LEG_GROUPS.contains(name)) {
                var split = splitByX(tris);
                leftLeg.add(new LimbPart(split[0], texture));
                rightLeg.add(new LimbPart(split[1], texture));
            } else {
                staticGroups.put(name, tris);
            }
        }
    }

    @Override
    public void render(SnakeEntity entity, float entityYaw, float partialTick,
                       PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {

        poseStack.pushPose();
        poseStack.mulPose(Axis.YP.rotationDegrees(-entityYaw));
        poseStack.scale(SCALE, SCALE, SCALE);

        // Walk animation — driven by tickCount for smooth looping
        float time = (entity.tickCount + partialTick) * 0.15f;
        boolean isMoving = entity.getDeltaMovement().horizontalDistanceSqr() > 0.0001;
        float swing = isMoving ? (float)(Math.sin(time) * 25.0) : 0f;

        // Static body parts (head, torso, waist, neck, collar etc.)
        PoseStack.Pose staticPose = poseStack.last();
        for (Map.Entry<String, List<ObjModel.Triangle>> entry : staticGroups.entrySet()) {
            ResourceLocation texture = MATERIAL_TEXTURES.getOrDefault(entry.getKey(), tex("sna_hed"));
            VertexConsumer consumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(texture));
            renderTriangles(consumer, staticPose, entry.getValue(), packedLight);
        }

        // Arms swing opposite to their paired leg
        renderLimb(bufferSource, poseStack, leftArm,  -SHOULDER_X, SHOULDER_Y, SHOULDER_Z,  swing, packedLight);
        renderLimb(bufferSource, poseStack, rightArm,  SHOULDER_X, SHOULDER_Y, SHOULDER_Z, -swing, packedLight);

        // Legs swing opposite to each other
        renderLimb(bufferSource, poseStack, leftLeg,  -HIP_X, HIP_Y, HIP_Z, -swing, packedLight);
        renderLimb(bufferSource, poseStack, rightLeg,  HIP_X, HIP_Y, HIP_Z,  swing, packedLight);

        poseStack.popPose();
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }

    private void renderLimb(MultiBufferSource bufferSource, PoseStack poseStack,
                            List<LimbPart> parts, float pivotX, float pivotY, float pivotZ,
                            float angleDeg, int packedLight) {
        poseStack.pushPose();
        // Translate to pivot, rotate, translate back — standard limb rotation trick
        poseStack.translate(pivotX, pivotY, pivotZ);
        poseStack.mulPose(Axis.XP.rotationDegrees(angleDeg));
        poseStack.translate(-pivotX, -pivotY, -pivotZ);

        PoseStack.Pose pose = poseStack.last();
        for (LimbPart part : parts) {
            VertexConsumer consumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(part.texture()));
            renderTriangles(consumer, pose, part.triangles(), packedLight);
        }
        poseStack.popPose();
    }

    private void renderTriangles(VertexConsumer consumer, PoseStack.Pose pose,
                                 List<ObjModel.Triangle> triangles, int packedLight) {
        for (ObjModel.Triangle tri : triangles) {
            // Front face
            submitVertex(consumer, pose, tri.v0(), tri.t0(), tri.n0(), packedLight);
            submitVertex(consumer, pose, tri.v1(), tri.t1(), tri.n1(), packedLight);
            submitVertex(consumer, pose, tri.v2(), tri.t2(), tri.n2(), packedLight);
            // Back face (reversed winding)
            submitVertex(consumer, pose, tri.v2(), tri.t2(), tri.n2(), packedLight);
            submitVertex(consumer, pose, tri.v1(), tri.t1(), tri.n1(), packedLight);
            submitVertex(consumer, pose, tri.v0(), tri.t0(), tri.n0(), packedLight);
        }
    }

    private void submitVertex(VertexConsumer consumer, PoseStack.Pose pose,
                              ObjModel.Vertex v, ObjModel.TexCoord t, ObjModel.Normal n, int packedLight) {
        consumer.addVertex(pose.pose(), v.x(), v.y(), v.z())
                .setColor(255, 255, 255, 255)
                .setUv(t.u(), t.v())
                .setOverlay(OverlayTexture.NO_OVERLAY)
                .setLight(packedLight)
                .setNormal(pose, n.x(), n.y(), n.z());
    }

    @Override
    public ResourceLocation getTextureLocation(SnakeEntity entity) {
        return tex("sna_hed");
    }

    @SuppressWarnings("unchecked")
    private static List<ObjModel.Triangle>[] splitByX(List<ObjModel.Triangle> triangles) {
        List<ObjModel.Triangle> left  = new ArrayList<>();
        List<ObjModel.Triangle> right = new ArrayList<>();
        for (ObjModel.Triangle tri : triangles) {
            float avgX = (tri.v0().x() + tri.v1().x() + tri.v2().x()) / 3f;
            if (avgX < 0) left.add(tri);
            else right.add(tri);
        }
        return new List[]{left, right};
    }
}