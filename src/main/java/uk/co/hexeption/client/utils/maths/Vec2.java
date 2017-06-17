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
