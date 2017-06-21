/*******************************************************************************
 *     ITweaker-Client
 *     Copyright (C) 2017  Hexeption (Keir Davis)
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
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

    @Shadow
    public boolean onGround;

    @Shadow
    public double motionX;

    @Shadow
    public double motionY;

    @Shadow
    public double motionZ;

    private Vec3 pos, prevPos, lastTickPos;

    private Vec2 rotations, prevRotations;

    @Shadow
    public abstract boolean isSprinting();

    @Shadow
    public abstract boolean isRiding();

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

