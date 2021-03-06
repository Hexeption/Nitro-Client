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

package uk.co.hexeption.client.mod.mods;

import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import me.zero.alpine.type.EventState;
import org.lwjgl.input.Keyboard;
import uk.co.hexeption.client.events.EventMotionUpdate;
import uk.co.hexeption.client.mod.Mod;

@Mod.ModInfo(name = "Auto Sprint", description = "Automatically sprints for you.", category = Mod.Category.MOVEMENT, bind = Keyboard.KEY_L)
public class AutoSprint extends Mod {

    @EventHandler
    private Listener<EventMotionUpdate> eventMotionUpdateListener = new Listener<>(eventMotionUpdate -> {
        if (eventMotionUpdate.getState() == EventState.PRE && (mc.player.field_191988_bg > 0) && !(mc.player.isCollidedHorizontally)) {
            mc.player.setSprinting(true);
        }
    });

    @Override
    public void onDisable() {

        mc.player.setSprinting(false);
    }
}
