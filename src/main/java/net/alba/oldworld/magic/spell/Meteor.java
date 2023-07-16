package net.alba.oldworld.magic.spell;

import org.jetbrains.annotations.Nullable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class Meteor extends SpellRay{

    public Meteor() {
        super(RayHit.BLOCK, 35, 30);
    }

    @Override
    protected void onEntityHit(World world, @Nullable PlayerEntity caster, Vec3d startPosition,
            LivingEntity entity) {
    }

    @Override
    protected void onBlockHit(World world, @Nullable PlayerEntity caster, Vec3d startPosition,
            BlockPos blockPos) {
        FireballEntity fireBall = new FireballEntity(world, caster, 0, -90, 0, 4);
        fireBall.setPosition(blockPos.getX(), blockPos.getY() + 35, blockPos.getZ());
        world.spawnEntity(fireBall);
    }

    @Nullable
    @Override
    public SoundEvent getSpellSound() {
        return null;
    }
}
