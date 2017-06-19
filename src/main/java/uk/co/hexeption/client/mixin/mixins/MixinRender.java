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

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.co.hexeption.client.Client;
import uk.co.hexeption.client.events.EventRenderLable;
import uk.co.hexeption.client.events.EventTeamColour;

@Mixin(Render.class)
public class MixinRender {

    @Inject(method = "renderLivingLabel", at = @At(value = "HEAD"), cancellable = true)
    private <T extends Entity> void renderLivingLabelPre(T entityIn, String str, double x, double y, double z, int maxDistance, CallbackInfo callbackInfo) {

        EventRenderLable event = new EventRenderLable(entityIn, str, x, y, z, maxDistance);
        Client.INSTANCE.eventBus.post(event);

        if (event.isCancelled()) {
            callbackInfo.cancel();
        }
    }

    @Inject(method = "renderLivingLabel", at = @At(value = "HEAD"))
    private <T extends Entity> void renderLivingLabelPost(T entityIn, String str, double x, double y, double z, int maxDistance, CallbackInfo callbackInfo) {

        Client.INSTANCE.eventBus.post(new EventRenderLable(entityIn, str, x, y, z, maxDistance));
    }

    @Inject(method = "getTeamColor", at = @At("HEAD"), cancellable = true)
    private <T extends Entity> void getTeamColor(T entityIn, CallbackInfoReturnable<Integer> callbackInfo) {

        EventTeamColour event = new EventTeamColour(entityIn);
        Client.INSTANCE.eventBus.post(event);

        if (event.isCancelled()) {
            callbackInfo.setReturnValue(event.getColor());
        }
    }

}
