package uk.co.hexeption.client.mixin.mixins;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.co.hexeption.client.event.Event;
import uk.co.hexeption.client.event.events.EventPlayerDeath;
import uk.co.hexeption.client.managers.EventManager;

@Mixin(EntityLivingBase.class)
public class MixinEntityLivingBase {

    @Inject(method = "onDeath", at = @At("HEAD"))
    private void onDeath(DamageSource source, CallbackInfo callbackInfo) {

        Event event = new EventPlayerDeath(Event.Type.PRE, (EntityLivingBase) (Object) this, source);
        EventManager.handleEvent(event);
    }
}
