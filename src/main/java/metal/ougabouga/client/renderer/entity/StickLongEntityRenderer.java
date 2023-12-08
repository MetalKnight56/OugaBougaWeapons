package metal.ougabouga.client.renderer.entity;

import metal.ougabouga.client.renderer.entity.layers.StickClothLayer;
import metal.ougabouga.model.OugaBougaModelLayers;
import metal.ougabouga.model.stick_long_3d_cloth;
import metal.ougabouga.model.stick_long_3d_stick;
import metal.ougabouga.world.entity.custom.StickLongEntity;
import net.minecraft.client.model.SheepModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.layers.SheepFurLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


@OnlyIn(Dist.CLIENT)
public class StickLongEntityRenderer extends EntityRenderer<StickLongEntity>{
	 private static final ResourceLocation STICK_LOCATION = new ResourceLocation("textures/item/stick_long_3d_layer0.png");
	
	 protected StickLongEntityRenderer(EntityRendererProvider.Context context) {
		    super(context, new stick_long_3d_stick<>(context.bakeLayer(OugaBougaModelLayers.STICK_LONG)), 0.7F);
		    this.addLayer(new StickClothLayer(this, context.getModelSet()));
		}



	
	public ResourceLocation getTextureLocation(Sheep pEntity) {
	      return STICK_LOCATION;
	   }

	@Override
	public ResourceLocation getTextureLocation(StickLongEntity pEntity) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
