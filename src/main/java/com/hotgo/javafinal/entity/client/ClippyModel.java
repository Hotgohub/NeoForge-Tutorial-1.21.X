package com.hotgo.javafinal.entity.client;

import com.hotgo.javafinal.JavaFinal;
import com.hotgo.javafinal.entity.custom.ClippyEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

import javax.swing.text.html.parser.Entity;

public class ClippyModel<T extends ClippyEntity> extends HierarchicalModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION =
            new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(JavaFinal.MOD_ID, "clippy"), "main");

    private final ModelPart body;
    private final ModelPart eyebrows;
    private final ModelPart eyes;
    private final ModelPart paperclip;

    public ClippyModel(ModelPart root) {
        this.body = root.getChild("body");
        this.eyebrows = this.body.getChild("eyebrows");
        this.eyes = this.body.getChild("eyes");
        this.paperclip = this.body.getChild("paperclip");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(-3.0F, 9.0F, -1.5F));

        PartDefinition eyebrows = body.addOrReplaceChild("eyebrows", CubeListBuilder.create(), PartPose.offset(2.0F, 0.0F, 1.0F));

        PartDefinition cube_r1 = eyebrows.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(6, 14).addBox(0.0F, -1.0F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 0.0F, -1.0F, 0.0F, 0.0F, 1.5708F));

        PartDefinition cube_r2 = eyebrows.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(16, 6).addBox(0.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 1.0F, -1.0F, 0.0F, 0.0F, 1.5708F));

        PartDefinition cube_r3 = eyebrows.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(16, 4).addBox(0.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 1.0F, -1.0F, 0.0F, 0.0F, 1.5708F));

        PartDefinition cube_r4 = eyebrows.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(10, 14).addBox(0.0F, -1.0F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 0.0F, -1.0F, 0.0F, 0.0F, 1.5708F));

        PartDefinition eyes = body.addOrReplaceChild("eyes", CubeListBuilder.create().texOffs(0, 6).addBox(1.0F, -3.0F, -1.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(8, 6).addBox(-4.0F, -3.0F, -1.0F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 6.0F, 1.0F));

        PartDefinition paperclip = body.addOrReplaceChild("paperclip", CubeListBuilder.create().texOffs(0, 10).addBox(-2.0F, -1.0F, 0.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(12, 12).addBox(-2.0F, -16.0F, 0.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(16, 2).addBox(-1.0F, -5.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 15.0F, 1.0F));

        PartDefinition cube_r5 = paperclip.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(14, 14).addBox(0.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -5.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

        PartDefinition cube_r6 = paperclip.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(0, 4).addBox(-9.0F, -1.0F, 0.0F, 7.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -3.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

        PartDefinition cube_r7 = paperclip.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(0, 2).addBox(-9.0F, -1.0F, 0.0F, 7.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -4.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

        PartDefinition cube_r8 = paperclip.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(0, 0).addBox(-9.0F, -1.0F, 0.0F, 10.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -4.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

        PartDefinition cube_r9 = paperclip.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(0, 16).addBox(0.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -6.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

        PartDefinition cube_r10 = paperclip.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(14, 10).addBox(0.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -4.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

        PartDefinition cube_r11 = paperclip.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(0, 14).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -14.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

        PartDefinition cube_r12 = paperclip.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(6, 12).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -14.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

        PartDefinition cube_r13 = paperclip.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(0, 12).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -2.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

        PartDefinition cube_r14 = paperclip.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(8, 10).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -2.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }

    @Override
    public void setupAnim(ClippyEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netBodyYaw, float bodyPitch) {
        //this tells it to not be additive for each animation
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyBodyRotation(netBodyYaw, bodyPitch);

        this.animateWalk(ClippyAnimations.ANIM_CLIPPY_WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
        this.animate(entity.idleAnimationState, ClippyAnimations.ANIM_CLIPPY_IDLE, ageInTicks, 1f);
    }

    //use this to define how the head or in this case entire entity rotates when looking at the player
    private void applyBodyRotation(float bodyYaw, float bodyPitch) {
        bodyYaw = Mth.clamp(bodyYaw, -30f, 30f);
        bodyPitch = Mth.clamp(bodyPitch, -25f, 45f);

        this.body.yRot = bodyYaw * ((float)Math.PI / 180f);
        this.body.xRot = bodyPitch * ((float)Math.PI / 180f);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        body.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public ModelPart root() {
        return body;
    }
}
