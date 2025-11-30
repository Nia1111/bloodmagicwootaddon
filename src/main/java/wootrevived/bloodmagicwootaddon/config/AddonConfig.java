package wootrevived.bloodmagicwootaddon.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

public class AddonConfig {
    public static ForgeConfigSpec.ConfigValue<Integer> BLOOD_COLLECTOR_COST;

    public static void build(ForgeConfigSpec.Builder builder){
        builder.comment("Addon").push("addon");
        {
            BLOOD_COLLECTOR_COST = builder.comment(String.format("The life essence cost per kill for the blood collector upgrade [Default: %d]", Default.BLOOD_COLLECTOR_COST))
                    .define("bloodCollectorCost", Default.BLOOD_COLLECTOR_COST);
        }
        builder.pop();
    }

    private static class Default {
        public static final int BLOOD_COLLECTOR_COST = 10;
    }

    private static final ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();

    public static ForgeConfigSpec COMMON_CONFIG;

    static {
        build(COMMON_BUILDER);
        COMMON_CONFIG = COMMON_BUILDER.build();
    }

    public static void init(){
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, COMMON_CONFIG);
    }
}
