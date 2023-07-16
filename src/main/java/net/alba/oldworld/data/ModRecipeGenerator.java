package net.alba.oldworld.data;

import java.util.List;
import java.util.function.Consumer;

import net.alba.oldworld.registry.OldBlocks;
import net.alba.oldworld.registry.OldItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;

public class ModRecipeGenerator extends FabricRecipeProvider {

    public ModRecipeGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        offerSmelting(exporter, List.of(OldBlocks.OLD_ORE), RecipeCategory.MISC, OldItems.OLD_INGOT,
            0.7f, 200, "old_ingot");

        offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, OldItems.OLD_INGOT, 
            RecipeCategory.DECORATIONS, OldBlocks.OLD_INGOT_BLOCK);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, OldItems.OLD_SWORD)
            .pattern("O")
            .pattern("S")
            .pattern("S")
            .input('O', OldItems.OLD_INGOT)
            .input('S', Items.STICK)
            .criterion(FabricRecipeProvider.hasItem(Items.STICK), 
                FabricRecipeProvider.conditionsFromItem(Items.STICK))
            .criterion(FabricRecipeProvider.hasItem(OldItems.OLD_INGOT), 
                FabricRecipeProvider.conditionsFromItem(OldItems.OLD_INGOT))
            .offerTo(exporter, new Identifier(FabricRecipeProvider.getRecipeName(OldItems.OLD_SWORD)));
    }
}
