package uk.co.hexeption.client.event.events;

import net.minecraft.entity.Entity;
import uk.co.hexeption.client.event.Event;

public class EventRenderEntity extends Event {

    private final Entity entity;

    private final double x, y, z;

    private final float yaw, ticks;

    public EventRenderEntity(Type type, Entity entity, double x, double y, double z, float yaw, float ticks) {

        super(type);
        this.entity = entity;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.ticks = ticks;
    }

    public Entity getEntity() {

        return entity;
    }

    public double getX() {

        return x;
    }

    public double getY() {

        return y;
    }

    public double getZ() {

        return z;
    }

    public float getYaw() {

        return yaw;
    }

    public float getTicks() {

        return ticks;
    }
}
