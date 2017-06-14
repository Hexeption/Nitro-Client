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
