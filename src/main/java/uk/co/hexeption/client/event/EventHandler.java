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

package uk.co.hexeption.client.event;

import org.lwjgl.input.Keyboard;
import uk.co.hexeption.client.Client;
import uk.co.hexeption.client.event.events.EventKeyboard;
import uk.co.hexeption.client.managers.EventManager;

public class EventHandler implements EventListener {

    public EventHandler() {

        EventManager.register(this);
    }

    @Override
    public void onEvent(Event event) {

        if (event instanceof EventKeyboard) {
            Client.INSTANCE.modManager.getMods().forEach(mod -> {
                if (Keyboard.getEventKey() == mod.getBind()) {
                    if (Keyboard.getEventKeyState()) {
                        mod.toggle();
                    }
                }
            });
        }

        //Other Events:
    }
}
