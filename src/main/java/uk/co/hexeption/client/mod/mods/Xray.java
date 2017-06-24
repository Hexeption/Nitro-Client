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
import me.zero.alpine.type.Cancellable;
import net.minecraft.init.Blocks;
import org.lwjgl.input.Keyboard;
import uk.co.hexeption.client.events.EventOpaquCube;
import uk.co.hexeption.client.events.EventRenderBlockSide;
import uk.co.hexeption.client.mod.Mod;

import java.util.ArrayList;

@Mod.ModInfo(name = "Xray", description = "Only show certain blocks", category = Mod.Category.RENDER, bind = Keyboard.KEY_X)
public class Xray extends Mod {

    private transient ArrayList blocks = new ArrayList();

    private transient int ambientOcclusion;

    private transient float gammaSetting;

    /**
     * Events
     */

    @EventHandler
    private Listener<EventOpaquCube> eventOpaquCubeListener = new Listener<>(Cancellable::cancel);

    @EventHandler
    private Listener<EventRenderBlockSide> eventRenderBlockSideListener = new Listener<>(event -> {
        if (blocks.contains(event.getState().getBlock())) {
            event.setToRender(true);
        } else {
            event.cancel();
        }
    });

    public Xray() {

        //TODO: Add Default blocks/ores
        blocks.add(Blocks.IRON_ORE);
    }

    @Override
    public void onEnable() {
        super.onEnable();
        ambientOcclusion = mc.gameSettings.ambientOcclusion;
        gammaSetting = mc.gameSettings.gammaSetting;
        mc.gameSettings.gammaSetting = 100f;
        mc.gameSettings.ambientOcclusion = 0;
        mc.renderGlobal.loadRenderers();

    }

    @Override
    public void onDisable() {
        mc.gameSettings.ambientOcclusion = ambientOcclusion;
        mc.gameSettings.gammaSetting = gammaSetting;
        mc.renderGlobal.loadRenderers();
        super.onDisable();
    }
}
