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

package uk.co.hexeption.client;

import uk.co.hexeption.client.managers.ModManager;
import uk.co.hexeption.client.utils.LogHelper;

public enum Client {

    INSTANCE;

    public final ModManager modManager = new ModManager();


    public void start() {

        LogHelper.info("Loading Mods...");
        modManager.init();

        Runtime.getRuntime().addShutdownHook(new Thread(this::end));
    }

    public void end() {

    }

}
