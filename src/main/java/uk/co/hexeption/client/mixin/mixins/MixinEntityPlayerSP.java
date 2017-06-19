/*******************************************************************************
 *     ITweaker-Client
 *     Copyright (C) 2017  Hexeption (Keir Davis)
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
