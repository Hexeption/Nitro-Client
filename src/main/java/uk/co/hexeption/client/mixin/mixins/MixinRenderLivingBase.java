package uk.co.hexeption.client.mixin.mixins;

import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import uk.co.hexeption.client.event.Event;
import uk.co.hexeption.client.event.events.EventRenderLayers;
import uk.co.hexeption.client.managers.EventManager;

@Mixin(RenderLivingBase.class)
public class MixinRenderLivingBase {

    @Redirect(method = "renderLayers", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/layers/LayerRenderer;doRenderLayer(Lnet/minecraft/entity/EntityLivingBase;FFFFFFF)V"))
    private void doRender(LayerRenderer renderer, EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scaleIn) {

        EventRenderLayers event = new EventRenderLayers(Event.Type.PRE, entitylivingbaseIn, renderer);
        EventManager.handleEvent(event);

        if (!event.isCancelled()) {
            renderer.doRenderLayer(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scaleIn);
        }
    }
}
