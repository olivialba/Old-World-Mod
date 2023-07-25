package net.alba.oldworld.client.renderer;

import org.joml.Matrix4f;

import net.alba.oldworld.entity.projectiles.InvisibleProjectile;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.random.Random;

@Environment(EnvType.CLIENT)
public class InvisibleRenderer extends EntityRenderer<InvisibleProjectile> {
    private static final float HALF_SQRT_3 = (float)(Math.sqrt(3.0) / 2.0);

    public InvisibleRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public void render(InvisibleProjectile projectile, float f,  float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        lightBeams(projectile, matrixStack, vertexConsumerProvider, g);
        super.render(projectile, f, g, matrixStack, vertexConsumerProvider, i);
    }

    public static void lightBeams(InvisibleProjectile placeHolder, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, float g) {
        float l = ((float)placeHolder.age + g) / 200.0F;
        float m = Math.min(l > 0.8F ? (l - 0.8F) / 0.2F : 0.0F, 1.0F);
        Random random = Random.create(432L);
        VertexConsumer vertexConsumer4 = vertexConsumerProvider.getBuffer(RenderLayer.getLightning());
        matrixStack.push();
        for(int n = 0; (float)n < (l + l * l) / 2.0F * 60.0F; ++n) {
            matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(random.nextFloat() * 360.0F));
            matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(random.nextFloat() * 360.0F));
            matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(random.nextFloat() * 360.0F));
            matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(random.nextFloat() * 360.0F));
            matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(random.nextFloat() * 360.0F));
            matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(random.nextFloat() * 360.0F + l * 90.0F));
            float o = random.nextFloat() * 20.0F + 5.0F + m * 10.0F;
            float p = random.nextFloat() * 2.0F + 1.0F + m * 2.0F;
            Matrix4f matrix4f = matrixStack.peek().getPositionMatrix();
            int q = (int)(255.0F * (1.0F - m));
            putDeathLightSourceVertex(vertexConsumer4, matrix4f, q);
            putDeathLightNegativeXTerminalVertex(vertexConsumer4, matrix4f, o, p);
            putDeathLightPositiveXTerminalVertex(vertexConsumer4, matrix4f, o, p);
            putDeathLightSourceVertex(vertexConsumer4, matrix4f, q);
            putDeathLightPositiveXTerminalVertex(vertexConsumer4, matrix4f, o, p);
            putDeathLightPositiveZTerminalVertex(vertexConsumer4, matrix4f, o, p);
            putDeathLightSourceVertex(vertexConsumer4, matrix4f, q);
            putDeathLightPositiveZTerminalVertex(vertexConsumer4, matrix4f, o, p);
            putDeathLightNegativeXTerminalVertex(vertexConsumer4, matrix4f, o, p);
            }
        matrixStack.pop();
    }
    private static void putDeathLightSourceVertex(VertexConsumer buffer, Matrix4f matrix, int alpha) {
        buffer.vertex(matrix, 0.0F, 0.0F, 0.0F).color(255, 255, 255, alpha).next();
    }
    private static void putDeathLightNegativeXTerminalVertex(VertexConsumer buffer, Matrix4f matrix, float radius, float width) {
        buffer.vertex(matrix, -HALF_SQRT_3 * width, radius, -0.5F * width).color(255, 0, 255, 0).next();
    }
    private static void putDeathLightPositiveXTerminalVertex(VertexConsumer buffer, Matrix4f matrix, float radius, float width) {
        buffer.vertex(matrix, HALF_SQRT_3 * width, radius, -0.5F * width).color(255, 0, 255, 0).next();
    }
    private static void putDeathLightPositiveZTerminalVertex(VertexConsumer buffer, Matrix4f matrix, float radius, float width) {
        buffer.vertex(matrix, 0.0F, radius, 1.0F * width).color(255, 0, 255, 0).next();
    }

    @Override
    public Identifier getTexture(InvisibleProjectile entity) {
        return null;
    }
}
