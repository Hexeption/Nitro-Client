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
