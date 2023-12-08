package metal.ougabouga.event;

import net.minecraftforge.fml.common.Mod;
import metal.ougabouga.main.OugaBougaWeapons;
import metal.ougabouga.model.OugaBougaModelLayers;
import metal.ougabouga.model.rock_3d;
import metal.ougabouga.model.stick_long_3d_cloth;
import metal.ougabouga.model.stick_long_3d_stick;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

@Mod.EventBusSubscriber(modid = OugaBougaWeapons.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class OugaBougaEventBusClientEvents {
	@SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(OugaBougaModelLayers.ROCK_3D, rock_3d::createBodyLayer);
        event.registerLayerDefinition(OugaBougaModelLayers.STICK_LONG, stick_long_3d_stick::createBodyLayer);
        event.registerLayerDefinition(OugaBougaModelLayers.STICK_LONG_CLOTH, stick_long_3d_cloth::createBodyLayer);

	}
}