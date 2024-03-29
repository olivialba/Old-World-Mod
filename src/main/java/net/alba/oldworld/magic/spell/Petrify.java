package net.alba.oldworld.magic.spell;

import org.jetbrains.annotations.Nullable;

import net.alba.oldworld.registry.OldMix;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class Petrify extends SpellRay {

    public Petrify() {
        super(RayHit.ENTITY, 20, 30);
    }

    @Override
    protected void onEntityHit(World world, @Nullable PlayerEntity caster, Vec3d startPosition,
            LivingEntity entity) {
        entity.addStatusEffect(new StatusEffectInstance(OldMix.PETRIFY, 140, 0, false, false));
        ((ServerWorld) world).spawnParticles(ParticleTypes.MYCELIUM, entity.getX(), entity.getEyeY() - 0.3, entity.getZ(), 60, 0.5, 0.5, 0.5, 0.1); 
        world.playSound(null, entity.getBlockPos(), SoundEvents.ENTITY_IRON_GOLEM_DAMAGE, SoundCategory.PLAYERS, 0.75f, 1f);
    }

    @Override
    protected void onBlockHit(World world, @Nullable PlayerEntity caster, Vec3d startPosition,
            BlockPos blockPos) {
    }

    @Override
    public @Nullable SoundEvent getSpellSound() {
        return null;
    }
}
