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

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.co.hexeption.client.Client;
import uk.co.hexeption.client.events.EventRenderEntities;
import uk.co.hexeption.client.events.EventRenderEntity;

@Mixin(RenderManager.class)
public class MixinRenderManager {

    @Inject(method = "renderEntityStatic", at = @At(value = "RETURN", shift = At.Shift.BEFORE), cancellable = true)
    private void renderEntityStaticPre(Entity entityIn, float partialTicks, boolean p_188388_3_, CallbackInfo callbackInfo) {

        EventRenderEntities event = new EventRenderEntities(entityIn, partialTicks);
        Client.INSTANCE.eventBus.post(event);

        if (event.isCancelled()) {
            callbackInfo.cancel();
        }
    }

    @Inject(method = "renderEntityStatic", at = @At(value = "RETURN", shift = At.Shift.AFTER))
    private void renderEntityStaticPost(Entity entityIn, float partialTicks, boolean p_188388_3_, CallbackInfo callbackInfo) {

        Client.INSTANCE.eventBus.post(new EventRenderEntities(entityIn, partialTicks));
    }

    @Inject(method = "doRenderEntity", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/Render;doRender(Lnet/minecraft/entity/Entity;DDDFF)V", shift = At.Shift.BEFORE), cancellable = true)
    private void doRenderEntity(Entity entityIn, double x, double y, double z, float yaw, float partialTicks, boolean p_188391_10_, CallbackInfo callbackInfo) {

        EventRenderEntity event = new EventRenderEntity(entityIn, x, y, z, yaw, partialTicks);
        Client.INSTANCE.eventBus.post(event);

        if (event.isCancelled()) {
            callbackInfo.cancel();
        }
    }

}
