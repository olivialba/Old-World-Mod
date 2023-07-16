package net.alba.oldworld.event;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

@Environment(EnvType.CLIENT)
public class MouseEvent {
    public static final Event<ScrollEvent> SCROLL_EVENT = EventFactory.createArrayBacked(ScrollEvent.class,
    (listeners) -> (scrollDelta, callbackInfo) -> {
        for (ScrollEvent listener : listeners) {
            listener.onScroll(scrollDelta, callbackInfo);
        }
    });

    public interface ScrollEvent {
        void onScroll(double scrollDelta, CallbackInfo callbackInfo);
    }
}