package net.alba.oldworld.item.crystals;

import java.util.List;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

public class crystalTemplate extends Item {
    private static final Text NAME = Text.translatable("item.oldworld.crystals.template");
    private final int colorTexture;
    private final String tooltipText;
    private final String spellKey;

    public crystalTemplate(Settings settings, String tooltipText, String key, int colorTexture) {
        super(settings);
        this.colorTexture = colorTexture;
        this.tooltipText = tooltipText;
        this.spellKey = key;
    }

    public String getSpellKey() {
        return this.spellKey;
    }

    public int getColor(int tintIndex) {
        return tintIndex == 1 ? colorTexture: -1;
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return false;
    }

    @Override
    public Text getName(ItemStack stack) {
        return NAME;
    }

    @Environment(EnvType.CLIENT)
    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable(tooltipText).formatted(Formatting.GRAY));
    }
}
