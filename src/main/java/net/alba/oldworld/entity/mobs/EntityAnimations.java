package net.alba.oldworld.entity.mobs;

import net.minecraft.entity.LivingEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animation.Animation.LoopType;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;

public final class EntityAnimations {
    public static final RawAnimation WALK = RawAnimation.begin().thenLoop("move.walk");
    public static final RawAnimation IDLE = RawAnimation.begin().thenLoop("move.idle");
    public static final RawAnimation ATTACK = RawAnimation.begin().then("attack.strike", LoopType.PLAY_ONCE);

	/**
	 * Will play the walk animation if entity is considered moving, or idle if not.
     * <p>Walk animation: {@code move.walk}
     * <p>Idle animation: {@code move.idle}
	 */
	public static <T extends GeoAnimatable> AnimationController<T> WalkIdleAnimation(T animatable, int transitionTickTime) {
		return new AnimationController<T>(animatable, "Walk/Idle", transitionTickTime, state -> state.setAndContinue(state.isMoving() ? WALK : IDLE));
	}

	/**
	 * Will play the walk animation if entity is considered moving, or idle if not.
     * <p>Walk animation: {@code move.walk}
     * <p>Idle animation: {@code move.idle}
	 */
	public static <T extends LivingEntity & GeoAnimatable> AnimationController<T> MeleeAttackAnimation(T animatable, int transitionTickTime) {
		return new AnimationController<T>(animatable, "Attack", transitionTickTime, state -> {
			return PlayState.STOP;
        });
	}
}
