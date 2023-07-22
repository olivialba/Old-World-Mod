package net.alba.oldworld.magic.spell;

import org.jetbrains.annotations.Nullable;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

public class Teleport extends Spell {

    public Teleport() {
        super(12);
    }

    @Override
    public void invoke(World world, PlayerEntity player) {
        HitResult pos = player.raycast(15, 0, true);
        player.teleport(pos.getPos().x, pos.getPos().y, pos.getPos().z, true);
        world.playSound(null, player.getBlockPos(), SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.PLAYERS, 0.75f, 1f);
    } 

    @Override
    public @Nullable SoundEvent getSpellSound() {
        return null;
    }
}
