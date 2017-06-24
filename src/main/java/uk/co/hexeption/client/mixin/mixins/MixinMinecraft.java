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

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
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
import uk.co.hexeption.client.screen.MainMenu;

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

    @Shadow
    @Nullable
    public GuiScreen currentScreen;

    @Shadow
    public abstract void displayGuiScreen(@Nullable GuiScreen guiScreenIn);

    @Inject(method = "init", at = @At("RETURN"))
    private void init(CallbackInfo callbackInfo) {

        Client.INSTANCE.start();
    }

    @Inject(method = "runTick", at = @At("HEAD"))
    private void onTick(CallbackInfo callbackInfo) {

        Client.INSTANCE.eventBus.post(new EventTick());

        if (this.currentScreen instanceof GuiMainMenu) {
            displayGuiScreen(new MainMenu());
        }

    }

    @Inject(method = "runTickKeyboard", at = @At(value = "INVOKE", remap = false, target = "Lorg/lwjgl/input/Keyboard;getEventKey()I", ordinal = 0, shift = At.Shift.BEFORE))
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

    @Inject(method = "rightClickMouse", at = @At("HEAD"))
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
