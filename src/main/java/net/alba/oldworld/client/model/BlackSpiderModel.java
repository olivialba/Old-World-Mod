package net.alba.oldworld.client.model;

import net.alba.oldworld.OldWorld;
import net.alba.oldworld.entity.mobs.BlackSpiderEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

@Environment(EnvType.CLIENT)
public class BlackSpiderModel extends GeoModel<BlackSpiderEntity>{

    @Override
    public Identifier getModelResource(BlackSpiderEntity animatable) {
        return new Identifier(OldWorld.MOD_ID, "geo/black_spider.geo.json");
    }

    @Override
    public Identifier getTextureResource(BlackSpiderEntity animatable) {
        return new Identifier(OldWorld.MOD_ID, "textures/entity/black_spider.png");
    }

    @Override
    public Identifier getAnimationResource(BlackSpiderEntity animatable) {
        return new Identifier(OldWorld.MOD_ID, "animations/black_spider.animation.json");
    }
    
    @Override
    public void setCustomAnimations(BlackSpiderEntity animatable, long instanceId, AnimationState<BlackSpiderEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(entityData.headPitch() * MathHelper.RADIANS_PER_DEGREE);
            head.setRotY(entityData.netHeadYaw() * MathHelper.RADIANS_PER_DEGREE);
        }
    }
}
