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

package uk.co.hexeption.client.mixin.imp;

import uk.co.hexeption.client.utils.maths.Vec2;
import uk.co.hexeption.client.utils.maths.Vec3;

public interface IMixinEntity {

    Vec3 getPos();

    void setPos(Vec3 pos);

    Vec3 getPrevPos();

    void setPrevPos(Vec3 pos);

    Vec3 getLastTickPos();

    void setLastTickPos(Vec3 pos);

    Vec2 getRotations();

    void setRotations(Vec2 rotations);

    Vec2 getPrevRotations();

    void setPrevRotations(Vec2 rotations);

    Vec3 interpolate(float ticks);
}
