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

public class EventRenderLable extends Event {

    private Entity entity;

    private String lable;

    private double x, y, z;

    private int maxDistance;

    public EventRenderLable(Type type, Entity entity, String lable, double x, double y, double z, int maxDistance) {

        super(type);
        this.entity = entity;
        this.lable = lable;
        this.x = x;
        this.y = y;
        this.z = z;
        this.maxDistance = maxDistance;
    }

    public Entity getEntity() {

        return entity;
    }

    public void setEntity(Entity entity) {

        this.entity = entity;
    }

    public String getLable() {

        return lable;
    }

    public void setLable(String lable) {

        this.lable = lable;
    }

    public double getX() {

        return x;
    }

    public void setX(double x) {

        this.x = x;
    }

    public double getY() {

        return y;
    }

    public void setY(double y) {

        this.y = y;
    }

    public double getZ() {

        return z;
    }

    public void setZ(double z) {

        this.z = z;
    }

    public int getMaxDistance() {

        return maxDistance;
    }

    public void setMaxDistance(int maxDistance) {

        this.maxDistance = maxDistance;
    }
}
