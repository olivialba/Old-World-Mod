package net.alba.oldworld.networking.packet;

import net.alba.oldworld.OldWorld;
import net.alba.oldworld.util.MagicUtil;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class SpellIndexC2SPacket {
        public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler,
                                    PacketByteBuf buf, PacketSender responseSender) {
        // Everything here happens ONLY on the Server!
        if (!player.getMainHandStack().isEmpty()) {
            MagicUtil.addIndex((player.getMainHandStack()), ((int)buf.readByte()));
            player.sendMessage(Text.literal("Index: " + player.getMainHandStack().getSubNbt(OldWorld.MOD_ID).getByte("SpellIndex"))
                .fillStyle(Style.EMPTY.withColor(Formatting.AQUA)), true);
        }
    }
}
