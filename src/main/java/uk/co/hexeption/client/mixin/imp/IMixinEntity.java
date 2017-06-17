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
