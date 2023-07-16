package net.alba.oldworld.magic.spell;

import org.jetbrains.annotations.Nullable;

import net.alba.oldworld.util.MagicUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;

public abstract class Spell {
    
    private final int manaCost;

    public Spell(int cost) {
        this.manaCost = cost;
    }

    /**
     * Check mana cost and cast the spell. DON'T OVERRIDE.
     * @param world The world the spell is casted on
     * @param player Player casting the spell
     */
    public void cast(World world, PlayerEntity player) {
        if (MagicUtil.removeMana(player, getCost()) || player.isCreative()) {
            invoke(world, player);
        }
    }

    /**
     * Use to invoke Spell main function. Called after checking for mana cost in {@code cast}.
     */
    public abstract void invoke(World world, PlayerEntity player);

    /**
     * @return Sound of spell being casted, can be null
     */
    @Nullable
    public abstract SoundEvent getSpellSound();

    /**
     * Return mana cost of spell
     */
    public int getCost() {
        return this.manaCost;
    }
}
