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

package uk.co.hexeption.client.events;

import me.zero.alpine.type.Cancellable;
import net.minecraft.entity.Entity;

public class EventRenderEntities extends Cancellable {

    private Entity entity;

    private float ticks;

    public EventRenderEntities(Entity entity, float ticks) {

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
