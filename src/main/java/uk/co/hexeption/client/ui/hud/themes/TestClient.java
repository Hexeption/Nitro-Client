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

package uk.co.hexeption.client.ui.hud.themes;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import uk.co.hexeption.client.Client;
import uk.co.hexeption.client.mod.Mod;
import uk.co.hexeption.client.ui.hud.IGameHud;

import java.awt.*;
import java.util.Comparator;

public class TestClient implements IGameHud {

    private int y;

    @Override
    public void render(Minecraft minecraft, int displayWidth, int displayHeight) {

        ScaledResolution scaledResolution = new ScaledResolution(minecraft);

        FontRenderer font = minecraft.fontRenderer;

        font.drawString("Hud Client", 2, 2, new Color(88, 127, 82, 241).hashCode());

        y = 2;
        Client.INSTANCE.modManager.getMods().stream().filter(Mod::getState).sorted(Comparator.comparingInt(m -> font.getStringWidth(m.getName()))).forEach(mod -> {
            font.drawString(mod.getName(), scaledResolution.getScaledWidth() - 5 - font.getStringWidth(mod.getName()), y, new Color(143, 255, 120, 255).hashCode());
            y += font.FONT_HEIGHT;
        });

    }


    @Override
    public String name() {

        return "TestClient";
    }

    @Override
    public void onKeyPressed(int key) {

    }
}
