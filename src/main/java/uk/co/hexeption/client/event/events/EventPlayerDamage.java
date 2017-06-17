package uk.co.hexeption.client.event.events;

import net.minecraft.util.DamageSource;
import uk.co.hexeption.client.event.Event;

public class EventPlayerDamage extends Event {

    private DamageSource damageSource;

    private float amount;

    public EventPlayerDamage(Type type, DamageSource damageSource, float amount) {

        super(type);
        this.damageSource = damageSource;
        this.amount = amount;
    }

    public DamageSource getDamageSource() {

        return damageSource;
    }

    public float getAmount() {

        return amount;
    }
}


