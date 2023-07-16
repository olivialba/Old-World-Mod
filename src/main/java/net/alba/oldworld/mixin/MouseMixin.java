package net.alba.oldworld.mixin;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.alba.oldworld.event.MouseEvent;
import net.alba.oldworld.registry.OldItems;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.Mouse;
import net.minecraft.entity.player.PlayerEntity;

@Mixin(Mouse.class)
public class MouseMixin {
    @Shadow @Final private MinecraftClient client;

    /**
     * Injects custom behavior into the onMouseScroll method to detect scrolling of mouse wheel. 
     * This is being injected before 'this.client.player.getInventory().scrollInHotbar(i)' to cancel vanilla hotbar scrolling.
     */
    @Inject(method = "onMouseScroll", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;getInventory()Lnet/minecraft/entity/player/PlayerInventory;"), cancellable = true)
    private void injectOnMouseScroll(long window, double horizontal, double vertical, CallbackInfo callbackInfo) {
        if (shouldTriggerEvent(this.client.player)) {
            MouseEvent.SCROLL_EVENT.invoker().onScroll(vertical, callbackInfo);
            callbackInfo.cancel();
        }
    }

    /**
     * Checks if the scroll event should be triggered based on player's conditions.
     * Conditions already checked in original method: 
     *      !this.client.player.isSpectator(), this.client.currentScreen == null, this.client.player != null, 
     *      this.client.getOverlay() == null, window == MinecraftClient.getInstance().getWindow().getHandle()
     */
    private boolean shouldTriggerEvent(PlayerEntity player) {
        if (player.isSneaking() && !player.getMainHandStack().isEmpty() && 
                player.getMainHandStack().getItem() == OldItems.GRIMOIRE_BASIC) {
            return true;
        }
        return false;
    }
}