package net.alba.oldworld.client.renderer;

import net.alba.oldworld.OldWorld;
import net.alba.oldworld.client.model.EarthGolemModel;
import net.alba.oldworld.entity.mobs.EarthGolemEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory.Context;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class EarthGolemRenderer extends GeoEntityRenderer<EarthGolemEntity>{

    public EarthGolemRenderer(Context renderManager) {
        super(renderManager, new EarthGolemModel());
    }

    @Override
    public void render(EarthGolemEntity entity, float entityYaw, float partialTick, MatrixStack poseStack,
            VertexConsumerProvider bufferSource, int packedLight) {
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
    
    @Override
    public Identifier getTextureLocation(EarthGolemEntity animatable) {
        return new Identifier(OldWorld.MOD_ID, "textures/entity/earth_golem.png");
    }
}
