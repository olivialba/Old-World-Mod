package net.alba.oldworld.magic;

import net.alba.oldworld.OldWorld;
import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ManaTrait {
    public static final ClampedEntityAttribute MANA_CAPACITY = register("mana_capacity", 50, 1, 500);

    private static ClampedEntityAttribute register(String name, double base, double min, double max) {
        ClampedEntityAttribute attribute = (ClampedEntityAttribute) new ClampedEntityAttribute("player.oldworld." + name, base, min, max).setTracked(true);
        return Registry.register(Registries.ATTRIBUTE, new Identifier(OldWorld.MOD_ID, name), attribute);
    }

    public static void register() {}
}
