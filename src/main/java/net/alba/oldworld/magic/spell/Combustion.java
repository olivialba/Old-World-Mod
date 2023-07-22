package net.alba.oldworld.magic.spell;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import net.alba.oldworld.registry.OldParticles;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class Combustion extends SpellRay {

    public Combustion() {
        super(RayHit.BLOCK, 15, 30);
    }

    @Override
    protected void onEntityHit(World world, @Nullable PlayerEntity caster, Vec3d startPosition,
            LivingEntity entity) {
    }

    @Override
    protected void onBlockHit(World world, @Nullable PlayerEntity caster, Vec3d startPosition,
            BlockPos blockPos) {
        Box boundingBox = new Box(blockPos).expand(4);
        List<LivingEntity> entities = world.getEntitiesByClass(LivingEntity.class, boundingBox, EntityPredicates.VALID_LIVING_ENTITY);
        if (entities.size() > 0) {
            for (LivingEntity entity : entities) {
                entity.setOnFireFor(6);
            }
        }
        ((ServerWorld) world).spawnParticles(OldParticles.FIRE, blockPos.getX() + 0.5, blockPos.getY() + 0.5, blockPos.getZ() + 0.5, 35, 0.6, 0.6, 0.6, 0.2); 
    }

    @Nullable
    @Override
    public SoundEvent getSpellSound() {
        return null;
    }
}

