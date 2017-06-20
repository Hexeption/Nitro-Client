package uk.co.hexeption.client.events;

import me.zero.alpine.type.Cancellable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class EventRenderBlockSide extends Cancellable {

    private final IBlockState state;

    private final IBlockAccess access;

    private final BlockPos pos;

    private final EnumFacing side;

    private boolean toRender;

    public EventRenderBlockSide(IBlockState state, IBlockAccess access, BlockPos pos, EnumFacing side) {
        this.state = state;
        this.access = access;
        this.pos = pos;
        this.side = side;
    }

    public IBlockState getState() {
        return state;
    }

    public IBlockAccess getAccess() {
        return access;
    }

    public BlockPos getPos() {
        return pos;
    }

    public EnumFacing getSide() {
        return side;
    }

    public boolean isToRender() {
        return toRender;
    }

    public void setToRender(boolean toRender) {
        this.toRender = toRender;
    }
}
