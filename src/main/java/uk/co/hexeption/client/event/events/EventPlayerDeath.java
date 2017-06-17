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
