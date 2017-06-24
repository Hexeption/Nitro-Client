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

package uk.co.hexeption.client.mixin.mixins;

import me.zero.alpine.type.EventState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.MoverType;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.DamageSource;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.co.hexeption.client.Client;
import uk.co.hexeption.client.events.*;

@Mixin(EntityPlayerSP.class)
public abstract class MixinEntityPlayerSP extends MixinEntity {

    @Shadow
    @Final
    public NetHandlerPlayClient connection;

    @Shadow
    protected Minecraft mc;

    @Shadow
    private boolean serverSprintState;

    @Shadow
    private boolean serverSneakState;

    @Shadow
    private double lastReportedPosX;

    @Shadow
    private double lastReportedPosY;

    @Shadow
    private double lastReportedPosZ;

    @Shadow
    private float lastReportedYaw;

    @Shadow
    private float lastReportedPitch;

    @Shadow
    private int positionUpdateTicks;


    @Shadow
    private boolean autoJumpEnabled;

    @Shadow
    private boolean prevOnGround;

    @Shadow
    public abstract boolean isSneaking();

    @Shadow
    protected abstract boolean isCurrentViewEntity();

    @Inject(method = "onUpdate", at = @At("HEAD"))
    private void onPlayerUpdate(CallbackInfo callbackInfo) {

        Client.INSTANCE.eventBus.post(new EventUpdate());
    }

    @Inject(method = "attackEntityFrom", at = @At("HEAD"), cancellable = true)
    private void onPlayerDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> callbackInfoReturnable) {

        EventPlayerDamage event = new EventPlayerDamage(source, amount);
        Client.INSTANCE.eventBus.post(event);

        if (event.isCancelled()) {
            callbackInfoReturnable.cancel();
        }
    }

    @Inject(method = "sendChatMessage", at = @At("HEAD"), cancellable = true)
    private void onSendChatMessage(String message, CallbackInfo callbackInfo) {

        EventChat.Send event = new EventChat.Send(message, (EntityPlayerSP) (Object) this);
        Client.INSTANCE.eventBus.post(event);

        if (event.isCancelled()) {
            callbackInfo.cancel();
        }
    }

    @Inject(method = "onLivingUpdate", at = @At("HEAD"))
    private void onLivingUpdatePre(CallbackInfo callbackInfo) {

        Client.INSTANCE.eventBus.post(new EventLivingUpdate(EventState.PRE));
    }

    @Inject(method = "onLivingUpdate", at = @At("RETURN"))
    private void onLivingUpdatePost(CallbackInfo callbackInfo) {

        Client.INSTANCE.eventBus.post(new EventLivingUpdate(EventState.POST));
    }

    @Redirect(method = "move", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/AbstractClientPlayer;move(Lnet/minecraft/entity/MoverType;DDD)V"))
    public void move(AbstractClientPlayer player, MoverType type, double x, double y, double z) {

        EventMove event = new EventMove(type, x, y, z);
        Client.INSTANCE.eventBus.post(event);

        if (event.isCancelled()) {
            return;
        }

        super.move(type, event.getMotionX(), event.getMotionY(), event.getMotionZ());
    }

    /**
     * @author Hexeption
     */
    @Overwrite
    private void onUpdateWalkingPlayer() {

        EntityPlayerSP _this = (EntityPlayerSP) (Object) this;

        EventMotionUpdate pre = new EventMotionUpdate(EventState.PRE);
        Client.INSTANCE.eventBus.post(pre);

        boolean sprinting = this.isSprinting();
        if (sprinting != this.serverSprintState) {
            this.connection.sendPacket(new CPacketEntityAction(_this, sprinting ? CPacketEntityAction.Action.START_SPRINTING : CPacketEntityAction.Action.STOP_SPRINTING));
            this.serverSprintState = sprinting;
        }

        boolean sneaking = this.isSneaking();
        if (sneaking != this.serverSneakState) {
            this.connection.sendPacket(new CPacketEntityAction(_this, sneaking ? CPacketEntityAction.Action.START_SNEAKING : CPacketEntityAction.Action.STOP_SNEAKING));
            this.serverSneakState = sneaking;
        }

        if (this.isCurrentViewEntity()) {

            double prevX = pre.getX();
            double prevY = pre.getY();
            double prevZ = pre.getZ();

            float prevYaw = pre.getYaw();
            float prevPitch = pre.getPitch();

            boolean prevGround = pre.isOnGround();

            double d0 = prevX - this.lastReportedPosX;
            double d1 = prevY - this.lastReportedPosY;
            double d2 = prevZ - this.lastReportedPosZ;
            double d3 = prevYaw - this.lastReportedYaw;
            double d4 = prevPitch - this.lastReportedPitch;

            boolean positions = d0 * d0 + d1 * d1 + d2 * d2 > 9.0E-4D || ++this.positionUpdateTicks >= 20;
            boolean rotation = d3 != 0.0D || d4 != 0.0D;

            if (this.isRiding()) {
                this.connection.sendPacket(new CPacketPlayer.PositionRotation(this.motionX, -999.0D, this.motionZ, this.rotationYaw, this.rotationPitch, this.onGround));
                positions = false;
            } else if (positions && rotation) {
                this.connection.sendPacket(new CPacketPlayer.PositionRotation(prevX, prevY, prevZ, prevYaw, prevPitch, prevGround));
            } else if (positions) {
                this.connection.sendPacket(new CPacketPlayer.Position(prevX, prevY, prevZ, prevGround));
            } else if (rotation) {
                this.connection.sendPacket(new CPacketPlayer.Rotation(prevYaw, prevPitch, prevGround));
            } else if (this.prevOnGround != prevGround) {
                this.connection.sendPacket(new CPacketPlayer(prevGround));
            }

            if (positions) {
                this.lastReportedPosX = prevX;
                this.lastReportedPosY = prevY;
                this.lastReportedPosZ = prevZ;
                this.positionUpdateTicks = 0;
            }

            if (rotation) {
                this.lastReportedYaw = prevYaw;
                this.lastReportedPitch = prevPitch;
            }

            this.prevOnGround = prevGround;
            this.autoJumpEnabled = this.mc.gameSettings.autoJump;
        }

        Client.INSTANCE.eventBus.post(new EventMotionUpdate(EventState.POST));
    }
}
