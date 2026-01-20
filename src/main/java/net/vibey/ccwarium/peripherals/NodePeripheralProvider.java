package net.vibey.ccwarium.peripherals;

import dan200.computercraft.api.peripheral.IPeripheral;
import dan200.computercraft.api.peripheral.IPeripheralProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.common.util.LazyOptional;
import net.vibey.ccwarium.Config;

public class NodePeripheralProvider implements IPeripheralProvider {

    @Override
    public LazyOptional<IPeripheral> getPeripheral(Level level, BlockPos blockPos, Direction direction) {
        BlockEntity blockEntity = level.getBlockEntity(blockPos);
        if (blockEntity == null) {
            return LazyOptional.empty();
        }

        String id = BlockEntityType.getKey(blockEntity.getType()).toString();
        if (!Config.NODES.contains(id)) {
            return LazyOptional.empty();
        }

        return LazyOptional.of(() -> new vNodePeripheral(blockEntity));
    }
}