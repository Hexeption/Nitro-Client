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

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import uk.co.hexeption.client.event.Event;

public class EventPlayerDeath extends Event {

    private final EntityLivingBase entity;

    private final DamageSource source;

    public EventPlayerDeath(Type type, EntityLivingBase entity, DamageSource source) {

        super(type);
        this.entity = entity;
        this.source = source;
    }

    public EntityLivingBase getEntity() {

        return entity;
    }

    public DamageSource getSource() {

        return source;
    }
}
