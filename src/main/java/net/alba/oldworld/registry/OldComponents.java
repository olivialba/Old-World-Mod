package net.alba.oldworld.registry;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.entity.RespawnCopyStrategy;
import net.alba.oldworld.OldWorld;
import net.alba.oldworld.magic.ManaProperties;
import net.minecraft.util.Identifier;

public class OldComponents implements EntityComponentInitializer {
    public static final ComponentKey<ManaProperties> MANA_COMPONENT = ComponentRegistry.getOrCreate(new Identifier(OldWorld.MOD_ID, "mana"), ManaProperties.class);

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerForPlayers(MANA_COMPONENT, ManaProperties::new, RespawnCopyStrategy.ALWAYS_COPY);
    }
}
