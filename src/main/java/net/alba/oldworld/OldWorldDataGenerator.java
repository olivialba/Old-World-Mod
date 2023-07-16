package net.alba.oldworld;

import net.alba.oldworld.data.ModLootTableGenerator;
import net.alba.oldworld.data.ModModelProvider;
import net.alba.oldworld.data.ModRecipeGenerator;
import net.alba.oldworld.data.ModTagGenerator;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class OldWorldDataGenerator implements DataGeneratorEntrypoint {
    
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(ModLootTableGenerator::new);
        pack.addProvider(ModModelProvider::new);
        pack.addProvider(ModRecipeGenerator::new);
        pack.addProvider(ModTagGenerator::new);
    }
}
