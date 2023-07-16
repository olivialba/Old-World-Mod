package net.alba.oldworld.data;

import net.alba.oldworld.registry.OldBlocks;
import net.alba.oldworld.registry.OldItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

public class ModLootTableGenerator extends FabricBlockLootTableProvider {

    public ModLootTableGenerator(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(OldBlocks.OLD_INGOT_BLOCK);

        addDrop(OldBlocks.OLD_ORE, oreDrops(OldBlocks.OLD_ORE, OldItems.OLD_INGOT));
        addDrop(OldBlocks.DEEPSLATE_OLD_ORE, oreDrops(OldBlocks.DEEPSLATE_OLD_ORE, OldItems.OLD_INGOT));
    }
}
