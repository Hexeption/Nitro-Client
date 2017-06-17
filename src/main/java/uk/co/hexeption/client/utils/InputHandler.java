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

package uk.co.hexeption.client.utils;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import uk.co.hexeption.client.Client;
import uk.co.hexeption.client.event.Event;
import uk.co.hexeption.client.event.events.EventKeybind;
import uk.co.hexeption.client.event.events.EventKeyboard;
import uk.co.hexeption.client.managers.EventManager;
import uk.co.hexeption.client.mod.Mod;

/**
 * Created by Keir on 21/04/2017.
 */
public class InputHandler {

    public static void handleBind(boolean state, int key, char character) {

        EventKeybind event = new EventKeybind(Event.Type.PRE, state, key, character);
        EventManager.handleEvent(event);

        for (Mod mod : Client.INSTANCE.modManager.getMods()) {
            if (Keyboard.getEventKey() == mod.getBind()) {
                mod.toggle();
            }
        }

        event.setType(Event.Type.POST);
        EventManager.handleEvent(event);
    }

    public static void handleKeyboard() {

        boolean state = Keyboard.getEventKeyState();
        int key = Keyboard.getEventKey();
        char character = Keyboard.getEventCharacter();
        if (key != Keyboard.KEY_NONE) {
            handleBind(state, key, character);
        }
        EventKeyboard event = new EventKeyboard(Event.Type.PRE, key);
        EventManager.handleEvent(event);

    }

    public static void handleMouse() {

        int button = Mouse.getEventButton();
        boolean state = Mouse.getEventButtonState();

        if (button >= 0) {
            handleBind(state, button - 100, '\0');
        }
    }
}