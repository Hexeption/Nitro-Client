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

package uk.co.hexeption.client.mod.mods;

import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumHand;
import org.lwjgl.input.Keyboard;
import uk.co.hexeption.client.events.EventMotionUpdate;
import uk.co.hexeption.client.mixin.imp.IMixinEntity;
import uk.co.hexeption.client.mod.Mod;
import uk.co.hexeption.client.utils.maths.Vec2;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Mod.ModInfo(name = "Kill Aura", description = "Kill things", category = Mod.Category.COMBAT, bind = Keyboard.KEY_R)
public class KillAura extends Mod {

    /**
     * TODO: Toggle Locked View
     */

    private Entity targetEntity;

    @EventHandler
    private Listener<EventMotionUpdate> motionUpdateListener = new Listener<>(event -> {
        switch (event.getState()) {
            case PRE:
                List<Entity> entities = mc.world.loadedEntityList.stream().filter(entity -> entity instanceof EntityLivingBase && !entity.isDead && !entity.isInvisible() && entity != mc.player && entity.getDistanceToEntity(mc.player) < 4).sorted(Comparator.comparingDouble(entity -> mc.player.getDistanceToEntity(entity))).collect(Collectors.toList());

                if (entities.size() > 0) {
                    targetEntity = entities.get(0);
                    IMixinEntity player = (IMixinEntity) mc.player;
                    IMixinEntity target = (IMixinEntity) targetEntity;
                    Vec2 rotations = player.getPos().rotateTo(target.getPos());
                    event.setYaw(rotations.getX()).setPitch(rotations.getY());
                    player.setRotations(rotations);
                } else {
                    targetEntity = null;
                }
                break;
            case POST:
                if (targetEntity != null && mc.player.getCooledAttackStrength(0f) == 1f) {
                    mc.playerController.attackEntity(mc.player, targetEntity);
                    mc.player.swingArm(EnumHand.MAIN_HAND);
                }
                break;
        }
    });


}
