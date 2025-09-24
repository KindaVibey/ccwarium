package net.vibey.ccwarium.peripherals;

import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.lua.LuaFunction;
import dan200.computercraft.api.peripheral.IPeripheral;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class vNodePeripheral implements IPeripheral {

    private final BlockEntity node;

    public vNodePeripheral(BlockEntity node) {
        this.node = node;
    }

    @Override
    public String getType() {
        return "vehicle_control_node";
    }

    @Override
    public boolean equals(IPeripheral iPeripheral) {
        if(iPeripheral instanceof  vNodePeripheral peripheral){
            return node==peripheral.node;
        }
        return false;
    }


    @LuaFunction
    public void setNodePitch(double pitch) throws LuaException {
        if (pitch > 1 || pitch < -1) throw new LuaException("Control surface values can only match -1,0,1");
        node.getPersistentData().putDouble("Pitch",pitch/1);
        BlockState state = node.getLevel().getBlockState(node.getBlockPos());
        node.getLevel().sendBlockUpdated(node.getBlockPos(),state,state,3);
    }
}
