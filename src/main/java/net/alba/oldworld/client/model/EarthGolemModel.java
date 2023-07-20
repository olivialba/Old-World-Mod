package net.alba.oldworld.client.model;

import net.alba.oldworld.OldWorld;
import net.alba.oldworld.entity.mobs.EarthGolemEntity;
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
public class EarthGolemModel extends GeoModel<EarthGolemEntity>{

    @Override
    public Identifier getModelResource(EarthGolemEntity animatable) {
        return new Identifier(OldWorld.MOD_ID, "geo/earth_golem.geo.json");
    }

    @Override
    public Identifier getTextureResource(EarthGolemEntity animatable) {
        return new Identifier(OldWorld.MOD_ID, "textures/entity/earth_golem.png");
    }

    @Override
    public Identifier getAnimationResource(EarthGolemEntity animatable) {
        return new Identifier(OldWorld.MOD_ID, "animations/earth_golem.animation.json");
    }
    
    @Override
    public void setCustomAnimations(EarthGolemEntity animatable, long instanceId, AnimationState<EarthGolemEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(entityData.headPitch() * MathHelper.RADIANS_PER_DEGREE);
            head.setRotY(entityData.netHeadYaw() * MathHelper.RADIANS_PER_DEGREE);
        }
    }
}
