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
    @LuaFunction
    public void setNodeYaw(double yaw) throws LuaException {
        if (yaw > 1 || yaw < -1) throw new LuaException("Control surface values can only match -1,0,1");
        node.getPersistentData().putDouble("Yaw",yaw/1);
        BlockState state = node.getLevel().getBlockState(node.getBlockPos());
        node.getLevel().sendBlockUpdated(node.getBlockPos(),state,state,3);
    }
    @LuaFunction
    public void setNodeRoll(double roll) throws LuaException {
        if (roll > 1 || roll < -1) throw new LuaException("Control surface values can only match -1,0,1");
        node.getPersistentData().putDouble("Roll", roll/1);
        BlockState state = node.getLevel().getBlockState(node.getBlockPos());
        node.getLevel().sendBlockUpdated(node.getBlockPos(),state,state,3);
    }
    @LuaFunction
    public void setNodeThrottle(double throttle) throws LuaException {
        if (throttle > 10 || throttle < -1) throw new LuaException("Throttle values can only be -1-10");
        double throttlee;
        throttlee = throttle*10;
        node.getPersistentData().putDouble("Throttle",throttlee/10);
        BlockState state = node.getLevel().getBlockState(node.getBlockPos());
        node.getLevel().sendBlockUpdated(node.getBlockPos(),state,state,3);
    }
    @LuaFunction
    public void setNodeLandingGear(boolean LG) throws LuaException {
        if (LG != true && LG != false) throw new LuaException("landing gear can only match true or false");
        node.getPersistentData().putBoolean("LandingGear", LG);
        BlockState state = node.getLevel().getBlockState(node.getBlockPos());
        node.getLevel().sendBlockUpdated(node.getBlockPos(),state,state,3);
    }
    @LuaFunction
    public void setNodeTrigger(int channel, boolean trigger) throws LuaException {
        if (trigger != true && trigger != false) throw new LuaException("trigger can only match true or false");
        if (channel > 10 || channel < 0) throw new LuaException("channel values can only be 0-10");
        double trig;
        if (trigger) trig = 1;
        else trig = 0;
        node.getPersistentData().putDouble("Trigger"+channel+".0", trig/1);
        BlockState state = node.getLevel().getBlockState(node.getBlockPos());
        node.getLevel().sendBlockUpdated(node.getBlockPos(), state, state, 3);
    }





    @LuaFunction
    public boolean getNodeTrigger(int channel) throws LuaException {
        if (channel > 10 || channel < 0) throw new LuaException("channel values can only be 0-10");

        double trig = node.getPersistentData().getDouble("Trigger"+channel+".0");
        boolean trigger;
        if (trig > 0) trigger = true;
        else trigger = false;

        return trigger;
    }
    @LuaFunction
    public double getNodePitch() {
        double pitch = node.getPersistentData().getDouble("Pitch");

        return pitch;
    }
    @LuaFunction
    public double getNodeYaw() {
        double yaw = node.getPersistentData().getDouble("Yaw");

        return yaw;
    }
    @LuaFunction
    public double getNodeRoll() {
        double roll = node.getPersistentData().getDouble("Roll");

        return roll;
    }
    @LuaFunction
    public double getNodeThrottle() {
        double throttle = node.getPersistentData().getDouble("Throttle");

        return throttle;
    }
    @LuaFunction
    public boolean getNodeLandingGear() {
        boolean LG = node.getPersistentData().getDouble("LandingGear");

        return LG;
    }
}
