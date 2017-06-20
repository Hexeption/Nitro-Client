package uk.co.hexeption.client.events;

import me.zero.alpine.type.Cancellable;
import net.minecraft.util.math.BlockPos;

public class EventOpaquCube extends Cancellable {

    private final BlockPos pos;

    public EventOpaquCube(BlockPos pos) {
        this.pos = pos;
    }

    public BlockPos getPos() {
        return pos;
    }
}
