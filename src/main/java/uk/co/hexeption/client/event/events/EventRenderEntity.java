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
