package net.alba.oldworld.magic;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.component.tick.ServerTickingComponent;
import net.alba.oldworld.registry.OldComponents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;

public class ManaProperties implements AutoSyncedComponent, ServerTickingComponent {
    private int mana = 0;
    private int manaTicks = 0;
    private int oneMana = 20; // 1 second
    private PlayerEntity player;

    public ManaProperties(PlayerEntity player) {
        this.player = player;
    }

    @Override
    public void readFromNbt(NbtCompound playerNbt) {
        this.mana = playerNbt.getInt("mana");
        this.manaTicks = playerNbt.getInt("mana_regen");
        this.oneMana = playerNbt.getInt("one_mana");
    }

    @Override
    public void writeToNbt(NbtCompound playerNbt) {
        playerNbt.putInt("mana", this.mana);
        playerNbt.putInt("mana_regen", this.manaTicks);
        playerNbt.putInt("one_mana", this.oneMana);
    }

    @Override
    public void serverTick() {
        if (this.getMana() < this.getManaCapacity()) {
            ++this.manaTicks;
            if (this.manaTicks >= oneMana) {
                ++this.mana;
                this.manaTicks = 0;
                OldComponents.MANA_COMPONENT.sync(player);
            }
        }
        else {
            this.manaTicks = 0;
        }
    }

    /**
     * Checks if you can add and add mana to player.
     * <p>Returns {@code True}, if mana was successfully added, or {@code False}, if failed.
     */
    public boolean canAddMana(int num) {
        if ((this.getMana() + num) <= this.getManaCapacity()) {
            this.mana += num;
            OldComponents.MANA_COMPONENT.sync(player);
            return true;
        }
        return false;
    }

    /**
     * Checks if you can remove and remove mana from player.
     * <p>Returns {@code True}, if mana was successfully removed, or {@code False}, if failed.
     */
    public boolean canRemoveMana(int num) {
        if (this.getMana() >= num) {
            this.mana -= num;
            OldComponents.MANA_COMPONENT.sync(player);
            return true;
        }
        return false;
    }

    public int getMana() {
        return this.mana;
    }

    public int getManaCapacity() {
        return (int)player.getAttributeValue(ManaTrait.MANA_CAPACITY);
    }
}
