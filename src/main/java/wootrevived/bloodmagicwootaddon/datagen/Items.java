package wootrevived.bloodmagicwootaddon.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import wootrevived.api.models.DynamicUpgradeItemModelBuilder;
import wootrevived.bloodmagicwootaddon.BloodMagicWootAddon;
import wootrevived.bloodmagicwootaddon.upgrades.BloodCollector;

import java.util.Objects;

public class Items extends ItemModelProvider {
    public Items(PackOutput packOutput, ExistingFileHelper existingFileHelper) {
        super(packOutput, BloodMagicWootAddon.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        itemGenerated(BloodCollector.BLOOD_COLLECTOR_ITEM);
    }

    public ResourceLocation getItemResource(Item item){
        return Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item)).withPrefix("item/");
    }

    public void itemGenerated(RegistryObject<?> item){
        ResourceLocation itemResource = getItemResource((Item) item.get());
        getBuilder(itemResource.getPath())
                .parent(getExistingFile(mcLoc("item/generated")))
                .texture("layer0", itemResource);
    }

    public void itemUpgrade(RegistryObject<?> item){
        ResourceLocation itemResource = getItemResource((Item) item.get());
        getBuilder(itemResource.getPath())
                .parent(getExistingFile(mcLoc("item/generated")))
                .customLoader(DynamicUpgradeItemModelBuilder::begin);
    }
}