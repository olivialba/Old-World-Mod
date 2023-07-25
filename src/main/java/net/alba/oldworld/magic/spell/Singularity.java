package net.alba.oldworld.magic.spell;

import org.jetbrains.annotations.Nullable;

import net.alba.oldworld.entity.projectiles.InvisibleProjectile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;

public class Singularity extends Spell{

    public Singularity() {
        super(50);
    }

    @Override
    public void invoke(World world, PlayerEntity player) {
        InvisibleProjectile singularity = new InvisibleProjectile(world, player);
        singularity.setPosition(player.getX(), player.getBodyY(0.85D), player.getZ());
        singularity.setVelocity(player, player.getPitch(), player.getYaw(), 0.0F, 0.7F, 1.0F);
        world.spawnEntity(singularity);
    }

    @Override
    public @Nullable SoundEvent getSpellSound() {
        return null;
    }
}
