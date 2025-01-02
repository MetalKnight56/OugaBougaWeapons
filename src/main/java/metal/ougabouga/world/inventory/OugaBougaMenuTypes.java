package metal.ougabouga.world.inventory;

import metal.ougabouga.main.OugaBougaWeapons;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class OugaBougaMenuTypes {
	public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, OugaBougaWeapons.MOD_ID);

	public static final RegistryObject<MenuType<BasketContainerMenu>> BASKET = CONTAINERS.register("basket", () -> new MenuType<>(BasketContainerMenu::new, FeatureFlags.VANILLA_SET));
	
	public static void register(IEventBus eventBus) {
		CONTAINERS.register(eventBus);
    }
}
