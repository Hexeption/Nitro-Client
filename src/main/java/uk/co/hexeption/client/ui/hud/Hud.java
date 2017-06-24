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

package uk.co.hexeption.client.ui.hud;

import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import org.lwjgl.opengl.Display;
import uk.co.hexeption.client.Client;
import uk.co.hexeption.client.IMC;
import uk.co.hexeption.client.events.EventHud;
import uk.co.hexeption.client.events.EventKey;
import uk.co.hexeption.client.ui.hud.themes.NitroHud;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Hud implements IMC {

    private final List<IGameHud> themes = new CopyOnWriteArrayList<>();

    private int themeIndex = 0;

    @EventHandler
    private Listener<EventHud> renderHud = new Listener<EventHud>(event -> {
        if (mc.gameSettings.showDebugInfo)
            return;

        IGameHud currentTheme = getCurrentTheme();
        currentTheme.render(mc, Display.getWidth(), Display.getHeight());
    });

    @EventHandler
    private Listener<EventKey> keyboardEventListener = new Listener<EventKey>(event -> getCurrentTheme().onKeyPressed(event.getKey()));


    public void initialization() {

        Client.INSTANCE.eventBus.subscribe(this);
        this.themes.add(new NitroHud());
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

}
