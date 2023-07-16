package net.alba.oldworld.data;

import java.util.concurrent.CompletableFuture;

import net.alba.oldworld.OldWorld;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;

public class ModWorldGenerator extends FabricDynamicRegistryProvider{

    public ModWorldGenerator(FabricDataOutput output, CompletableFuture<WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(WrapperLookup registries, Entries entries) {

    }

    @Override
    public String getName() {
        return OldWorld.MOD_ID;
    }
}
