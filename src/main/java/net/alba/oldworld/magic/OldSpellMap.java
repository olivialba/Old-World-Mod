package net.alba.oldworld.magic;

import java.util.ArrayList;
import java.util.List;
import net.alba.oldworld.OldWorld;
import net.alba.oldworld.item.crystals.crystalTemplate;
import net.alba.oldworld.magic.spell.Bite;
import net.alba.oldworld.magic.spell.Combustion;
import net.alba.oldworld.magic.spell.FireBall;
import net.alba.oldworld.magic.spell.Meteor;
import net.alba.oldworld.magic.spell.Petrify;
import net.alba.oldworld.magic.spell.Spell;
import net.alba.oldworld.registry.OldItemGroup;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class OldSpellMap {
    private static final String tooltip = "item.oldworld.crystals.tooltip.";
    public static final RegistryKey<Registry<Spell>> REGISTRY_KEY = RegistryKey.ofRegistry(new Identifier(OldWorld.MOD_ID, "spell_map"));

    public static final Registry<Spell> REGISTRY_SPELL = FabricRegistryBuilder.createSimple(REGISTRY_KEY).buildAndRegister();
    public static final List<Item> CRYSTAL_ITEM_LIST = new ArrayList<>();

    // SPELLS
    public static Spell FIREBALL = register("fireball", new FireBall(), 0xFF0000);
    public static Spell COMBUSTION = register("combustion", new Combustion(), 0xE60000);
    public static Spell METEOR = register("meteor", new Meteor(), 0xE60000);
    public static Spell BITE = register("bite", new Bite(), 0xE60000);
    public static Spell PETRIFY = register("petrify", new Petrify(), 0xE60000);


    private static Spell register(String name, Spell spell, int colorTexture) {
        addItemToItemGroup(OldItemGroup.OLD_WORLD, makeCrystalItem(name, colorTexture));
        return Registry.register(REGISTRY_SPELL, new Identifier(name), spell);
    }

    private static Item makeCrystalItem(String name, int colorTexture) {
        Item item = Registry.register(Registries.ITEM, new Identifier(OldWorld.MOD_ID, name), new crystalTemplate(settingMaxCount(), tooltip + name, name, colorTexture));
        CRYSTAL_ITEM_LIST.add(item);
        return item;
    }

    private static void addItemToItemGroup(ItemGroup group, Item item) {
        ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.add(item));
    }

    public static FabricItemSettings settingMaxCount() {
        return new FabricItemSettings().maxCount(1);
    }

    public static void register() {}
}
