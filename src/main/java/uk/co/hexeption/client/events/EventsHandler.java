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

package uk.co.hexeption.client.events;

import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import org.lwjgl.input.Keyboard;
import uk.co.hexeption.client.Client;
import uk.co.hexeption.client.IMC;

import static org.lwjgl.input.Keyboard.KEYBOARD_SIZE;

public class EventsHandler implements IMC {

    private final boolean[] keyMap = new boolean[KEYBOARD_SIZE];


    @EventHandler
    private final Listener<EventKey> keyListener = new Listener<>(event -> Client.INSTANCE.modManager.getMods().forEach(mod -> {

        if (Keyboard.getEventKey() == mod.getBind()) {
            mod.toggle();
        }

    }));



}
