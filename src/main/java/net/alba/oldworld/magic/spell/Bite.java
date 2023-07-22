package net.alba.oldworld.magic.spell;

import java.util.Random;
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
        float[] rotations = {45, 0, -45, 30, -20, 50};
        Random random = new Random();
        EvokerFangsEntity fang;
        for (float rotation : rotations) {
            double theta = random.nextDouble() * 2 * Math.PI;
            double randomRadius = random.nextDouble() * 3.5;
            double x = center.x + randomRadius * Math.cos(theta);
            double z = center.z + randomRadius * Math.sin(theta);
            fang = new EvokerFangsEntity(world, x, center.y + 0.7, z, rotation, 0, caster);
            world.spawnEntity(fang);
        }
    }

    @Nullable
    @Override
    public SoundEvent getSpellSound() {
        return null;
    }
}
