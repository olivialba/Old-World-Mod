package net.alba.oldworld.data;

import java.util.Optional;

import net.alba.oldworld.OldWorld;
import net.alba.oldworld.magic.OldSpellMap;
import net.alba.oldworld.registry.OldBlocks;
import net.alba.oldworld.registry.OldItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Model;
import net.minecraft.data.client.ModelIds;
import net.minecraft.data.client.Models;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

public class ModModelProvider extends FabricModelProvider {

    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerCubeAllModelTexturePool(OldBlocks.DEEPSLATE_OLD_ORE);
        blockStateModelGenerator.registerCubeAllModelTexturePool(OldBlocks.OLD_ORE);
        blockStateModelGenerator.registerCubeAllModelTexturePool(OldBlocks.OLD_INGOT_BLOCK);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(OldBlocks.CRYSTAl_IMBUER);

        blockStateModelGenerator.registerParentedItemModel(OldItems.BLACK_SPIDER_SPAWN_EGG, ModelIds.getMinecraftNamespacedItem("template_spawn_egg"));
        blockStateModelGenerator.registerParentedItemModel(OldItems.EARTH_GOLEM_SPAWN_EGG, ModelIds.getMinecraftNamespacedItem("template_spawn_egg"));

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(OldItems.DEBUG_TOOL, Models.HANDHELD);
        itemModelGenerator.register(OldItems.OLD_INGOT, Models.GENERATED);

        itemModelGenerator.register(OldItems.OLD_SWORD, Models.HANDHELD);
        itemModelGenerator.register(OldItems.OLD_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(OldItems.OLD_AXE, Models.HANDHELD);
        itemModelGenerator.register(OldItems.OLD_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(OldItems.OLD_HOE, Models.HANDHELD);

        for (Item crystalItem : OldSpellMap.SCROLL_ITEM_LIST) {
            itemModelGenerator.register(crystalItem, colorScroll("template_scroll"));
        }
    }

    public static Model colorScroll(String parent) {
        return new Model(Optional.of(new Identifier(OldWorld.MOD_ID, "item/" + parent)), Optional.empty());
    }
}
