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

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.util.Session;
import net.minecraft.util.Timer;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.co.hexeption.client.Client;
import uk.co.hexeption.client.events.EventClick;
import uk.co.hexeption.client.events.EventKey;
import uk.co.hexeption.client.events.EventTick;
import uk.co.hexeption.client.events.EventWorld;
import uk.co.hexeption.client.mixin.imp.IMixinMinecraft;

import javax.annotation.Nullable;


@Mixin(Minecraft.class)
public abstract class MixinMinecraft implements IMixinMinecraft {

    @Shadow
    @Mutable
    @Final
    private Session session;

    @Shadow
    @Final
    private Timer timer;

    @Inject(method = "init", at = @At("RETURN"))
    private void init(CallbackInfo callbackInfo) {

        Client.INSTANCE.start();
    }

    @Inject(method = "runTick", at = @At("HEAD"))
    private void onTick(CallbackInfo callbackInfo) {

        Client.INSTANCE.eventBus.post(new EventTick());
    }

    @Inject(method = "runTickKeyboard", at = @At("HEAD"))
    private void onKeyboard(CallbackInfo callbackInfo) {

        int key = Keyboard.getEventKey() == 0 ? Keyboard.getEventCharacter() + 256 : Keyboard.getEventKey();

        if (Keyboard.getEventKeyState()) {
            Client.INSTANCE.eventBus.post(new EventKey(key));
        }
    }

    @Inject(method = "clickMouse", at = @At("HEAD"))
    private void onLeftClick(CallbackInfo callbackInfo) {

        Client.INSTANCE.eventBus.post(new EventClick(EventClick.MouseButtons.LEFT));
    }

    @Inject(method = "runTickKeyboard", at = @At(value = "INVOKE", remap = false, target = "Lorg/lwjgl/input/Keyboard;getEventKey()I", ordinal = 0, shift = At.Shift.BEFORE))
    private void onRightClick(CallbackInfo callbackInfo) {

        Client.INSTANCE.eventBus.post(new EventClick(EventClick.MouseButtons.RIGHT));
    }

    @Inject(method = "middleClickMouse", at = @At("HEAD"))
    private void onMiddleClick(CallbackInfo callbackInfo) {

        Client.INSTANCE.eventBus.post(new EventClick(EventClick.MouseButtons.MIDDLE));
    }

    @Inject(method = "loadWorld(Lnet/minecraft/client/multiplayer/WorldClient;Ljava/lang/String;)V", at = @At("HEAD"))
    private void loadWorld(@Nullable WorldClient worldClientIn, String loadingMessage, CallbackInfo callbackInfo) {

        if (worldClientIn != null) {
            Client.INSTANCE.eventBus.post(new EventWorld.Load(worldClientIn));
        } else {
            Client.INSTANCE.eventBus.post(new EventWorld.Unload());
        }
    }

    @Override
    public Session getSession() {

        return session;
    }

    @Override
    public void setSession(Session session) {

        this.session = session;
    }

    @Override
    public Timer getTimer() {

        return timer;
    }
}
