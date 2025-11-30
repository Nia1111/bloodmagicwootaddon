package wootrevived.bloodmagicwootaddon;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;
import wootrevived.bloodmagicwootaddon.config.AddonConfig;

import java.util.Objects;

@Mod(BloodMagicWootAddon.MOD_ID)
public class BloodMagicWootAddon
{
    public static final String MOD_ID = "bloodmagicwootaddon";

    public BloodMagicWootAddon()
    {
        AddonConfig.init();
    }

    public static @NotNull ResourceLocation location(String path) {
        return Objects.requireNonNull(ResourceLocation.tryBuild(MOD_ID, path));
    }
}
