package net.alba.oldworld.entity.projectiles;

import net.alba.oldworld.registry.OldEntities;
import net.alba.oldworld.registry.OldItems;
import net.alba.oldworld.registry.OldParticles;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.World.ExplosionSourceType;

@SuppressWarnings("deprecation")
public class FireOrbEntity extends ThrownItemEntity {
    private float explosionStength = 0;

	public FireOrbEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
		super(entityType, world);
	}
 
	public FireOrbEntity(World world, LivingEntity owner, float explosionStength) {
		super(OldEntities.FIRE_ORB_PROJECTILE, owner, world);
        this.explosionStength = explosionStength;
        this.setNoGravity(true);
	}
 
	public FireOrbEntity(World world, LivingEntity owner, double x, double y, double z, float explosionStength) {
		super(OldEntities.FIRE_ORB_PROJECTILE, x, y, z, world);
        this.explosionStength = explosionStength;
        this.setOwner(owner);
        this.setNoGravity(true);
	}
 
	@Override
	protected Item getDefaultItem() {
		return OldItems.FIRE_ORB; 
	}
    
    public void tick() {
        super.tick();
        Entity entity = this.getOwner();
        if (this.world.isClient || (entity == null || !entity.isRemoved()) && this.world.isChunkLoaded(this.getBlockPos())) {
            Vec3d vec3d = this.getVelocity();
            double d = this.getX() + vec3d.x;
            double e = this.getY() + vec3d.y + 0.3;
            double f = this.getZ() + vec3d.z;
            for (int i = 0; i < 2; i++) {
                this.world.addParticle(OldParticles.FIRE, d, e, f, 0.0, 0.0, 0.0);
                this.world.addParticle(ParticleTypes.LARGE_SMOKE, d, e, f, 0.0, 0.0, 0.0);
            }
        }
        else {
            this.discard();
        }
    }
 
	protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        if (!this.world.isClient) {
            Entity entity = entityHitResult.getEntity();
            Entity entity2 = this.getOwner();
            entity.damage(getDamageSources().magic(), 6.5F);
            if (entity2 instanceof LivingEntity) {
                this.applyDamageEffects((LivingEntity)entity2, entity);
            }
        }
	}
 
	protected void onCollision(HitResult hitResult) { 
        super.onCollision(hitResult);
        if (!this.world.isClient) {
            boolean bl = this.world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING);
            this.world.createExplosion(this, this.getX(), this.getY(), this.getZ(), explosionStength, bl, ExplosionSourceType.MOB);
            this.discard();
        }
	}


}