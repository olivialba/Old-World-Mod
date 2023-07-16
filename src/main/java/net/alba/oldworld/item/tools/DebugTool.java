package net.alba.oldworld.item.tools;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class DebugTool extends Item {
    private static final Text NAME = Text.literal("Debug Tool");

    public DebugTool(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if (!world.isClient()) {
            //MagicUtil.removeMana(user, 10);
            //user.sendMessage(Text.literal("Mana: " + MagicUtil.getMana(user)));
        }
        return TypedActionResult.success(stack);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        System.out.println("Pose: " + entity.getPose());
        return ActionResult.SUCCESS;
    }

    @Override
    public Text getName(ItemStack stack) {
        return NAME;
    }
}