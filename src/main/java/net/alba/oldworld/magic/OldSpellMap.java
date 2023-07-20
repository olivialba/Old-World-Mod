package net.alba.oldworld.magic;

import java.util.ArrayList;
import java.util.List;
import net.alba.oldworld.OldWorld;
import net.alba.oldworld.item.scroll.grimoireScroll;
import net.alba.oldworld.magic.spell.Bite;
import net.alba.oldworld.magic.spell.Combustion;
import net.alba.oldworld.magic.spell.FireBall;
import net.alba.oldworld.magic.spell.Meteor;
import net.alba.oldworld.magic.spell.Petrify;
import net.alba.oldworld.magic.spell.Spell;
import net.alba.oldworld.magic.spell.Teleport;
import net.alba.oldworld.registry.OldItemGroup;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

public class OldSpellMap {
    public static final RegistryKey<Registry<Spell>> REGISTRY_KEY = RegistryKey.ofRegistry(new Identifier(OldWorld.MOD_ID, "spell_map"));

    public static final Registry<Spell> REGISTRY_SPELL = FabricRegistryBuilder.createSimple(REGISTRY_KEY).buildAndRegister(); // REGISTRY FOR SPELL INVOKING
    public static final List<Item> SCROLL_ITEM_LIST = new ArrayList<>();

    // SPELLS T-1
    public static Spell FIREBALL = register("fireball", new FireBall(), 0xF8A770, Formatting.GREEN);
    public static Spell COMBUSTION = register("combustion", new Combustion(), 0xFFB68C, Formatting.GREEN);
    public static Spell METEOR = register("meteor", new Meteor(), 0xFFB68C, Formatting.GREEN);
    public static Spell BITE = register("bite", new Bite(), 0xFFB68C, Formatting.GREEN);
    public static Spell PETRIFY = register("petrify", new Petrify(), 0xFFB68C, Formatting.GREEN);

    // SPELLS T-2

    // SPELLS T-3
    public static Spell TELEPORT = register("teleport", new Teleport(), 0xFFB68C, Formatting.LIGHT_PURPLE);


    private static Spell register(String name, Spell spell, int colorTexture, Formatting colorName) {
        addItemToItemGroup(OldItemGroup.OLD_WORLD, makeGrimoireScrollItem(name, colorTexture, colorName));
        return Registry.register(REGISTRY_SPELL, new Identifier(name), spell);
    }

    private static Item makeGrimoireScrollItem(String name, int colorTexture, Formatting colorName) {
        Item item = Registry.register(Registries.ITEM, 
            new Identifier(OldWorld.MOD_ID, name), 
            new grimoireScroll(settingMaxCount(), name, colorTexture, colorName));
        SCROLL_ITEM_LIST.add(item);
        return item;
    }

    private static void addItemToItemGroup(ItemGroup group, Item item) {
        ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.add(item));
    }

    public static FabricItemSettings settingMaxCount() {
        return new    FabricItemSettings().maxCount(1);
    }

    public static void register() {}
}
