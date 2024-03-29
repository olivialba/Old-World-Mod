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
import net.alba.oldworld.magic.spell.Singularity;
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

    // SPELLS COMMON
    public static Spell FIREBALL = register("fireorb_c", new FireBall(), Formatting.GREEN);
    public static Spell COMBUSTION = register("combustion_c", new Combustion(), Formatting.GREEN);
    public static Spell METEOR = register("meteor_c", new Meteor(), Formatting.GREEN);
    public static Spell BITE = register("fangs_c", new Bite(), Formatting.GREEN);
    public static Spell PETRIFY = register("petrify_c", new Petrify(), Formatting.GREEN);

    // SPELLS RARE

    // SPELLS EPIC
    public static Spell TELEPORT = register("teleport_e", new Teleport(), Formatting.LIGHT_PURPLE);

    // SPELLS LEGENDARY
    public static Spell SINGULARITY = register("singularity_l", new Singularity(), Formatting.GOLD);


    private static Spell register(String name, Spell spell, Formatting colorName) {
        addItemToItemGroup(OldItemGroup.OLD_WORLD, makeGrimoireScrollItem(name, colorName));
        return Registry.register(REGISTRY_SPELL, new Identifier(name), spell);
    }

    private static Item makeGrimoireScrollItem(String name, Formatting colorName) {
        Item item = Registry.register(Registries.ITEM, new Identifier(OldWorld.MOD_ID, name), 
            new grimoireScroll(settingMaxCount(), name, colorName));
        SCROLL_ITEM_LIST.add(item);
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
