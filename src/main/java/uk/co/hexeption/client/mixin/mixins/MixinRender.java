/*******************************************************************************
 *     Nitro Client
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
