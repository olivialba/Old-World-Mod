package net.alba.oldworld.client.renderer;

import org.joml.Matrix3f;
import org.joml.Matrix4f;

import net.alba.oldworld.OldWorld;
import net.alba.oldworld.entity.projectiles.PlaceHolder;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.entity.BeaconBlockEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;

@Environment(EnvType.CLIENT)
public class PlaceHolderRenderer extends EntityRenderer<PlaceHolder> {
    private static final Identifier BEAM_TEXTURE = new Identifier(OldWorld.MOD_ID, "textures/block/beam_strike.png");
    private static final float[] COLOR = {(float)255 / 255.0F, (float)165 / 255.0F, (float)0 / 255.0F};
    private static final float HALF_SQRT_3 = (float)(Math.sqrt(3.0) / 2.0);

    public PlaceHolderRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public void render(PlaceHolder placeHolder, float f,  float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        long l = placeHolder.getWorld().getTime();
        int segments = placeHolder.age >= 25 ? 25 : placeHolder.age;
        for (int s = 0; s < segments * 2; s++) {
            renderBeam(placeHolder, matrixStack, vertexConsumerProvider, f, l, s, 1, COLOR);
        }
        //lightBeams(placeHolder, matrixStack, vertexConsumerProvider, g);
        super.render(placeHolder, f, g, matrixStack, vertexConsumerProvider, i);
    }

    private static void renderBeam(PlaceHolder placeHolder, MatrixStack matrices, VertexConsumerProvider vertexConsumers, float tickDelta, long worldTime, int yOffset, int maxY, float[] color) {
        renderBeam(placeHolder, matrices, vertexConsumers, BEAM_TEXTURE, tickDelta, 1.0F, worldTime, yOffset, maxY, color, 0.8F, 1.1F);
    }

    public static void renderBeam(PlaceHolder placeHolder, MatrixStack matrices, VertexConsumerProvider vertexConsumers, Identifier textureId, float tickDelta, float heightScale, long worldTime, int yOffset, int maxY, float[] color, float innerRadius, float outerRadius) {
        int i = yOffset + maxY;
        matrices.push();
        matrices.translate(0.0, 50, 0.0);
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(180));
        float f = (float)Math.floorMod(worldTime, 40) + tickDelta;
        float g = maxY < 0 ? f : -f;
        float h = MathHelper.fractionalPart(g * 0.2F - (float)MathHelper.floor(g * 0.1F));
        float j = color[0];
        float k = color[1];
        float l = color[2];
        matrices.push();
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(f * 2.25F - 45.0F));
        float m = 0.0F;
        float p = 0.0F;
        float q = -innerRadius;
        float t = -innerRadius;
        float w = -1.0F + h;
        float x = (float)maxY * heightScale * (0.5F / innerRadius) + w;
        renderBeamLayer(matrices, vertexConsumers.getBuffer(RenderLayer.getBeaconBeam(textureId, false)), j, k, l, 1.0F, yOffset, i, 0.0F, innerRadius, innerRadius, 0.0F, q, 0.0F, 0.0F, t, 0.0F, 1.0F, x, w);
        matrices.pop();
        m = -outerRadius;
        float n = -outerRadius;
        p = -outerRadius;
        q = -outerRadius;
        w = -1.0F + h;
        x = (float)maxY * heightScale + w;
        renderBeamLayer(matrices, vertexConsumers.getBuffer(RenderLayer.getBeaconBeam(textureId, true)), j, k, l, 0.125F, yOffset, i, m, n, outerRadius, p, q, outerRadius, outerRadius, outerRadius, 0.0F, 1.0F, x, w);
        matrices.pop();
    }

    private static void renderBeamLayer(MatrixStack matrices, VertexConsumer vertices, float red, float green, float blue, float alpha, int yOffset, int height, float x1, float z1, float x2, float z2, float x3, float z3, float x4, float z4, float u1, float u2, float v1, float v2) {
        MatrixStack.Entry entry = matrices.peek();
        Matrix4f matrix4f = entry.getPositionMatrix();
        Matrix3f matrix3f = entry.getNormalMatrix();
        renderBeamFace(matrix4f, matrix3f, vertices, red, green, blue, alpha, yOffset, height, x1, z1, x2, z2, u1, u2, v1, v2);
        renderBeamFace(matrix4f, matrix3f, vertices, red, green, blue, alpha, yOffset, height, x4, z4, x3, z3, u1, u2, v1, v2);
        renderBeamFace(matrix4f, matrix3f, vertices, red, green, blue, alpha, yOffset, height, x2, z2, x4, z4, u1, u2, v1, v2);
        renderBeamFace(matrix4f, matrix3f, vertices, red, green, blue, alpha, yOffset, height, x3, z3, x1, z1, u1, u2, v1, v2);
    }

    private static void renderBeamFace(Matrix4f positionMatrix, Matrix3f normalMatrix, VertexConsumer vertices, float red, float green, float blue, float alpha, int yOffset, int height, float x1, float z1, float x2, float z2, float u1, float u2, float v1, float v2) {
        renderBeamVertex(positionMatrix, normalMatrix, vertices, red, green, blue, alpha, height, x1, z1, u2, v1);
        renderBeamVertex(positionMatrix, normalMatrix, vertices, red, green, blue, alpha, yOffset, x1, z1, u2, v2);
        renderBeamVertex(positionMatrix, normalMatrix, vertices, red, green, blue, alpha, yOffset, x2, z2, u1, v2);
        renderBeamVertex(positionMatrix, normalMatrix, vertices, red, green, blue, alpha, height, x2, z2, u1, v1);
    }

    private static void renderBeamVertex(Matrix4f positionMatrix, Matrix3f normalMatrix, VertexConsumer vertices, float red, float green, float blue, float alpha, int y, float x, float z, float u, float v) {
        vertices.vertex(positionMatrix, x, (float)y, z).color(red, green, blue, alpha).texture(u, v).overlay(OverlayTexture.DEFAULT_UV).light(15728880).normal(normalMatrix, 0.0F, 1.0F, 0.0F).next();
    }

    public boolean rendersOutsideBoundingBox(BeaconBlockEntity beaconBlockEntity) {
        return true;
    }

    public int getRenderDistance() {
        return 256;
    }

    public boolean isInRenderDistance(BeaconBlockEntity beaconBlockEntity, Vec3d vec3d) {
        return Vec3d.ofCenter(beaconBlockEntity.getPos()).multiply(1.0, 0.0, 1.0).isInRange(vec3d.multiply(1.0, 0.0, 1.0), (double)this.getRenderDistance());
    }

    public static void lightBeams(PlaceHolder placeHolder, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, float g) {
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
    public Identifier getTexture(PlaceHolder entity) {
        return BEAM_TEXTURE;
    }
}
