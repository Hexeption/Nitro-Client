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

package uk.co.hexeption.client.mod.mods;

import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import org.lwjgl.input.Keyboard;
import uk.co.hexeption.client.events.EventUpdate;
import uk.co.hexeption.client.mod.Mod;

@Mod.ModInfo(name = "Fly", description = "de", category = Mod.Category.TEST, bind = Keyboard.KEY_F)
public class Fly extends Mod {

    @EventHandler
    private Listener<EventUpdate> eventUpdateListener = new Listener<>(eventUpdate -> {
        mc.player.capabilities.isFlying = true;
        mc.player.motionY = Boolean.compare(mc.gameSettings.keyBindJump.isKeyDown(), mc.gameSettings.keyBindSneak.isKeyDown()) * 0.3;
        mc.player.capabilities.setFlySpeed(0.09f);
    });

    @Override
    public void onDisable() {

        mc.player.capabilities.isFlying = false;
        mc.player.capabilities.setFlySpeed(0.05f);
    }

    @Override
    public void onEnable() {
        //Maybe?
    }
}
