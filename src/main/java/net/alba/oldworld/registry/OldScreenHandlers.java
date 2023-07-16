package net.alba.oldworld.registry;

import net.alba.oldworld.screen.CrystalImbuerBlock.CrystalImbuerScreenHandler;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandlerType;

public class OldScreenHandlers {
    public static ScreenHandlerType<CrystalImbuerScreenHandler> CRYSTAL_IMBUER_SCREEN_HANDLER;

    public static void registerAllScreenHandlers() {
        CRYSTAL_IMBUER_SCREEN_HANDLER = new ScreenHandlerType<>(CrystalImbuerScreenHandler::new, FeatureFlags.VANILLA_FEATURES);
    }
}
