package net.alba.oldworld;

import net.alba.oldworld.event.OldClientEvents;
import net.alba.oldworld.networking.OldPackets;
import net.alba.oldworld.registry.OldClient;
import net.fabricmc.api.ClientModInitializer;

public class OldWorldClient implements ClientModInitializer{

    @Override
    public void onInitializeClient() {
        OldClient.register();
        
        OldPackets.registerS2CPackets();
        OldClientEvents.registerEvents();
    }
}
