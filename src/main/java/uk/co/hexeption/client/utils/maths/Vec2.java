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

package uk.co.hexeption.client.utils.maths;

public class Vec2 {

    private float x, y;

    public Vec2() {

        this(0, 0);
    }

    public Vec2(double x, double y) {

        this((float) x, (float) y);
    }

    public Vec2(float x, float y) {

        this.x = x;
        this.y = y;
    }

    public float getX() {

        return x;
    }

    public final Vec2 setX(float x) {

        this.x = x;
        return this;
    }

    public float getY() {

        return y;
    }

    public final Vec2 setY(float y) {

        this.y = y;
        return this;
    }

    public final Vec2 add(Vec2 vec) {

        return new Vec2(this.x + x, this.y + y);
    }

    public final Vec2 add(double x, double y) {

        return add(new Vec2(x, y));
    }

    public final Vec2 add(float x, float y) {

        return add(new Vec2(x, y));
    }

    public final Vec2 sub(Vec2 vec) {

        return new Vec2(this.x - x, this.y - y);
    }

    public final Vec2 sub(double x, double y) {

        return sub(new Vec2(x, y));
    }

    public final Vec2 sub(float x, float y) {

        return sub(new Vec2(x, y));
    }

    public final Vec2 scale(float scale) {

        return new Vec2(this.x * scale, this.y * scale);
    }

    public final Vec2 clone() {

        return new Vec2(this.x, this.y);
    }

    public final Vec2 copyFrom(Vec2 vec) {

        this.x = vec.x;
        this.y = vec.y;
        return this;
    }

    public final double distance(Vec2 vec) {

        return Math.sqrt(((this.x - vec.x) * (this.x - vec.x)) + ((this.y - vec.y) * (this.y - vec.y)));
    }

}
