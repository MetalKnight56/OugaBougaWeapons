package metal.ougabouga.model;

import metal.ougabouga.main.OugaBougaWeapons;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class OugaBougaModelLayers {
	public static final ModelLayerLocation ROCK_3D = new ModelLayerLocation(
            new ResourceLocation(OugaBougaWeapons.MOD_ID, "rock_3d"), "main");
	
	public static final ModelLayerLocation STICK_LONG = new ModelLayerLocation(
			new ResourceLocation(OugaBougaWeapons.MOD_ID), "stick_long");
	
	public static final ModelLayerLocation STICK_LONG_CLOTH = new ModelLayerLocation(
			new ResourceLocation(OugaBougaWeapons.MOD_ID), "stick_long_cloth");
}
