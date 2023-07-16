package net.alba.oldworld.registry;

import net.alba.oldworld.OldWorld;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class OldParticles {

    public static final DefaultParticleType BEAM = FabricParticleTypes.simple();


    public static void registerParticle() {
        Registry.register(Registries.PARTICLE_TYPE, new Identifier(OldWorld.MOD_ID, "beam_particle"), BEAM);
    }
}
