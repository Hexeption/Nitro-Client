package uk.co.hexeption.client.event.events;

import uk.co.hexeption.client.event.Event;

public class EventKeyboard extends Event {

    private final int key;

    public EventKeyboard(Type type, int key) {

        super(type);
        this.key = key;
    }

    public int getKey() {

        return key;
    }
}
