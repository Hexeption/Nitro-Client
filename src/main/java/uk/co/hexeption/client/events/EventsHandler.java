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

package uk.co.hexeption.client.events;

import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import org.lwjgl.input.Keyboard;
import uk.co.hexeption.client.Client;

public class EventsHandler {

    @EventHandler
    private final Listener<EventKey> keyListener = new Listener<>(event -> Client.INSTANCE.modManager.getMods().forEach(mod -> {

        if (Keyboard.getEventKey() == mod.getBind()) {
            mod.toggle();
        }

    }));


}
