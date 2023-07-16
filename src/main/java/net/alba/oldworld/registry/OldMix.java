package net.alba.oldworld.registry;

import net.alba.oldworld.OldWorld;
import net.alba.oldworld.magic.effect.PetrifyStatus;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class OldMix {

    // Status Effects
    public static final StatusEffect PETRIFY = new PetrifyStatus();

    public static void registerMix() {
        Registry.register(Registries.STATUS_EFFECT, new Identifier(OldWorld.MOD_ID, "petrify"), PETRIFY);
    }
}
