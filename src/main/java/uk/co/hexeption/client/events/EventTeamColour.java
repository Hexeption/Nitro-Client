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

public class EventTeamColour extends Cancellable {

    private final Entity entity;

    private int color = 0xffffffff;

    public EventTeamColour(Entity entity) {

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
