package metal.ougabouga.world.item;

import metal.ougabouga.main.OugaBougaWeapons;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class OugaBougaCreativeModeTabs {
	public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
			DeferredRegister.create(Registries.CREATIVE_MODE_TAB, OugaBougaWeapons.MOD_ID);
	
	public static final RegistryObject<CreativeModeTab> OugaBouga_TAB = CREATIVE_MODE_TABS.register("ougabouga_tab", 
			() -> CreativeModeTab.builder().icon(() -> new ItemStack(OugaBougaItems.CLUB.get()))
			
			.title(Component.translatable("creativetab.ougabouga_tab"))
			.displayItems((pParameters, pOutput) -> {
				pOutput.accept(OugaBougaItems.CLUB.get());
				pOutput.accept(OugaBougaItems.STICK_LONG.get());
				pOutput.accept(OugaBougaItems.ROCK.get());
			})
			.withBackgroundLocation(new ResourceLocation(OugaBougaWeapons.MOD_ID, "textures/gui/ougabouga_tab.png"))
			.build());
	
	public static void register(IEventBus eventBus) {
		CREATIVE_MODE_TABS.register(eventBus);
	}
}