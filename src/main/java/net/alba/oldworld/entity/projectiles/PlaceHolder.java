package net.alba.oldworld.entity.projectiles;

import net.alba.oldworld.registry.OldEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import net.minecraft.world.World.ExplosionSourceType;

public class PlaceHolder extends OldWorldProjectile {
    public int distanceToTarget;

    public PlaceHolder(EntityType<? extends PlaceHolder> entityType, World world) {
        super(entityType, world);
    }

    public PlaceHolder(World world, LivingEntity owner) {
        super(OldEntities.PLACE, world);
        this.setOwner(owner);
        this.setInvulnerable(true);
    }

    public PlaceHolder(World world, LivingEntity owner, double directionX, double directionY, double directionZ) {
        super(OldEntities.PLACE, owner, directionX, directionY, directionZ, world);
        this.setOwner(owner);
        this.setInvulnerable(true);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.age >= 50) {
            this.world.createExplosion(this, this.getX(), this.getY(), this.getZ(), 5F, ExplosionSourceType.MOB);
            this.discard();
        }
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
    }
    
    @Override
    protected boolean getTrail() {
        return false;
    }
}
