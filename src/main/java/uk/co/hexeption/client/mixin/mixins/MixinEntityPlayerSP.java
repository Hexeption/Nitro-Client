package uk.co.hexeption.client.mixin.mixins;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.MoverType;
import net.minecraft.util.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.co.hexeption.client.event.Event;
import uk.co.hexeption.client.event.events.*;
import uk.co.hexeption.client.managers.EventManager;

@Mixin(EntityPlayerSP.class)
public abstract class MixinEntityPlayerSP extends MixinEntity {

    @Inject(method = "onUpdate", at = @At("HEAD"))
    private void onPlayerUpdate(CallbackInfo callbackInfo) {

        EventPlayerUpdate event = new EventPlayerUpdate(Event.Type.PRE);
        EventManager.handleEvent(event);
    }

    @Inject(method = "attackEntityFrom", at = @At("HEAD"), cancellable = true)
    private void onPlayerDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> callbackInfoReturnable) {

        Event event = new EventPlayerDamage(Event.Type.PRE, source, amount);
        EventManager.handleEvent(event);
        if (event.isCancelled()) {
            callbackInfoReturnable.cancel();
        }
    }

    @Inject(method = "sendChatMessage", at = @At("HEAD"), cancellable = true)
    private void onSendChatMessage(String message, CallbackInfo callbackInfo) {

        Event event = new EventChat.Send(Event.Type.PRE, message, (EntityPlayerSP) (Object) this);
        EventManager.handleEvent(event);
        if (event.isCancelled()) {
            callbackInfo.cancel();
        }
    }

    @Inject(method = "onLivingUpdate", at = @At("HEAD"))
    private void onLivingUpdate(CallbackInfo callbackInfo) {

        EventLivingUpdate event = new EventLivingUpdate(Event.Type.PRE);
        EventManager.handleEvent(event);
    }

    @Redirect(method = "move", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/AbstractClientPlayer;move(Lnet/minecraft/entity/MoverType;DDD)V"))
    public void move(AbstractClientPlayer player, MoverType type, double x, double y, double z) {

        EventMove event = new EventMove(Event.Type.PRE, type, x, y, z);
        EventManager.handleEvent(event);

        if (event.isCancelled()) {
            return;
        }

        super.move(type, event.getMotionX(), event.getMotionY(), event.getMotionZ());
    }
}
