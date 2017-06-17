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
import uk.co.hexeption.client.event.Event;
import uk.co.hexeption.client.event.events.EventRenderEntities;
import uk.co.hexeption.client.event.events.EventRenderEntity;
import uk.co.hexeption.client.managers.EventManager;

@Mixin(RenderManager.class)
public class MixinRenderManager {

    @Inject(method = "renderEntityStatic", at = @At(value = "RETURN", shift = At.Shift.BEFORE), cancellable = true)
    private void renderEntityStaticPre(Entity entityIn, float partialTicks, boolean p_188388_3_, CallbackInfo callbackInfo) {

        Event event = new EventRenderEntities(Event.Type.PRE, entityIn, partialTicks);
        EventManager.handleEvent(event);
        if (event.isCancelled()) {
            callbackInfo.cancel();
        }
    }

    @Inject(method = "renderEntityStatic", at = @At(value = "RETURN", shift = At.Shift.AFTER))
    private void renderEntityStaticPost(Entity entityIn, float partialTicks, boolean p_188388_3_, CallbackInfo callbackInfo) {

        Event event = new EventRenderEntities(Event.Type.POST, entityIn, partialTicks);
        EventManager.handleEvent(event);
    }

    @Inject(method = "doRenderEntity", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/Render;doRender(Lnet/minecraft/entity/Entity;DDDFF)V", shift = At.Shift.BEFORE), cancellable = true)
    private void doRenderEntity(Entity entityIn, double x, double y, double z, float yaw, float partialTicks, boolean p_188391_10_, CallbackInfo callbackInfo) {

        Event event = new EventRenderEntity(Event.Type.PRE, entityIn, x, y, z, yaw, partialTicks);
        EventManager.handleEvent(event);
        if (event.isCancelled()) {
            callbackInfo.cancel();
        }
    }

}
