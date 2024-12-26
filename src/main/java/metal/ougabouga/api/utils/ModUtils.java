package metal.ougabouga.api.utils;

import org.jetbrains.annotations.NotNull;

import metal.ougabouga.main.OugaBougaWeapons;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.registries.RegistryObject;

public class ModUtils {
	@NotNull
    public static ResourceLocation modLocation(@NotNull String path) {
        return  new ResourceLocation(OugaBougaWeapons.MOD_ID, path);
    }
	
	@NotNull
    public static ResourceLocation modItemLoc(@NotNull String path) {
        return modLocation(ModelProvider.ITEM_FOLDER + "/" + path);
    }
	
	public static String getPath(RegistryObject<?> object) {
	    return object.getId().getPath();
	}
}
