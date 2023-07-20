package net.alba.oldworld.magic.spell;

import org.jetbrains.annotations.Nullable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

public class Teleport extends Spell {

    public Teleport() {
        super(35);
    }

    @Override
    public void invoke(World world, PlayerEntity player) {
        Vec3d startPosition = player.getCameraPosVec(1.0F);
        Vec3d endPosition = startPosition.add((player.getRotationVec(1.0F)).multiply(30));

        BlockPos pos = (world.raycast(new RaycastContext(startPosition, endPosition, RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE, player))).getBlockPos();
        BlockPos posT = pos;
        for (int i = 0; i < 4; i++) {
            if (player.world.isAir(posT)) {
                player.teleport(posT.getX(), posT.getY(), posT.getZ(), true);
                return;
            }
            else {
                posT.up(1);
            }
        }
        player.teleport(pos.getX(), pos.getY(), pos.getZ(), true);
    } 

    @Override
    public @Nullable SoundEvent getSpellSound() {
        return null;
    }
}
