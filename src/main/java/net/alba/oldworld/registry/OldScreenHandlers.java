package net.alba.oldworld.registry;

import net.alba.oldworld.screen.CrystalImbuerBlock.ScrollImbuerScreenHandler;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandlerType;

public class OldScreenHandlers {
    public static ScreenHandlerType<ScrollImbuerScreenHandler> CRYSTAL_IMBUER_SCREEN_HANDLER;

    public static void registerAllScreenHandlers() {
        CRYSTAL_IMBUER_SCREEN_HANDLER = new ScreenHandlerType<>(ScrollImbuerScreenHandler::new, FeatureFlags.VANILLA_FEATURES);
    }
}
