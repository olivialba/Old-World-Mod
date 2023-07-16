package net.alba.oldworld.client.renderer;

import net.alba.oldworld.OldWorld;
import net.alba.oldworld.client.model.CrystalModel;
import net.alba.oldworld.entity.projectiles.CrystalProjectileEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class CrystalRenderer extends EntityRenderer<CrystalProjectileEntity> {
    private final CrystalModel model;
    private static final Identifier TEXTURE = new Identifier(OldWorld.MOD_ID, "textures/entity/crystal_projectile.png");

    public CrystalRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.model = new CrystalModel(CrystalModel.getTexturedModelData().createModel());
    }

    public void render(CrystalProjectileEntity entity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.push();
        matrixStack.translate(0.0f, -0.4, 0.0f);
        VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(this.model.getLayer(TEXTURE));
        this.model.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, 1.0f, 1.0f, 1.0f, 1.0f);
        matrixStack.pop();
        super.render(entity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    public Identifier getTexture(CrystalProjectileEntity entity) {
        return TEXTURE;
    }
}
