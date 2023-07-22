package net.alba.oldworld.item;

import java.util.List;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public abstract class MagicItems extends Item{

    public MagicItems(Settings settings) {
        super(settings);
    }

    /**
     * If the user right click with the item, happens only on the server
     * 
     * @param world - world object
     * @param player - player with the staff weapons
     * @param stack - stack with the item
     * @param hand - hand of the player
     */
    public abstract boolean rightClick(World world, PlayerEntity player, ItemStack stack, Hand hand);

    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        if (!world.isClient) {
            if (rightClick(world, player, stack, hand)) {
                return TypedActionResult.success(stack);
            }
        }
        return TypedActionResult.pass(stack);
    }

    /**
     * Setting tooltips for staffs (color GRAY).
     * 
     * @return lang path for tooltip, example: 'item.oldworld.frost_staff.tooltip'
     */
    public String setToolTipString() {
        return null;
    }
    
    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        if (setToolTipString() == null) {
            return;
        }
        tooltip.add(Text.translatable(setToolTipString()).formatted(Formatting.GRAY));
    }

    /**
     * Set cooldown of the item.
     * @param player player
     * @param duration cooldown duration
     */
    public void setCooldown(PlayerEntity player, int duration) {
        player.getItemCooldownManager().set(this, duration);
    }
    
}
