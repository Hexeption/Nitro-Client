package uk.co.hexeption.client.event.events;

import uk.co.hexeption.client.event.Event;

public class EventKeybind extends Event {

    private final boolean state;

    private final int key;

    private final char character;

    public EventKeybind(Type type, boolean state, int key, char character) {

        super(type);
        this.state = state;
        this.key = key;
        this.character = character;
    }

    public boolean getState() {

        return state;
    }

    public int getKey() {

        return key;
    }

    public char getCharacter() {

        return character;
    }
}