package net.alba.oldworld.entity.mobs;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager.ControllerRegistrar;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class EarthGolemEntity extends HostileEntity implements GeoEntity {
    private AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public EarthGolemEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
        this.experiencePoints = 30;
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return HostileEntity.createHostileAttributes()
            .add(EntityAttributes.GENERIC_MAX_HEALTH, 250.0D)
            .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 17.0D)
            .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3f)
            .add(EntityAttributes.GENERIC_ATTACK_SPEED, 1D)
            .add(EntityAttributes.GENERIC_ARMOR, 2f)
            .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 20f);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(2, new MeleeAttackGoal(this, 1.1D, false));
        this.goalSelector.add(5, new WanderAroundFarGoal(this, 0.75f, 1));
        this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 23));

        this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.add(3, new ActiveTargetGoal<>(this, MerchantEntity.class, true));
    }

    @Override
    public void registerControllers(ControllerRegistrar controller) {
        controller.add(new AnimationController<>(this, "Movement", 0, this::movement));
        controller.add(new AnimationController<>(this, "Attack", 0, this::attack));
    }

    private <T extends GeoAnimatable> PlayState movement(AnimationState<T> event) {
        if (event.isMoving()) {
            event.setAnimation(EntityAnimations.WALK);
            return PlayState.CONTINUE;
        }
        event.setAnimation(EntityAnimations.IDLE);
        return PlayState.CONTINUE;
    }

    private <T extends GeoAnimatable> PlayState attack(AnimationState<T> event) {
        if (this.handSwinging) {
            event.resetCurrentAnimation();
            event.setAndContinue(EntityAnimations.ATTACK);
            this.handSwinging = false;
        }
        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}

    // CUSTOM INSTRUCTION KEY 

    // controller.add(new AnimationController<>(this, "Movement", 0, this::movement).setCustomInstructionKeyframeHandler(state -> {
    //             if (state.getController().getCurrentRawAnimation() == EntityAnimations.IDLE) {
    //                 System.out.println("This works only on client");
    //             }
    //         }));