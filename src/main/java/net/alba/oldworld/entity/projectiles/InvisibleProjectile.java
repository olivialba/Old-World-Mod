package net.alba.oldworld.entity.projectiles;

import java.util.List;

import net.alba.oldworld.registry.OldEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class InvisibleProjectile extends OldWorldProjectile {
    private List<LivingEntity> entities;
    private Box box;

    public InvisibleProjectile(EntityType<? extends InvisibleProjectile> entityType, World world) {
        super(entityType, world);
    }

    public InvisibleProjectile(World world, LivingEntity owner) {
        super(OldEntities.PLACE, world);
        this.setOwner(owner);
        this.setInvulnerable(true);
    }

    public InvisibleProjectile(World world, LivingEntity owner, double directionX, double directionY, double directionZ) {
        super(OldEntities.PLACE, owner, directionX, directionY, directionZ, world);
        this.setInvulnerable(true);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.age % 5 == 0) {
            if (this.age >= 170 || this.getOwner() == null) {
                this.discard();
            }
            if (this.age % 15 == 0) {
                box = new Box(this.getBlockPos()).expand(15);
                entities = world.getEntitiesByClass(LivingEntity.class, box, EntityPredicates.EXCEPT_CREATIVE_OR_SPECTATOR);
            }
            if (entities != null && !entities.isEmpty()) {
                for (LivingEntity entity : entities) {
                    if (this.getOwner() != entity) {
                        entity.setVelocity(Vec3d.ZERO);
                        Vec3d direction = this.getPos().subtract(entity.getPos()).normalize();
                        entity.move(MovementType.SELF, direction.multiply(1));
                    }
                    if (entity.getBlockPos().isWithinDistance(this.getBlockPos(), 4.5) || entity.getType() == EntityType.ENDER_DRAGON) {
                        entity.damage(getDamageSources().outOfWorld(), (entity.getMaxHealth() / 4.5F));
                    }
                }
            }
        }
    }

    protected void onCollision(HitResult hitResult) { 
        super.onCollision(hitResult);
        if (!this.world.isClient) {
            this.setVelocity(Vec3d.ZERO);
        }
    }

    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        if (!this.world.isClient) {
        }
    }
    
    @Override
    protected boolean getTrail() {
        return true;
    }

    @Override
    protected void spawnParticleTrail(double d, double e, double f) {
    }
}
