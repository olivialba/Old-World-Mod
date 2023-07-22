package net.alba.oldworld.registry;

import net.alba.oldworld.OldWorld;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class OldParticles {

    public static final DefaultParticleType MAGIC_CIRCLE = FabricParticleTypes.simple();
    public static final DefaultParticleType FIRE = FabricParticleTypes.simple();

    public static void registerParticle() {
        Registry.register(Registries.PARTICLE_TYPE, new Identifier(OldWorld.MOD_ID, "magic_circle"), MAGIC_CIRCLE);
        Registry.register(Registries.PARTICLE_TYPE, new Identifier(OldWorld.MOD_ID, "fire"), FIRE);
    }
}
