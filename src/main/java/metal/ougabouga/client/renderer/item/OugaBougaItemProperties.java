package metal.ougabouga.client.renderer.item;

import metal.ougabouga.world.item.OugaBougaItems;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class OugaBougaItemProperties {

    public static void registerProperties() {
        ItemProperties.register(OugaBougaItems.STICK_LONG.get(),
            new ResourceLocation("throwing"), (stack, world, entity, seed) -> {
                return entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F;
            }
        );
    }
}
