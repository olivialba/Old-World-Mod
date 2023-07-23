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
    private static final String NAME = "magic.oldworld.spell_scroll.";
    private static final String TOOLTIP = "magic.oldworld.spell_scroll.tooltip";
    private final String spellName;
    private final Formatting nameColor;

    public grimoireScroll(Settings settings, String name, Formatting nameColors) {
        super(settings);
        this.spellName = name;
        this.nameColor = nameColors;
    }

    public String getSpellKey() {
        return this.spellName;
    }

    // public int getColor(int tintIndex) {
    //     return tintIndex == 1 ? colorTexture: -1;
    // }
 
    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }

    @Override
    public Text getName(ItemStack stack) {
        return Text.translatable(NAME + spellName).formatted(nameColor);
    }

    @Environment(EnvType.CLIENT)
    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable(TOOLTIP).formatted(Formatting.GRAY, Formatting.ITALIC));
    }
}
