package metal.ougabouga.world.item.crafting;

import metal.ougabouga.main.OugaBougaWeapons;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class OugaBougaRecipes {
	public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZER = DeferredRegister
			.create(ForgeRegistries.RECIPE_SERIALIZERS, OugaBougaWeapons.MOD_ID);

	public static final RegistryObject<SimpleCraftingRecipeSerializer<StickLongRecipe>> STICK_lONG = RECIPE_SERIALIZER
			.register("stick_long", () -> new SimpleCraftingRecipeSerializer<>(StickLongRecipe::new));

	// RecipeSerializer

	public static void register(IEventBus eventBus) {
		RECIPE_SERIALIZER.register(eventBus);
	}
}
