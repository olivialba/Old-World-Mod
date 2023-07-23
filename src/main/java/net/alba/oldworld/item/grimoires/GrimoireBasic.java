package net.alba.oldworld.item.grimoires;

import java.util.List;
import net.alba.oldworld.OldWorld;
import net.alba.oldworld.item.MagicItems;
import net.alba.oldworld.magic.OldSpellMap;
import net.alba.oldworld.magic.spell.Spell;
import net.alba.oldworld.util.MagicUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class GrimoireBasic extends MagicItems {
    private static final String Error = "Error: can't find spell in the SpellMap. This may be due to a change in the Mod's registry.";

    public GrimoireBasic(Settings settings) {
        super(settings);
    }

    @Override
    public boolean rightClick(World world, PlayerEntity player, ItemStack stack, Hand hand) {
        Spell currentSpell = getSpell(stack, player);
        if (currentSpell != null) {
            currentSpell.cast(world, player);
            return true;
        }
        return false;
    }

    private static Spell getSpell(ItemStack mainHand, PlayerEntity player) {
        NbtCompound grimoireModNbt = mainHand.getSubNbt(OldWorld.MOD_ID);
        if (grimoireModNbt == null) {
            return null;
        }
        String spellIdentifier = grimoireModNbt.getString("SP" + MagicUtil.get(grimoireModNbt));
        if (spellIdentifier.isEmpty()) {
            player.sendMessage(Text.literal("No spell found in index " + MagicUtil.get(grimoireModNbt))
                .fillStyle(Style.EMPTY.withColor(Formatting.AQUA)), true);
            return null;
        }
        Spell currentSpell = OldSpellMap.REGISTRY_SPELL.get(new Identifier(spellIdentifier)); // find Spell in the SPELL REGISTRY
        if (currentSpell == null) {
            player.sendMessage(Text.literal(Error).formatted(Formatting.RED));
        }
        return currentSpell;
    }
    
    @Override
    public void onCraft(ItemStack stack, World world, PlayerEntity player) {
        NbtCompound OldWorldMOD = stack.getOrCreateSubNbt(OldWorld.MOD_ID);

        if (!OldWorldMOD.contains("SpellIndex", 1)) {
            OldWorldMOD.putByte("SpellIndex", (byte)1);
        }
    }

    @Environment(EnvType.CLIENT)
    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(Text.translatable("item.oldworld.grimoire_basic.tooltip").formatted(Formatting.GRAY));
    }

    @Override
    public boolean allowNbtUpdateAnimation(PlayerEntity player, Hand hand, ItemStack oldStack, ItemStack newStack) {
        return false;
    }
}
