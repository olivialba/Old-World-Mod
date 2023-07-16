package net.alba.oldworld.networking;

import net.alba.oldworld.OldWorld;
import net.alba.oldworld.networking.packet.SpellIndexC2SPacket;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;

public class OldPackets {
    public static final Identifier SPELL_CHANGE_ID = new Identifier(OldWorld.MOD_ID, "spell_change");

    public static void registerC2SPackets() {
        ServerPlayNetworking.registerGlobalReceiver(SPELL_CHANGE_ID, SpellIndexC2SPacket::receive);
    }

    public static void registerS2CPackets() {
        
    }
}
