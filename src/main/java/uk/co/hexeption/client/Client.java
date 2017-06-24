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

package uk.co.hexeption.client;


import me.zero.alpine.EventBus;
import uk.co.hexeption.client.events.EventsHandler;
import uk.co.hexeption.client.managers.ClientEventManager;
import uk.co.hexeption.client.managers.ModManager;
import uk.co.hexeption.client.ui.hud.Hud;
import uk.co.hexeption.client.utils.Auth;
import uk.co.hexeption.client.utils.LogHelper;

public enum Client implements IMC {

    INSTANCE;

    public final EventBus eventBus = new ClientEventManager();

    public final ModManager modManager = new ModManager();

    public final Hud hud = new Hud();

    public final EventsHandler eventsHandler = new EventsHandler();

    public final String clientName = "Nitro";

    public final String clientVersion = "B1-20e9b79";

    public void start() {

        LogHelper.info(String.format("Loading %s, Build %s", clientName, clientVersion));

        LogHelper.info("Loading Mods...");
        modManager.init();

        LogHelper.info("Loading Hud...");
        hud.initialization();

        eventBus.subscribe(eventsHandler);

        Runtime.getRuntime().addShutdownHook(new Thread(this::end));
    }

    public void end() {

    }

}
