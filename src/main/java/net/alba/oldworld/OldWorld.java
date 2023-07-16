package net.alba.oldworld;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.alba.oldworld.magic.ManaTrait;
import net.alba.oldworld.networking.OldPackets;
import net.alba.oldworld.registry.OldBlockEntities;
import net.alba.oldworld.registry.OldBlocks;
import net.alba.oldworld.registry.OldEntities;
import net.alba.oldworld.registry.OldItemGroup;
import net.alba.oldworld.registry.OldItems;
import net.alba.oldworld.registry.OldMix;
import net.alba.oldworld.registry.OldParticles;
import net.alba.oldworld.registry.OldScreenHandlers;
import net.fabricmc.api.ModInitializer;

public class OldWorld implements ModInitializer {
	public static final String MOD_ID = "oldworld";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		OldItemGroup.registerItemGroups();
		OldItems.registerModItems();
		OldBlocks.registerModBlocks();
        OldBlockEntities.registerBlockEntities();
		OldEntities.registerEntities();
        OldMix.registerMix();

        OldParticles.registerParticle();
        OldScreenHandlers.registerAllScreenHandlers();
        OldPackets.registerC2SPackets();
        ManaTrait.register();
	}       
}