package wootrevived.bloodmagicwootaddon.datagen.languages;

import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;
import wootrevived.bloodmagicwootaddon.BloodMagicWootAddon;
import wootrevived.bloodmagicwootaddon.upgrades.BloodCollector;

public class Japanese extends LanguageProvider {
    public Japanese(PackOutput output){
        super(output, BloodMagicWootAddon.MOD_ID, "ja_jp");
    }

    @Override
    protected void addTranslations() {
        add(BloodCollector.BLOOD_COLLECTOR_ITEM.get(), "血液収集アップグレード");

        add("tooltip.bloodmagicwootaddon.altar_binded", "紐づけられている祭壇: %d %d %d");
        add("tooltip.bloodmagicwootaddon.altar_not_binded", "祭壇は紐づけられていません");
    }
}