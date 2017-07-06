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

package uk.co.hexeption.client.ui.hud.themes;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import uk.co.hexeption.client.Client;
import uk.co.hexeption.client.font.FontLoader;
import uk.co.hexeption.client.font.MinecraftFontRenderer;
import uk.co.hexeption.client.mod.Mod;
import uk.co.hexeption.client.ui.hud.IGameHud;

import java.awt.*;
import java.util.Comparator;

public class NitroHud implements IGameHud {

    private int y;

    @Override
    public void render(Minecraft minecraft, int displayWidth, int displayHeight) {

        ScaledResolution scaledResolution = new ScaledResolution(minecraft);

        MinecraftFontRenderer font = FontLoader.INSTANCE.standard20;


        font.drawString(Client.INSTANCE.clientName + ", " + Client.INSTANCE.clientVersion, 2, 2, new Color(63, 120, 127, 241).hashCode());

        y = scaledResolution.getScaledHeight() - 12;
        Client.INSTANCE.modManager.getMods().stream().filter(Mod::getState).sorted(Comparator.comparingInt(m -> font.getStringWidth(m.getName()))).forEach(mod -> {
            font.drawString(mod.getName(), scaledResolution.getScaledWidth() - 2 - font.getStringWidth(mod.getName()), y, new Color(61, 105, 255, 255).hashCode());
            y -= 11;
        });

    }


    @Override
    public String name() {

        return "Nitro";
    }

    @Override
    public void onKeyPressed(int key) {

    }
}
