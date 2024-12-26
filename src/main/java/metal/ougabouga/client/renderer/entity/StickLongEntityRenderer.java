package metal.ougabouga.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;

import metal.ougabouga.main.OugaBougaWeapons;
import metal.ougabouga.model.OugaBougaModelLayers;
import metal.ougabouga.model.StickLong3dCloth;
import metal.ougabouga.model.StickLong3dStick;
import metal.ougabouga.world.entity.custom.StickLongEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class StickLongEntityRenderer extends EntityRenderer<StickLongEntity> {
	private static final ResourceLocation STICK_LOCATION = new ResourceLocation(OugaBougaWeapons.MOD_ID,
			"textures/entity/stick_long_3d_layer0.png");
	private static final ResourceLocation STICK_LOCATION_CLOTH = new ResourceLocation(OugaBougaWeapons.MOD_ID,
			"textures/entity/stick_long_3d_layer1_java.png");

	// StckLong3dStick
	protected StickLong3dCloth modelCloth;
	protected StickLong3dStick modelStick;

	public StickLongEntityRenderer(EntityRendererProvider.Context context) {
		super(context);
		/**
		 * , new
		 * stick_long_3d_stick<>(context.bakeLayer(OugaBougaModelLayers.STICK_LONG)),
		 * 0.7F
		 **/
		// this.addLayer(new StickClothLayer(this, context.getModelSet()));

		this.modelCloth = new StickLong3dCloth(context.bakeLayer(OugaBougaModelLayers.STICK_LONG_CLOTH));
		this.modelStick = new StickLong3dStick(context.bakeLayer(OugaBougaModelLayers.STICK_LONG));
	}

	@Override
	public void render(StickLongEntity pEntity, float pEntityYaw, float pPartialTick, PoseStack pPoseStack,
			MultiBufferSource pBuffer, int pPackedLight) {
		super.render(pEntity, pEntityYaw, pPartialTick, pPoseStack, pBuffer, pPackedLight);

		// this.model.renderToBuffer(pPoseStack, vertexconsumer, pPackedLight, i, 1.0F,
		// 1.0F, 1.0F, flag1 ? 0.15F : 1.0F);

		pPoseStack.pushPose();
		pPoseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(pPartialTick, pEntity.yRotO, pEntity.getYRot()) - 90.0F));
		pPoseStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(pPartialTick, pEntity.xRotO, pEntity.getXRot()) + 90.0F));

		int overlay = OverlayTexture.pack(OverlayTexture.u(0.0F), OverlayTexture.v(false));

		float f;
		float f1;
		float f2;
		if (pEntity.getPickupItem().getItem() instanceof DyeableLeatherItem dyeableleatheritem) {
			int i = pEntity.getItemStackColor();
			// dyeableleatheritem.getColor(pEntity.getPickupItem());
			f = (float) (i >> 16 & 255) / 255.0F;
			f1 = (float) (i >> 8 & 255) / 255.0F;
			f2 = (float) (i & 255) / 255.0F;

			// System.out.println(f +" "+ f1 +" "+ f2);

		} else {
			f = 1.0F;
			f1 = 1.0F;
			f2 = 1.0F;
		}

		VertexConsumer vertexConsumer = pBuffer.getBuffer(this.modelCloth.renderType(STICK_LOCATION_CLOTH));

		this.modelCloth.renderToBuffer(pPoseStack, vertexConsumer, pPackedLight, overlay, f, f1, f2, 1.0F);

		// System.out.println(overlay);
		VertexConsumer vertexConsumer2 = pBuffer.getBuffer(this.modelStick.renderType(STICK_LOCATION));

		this.modelStick.renderToBuffer(pPoseStack, vertexConsumer2, pPackedLight, overlay, 1.0F, 1.0F, 1.0F, 1.0F);

		pPoseStack.popPose();

		// this.modelWoodenlePart.render();
	}
	// this.modelDyab

	public ResourceLocation getTextureLocation(Sheep pEntity) {
		return STICK_LOCATION;
	}

	@Override
	public ResourceLocation getTextureLocation(StickLongEntity pEntity) {
		// TODO Auto-generated method stub
		return null;
	}

}
