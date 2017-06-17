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


    public final double distance(Vec2 vec) {

        return Math.sqrt(((this.x - vec.x) * (this.x - vec.x)) + ((this.y - vec.y) * (this.y - vec.y)));
    }

}
