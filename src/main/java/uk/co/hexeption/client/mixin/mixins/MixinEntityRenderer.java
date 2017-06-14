package uk.co.hexeption.client.mixin.mixins;

import net.minecraft.client.renderer.EntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.co.hexeption.client.event.Event;
import uk.co.hexeption.client.event.events.EventRenderWorld;
import uk.co.hexeption.client.managers.EventManager;

@Mixin(EntityRenderer.class)
public class MixinEntityRenderer {

    @Inject(method = "renderWorldPass", at = @At(value = "FIELD", target = "Lnet/minecraft/client/renderer/EntityRenderer;renderHand:Z", shift = At.Shift.BEFORE))
    private void renderWorldPassPre(int pass, float partialTicks, long finishTimeNano, CallbackInfo callbackInfo) {

        EventRenderWorld eventRenderWorld = new EventRenderWorld(Event.Type.PRE);
        EventManager.handleEvent(eventRenderWorld);
    }

    @Inject(method = "renderWorldPass", at = @At(value = "FIELD", target = "Lnet/minecraft/client/renderer/EntityRenderer;renderHand:Z", shift = At.Shift.AFTER))
    private void renderWorldPassPost(int pass, float partialTicks, long finishTimeNano, CallbackInfo callbackInfo) {

        EventRenderWorld eventRenderWorld = new EventRenderWorld(Event.Type.POST);
        EventManager.handleEvent(eventRenderWorld);
    }


}
