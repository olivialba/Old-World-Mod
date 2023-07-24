package net.alba.oldworld.screen.CrystalImbuerBlock;

import com.mojang.blaze3d.systems.RenderSystem;

import net.alba.oldworld.OldWorld;
import net.alba.oldworld.registry.OldItems;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class CrystalImbuerScreen extends HandledScreen<CrystalImbuerScreenHandler> {
    private static final Identifier TEXTURE = new Identifier(OldWorld.MOD_ID, "textures/gui/scroll_imbuer_gui.png");

    public CrystalImbuerScreen(CrystalImbuerScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.backgroundHeight = 171;
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - 171) / 2;
        drawTexture(matrices, x, y, 0, 0, backgroundWidth, 171);
        renderGrimoireInfo(matrices, x, y);
        renderProgressArrow(matrices, x, y);
    }

    private void renderGrimoireInfo(MatrixStack matrices, int x, int y) {
        Slot zero = this.handler.getSlot(0);
        if (this.focusedSlot == zero && zero.hasStack() && zero.getStack().getItem() == OldItems.GRIMOIRE_BASIC) {
            NbtCompound grimoireModNbt = zero.getStack().getSubNbt(OldWorld.MOD_ID);
            if (grimoireModNbt != null) {
                drawTexture(matrices, x - 90, y + 11 , 0, 171, 90, 79);
                x -= 83;
                y += 5;
                for (int i = 1; i <= 5; i++) {
                    String spellName = grimoireModNbt.getString("SP" + i);
                    Text nameSpell = spellName.isEmpty() ? 
                        Text.literal("") :
                        Text.translatable("magic.oldworld.spell_scroll." + spellName).formatted(spellColor(spellName));
                    this.textRenderer.draw(matrices, i + ".", x, y + 1 + (14 * i), 0);
                    drawTextWithShadow(matrices, textRenderer, nameSpell, x + 9, y + (14 * i), i);

                }
            }
        }
    }

    private void renderProgressArrow(MatrixStack matrices, int x, int y) {
        if (handler.isCrafting()) {
            int progress = handler.getScaledProgress();
            drawTexture(matrices, x + (74 - progress), y + 21, 228 - progress, 0, progress, 43);
        }
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        drawMouseoverTooltip(matrices, mouseX, mouseY);
    }

    private static Formatting spellColor(String str) {
        char color = str.charAt(str.length() - 1);
        switch (color) {
            case 'c': // Common
                return Formatting.GREEN;
            case 'r': // Rare
                return Formatting.AQUA;
            case 'e': // Epic
                return Formatting.LIGHT_PURPLE;
            case 'l': // Legendary
                return Formatting.GOLD;
        }
        return Formatting.GREEN;
    }
}
