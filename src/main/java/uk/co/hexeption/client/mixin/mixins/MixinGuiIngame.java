package uk.co.hexeption.client.mixin.mixins;

import net.minecraft.client.gui.GuiIngame;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.co.hexeption.client.event.Event;
import uk.co.hexeption.client.event.events.EventHud;
import uk.co.hexeption.client.managers.EventManager;

@Mixin(GuiIngame.class)
public class MixinGuiIngame {

    @Inject(method = "renderGameOverlay", at = @At("RETURN"))
    private void renderGameOverlay(float partialTicks, CallbackInfo callbackInfo) {

        Event event = new EventHud(Event.Type.PRE, partialTicks);
        EventManager.handleEvent(event);
    }
}
