package net.alba.oldworld.entity.mobs;

import software.bernie.geckolib.core.animation.Animation;
import software.bernie.geckolib.core.animation.RawAnimation;

public final class EntityAnimations {
    public static final RawAnimation WALK = RawAnimation.begin().then("move.walk", Animation.LoopType.LOOP);
    public static final RawAnimation IDLE = RawAnimation.begin().then("move.idle", Animation.LoopType.LOOP);
    public static final RawAnimation ATTACK = RawAnimation.begin().then("attack.punch", Animation.LoopType.PLAY_ONCE);
}
