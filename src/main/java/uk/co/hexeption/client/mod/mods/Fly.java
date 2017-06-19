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

package uk.co.hexeption.client.mod.mods;

import org.lwjgl.input.Keyboard;
import uk.co.hexeption.client.mod.Mod;

@Mod.ModInfo(name = "Fly", description = "de", category = Mod.Category.TEST, bind = Keyboard.KEY_F)
public class Fly extends Mod {

    @Override
    public void onDisable() {

        mc.player.capabilities.isFlying = false;
    }

    @Override
    public void onEnable() {

        mc.player.capabilities.isFlying = true;
    }


}
