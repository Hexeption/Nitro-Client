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
import net.minecraft.util.EnumHand;
import org.lwjgl.input.Keyboard;
import uk.co.hexeption.client.events.EventLivingUpdate;
import uk.co.hexeption.client.mod.Mod;

@Mod.ModInfo(name = "No Slow", description = "Don't get slowed down with item's", category = Mod.Category.MOVEMENT, bind = Keyboard.KEY_J)
public class NoSlow extends Mod {

    private EnumHand activeHand;

    @EventHandler
    private Listener<EventLivingUpdate> livingUpdateListener = new Listener<>(eventLivingUpdate -> {
        if (mc.player.isHandActive()) {
            switch (eventLivingUpdate.getEventState()) {
                case PRE:
                    activeHand = mc.player.getActiveHand();
                    mc.player.resetActiveHand();
                    break;
                case POST:
                    mc.player.setActiveHand(activeHand);
                    break;
            }
        }
    });

}
