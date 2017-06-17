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

package uk.co.hexeption.client.mixin.mixins;

import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import uk.co.hexeption.client.mixin.imp.IMixinEntity;
import uk.co.hexeption.client.utils.maths.Vec2;
import uk.co.hexeption.client.utils.maths.Vec3;

@Mixin(Entity.class)
public abstract class MixinEntity implements IMixinEntity {

    @Shadow
    public double posX;

    @Shadow
    public double posY;

    @Shadow
    public double posZ;

    @Shadow
    public double prevPosX;

    @Shadow
    public double prevPosY;

    @Shadow
    public double prevPosZ;

    @Shadow
    public double lastTickPosX;

    @Shadow
    public double lastTickPosY;

    @Shadow
    public double lastTickPosZ;

    @Shadow
    public float prevRotationYaw;

    @Shadow
    public float prevRotationPitch;

    @Shadow
    public float rotationPitch;

    @Shadow
    public float rotationYaw;

    private Vec3 pos, prevPos, lastTickPos;

    private Vec2 rotations, prevRotations;


    @Shadow
    public void move(MoverType type, double x, double y, double z) {

    }



    @Override
    public Vec3 getPos() {

        if (pos == null) {
            pos = new Vec3();
        }

        return pos.setX(posX).setY(posY).setZ(posZ);
    }

    @Override
    public void setPos(Vec3 pos) {

        this.posX = pos.getX();
        this.posY = pos.getY();
        this.posZ = pos.getZ();
    }

    @Override
    public Vec3 getPrevPos() {

        if (prevPos == null) {
            prevPos = new Vec3();
        }

        return prevPos.setX(prevPosX).setY(prevPosY).setZ(prevPosZ);
    }

    @Override
    public void setPrevPos(Vec3 pos) {

        this.prevPosX = pos.getX();
        this.prevPosY = pos.getY();
        this.prevPosZ = pos.getZ();
    }

    @Override
    public Vec3 getLastTickPos() {

        if (lastTickPos == null) {
            lastTickPos = new Vec3();
        }

        return lastTickPos.setX(lastTickPosX).setY(lastTickPosY).setZ(lastTickPosZ);
    }

    @Override
    public void setLastTickPos(Vec3 pos) {

        this.lastTickPosX = pos.getX();
        this.lastTickPosY = pos.getY();
        this.lastTickPosZ = pos.getZ();
    }

    @Override
    public Vec2 getRotations() {

        if (rotations == null) {
            rotations = new Vec2();
        }
        return rotations.setX(rotationYaw).setY(rotationPitch);
    }

    @Override
    public void setRotations(Vec2 rotations) {

        this.rotationYaw = rotations.getX();
        this.rotationPitch = rotations.getY();
    }

    @Override
    public Vec2 getPrevRotations() {

        if (prevRotations == null) {
            prevRotations = new Vec2();
        }
        return prevRotations.setX(prevRotationYaw).setY(prevRotationPitch);
    }

    @Override
    public void setPrevRotations(Vec2 rotations) {

        this.prevRotationYaw = rotations.getX();
        this.prevRotationPitch = rotations.getY();
    }

    @Override
    public Vec3 interpolate(float ticks) {

        return this.getLastTickPos().add(this.getPos().sub(this.getLastTickPos()).scale(ticks));
    }
}

