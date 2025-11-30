package wootrevived.bloodmagicwootaddon.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import wootrevived.bloodmagicwootaddon.BloodMagicWootAddon;

@Mod.EventBusSubscriber(modid = BloodMagicWootAddon.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        PackOutput packOutput = generator.getPackOutput();
        if(event.includeServer()) {
            generator.addProvider(true, new Recipes(packOutput));
        }
        if(event.includeClient()) {
            generator.addProvider(true, new Items(packOutput, existingFileHelper));
            generator.addProvider(true, new Languages(packOutput));
        }
    }
}