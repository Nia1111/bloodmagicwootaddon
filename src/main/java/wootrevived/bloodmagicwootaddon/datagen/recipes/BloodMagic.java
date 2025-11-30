package wootrevived.bloodmagicwootaddon.datagen.recipes;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import wayoftime.bloodmagic.common.data.recipe.builder.TartaricForgeRecipeBuilder;
import wayoftime.bloodmagic.common.item.BloodMagicItems;
import wootrevived.bloodmagicwootaddon.BloodMagicWootAddon;
import wootrevived.bloodmagicwootaddon.datagen.Recipes;
import wootrevived.bloodmagicwootaddon.upgrades.BloodCollector;
import wootrevived.woot.registries.UpgradeItemsRegistry;

import java.util.function.Consumer;

public class BloodMagic {
    public static void registerRecipes(Recipes recipes, Consumer<FinishedRecipe> consumer){
        TartaricForgeRecipeBuilder.tartaricForge(
                new ItemStack(BloodCollector.BLOOD_COLLECTOR_ITEM.get()),
                1000, 100,
                Ingredient.of(UpgradeItemsRegistry.UPGRADE_BASE_ITEM.get()),
                Ingredient.of(BloodMagicItems.IMBUED_SLATE.get()),
                Ingredient.of(BloodMagicItems.WEAK_BLOOD_SHARD.get()),
                Ingredient.of(Items.NETHERITE_SCRAP)
        ).build(consumer, BloodMagicWootAddon.location("soulforge/blood_collector_upgrade"));
    }
}
