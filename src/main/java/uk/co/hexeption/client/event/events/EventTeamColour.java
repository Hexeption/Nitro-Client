package uk.co.hexeption.client.event.events;

import net.minecraft.entity.Entity;
import uk.co.hexeption.client.event.Event;

public class EventTeamColour extends Event {

    private final Entity entity;

    private int color = 0xffffffff;

    public EventTeamColour(Type type, Entity entity) {

        super(type);
        this.entity = entity;
    }

    public Entity getEntity() {

        return entity;
    }

    public int getColor() {

        return color;
    }

    public void setColor(int color) {

        this.color = color;
    }
}
