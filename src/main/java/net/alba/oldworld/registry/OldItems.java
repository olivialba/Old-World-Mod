package net.alba.oldworld.registry;

import net.alba.oldworld.OldWorld;
import net.alba.oldworld.item.ToolMaterialGroups;
import net.alba.oldworld.item.grimoires.GrimoireBasic;
import net.alba.oldworld.item.tools.DebugTool;
import net.alba.oldworld.item.tools.OldHammer;
import net.alba.oldworld.magic.OldSpellMap;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.AxeItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class OldItems {

    // TAGS
    public static final TagKey<Item> TAG_SPELL_CRYSTALS = TagKey.of(RegistryKeys.ITEM, new Identifier("oldworld:spell_crystals"));
    
    // ITEMS
    public static final Item OLD_INGOT = registerItem("old_ingot", new Item(settings()));
    public static final Item DEBUG_TOOL = registerItem("debug_tool", new DebugTool(settings().rarity(Rarity.EPIC)));

    // WEAPONS / EQUIPMENT
    public static final Item OLD_HAMMER = registerItem("old_hammer", new OldHammer(ToolMaterialGroups.OLD_METAL, 6, -3.3F, settings().maxCount(1)));
    public static final ToolItem OLD_SWORD = registerToolItem("old_sword", new SwordItem(ToolMaterialGroups.OLD_METAL, 4, -2F, settings()));
    public static final ToolItem OLD_PICKAXE = registerToolItem("old_pickaxe", new PickaxeItem(ToolMaterialGroups.OLD_METAL, 3, -2.8F, settings()));
    public static final ToolItem OLD_AXE = registerToolItem("old_axe", new AxeItem(ToolMaterialGroups.OLD_METAL, 6, -3.1F, settings()));
    public static final ToolItem OLD_SHOVEL = registerToolItem("old_shovel", new ShovelItem(ToolMaterialGroups.OLD_METAL, 1, -3F, settings()));
    public static final ToolItem OLD_HOE = registerToolItem("old_hoe", new HoeItem(ToolMaterialGroups.OLD_METAL, 2, -2.5F, settings()));

    // GRIMOIRE
    public static final Item GRIMOIRE_BASIC = registerItem("grimoire_basic", new GrimoireBasic(settingMaxCount().rarity(Rarity.UNCOMMON)));

    // SPAWN EGG
    public static final Item BLACK_SPIDER_SPAWN_EGG = registerItem("black_spider_spawn_egg", new SpawnEggItem(OldEntities.BLACK_SPIDER, 0x333E48, 0x7C868E, settings()));

    public static void addItemsToItemGroup() {
        addItemToItemGroup(OldItemGroup.OLD_WORLD, DEBUG_TOOL);

        addItemToItemGroup(OldItemGroup.OLD_WORLD, OLD_SWORD);
        addItemToItemGroup(OldItemGroup.OLD_WORLD, OLD_PICKAXE);
        addItemToItemGroup(OldItemGroup.OLD_WORLD, OLD_AXE);
        addItemToItemGroup(OldItemGroup.OLD_WORLD, OLD_SHOVEL);
        addItemToItemGroup(OldItemGroup.OLD_WORLD, OLD_HOE);

        addItemToItemGroup(OldItemGroup.OLD_WORLD, OLD_INGOT);
        addItemToItemGroup(OldItemGroup.OLD_WORLD, BLACK_SPIDER_SPAWN_EGG);

        addItemToItemGroup(OldItemGroup.OLD_WORLD, GRIMOIRE_BASIC);
    }

    public static FabricItemSettings settings() {
        return new FabricItemSettings();
    }

    public static FabricItemSettings settingMaxCount() {
        return new FabricItemSettings().maxCount(1);
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(OldWorld.MOD_ID, name), item);
    }

    private static ToolItem registerToolItem(String name, ToolItem toolItem) {
        return Registry.register(Registries.ITEM, new Identifier(OldWorld.MOD_ID, name), toolItem);
    }

    private static void addItemToItemGroup(ItemGroup group, Item item) {
        ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.add(item));
    }
    
    public static void registerModItems() {
        OldWorld.LOGGER.info("Registering Mod Items for " + OldWorld.MOD_ID);

        addItemsToItemGroup();
        OldSpellMap.register();
    }
}
