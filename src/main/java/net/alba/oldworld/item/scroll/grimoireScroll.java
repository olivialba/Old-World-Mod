package net.alba.oldworld.item.scroll;

import java.util.List;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

public class grimoireScroll extends Item {
    private static final String TOOLTIP = "item.oldworld.grim_scroll.tooltip.";
    private static final String NAME = "item.oldworld.grim_scroll.template";
    private final int colorTexture;
    private final String spellKey;
    private final Formatting nameColor;

    public grimoireScroll(Settings settings, String key, int colorTexture, Formatting color) {
        super(settings);
        this.colorTexture = colorTexture;
        this.spellKey = key;
        this.nameColor = color;
    }

    public String getSpellKey() {
        return this.spellKey;
    }

    public int getColor(int tintIndex) {
        return tintIndex == 1 ? colorTexture: -1;
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }

    @Override
    public Text getName(ItemStack stack) {
        return Text.translatable(NAME).formatted(nameColor);
    }

    @Environment(EnvType.CLIENT)
    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable(TOOLTIP + spellKey).formatted(Formatting.GRAY));
    }
}
