package net.alba.oldworld.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

public final class RayUtil {


    /**
     * Returns {@code BlockPos} or {@code Null} (if it doesn't detect any block)
     */
    public static BlockPos blockRaycast(World world, Vec3d startPosition, Vec3d endPosition, PlayerEntity player) {
        BlockHitResult hitResult = world.raycast(new RaycastContext(startPosition, endPosition, RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE, player));
        if (hitResult.getType() == HitResult.Type.BLOCK) {
            return hitResult.getBlockPos();
        }
        return null;
    }

    /**
     * DOes a living entity raycast and checks if there is a block in the way.
     * <p>Returns {@code LivingEntity} or {@code Null} (if doesn't detect any entity)
     */
    public static LivingEntity entityRaycast(World world, Vec3d startPosition, Vec3d endPosition, Vec3d direction, PlayerEntity player, double maxDistance) {
        Box boxHit = player.getBoundingBox().stretch(direction.multiply(maxDistance)).expand(1.0D, 1.0D, 1.0D);
        EntityHitResult entityResult = ProjectileUtil.raycast(player, startPosition, endPosition, boxHit, (entityx) -> !entityx.isSpectator() && entityx.canHit(), maxDistance * maxDistance);
        if (entityResult != null && entityResult.getEntity() instanceof LivingEntity mob) {
            if (noBlockInTheWay(player, startPosition, entityResult.getPos())) {
                return mob;
            }
        }
        return null;
    }

    /**
     * Detect if there is a LivingEntity or Block in the raycast, if there is a block, it adjusts the end of the raycast to that block.
     * <p>Returns {@code EntityHitResult}, {@code BlockHitResult}, or {@code Null} (if doesn't detect either entity or block)
     */
    public static HitResult entityAndBlockRaycast(World world, Vec3d startPosition, Vec3d endPosition, Vec3d direction, PlayerEntity player, double maxDistance) {
        boolean blockDetected = false;
        HitResult blockResult = world.raycast(new RaycastContext(startPosition, endPosition, RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE, player));
        if (blockResult.getType() == HitResult.Type.BLOCK) {
            blockDetected = true;
            endPosition = blockResult.getPos();
            maxDistance = startPosition.distanceTo(endPosition);
        }

        Box boxHit = player.getBoundingBox().stretch(direction.multiply(maxDistance)).expand(1.0D, 1.0D, 1.0D);
        EntityHitResult entityResult = ProjectileUtil.raycast(player, startPosition, endPosition, boxHit, (entityx) -> !entityx.isSpectator() && entityx.canHit(), maxDistance * maxDistance);
        if (entityResult != null && (entityResult.getEntity() instanceof LivingEntity)) {
            return entityResult;
        }
        else if (blockDetected) {
            return blockResult;
        }
        return null;
    }

    /**
     * Checks if there is no block in the way of the raycast
     */
    public static boolean noBlockInTheWay(Entity player, Vec3d startPosition, Vec3d endPosition) {
        BlockHitResult hit = player.world.raycast(new RaycastContext(startPosition, endPosition, RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE, player));
        return hit.getType() != HitResult.Type.BLOCK;
    }


    /**
     * Get nearest entity in a box {@code lengthX}, {@code lengthY} and {@code lengthZ}
     * @param position Starting position of the box
     */
    public static LivingEntity getNearestLivingEntity(BlockPos position, World world, int lengthX, int lengthY, int lengthZ) {
        LivingEntity targetEntity = world.getClosestEntity(world.getEntitiesByClass(LivingEntity.class, new Box(position).expand(lengthX, lengthY, lengthZ), EntityPredicates.VALID_LIVING_ENTITY),
            TargetPredicate.DEFAULT, null, position.getX() + 0.5, position.getY() + 0.5, position.getZ() + 0.5);
        return targetEntity;
    }

    /**
     * Get nearest entity in a square box
     * @param position Starting position of the box
     */
    public static LivingEntity getNearestLivingEntity(BlockPos position, World world, int length) {
        return getNearestLivingEntity(position, world, length, length, length);
    }


    // ROTATIONS
    public static double getRotationY(PlayerEntity user) {
        return (user.getY() + user.getRotationVec(0.0F).y * 4.0D) - user.getY();
    }
    
    public static double getRotationX(PlayerEntity user) {
        return (user.getX() + user.getRotationVec(0.0F).x * 4.0D) - user.getX();
    }

    public static double getRotationZ(PlayerEntity user) {
        return (user.getZ() + user.getRotationVec(0.0F).z * 4.0D) - user.getZ();
    }
}
