package wootrevived.bloodmagicwootaddon.datagen;

import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;
import wootrevived.bloodmagicwootaddon.datagen.recipes.BloodMagic;

import java.util.function.Consumer;

public class Recipes extends RecipeProvider {
    public Recipes(PackOutput packOutput) { super(packOutput); }

    @Override
    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> consumer){
        BloodMagic.registerRecipes(this, consumer);
    }

    public InventoryChangeTrigger.TriggerInstance hasItem(ItemLike item) { return has(item); }

    public InventoryChangeTrigger.TriggerInstance hasItem(TagKey<Item> item) { return has(item); }
}