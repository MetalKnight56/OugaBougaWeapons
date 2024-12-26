package metal.ougabouga.event;

import metal.ougabouga.main.OugaBougaWeapons;
import metal.ougabouga.model.OugaBougaModelLayers;
import metal.ougabouga.model.StickLong3dCloth;
import metal.ougabouga.model.StickLong3dStick;
import metal.ougabouga.model.rock_3d;
import metal.ougabouga.world.item.OugaBougaItems;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = OugaBougaWeapons.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class OugaBougaEventBusClientEvents {
	@SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(OugaBougaModelLayers.ROCK_3D, rock_3d::createBodyLayer);
        event.registerLayerDefinition(OugaBougaModelLayers.STICK_LONG, StickLong3dStick::createBodyLayer);
        event.registerLayerDefinition(OugaBougaModelLayers.STICK_LONG_CLOTH, StickLong3dCloth::createBodyLayer);

	}
	
	//metals code
	
	@SubscribeEvent
	public static void OnItemColorsInit(RegisterColorHandlersEvent.Item event)
	{
		event.register((stack, tint) -> {
			return tint > 0 ? ((DyeableLeatherItem)stack.getItem()).getColor(stack) : -1;
		}, OugaBougaItems.STICK_LONG.get());
		//ModLoader.get().postEvent(new RegisterColorHandlersEvent.Item(itemColors, blockColors));
	}
}