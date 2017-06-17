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

package uk.co.hexeption.client.ui.hud;

import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.Display;
import uk.co.hexeption.client.event.Event;
import uk.co.hexeption.client.event.EventListener;
import uk.co.hexeption.client.event.events.EventHud;
import uk.co.hexeption.client.event.events.EventKeyboard;
import uk.co.hexeption.client.managers.EventManager;
import uk.co.hexeption.client.ui.hud.themes.TestClient;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Hud implements EventListener {

    private final List<IGameHud> themes = new CopyOnWriteArrayList<>();

    private int themeIndex = 0;

    public Hud() {

        EventManager.register(this);
    }

    public void initialization() {

        this.themes.add(new TestClient());
    }

    public IGameHud getCurrentTheme() {

        return this.themes.get(this.themeIndex);
    }

    public IGameHud getTheme(String name) {

        for (IGameHud theme : this.themes) {
            if (theme.name().equals(name)) {
                return theme;
            }
        }
        return null;
    }

    public void onNextTheme() {

        int index = this.themeIndex;
        int maxSize = this.themes.size();

        if (index != -1) {
            index++;

            if (index >= maxSize) {
                index = 0;
            }

            this.themeIndex = index;
        }
    }

    @Override
    public void onEvent(Event event) {

        if (event instanceof EventHud) {
            if (Minecraft.getMinecraft().gameSettings.showDebugInfo)
                return;

            IGameHud currentTheme = getCurrentTheme();
            currentTheme.render(Minecraft.getMinecraft(), Display.getWidth(), Display.getHeight());
        } else if (event instanceof EventKeyboard) {
            getCurrentTheme().onKeyPressed(((EventKeyboard) event).getKey());
        }
    }
}
