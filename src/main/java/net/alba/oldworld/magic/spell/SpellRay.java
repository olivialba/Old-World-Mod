package net.alba.oldworld.magic.spell;

import org.jetbrains.annotations.Nullable;

import net.alba.oldworld.util.RayUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public abstract class SpellRay extends Spell {

    private final double range;
    private final RayHit hitType;
    
    /**
     * Raycasts doesn't go through blocks
     * 
     * @param ENTITY raycast closest entity ({@code onEntityHit})
     * @param BLOCK raycast closest block ({@code onBlockHit})
     * @param BOTH raycast the closest block or entity ({@code onBlockHit} and {@code onEntityHit})
     */
    static enum RayHit {
        ENTITY,
        BLOCK,
        BOTH
    }

    public SpellRay(RayHit hitType, int cost, int range) {
        super(cost);
        this.hitType = hitType;
        this.range = range;
    }

    @Override
    public void invoke(World world, PlayerEntity player) {
        spell(world, player, range);
    }

    /**
     * Spell raycast main function, added {@code range} variable.
     * <p>Don't edit this!
     * <p>Use {@code onEntityHit} and {@code onBlockHit} for spell function.
     */
    private void spell(World world, @Nullable PlayerEntity caster, double range) {
        Vec3d startPosition = caster.getCameraPosVec(1.0F);
        Vec3d direction = caster.getRotationVec(1.0F);
        Vec3d endPosition = startPosition.add(direction.x * range, direction.y * range, direction.z * range);
        
        switch (this.hitType) {
            case ENTITY:
                LivingEntity entityTargeted = RayUtil.entityRaycast(world, startPosition, endPosition, direction, caster, range);
                if (entityTargeted != null) {
                    onEntityHit(world, caster, startPosition, entityTargeted);
                }
                break;

            case BLOCK:
                BlockPos blockTargeted = RayUtil.blockRaycast(world, startPosition, endPosition, caster);
                if (blockTargeted != null) {
                    onBlockHit(world, caster, startPosition, blockTargeted);
                }
                break;

            case BOTH:
                HitResult hitResult = RayUtil.entityAndBlockRaycast(world, startPosition, endPosition, direction, caster, range);
                if (hitResult != null) {
                    if (hitResult instanceof EntityHitResult entity) {
                        onEntityHit(world, caster, startPosition, (LivingEntity)entity.getEntity());
                    }
                    else if (hitResult instanceof BlockHitResult block) {
                        onBlockHit(world, caster, startPosition, block.getBlockPos());
                    }
                }
                break;
        }
        //spawnParticleRay();
    }

    /**
     * If raycast hit an entity
     */
    protected abstract void onEntityHit(World world, @Nullable PlayerEntity caster, Vec3d startPosition, LivingEntity entity);

    /**
     * If raycast hit a block
     */
    protected abstract void onBlockHit(World world, @Nullable PlayerEntity caster, Vec3d startPosition, BlockPos blockPos);

    /**
     * Particle ray that spawns when spell is cast
     */
    protected void spawnParticleRay() {}; 

    /**
     * @return what the raycast can hit
     */
    public RayHit getHitType() {
        return this.hitType;
    }
}
