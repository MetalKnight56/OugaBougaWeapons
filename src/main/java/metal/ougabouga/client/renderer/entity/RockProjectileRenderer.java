package metal.ougabouga.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import metal.ougabouga.model.OugaBougaModelLayers;
import metal.ougabouga.model.rock_3d;
import metal.ougabouga.world.entity.custom.RockProjectileEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class RockProjectileRenderer extends EntityRenderer<RockProjectileEntity> {
	private final rock_3d<RockProjectileEntity> model;
;

    public RockProjectileRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new rock_3d<RockProjectileEntity>(context.bakeLayer(OugaBougaModelLayers.ROCK_3D));
    }

    @Override
    public void render(RockProjectileEntity entity, float entityYaw, float partialTicks, PoseStack matrixStack,
                       MultiBufferSource buffer, int packedLight) {
    	this.model.setupAnim(entity, 0, 0, entity.tickCount, 0, 0);
    	
        this.model.renderToBuffer(matrixStack, buffer.getBuffer(this.model.renderType(getTextureLocation(entity))), packedLight, OverlayTexture.NO_OVERLAY,
                1.0F, 1.0F, 1.0F, 1.0F);
       
        super.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
    }



    @Override
    public ResourceLocation getTextureLocation(RockProjectileEntity entity) {
        return new ResourceLocation("ougabougaweapons", "textures/item/rock_3d.png");
    }
}





