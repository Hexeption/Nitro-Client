package uk.co.hexeption.client.event.events;

import uk.co.hexeption.client.event.Event;

public class EventHudEvent extends Event {

    private final float partialTicks;

    public EventHudEvent(Type type, float partialTicks) {

        super(type);
        this.partialTicks = partialTicks;
    }

    public float getPartialTicks() {

        return partialTicks;
    }
}
