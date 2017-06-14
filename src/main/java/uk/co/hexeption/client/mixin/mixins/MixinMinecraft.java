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
import uk.co.hexeption.client.event.Event;
import uk.co.hexeption.client.event.events.EventClick;
import uk.co.hexeption.client.event.events.EventTick;
import uk.co.hexeption.client.event.events.EventWorld;
import uk.co.hexeption.client.managers.EventManager;
import uk.co.hexeption.client.mixin.imp.IMixinMinecraft;
import uk.co.hexeption.client.utils.InputHandler;

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

        EventTick eventTick = new EventTick(Event.Type.PRE);
        EventManager.handleEvent(eventTick);
    }

    @Inject(method = "runTickKeyboard", at = @At("HEAD"))
    private void onKeyboard(CallbackInfo callbackInfo) {

        if (Keyboard.getEventKeyState()) {
            InputHandler.handleKeyboard();
        }

    }

    @Inject(method = "clickMouse", at = @At("HEAD"))
    private void onLeftClick(CallbackInfo callbackInfo) {

        EventClick eventClick = new EventClick(Event.Type.PRE, EventClick.MouseButtons.LEFT);
        EventManager.handleEvent(eventClick);

    }

    @Inject(method = "rightClickMouse", at = @At("HEAD"))
    private void onRightClick(CallbackInfo callbackInfo) {

        EventClick eventClick = new EventClick(Event.Type.PRE, EventClick.MouseButtons.RIGHT);
        EventManager.handleEvent(eventClick);

    }

    @Inject(method = "middleClickMouse", at = @At("HEAD"))
    private void onMiddleClick(CallbackInfo callbackInfo) {

        EventClick eventClick = new EventClick(Event.Type.PRE, EventClick.MouseButtons.MIDDLE);
        EventManager.handleEvent(eventClick);

    }

    @Inject(method = "loadWorld(Lnet/minecraft/client/multiplayer/WorldClient;Ljava/lang/String;)V", at = @At("HEAD"))
    private void loadWorld(@Nullable WorldClient worldClientIn, String loadingMessage, CallbackInfo callbackInfo) {

        Event event;
        if (worldClientIn != null) {
            event = new EventWorld.Load(Event.Type.PRE, worldClientIn);
        } else {
            event = new EventWorld.Unload(Event.Type.PRE, null);
        }

        EventManager.handleEvent(event);
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
