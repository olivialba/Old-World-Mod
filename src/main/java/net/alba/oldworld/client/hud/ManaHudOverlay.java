package net.alba.oldworld.client.hud;

import com.mojang.blaze3d.systems.RenderSystem;
import net.alba.oldworld.OldWorld;
import net.alba.oldworld.util.MagicUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class ManaHudOverlay implements HudRenderCallback {
    private static final Identifier MANA_HUD = new Identifier(OldWorld.MOD_ID, "textures/gui/mana_hud.png");

    @SuppressWarnings("resource")
    @Override
    public void onHudRender(MatrixStack matrixStack, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (!client.player.isCreative()) {
            int x = 0;
            int y = 0;
            if (client != null) {
                int width = client.getWindow().getScaledWidth();
                int height = client.getWindow().getScaledHeight();

                x = width / 2;
                y = height;
            }

            RenderSystem.setShader(GameRenderer::getPositionTexProgram);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.setShaderTexture(0, MANA_HUD);
            for (int i = 0; i < 5; i++) {
                if (MagicUtil.getMana(MinecraftClient.getInstance().player) >= (i + 1) * 10) {
                    DrawableHelper.drawTexture(matrixStack,x - 90 + (i * 8),y - 50,7,0,7,9,14,9);
                }
                else {
                    DrawableHelper.drawTexture(matrixStack,x - 90 + (i * 8),y - 50,0,0,7,9,14,9);
                }
            }
        }
    }
}
