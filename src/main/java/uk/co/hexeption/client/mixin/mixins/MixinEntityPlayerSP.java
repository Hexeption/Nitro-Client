/*******************************************************************************
 *             DO WHAT THE FUCK YOU WANT TO PUBLIC LICENSE
 *                     Version 2, December 2004
 *
 *  Copyright (C) 2004 Sam Hocevar <sam@hocevar.net>
 *
 *  Everyone is permitted to copy and distribute verbatim or modified
 *  copies of this license document, and changing it is allowed as long
 *  as the name is changed.
 *
 *             DO WHAT THE FUCK YOU WANT TO PUBLIC LICENSE
 *    TERMS AND CONDITIONS FOR COPYING, DISTRIBUTION AND MODIFICATION
 *
 *   0. You just DO WHAT THE FUCK YOU WANT TO.
 *
 ******************************************************************************/

package uk.co.hexeption.client.mixin.mixins;

import me.zero.alpine.type.EventState;
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
import uk.co.hexeption.client.Client;
import uk.co.hexeption.client.events.*;

@Mixin(EntityPlayerSP.class)
public abstract class MixinEntityPlayerSP extends MixinEntity {

    @Inject(method = "onUpdate", at = @At("HEAD"))
    private void onPlayerUpdate(CallbackInfo callbackInfo) {

        Client.INSTANCE.eventBus.post(new EventUpdate());
    }

    @Inject(method = "attackEntityFrom", at = @At("HEAD"), cancellable = true)
    private void onPlayerDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> callbackInfoReturnable) {

        EventPlayerDamage event = new EventPlayerDamage(source, amount);
        Client.INSTANCE.eventBus.post(event);

        if (event.isCancelled()) {
            callbackInfoReturnable.cancel();
        }
    }

    @Inject(method = "sendChatMessage", at = @At("HEAD"), cancellable = true)
    private void onSendChatMessage(String message, CallbackInfo callbackInfo) {

        EventChat.Send event = new EventChat.Send(message, (EntityPlayerSP) (Object) this);
        Client.INSTANCE.eventBus.post(event);

        if (event.isCancelled()) {
            callbackInfo.cancel();
        }
    }

    @Inject(method = "onLivingUpdate", at = @At("HEAD"))
    private void onLivingUpdatePre(CallbackInfo callbackInfo) {

        Client.INSTANCE.eventBus.post(new EventLivingUpdate(EventState.PRE));
    }

    @Inject(method = "onLivingUpdate", at = @At("RETURN"))
    private void onLivingUpdatePost(CallbackInfo callbackInfo) {

        Client.INSTANCE.eventBus.post(new EventLivingUpdate(EventState.POST));
    }

    @Redirect(method = "move", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/AbstractClientPlayer;move(Lnet/minecraft/entity/MoverType;DDD)V"))
    public void move(AbstractClientPlayer player, MoverType type, double x, double y, double z) {

        EventMove event = new EventMove(type, x, y, z);
        Client.INSTANCE.eventBus.post(event);

        if (event.isCancelled()) {
            return;
        }

        super.move(type, event.getMotionX(), event.getMotionY(), event.getMotionZ());
    }
}
