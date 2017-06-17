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

import net.minecraft.entity.MoverType;
import uk.co.hexeption.client.event.Event;

public class EventMove extends Event {

    private final MoverType moverType;

    private double motionX, motionY, motionZ;

    public EventMove(Type type, MoverType moverType, double motionX, double motionY, double motionZ) {

        super(type);
        this.moverType = moverType;
        this.motionX = motionX;
        this.motionY = motionY;
        this.motionZ = motionZ;
    }

    public MoverType getMoverType() {

        return moverType;
    }

    public double getMotionX() {

        return motionX;
    }

    public void setMotionX(double motionX) {

        this.motionX = motionX;
    }

    public double getMotionY() {

        return motionY;
    }

    public void setMotionY(double motionY) {

        this.motionY = motionY;
    }

    public double getMotionZ() {

        return motionZ;
    }

    public void setMotionZ(double motionZ) {

        this.motionZ = motionZ;
    }
}
