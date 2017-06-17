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

package uk.co.hexeption.client.managers;

import com.google.common.collect.Lists;
import uk.co.hexeption.client.event.Event;
import uk.co.hexeption.client.event.EventListener;

import java.util.Comparator;
import java.util.List;

public class EventManager {

    private static final List<EventListener> LISTENERS = Lists.newCopyOnWriteArrayList();

    public static void register(EventListener listener) {

        LISTENERS.add(listener);
        LISTENERS.sort(Comparator.comparing(EventListener::getPriority));
    }

    public static void handleEvent(Event event) {

        for (EventListener listener : LISTENERS) {
            listener.onEvent(event);
        }
    }
}
