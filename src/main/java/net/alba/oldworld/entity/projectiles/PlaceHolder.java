package net.alba.oldworld.entity.projectiles;

import java.util.List;

import com.google.common.collect.Lists;

import net.alba.oldworld.registry.OldEntities;
import net.alba.oldworld.util.RayUtil;
import net.minecraft.block.entity.BeaconBlockEntity.BeamSegment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

public class PlaceHolder extends OldWorldProjectile {
    private List<BeamSegment> beamSegments = Lists.newArrayList();
    public int distanceToTarget;

    public PlaceHolder(EntityType<? extends PlaceHolder> entityType, World world) {
        super(entityType, world);
    }

    public PlaceHolder(World world, LivingEntity owner) {
        super(OldEntities.PLACE, world);
        this.setOwner(owner);
    }

    public PlaceHolder(World world, LivingEntity owner, double directionX, double directionY, double directionZ) {
        super(OldEntities.PLACE, owner, directionX, directionY, directionZ, world);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getOwner() != null) {
            Entity owner = this.getOwner();
            //this.move(MovementType.PLAYER, owner.getVelocity());
            this.setPosition(owner.getCameraPosVec(1.0F));
            this.setRotation(owner.getYaw(), owner.getPitch());
            if (this.age % 50 == 0 || this.age == 1) {
                distanceToTarget = RayUtil.beamBlockRaycast(this.world, this, 20);  
            }
        }
        else {
            this.discard();
        }
        if (this.age > 500) {
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

    public float[] getColor() {
        return new float[]{(float)255 / 255.0F, (float)99 / 255.0F, (float)71 / 255.0F};
    }

   public List<BeamSegment> getBeamSegments() {
        if (this.beamSegments.size() < 1) {
            BeamSegment beamSegment = new BeamSegment(new float[]{(float)5 / 255.0F, (float)9 / 255.0F, (float)24 / 255.0F});
            beamSegments.add(beamSegment);
        }
        return this.beamSegments;
   }
}
