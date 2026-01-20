package net.vibey.ccwarium.peripherals;

import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.lua.LuaFunction;
import dan200.computercraft.api.peripheral.IPeripheral;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.valkyrienskies.mod.api.ValkyrienSkies;
import org.valkyrienskies.core.api.world.ShipWorld;
import org.valkyrienskies.core.api.ships.LoadedShip;
import org.valkyrienskies.core.api.ships.QueryableShipData;
import org.joml.Vector3d;

import java.util.HashMap;
import java.util.Map;

public class vNodePeripheral implements IPeripheral {

    private final BlockEntity node;

    public vNodePeripheral(BlockEntity node) {
        this.node = node;
    }

    @Override
    public String getType() {
        return "controller";
    }

    @Override
    public boolean equals(IPeripheral iPeripheral) {
        if(iPeripheral instanceof vNodePeripheral peripheral){
            return node==peripheral.node;
        }
        return false;
    }


    @LuaFunction
    public void setNodePitch(double pitch) throws LuaException {
        if (pitch > 1 || pitch < -1) throw new LuaException("Pitch values can only match -1,0,1");
        node.getPersistentData().putDouble("Pitch",pitch/1);
        BlockState state = node.getLevel().getBlockState(node.getBlockPos());
        node.getLevel().sendBlockUpdated(node.getBlockPos(),state,state,3);
    }
    @LuaFunction
    public void setNodeYaw(double yaw) throws LuaException {
        if (yaw > 1 || yaw < -1) throw new LuaException("Yaw values can only match -1,0,1");
        node.getPersistentData().putDouble("Yaw",yaw/1);
        BlockState state = node.getLevel().getBlockState(node.getBlockPos());
        node.getLevel().sendBlockUpdated(node.getBlockPos(),state,state,3);
    }
    @LuaFunction
    public void setNodeRoll(double roll) throws LuaException {
        if (roll > 1 || roll < -1) throw new LuaException("Roll values can only match -1,0,1");
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
        boolean LG = node.getPersistentData().getBoolean("LandingGear");

        return LG;
    }






    @LuaFunction
    public Map<String, Double> getRadarLockCoords(int lock) throws LuaException {
        if (lock < 1) throw new LuaException("lock channel values have to be greater than 0");

        double X = node.getPersistentData().getDouble("TargetX"+lock+".0");
        double Z = node.getPersistentData().getDouble("TargetZ"+lock+".0");
        double Y = node.getPersistentData().getDouble("TargetY"+lock+".0");

        Map<String, Double> coords = new HashMap<>();
        coords.put("x", X);
        coords.put("y", Y);
        coords.put("z", Z);
        return coords;
    }

    @LuaFunction
    public double getRadarLockChannel() {
        return node.getPersistentData().getDouble("SelectedTarget");
    }

    @LuaFunction
    public void setRadarLockChannel(double target) throws LuaException {
        if (target < 1 || target > 10) throw new LuaException("Target must be 1-10");
        node.getPersistentData().putDouble("SelectedTarget", target);
        BlockState state = node.getLevel().getBlockState(node.getBlockPos());
        node.getLevel().sendBlockUpdated(node.getBlockPos(), state, state, 3);
    }

    @LuaFunction
    public double getRadarScan() {
        return node.getPersistentData().getDouble("Targets");
    }

    @LuaFunction
    public Map<String, Double> getRadarLockVelocity(int target) throws LuaException {
        if (target < 1) throw new LuaException("target channel values have to be greater than 0");

        double X = node.getPersistentData().getDouble("TMX" + target + ".0");
        double Y = node.getPersistentData().getDouble("TMY" + target + ".0");
        double Z = node.getPersistentData().getDouble("TMZ" + target + ".0");

        Map<String, Double> vel = new HashMap<>();
        vel.put("x", X);
        vel.put("y", Y);
        vel.put("z", Z);
        return vel;
    }

    @LuaFunction
    public String getRadarLockID() throws LuaException {
        Map<String, Double> coords = getRadarLockCoords((int) getRadarLockChannel());
        if (coords.isEmpty()) return "";

        ShipWorld shipWorld = ValkyrienSkies.api().getShipWorld(node.getLevel());
        if (shipWorld == null) return "";

        QueryableShipData<LoadedShip> loadedShips = shipWorld.getLoadedShips();

        LoadedShip nearest = null;
        double minDistSq = Double.MAX_VALUE;

        Vector3d targetPos = new Vector3d(coords.get("x"), coords.get("y"), coords.get("z"));

        for (LoadedShip ship : loadedShips) {
            Vector3d shipPos = new Vector3d(ship.getTransform().getPositionInWorld());
            double distSq = shipPos.distanceSquared(targetPos);
            if (distSq < minDistSq) {
                minDistSq = distSq;
                nearest = ship;
            }
        }

        if (nearest == null) return "";
        return nearest.getSlug() != null ? nearest.getSlug() : "";
    }


}
