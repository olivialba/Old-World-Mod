package net.alba.oldworld.entity.projectiles;

import net.alba.oldworld.registry.OldEntities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

public class BallBasicEntity extends OldWorldProjectile {
    private float damage;
    private StatusEffectInstance status;
    private boolean giveFire;

    public BallBasicEntity(EntityType<? extends BallBasicEntity> entityType, World world) {
        super(entityType, world);
    }

    public BallBasicEntity(World world, LivingEntity owner, double directionX, double directionY, double directionZ, float damage, StatusEffectInstance effect, boolean fire) {
        super(OldEntities.BASIC_BALL_PROJECTILE, owner, directionX, directionY, directionZ, world);
        this.damage = damage;
        this.status = effect;
        this.giveFire = fire;
    }

    protected void onCollision(HitResult hitResult) { 
        super.onCollision(hitResult);
        if (!this.world.isClient) {
            this.discard();
        }
    }

    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        if (this.world.isClient) {
            return;
        }
        Entity entityHit = entityHitResult.getEntity();
        entityHit.damage(world.getDamageSources().magic(), this.damage);
        if (entityHit instanceof LivingEntity living) {
            if (!(this.status == null)) {
                living.addStatusEffect(this.status);
            }
            if (this.giveFire) {
                living.setFireTicks(100);
            }
        }
    }
    
    @Override
    protected void spawnParticleTrail(double d, double e, double f) {
        this.world.addParticle(ParticleTypes.SMOKE, d, e + 0.3, f, 0.0, 0.0, 0.0);
    }
}