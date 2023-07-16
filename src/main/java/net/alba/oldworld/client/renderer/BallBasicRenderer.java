package net.alba.oldworld.client.renderer;

import net.alba.oldworld.OldWorld;
import net.alba.oldworld.client.model.BallBasicModel;
import net.alba.oldworld.entity.projectiles.BallBasicEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

@Environment(EnvType.CLIENT)
public class BallBasicRenderer extends EntityRenderer<BallBasicEntity> {
    private final BallBasicModel model;
    private static final Identifier TEXTURE = new Identifier(OldWorld.MOD_ID, "textures/entity/ball_basic.png");

    public BallBasicRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.model = new BallBasicModel(BallBasicModel.getTexturedModelData().createModel());
    }

    public void render(BallBasicEntity entity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.push();
        float k = (float)entity.age + g;
        matrixStack.translate(0.0f, 0.25, 0.0f);
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(MathHelper.sin(k * 0.1f) * 180.0f));
        matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(MathHelper.cos(k * 0.1f) * 180.0f));
        matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(MathHelper.sin(k * 0.15f) * 360.0f));
        VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(this.model.getLayer(TEXTURE));
        this.model.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, 1.0f, 1.0f, 1.0f, 1.0f);
        matrixStack.pop();
        super.render(entity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    public Identifier getTexture(BallBasicEntity entity) {
        return TEXTURE;
    }
}

/**
matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(MathHelper.lerp(g, entity.prevYaw, entity.getYaw()) - 90.0F));
matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(MathHelper.lerp(g, entity.prevPitch, entity.getPitch())));
this.model.setAngles(entity, g, 0.0F, -0.1F, 0.0F, 0.0F);
 */
