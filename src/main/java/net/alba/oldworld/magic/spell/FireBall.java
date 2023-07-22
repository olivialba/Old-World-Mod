package net.alba.oldworld.magic.spell;

import org.jetbrains.annotations.Nullable;

import net.alba.oldworld.entity.projectiles.FireOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;

public class FireBall extends Spell{

    public FireBall() {
        super(20);
    }

    @Override
    public void invoke(World world, PlayerEntity player) {
        FireOrbEntity fireOrb = new FireOrbEntity(world, player, player.getX(), player.getBodyY(0.85D), player.getZ(), 1.2F);
        fireOrb.setVelocity(player, player.getPitch(), player.getYaw(), 0.0F, 1.5F, 1.0F);
        world.spawnEntity(fireOrb);
    }

    @Nullable
    @Override
    public SoundEvent getSpellSound() {
        return null;
    }
}
