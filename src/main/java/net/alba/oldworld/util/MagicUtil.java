package net.alba.oldworld.util;

import net.alba.oldworld.OldWorld;
import net.alba.oldworld.magic.ManaProperties;
import net.alba.oldworld.registry.OldComponents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

public class MagicUtil {
    public static ManaProperties getManaComponent(PlayerEntity player) {
        return OldComponents.MANA_COMPONENT.get(player);
    }

    public static int getMana(PlayerEntity player) {
        return OldComponents.MANA_COMPONENT.get(player).getMana();
    }

    public static int getManaCapacity(PlayerEntity player) {
        return OldComponents.MANA_COMPONENT.get(player).getManaCapacity();
    }

    public static boolean addMana(PlayerEntity player, int num) {
        return OldComponents.MANA_COMPONENT.get(player).canAddMana(num);
    }

    public static boolean removeMana(PlayerEntity player, int num) {
        return OldComponents.MANA_COMPONENT.get(player).canRemoveMana(num);
    }

    public static int addIndex(ItemStack stack, int amount) {
        NbtCompound nbt = stack.getOrCreateSubNbt(OldWorld.MOD_ID);
        int index = getOrCreateIndex(nbt);

        if (index + amount >= 5) {
            index = 5;
        } 
        else if (index + amount <= 1) {
            index = 1;
        } 
        else {
            index += amount;
        }
        nbt.putByte("SpellIndex", (byte)index);
        return index;
    }

    public static byte getOrCreateIndex(NbtCompound nbt) {
        if (!nbt.contains("SpellIndex", 1)) {
            nbt.putByte("SpellIndex", (byte)1);
        }
        return nbt.getByte("SpellIndex");
    }

    public static byte get(NbtCompound nbt) {
        return nbt.getByte("SpellIndex");
    }
}
