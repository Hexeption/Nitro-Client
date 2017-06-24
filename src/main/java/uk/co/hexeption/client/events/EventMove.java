/*******************************************************************************
 *     Nitro Client
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

import me.zero.alpine.type.Cancellable;
import net.minecraft.entity.MoverType;

public class EventMove extends Cancellable {

    private final MoverType moverType;

    private double motionX, motionY, motionZ;

    public EventMove(MoverType moverType, double motionX, double motionY, double motionZ) {

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
