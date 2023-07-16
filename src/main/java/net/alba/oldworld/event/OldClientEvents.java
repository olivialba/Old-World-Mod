package net.alba.oldworld.event;

import net.alba.oldworld.networking.OldPackets;
import net.alba.oldworld.registry.OldClient;
import net.alba.oldworld.registry.OldItems;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;

public class OldClientEvents {

    @SuppressWarnings("resource")
    public static void registerEvents() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            PlayerEntity player = MinecraftClient.getInstance().player;
            if (player != null && hasGrimoireInHand(player)) {
                if (OldClient.NextSpell.wasPressed()) {
                    NextSpell();
                }
                else if (OldClient.PreviousSpell.wasPressed()) {
                    PreviousSpell();
                }
                else if (Screen.hasShiftDown()) {
                    
                }
            }
        });

        MouseEvent.SCROLL_EVENT.register((scrollDelta, callbackInfo) -> {
            double scrollDirection = Math.signum(scrollDelta);
        
            if (scrollDirection > 0) {
                PreviousSpell();
            } 
            else if (scrollDirection < 0) {
                NextSpell();
            }
        });
    }


    private static void NextSpell(){
        ClientPlayNetworking.send(OldPackets.SPELL_CHANGE_ID, CreatePacketByteBuf(1));
    }

    private static void PreviousSpell() {
        ClientPlayNetworking.send(OldPackets.SPELL_CHANGE_ID, CreatePacketByteBuf(-1));
    }

    private static PacketByteBuf CreatePacketByteBuf(int amount) {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeByte(amount);
        return buf;
    }

    private static boolean hasGrimoireInHand(PlayerEntity player) {
        return player.getMainHandStack().getItem() == OldItems.GRIMOIRE_BASIC;
    }
}
