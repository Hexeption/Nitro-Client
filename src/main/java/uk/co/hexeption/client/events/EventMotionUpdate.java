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

package uk.co.hexeption.client.events;

import me.zero.alpine.type.EventState;
import uk.co.hexeption.client.IMC;
import uk.co.hexeption.client.mixin.imp.IMixinEntity;
import uk.co.hexeption.client.utils.maths.Vec2;
import uk.co.hexeption.client.utils.maths.Vec3;

public class EventMotionUpdate implements IMC {

    private static Vec3 pos = new Vec3();

    private static Vec2 roations = new Vec2();

    private static boolean onGround;

    private final EventState state;

    public EventMotionUpdate(EventState state) {

        this.state = state;
        IMixinEntity player = (IMixinEntity) mc.player;
        pos.copyFrom(player.getPos()).setY(mc.player.getEntityBoundingBox().minY);
        roations.copyFrom(player.getRotations());
        onGround = mc.player.onGround;
    }

    public final double getY() {

        return pos.getY();
    }

    public EventMotionUpdate setY(double y) {

        pos.setY(y);
        return this;
    }

    public final double getZ() {

        return pos.getZ();
    }

    public EventMotionUpdate setZ(double z) {

        pos.setZ(z);
        return this;
    }

    public final float getYaw() {

        return roations.getX();
    }

    public EventMotionUpdate setYaw(float yaw) {

        roations.setX(yaw);
        return this;
    }

    public final float getPitch() {

        return roations.getY();
    }

    public EventMotionUpdate setPitch(float pitch) {

        roations.setY(pitch);
        return this;
    }

    public final boolean isOnGround() {

        return onGround;
    }

    public EventMotionUpdate setOnGround(boolean onGround) {

        EventMotionUpdate.onGround = onGround;
        return this;
    }

    public final Vec3 getPos() {

        return pos;
    }

    public final Vec2 getRoations() {

        return roations;
    }

    public final double getX() {

        return pos.getX();
    }

    public EventMotionUpdate setX(double x) {

        pos.setX(x);
        return this;
    }

    public EventState getState() {

        return state;
    }
}
