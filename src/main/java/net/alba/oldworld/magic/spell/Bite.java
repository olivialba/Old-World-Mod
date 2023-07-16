package net.alba.oldworld.magic.spell;

import org.jetbrains.annotations.Nullable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.EvokerFangsEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class Bite extends SpellRay {

    public Bite() {
        super(RayHit.BLOCK, 20, 30);
    }

    @Override
    protected void onEntityHit(World world, @Nullable PlayerEntity caster, Vec3d startPosition, LivingEntity entity) {
    }

    @Override
    protected void onBlockHit(World world, @Nullable PlayerEntity caster, Vec3d startPosition, BlockPos blockPos) {
        Vec3d center = blockPos.toCenterPos();
        double offsetX = 1.3;
        float[] rotations = {45, 0, -45};
        EvokerFangsEntity fang;
        for (float rotation : rotations) {
            fang = new EvokerFangsEntity(world, center.x + offsetX, center.y + 1, center.z, rotation, 0, caster);
            offsetX -= 1.3;
            world.spawnEntity(fang);
        }
    }

    @Override
    public void spawnParticleRay() {
    }

    @Nullable
    @Override
    public SoundEvent getSpellSound() {
        return null;
    }
}
