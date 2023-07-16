package net.alba.oldworld.data;

import java.util.concurrent.CompletableFuture;

import net.alba.oldworld.magic.OldSpellMap;
import net.alba.oldworld.registry.OldItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider.ItemTagProvider;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;

public class ModTagGenerator extends ItemTagProvider {

   public ModTagGenerator(FabricDataOutput output, CompletableFuture<WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(WrapperLookup arg) {

        for (Item crystalItem : OldSpellMap.CRYSTAL_ITEM_LIST) {
            getOrCreateTagBuilder(OldItems.TAG_SPELL_CRYSTALS).add(crystalItem);
        }      
    }
}
