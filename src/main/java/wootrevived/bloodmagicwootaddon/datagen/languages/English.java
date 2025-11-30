package wootrevived.bloodmagicwootaddon.datagen.languages;

import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;
import wootrevived.bloodmagicwootaddon.BloodMagicWootAddon;
import wootrevived.bloodmagicwootaddon.upgrades.BloodCollector;

public class English extends LanguageProvider {
    public English(PackOutput output){
        super(output, BloodMagicWootAddon.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add(BloodCollector.BLOOD_COLLECTOR_ITEM.get(), "Blood Collector Upgrade");

        add("tooltip.bloodmagicwootaddon.altar_binded", "Binded Altar: %d %d %d");
        add("tooltip.bloodmagicwootaddon.altar_not_binded", "No altar binded.");
    }
}