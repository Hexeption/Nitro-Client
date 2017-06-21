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
