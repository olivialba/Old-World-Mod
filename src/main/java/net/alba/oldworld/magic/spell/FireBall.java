package net.alba.oldworld.magic.spell;

import org.jetbrains.annotations.Nullable;

import net.alba.oldworld.util.RayUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;

public class FireBall extends Spell{

    public FireBall() {
        super(20);
    }

    @Override
    public void invoke(World world, PlayerEntity player) {
        FireballEntity fireBall = new FireballEntity(world, player, RayUtil.getRotationX(player), RayUtil.getRotationY(player), RayUtil.getRotationZ(player), 1);
        fireBall.setPosition(player.getX(), player.getBodyY(0.85D), fireBall.getZ());
        fireBall.setVelocity(player, player.getPitch(), player.getYaw(), 0.0F, 2.2F, 1.0F);
        world.spawnEntity(fireBall);
    }

    @Nullable
    @Override
    public SoundEvent getSpellSound() {
        return null;
    }
}
