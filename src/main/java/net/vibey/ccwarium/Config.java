package net.vibey.ccwarium;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Forge's config APIs
@Mod.EventBusSubscriber(modid = ccwarium.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    // a list of strings that are treated as resource locations for items
    private static final ForgeConfigSpec.ConfigValue<List<? extends String>> CONTROLABLE_CONF = BUILDER.comment("ids of wariumvs controlables.").defineList("nodes",List.of(
            "vehicle_control_node","control_seat"
    ),(e)->e instanceof String);

    static final ForgeConfigSpec SPEC = BUILDER.build();

    public static Set<String> NODES = new HashSet<>();
    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        NODES.clear();

        // Force set the config values to the defaults
        CONTROLABLE_CONF.set(List.of(
                "vehicle_control_node",
                "control_seat"
        ));

        // Save the config file to disk
        SPEC.save();

        CONTROLABLE_CONF.get().forEach(s -> {
            NODES.add("valkyrien_warium:"+s);
        });
    }
}