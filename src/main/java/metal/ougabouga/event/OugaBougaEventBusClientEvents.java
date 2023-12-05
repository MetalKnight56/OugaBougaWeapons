package metal.ougabouga.event;

import net.minecraftforge.fml.common.Mod;
import metal.ougabouga.main.OugaBougaWeapons;
import metal.ougabouga.model.OugaBougaModelLayers;
import metal.ougabouga.model.rock_3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

@Mod.EventBusSubscriber(modid = OugaBougaWeapons.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class OugaBougaEventBusClientEvents {
	@SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(OugaBougaModelLayers.ROCK_3D, rock_3d::createBodyLayer);

	}
}