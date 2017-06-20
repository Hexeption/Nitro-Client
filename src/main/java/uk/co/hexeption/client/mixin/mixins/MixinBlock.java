package uk.co.hexeption.client.mixin.mixins;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import uk.co.hexeption.client.Client;
import uk.co.hexeption.client.events.EventRenderBlockSide;

@Mixin(Block.class)
public class MixinBlock {

    @Inject(method = "shouldSideBeRendered", at = @At("HEAD"), cancellable = true)
    private void shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side, CallbackInfoReturnable<Boolean> callback) {
        EventRenderBlockSide event = new EventRenderBlockSide(blockState, blockAccess, pos, side);
        Client.INSTANCE.eventBus.post(event);

        if (event.isCancelled()) {
            callback.setReturnValue(false);
        } else if (event.isToRender()) {
            callback.setReturnValue(true);
        }
    }
}
