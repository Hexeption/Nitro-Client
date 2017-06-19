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
import net.minecraft.util.DamageSource;

public class EventPlayerDamage extends Cancellable {

    private DamageSource damageSource;

    private float amount;

    public EventPlayerDamage(DamageSource damageSource, float amount) {

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


