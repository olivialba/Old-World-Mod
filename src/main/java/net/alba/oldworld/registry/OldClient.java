package net.alba.oldworld.registry;

import org.lwjgl.glfw.GLFW;

import net.alba.oldworld.client.hud.ManaHudOverlay;
import net.alba.oldworld.client.renderer.BallBasicRenderer;
import net.alba.oldworld.client.renderer.BlackSpiderRenderer;
import net.alba.oldworld.client.renderer.CrystalRenderer;
import net.alba.oldworld.client.renderer.EarthGolemRenderer;
import net.alba.oldworld.client.renderer.PlaceHolderRenderer;
import net.alba.oldworld.item.scroll.grimoireScroll;
import net.alba.oldworld.magic.OldSpellMap;
import net.alba.oldworld.particles.BeamParticle;
import net.alba.oldworld.screen.CrystalImbuerBlock.CrystalImbuerScreen;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.item.Item;

public class OldClient {

    // Key Bindings
    public static KeyBinding NextSpell = new KeyBinding("key.oldworld.next_spell", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_N, "key.category.oldworld");
    public static KeyBinding PreviousSpell = new KeyBinding("key.oldworld.previous_spell", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_B, "key.category.oldworld");

    public static void register() {

        // Entities Renderer
        EntityRendererRegistry.register(OldEntities.BLACK_SPIDER, BlackSpiderRenderer::new);
        EntityRendererRegistry.register(OldEntities.EARTH_GOLEM, EarthGolemRenderer::new);

        EntityRendererRegistry.register(OldEntities.CRYSTAL_PROJECTILE, CrystalRenderer::new);
        EntityRendererRegistry.register(OldEntities.BASIC_BALL_PROJECTILE, BallBasicRenderer::new);
        EntityRendererRegistry.register(OldEntities.PLACE, PlaceHolderRenderer::new);   // TEST

        // Screens - HUD
        HandledScreens.register(OldScreenHandlers.CRYSTAL_IMBUER_SCREEN_HANDLER, CrystalImbuerScreen::new);
        HudRenderCallback.EVENT.register(new ManaHudOverlay());

        // Scroll Item Colors
        for (Item scrollItem : OldSpellMap.SCROLL_ITEM_LIST) {
            ColorProviderRegistry.ITEM.register((stack, tintIndex) -> ((grimoireScroll) stack.getItem()).getColor(tintIndex), scrollItem);
        }

        // Keys
        KeyBindingHelper.registerKeyBinding(NextSpell);
        KeyBindingHelper.registerKeyBinding(PreviousSpell);

        // Particles
        ParticleFactoryRegistry.getInstance().register(OldParticles.BEAM, BeamParticle.Factory::new);
    }
}
