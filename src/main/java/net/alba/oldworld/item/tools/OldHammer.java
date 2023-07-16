package net.alba.oldworld.item.tools;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class OldHammer extends SwordItem {

    public OldHammer(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        jumpMovement(user);
        
        if (!world.isClient) {
            world.createExplosion(user, user.getX(), user.getY(), user.getZ(), 3, false, World.ExplosionSourceType.NONE);
            user.getItemCooldownManager().set(this, 100);
        }

        return TypedActionResult.success(stack);
    }

    public void jumpMovement(PlayerEntity user) {
        double launchDistance = user.isSprinting() ? 1.6 : 1.5; // launch distance
        double launchHeight = !user.isOnGround() ? 1.6 : 1.0; // launch height

        double lookX = user.getRotationVector().x;
        double lookZ = user.getRotationVector().z;

        user.addVelocity(lookX * launchDistance, launchHeight, lookZ * launchDistance);
        user.fallDistance = 0;
    }
}
