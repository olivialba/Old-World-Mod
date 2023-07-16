package net.alba.oldworld.entity.projectiles;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractFireballEntity;
import net.minecraft.world.World;

public abstract class MODProjectileEntity extends AbstractFireballEntity{

    public MODProjectileEntity(EntityType<? extends MODProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    public MODProjectileEntity(EntityType<? extends MODProjectileEntity> entityType, LivingEntity owner, double velocityX, double velocityY, double velocityZ, World world) {
        super(entityType, owner, velocityX, velocityY, velocityZ, world);
    }

    protected boolean isBurning() {
        return false;
    }

    @Override
    public boolean canHit() {
        return false;
    }
}
