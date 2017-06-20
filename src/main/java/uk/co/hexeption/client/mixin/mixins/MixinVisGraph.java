package uk.co.hexeption.client.mixin.mixins;

import net.minecraft.client.renderer.chunk.VisGraph;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.co.hexeption.client.Client;
import uk.co.hexeption.client.events.EventOpaquCube;

@Mixin(VisGraph.class)
public class MixinVisGraph {

    @Inject(method = "setOpaqueCube", at = @At("HEAD"), cancellable = true)
    private void setOpaqueCube(BlockPos pos, CallbackInfo callback) {
        EventOpaquCube event = new EventOpaquCube(pos);
        Client.INSTANCE.eventBus.post(event);
        if (event.isCancelled()) {
            callback.cancel();
        }
    }
}
