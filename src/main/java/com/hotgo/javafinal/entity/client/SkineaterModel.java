package com.hotgo.javafinal.entity.client;

import com.hotgo.javafinal.JavaFinal;
import com.hotgo.javafinal.entity.custom.SkineaterEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class SkineaterModel<T extends SkineaterEntity> extends HierarchicalModel<T> {

    public static final ModelLayerLocation LAYER_LOCATION =
            new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(JavaFinal.MOD_ID, "skineater"), "main");

    private final ModelPart root;
    private final ModelPart Waist;
    private final ModelPart Neck;
    private final ModelPart Body;
    private final ModelPart ArmR;
    private final ModelPart UpperArmR;
    private final ModelPart LowerArmR;
    private final ModelPart ArmL;
    private final ModelPart UpperArmL;
    private final ModelPart LowerArmL;
    private final ModelPart Head;
    private final ModelPart Mouth;
    private final ModelPart UpperHead;
    private final ModelPart LegL;
    private final ModelPart UpperLegL;
    private final ModelPart LowerLegL;
    private final ModelPart LowerLegBranchL;
    private final ModelPart FootL;
    private final ModelPart LegR;
    private final ModelPart UpperLegR;
    private final ModelPart LowerLegR;
    private final ModelPart LowerLegBranchR;
    private final ModelPart FootR;

    public SkineaterModel(ModelPart root) {
        this.root = root.getChild("root");
        this.Waist = this.root.getChild("Waist");
        this.Neck = this.Waist.getChild("Neck");
        this.Body = this.Waist.getChild("Body");
        this.ArmR = this.Waist.getChild("ArmR");
        this.UpperArmR = this.ArmR.getChild("UpperArmR");
        this.LowerArmR = this.ArmR.getChild("LowerArmR");
        this.ArmL = this.Waist.getChild("ArmL");
        this.UpperArmL = this.ArmL.getChild("UpperArmL");
        this.LowerArmL = this.ArmL.getChild("LowerArmL");
        this.Head = this.Waist.getChild("Head");
        this.Mouth = this.Head.getChild("Mouth");
        this.UpperHead = this.Head.getChild("UpperHead");
        this.LegL = this.root.getChild("LegL");
        this.UpperLegL = this.LegL.getChild("UpperLegL");
        this.LowerLegL = this.LegL.getChild("LowerLegL");
        this.LowerLegBranchL = this.LowerLegL.getChild("LowerLegBranchL");
        this.FootL = this.LowerLegL.getChild("FootL");
        this.LegR = this.root.getChild("LegR");
        this.UpperLegR = this.LegR.getChild("UpperLegR");
        this.LowerLegR = this.LegR.getChild("LowerLegR");
        this.LowerLegBranchR = this.LowerLegR.getChild("LowerLegBranchR");
        this.FootR = this.LowerLegR.getChild("FootR");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        // True root — owns Waist, LegL, and LegR so the animation system can traverse all parts
        PartDefinition root = partdefinition.addOrReplaceChild("root",
                CubeListBuilder.create(), PartPose.ZERO);

        PartDefinition Waist = root.addOrReplaceChild("Waist",
                CubeListBuilder.create(), PartPose.offset(0.0F, 8.0F, 0.25F));

        PartDefinition Neck = Waist.addOrReplaceChild("Neck",
                CubeListBuilder.create(), PartPose.offset(0.0F, -14.5F, 0.0F));

        Neck.addOrReplaceChild("Neck_r1", CubeListBuilder.create()
                        .texOffs(48, 20)
                        .addBox(-5.0F, 1.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, -1.5F, 4.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition Body = Waist.addOrReplaceChild("Body",
                CubeListBuilder.create(), PartPose.offset(0.0F, -14.0F, 0.0F));

        Body.addOrReplaceChild("Body_r1", CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-2.0F, 0.0F, -6.0F, 4.0F, 14.0F, 12.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition ArmR = Waist.addOrReplaceChild("ArmR",
                CubeListBuilder.create(), PartPose.offset(-6.0F, -12.5F, 0.0F));

        ArmR.addOrReplaceChild("UpperArmR", CubeListBuilder.create()
                        .texOffs(0, 40)
                        .addBox(-3.0F, -1.5F, -1.5F, 3.0F, 9.0F, 3.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, 0.0F, 0.0F));

        ArmR.addOrReplaceChild("LowerArmR", CubeListBuilder.create()
                        .texOffs(24, 42)
                        .addBox(-1.5F, 0.0F, -1.5F, 3.0F, 9.0F, 3.0F, new CubeDeformation(0.0F)),
                PartPose.offset(-1.5F, 7.5F, 0.0F));

        PartDefinition ArmL = Waist.addOrReplaceChild("ArmL",
                CubeListBuilder.create(), PartPose.offset(6.0F, -12.5F, 0.0F));

        ArmL.addOrReplaceChild("UpperArmL", CubeListBuilder.create()
                        .texOffs(12, 40)
                        .addBox(0.0F, -1.5F, -1.5F, 3.0F, 9.0F, 3.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, 0.0F, 0.0F));

        ArmL.addOrReplaceChild("LowerArmL", CubeListBuilder.create()
                        .texOffs(32, 30)
                        .addBox(-1.5F, 0.0F, -1.5F, 3.0F, 9.0F, 3.0F, new CubeDeformation(0.0F)),
                PartPose.offset(1.5F, 7.5F, 0.0F));

        PartDefinition Head = Waist.addOrReplaceChild("Head",
                CubeListBuilder.create(), PartPose.offset(0.0F, -15.0F, 0.0F));

        PartDefinition Mouth = Head.addOrReplaceChild("Mouth",
                CubeListBuilder.create(), PartPose.offset(0.0F, -2.0F, 4.0F));

        Mouth.addOrReplaceChild("Mouth_r1", CubeListBuilder.create()
                        .texOffs(32, 0)
                        .addBox(-8.0F, 0.0F, -4.0F, 8.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition UpperHead = Head.addOrReplaceChild("UpperHead",
                CubeListBuilder.create(), PartPose.offset(0.0F, -2.0F, 4.0F));

        UpperHead.addOrReplaceChild("UpperHead_r1", CubeListBuilder.create()
                        .texOffs(0, 26)
                        .addBox(-4.0F, -8.0F, -5.0F, 8.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(-1.0F, 2.0F, -4.0F, 0.0F, -1.5708F, 0.0F));

        // LegL and LegR are now children of root, not partdefinition
        PartDefinition LegL = root.addOrReplaceChild("LegL",
                CubeListBuilder.create(), PartPose.offset(3.0F, 8.0F, 0.25F));

        LegL.addOrReplaceChild("UpperLegL", CubeListBuilder.create()
                        .texOffs(32, 10)
                        .addBox(-2.5F, 0.0F, -1.5F, 5.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition LowerLegL = LegL.addOrReplaceChild("LowerLegL",
                CubeListBuilder.create(), PartPose.offset(0.0F, 7.0F, 0.0F));

        LowerLegL.addOrReplaceChild("LowerLegBranchL", CubeListBuilder.create()
                        .texOffs(44, 30)
                        .addBox(-1.5F, 0.0F, -1.5F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, 0.0F, 0.0F));

        LowerLegL.addOrReplaceChild("FootL", CubeListBuilder.create()
                        .texOffs(48, 15)
                        .addBox(-1.0F, 0.0F, -3.25F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, 8.0F, 0.0F));

        PartDefinition LegR = root.addOrReplaceChild("LegR",
                CubeListBuilder.create(), PartPose.offset(-3.0F, 8.0F, 0.25F));

        LegR.addOrReplaceChild("UpperLegR", CubeListBuilder.create()
                        .texOffs(32, 20)
                        .addBox(-2.5F, 0.0F, -1.5F, 5.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition LowerLegR = LegR.addOrReplaceChild("LowerLegR",
                CubeListBuilder.create(), PartPose.offset(0.0F, 7.0F, 0.0F));

        LowerLegR.addOrReplaceChild("LowerLegBranchR", CubeListBuilder.create()
                        .texOffs(36, 42)
                        .addBox(-1.5F, 0.0F, -1.5F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, 0.0F, 0.0F));

        LowerLegR.addOrReplaceChild("FootR", CubeListBuilder.create()
                        .texOffs(48, 10)
                        .addBox(-1.0F, 0.0F, -3.25F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)),
                PartPose.offset(0.0F, 8.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(SkineaterEntity entity, float limbSwing, float limbSwingAmount,
                          float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyBodyRotation(netHeadYaw, headPitch);

        this.animateWalk(SkineaterAnimations.ANIM_SKINEATER_WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
        this.animate(entity.idleAnimationState, SkineaterAnimations.ANIM_SKINEATER_IDLE, ageInTicks, 1f);
    }

    private void applyBodyRotation(float headYaw, float headPitch) {
        headYaw = Mth.clamp(headYaw, -30f, 30f);
        headPitch = Mth.clamp(headPitch, -25f, 45f);

        this.Head.yRot = headYaw * ((float) Math.PI / 180f);
        this.Head.xRot = headPitch * ((float) Math.PI / 180f);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer,
                               int packedLight, int packedOverlay, int color) {
        this.root.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public ModelPart root() {
        return this.root;
    }
}