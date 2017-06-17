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
