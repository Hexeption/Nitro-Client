package uk.co.hexeption.client.event.events;

import net.minecraft.entity.Entity;
import uk.co.hexeption.client.event.Event;

public class EventRenderEntities extends Event {

    private Entity entity;

    private float ticks;

    public EventRenderEntities(Type type, Entity entity, float ticks) {

        super(type);
        this.entity = entity;
        this.ticks = ticks;
    }

    public Entity getEntity() {

        return entity;
    }

    public void setEntity(Entity entity) {

        this.entity = entity;
    }

    public float getTicks() {

        return ticks;
    }

    public void setTicks(float ticks) {

        this.ticks = ticks;
    }
}
