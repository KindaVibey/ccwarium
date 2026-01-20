package net.vibey.ccwarium;

import net.vibey.ccwarium.peripherals.NodePeripheralProvider;
import com.mojang.logging.LogUtils;
import dan200.computercraft.api.ForgeComputerCraftAPI;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import org.slf4j.Logger;

@Mod(ccwarium.MODID)
public class ccwarium {

    public static final String MODID = "ccwarium";
    private static final Logger LOGGER = LogUtils.getLogger();

    public ccwarium() {
        // Load config immediately
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
        Config.loadConfig();

        // Register peripheral provider
        ForgeComputerCraftAPI.registerPeripheralProvider(new NodePeripheralProvider());

        LOGGER.info("CC:Warium initialized");
    }
}