package net.vibey.ccwarium;

import net.minecraftforge.common.ForgeConfigSpec;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Config {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    private static final ForgeConfigSpec.ConfigValue<List<? extends String>> CONTROLABLE_CONF = BUILDER
            .comment("ids of wariumvs controlables.")
            .defineList("nodes", List.of("vehicle_control_node", "control_seat"), (e) -> e instanceof String);

    static final ForgeConfigSpec SPEC = BUILDER.build();

    public static Set<String> NODES = new HashSet<>();

    public static void loadConfig() {
        NODES.clear();
        CONTROLABLE_CONF.get().forEach(s -> {
            NODES.add("valkyrien_warium:" + s);
        });
    }
}