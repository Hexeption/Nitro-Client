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
