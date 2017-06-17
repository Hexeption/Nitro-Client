package uk.co.hexeption.client.utils.maths;

public class Vec3 {

    private double x, y, z;

    public Vec3() {

        this(0, 0, 0);
    }

    public Vec3(Vec3 vec) {

        this(vec.x, vec.y, vec.z);
    }

    public Vec3(double x, double y, double z) {

        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {

        return x;
    }

    public final Vec3 setX(double x) {

        this.x = x;
        return this;
    }

    public double getY() {

        return y;
    }

    public final Vec3 setY(double y) {

        this.y = y;
        return this;
    }

    public double getZ() {

        return z;
    }

    public final Vec3 setZ(double z) {

        this.z = z;
        return this;
    }

    public final Vec3 add(Vec3 vec) {

        return this.add(vec.x, vec.y, vec.z);
    }

    public final Vec3 add(double x, double y, double z) {

        return new Vec3(this.x + x, this.y + y, this.z + z);
    }

    public final Vec3 sub(Vec3 vec) {

        return this.sub(vec.x, vec.y, vec.z);
    }

    public final Vec3 sub(double x, double y, double z) {

        return new Vec3(this.x - x, this.y - y, this.z - z);
    }

    public final Vec3 scale(float scale) {

        return new Vec3(this.x * scale, this.y * scale, this.z * scale);
    }

    public final Vec3 clone() {

        return new Vec3(this.x, this.y, this.z);
    }


    public final double distance(Vec3 vec) {

        return Math.sqrt(((this.x - vec.x) * (this.x - vec.x)) + ((this.y - vec.y) * (this.y - vec.y)) + ((this.z - vec.z) * (this.z - vec.z)));
    }

}
