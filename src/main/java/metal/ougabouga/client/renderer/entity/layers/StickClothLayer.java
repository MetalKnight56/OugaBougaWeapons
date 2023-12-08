package metal.ougabouga.client.renderer.entity.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import metal.ougabouga.model.OugaBougaModelLayers;
import metal.ougabouga.model.stick_long_3d_cloth;
import metal.ougabouga.world.entity.custom.StickLongEntity;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;

public class StickClothLayer extends RenderLayer<StickLongEntity, stick_long_3d_cloth<StickLongEntity>> {
    private final stick_long_3d_cloth<StickLongEntity> model;
    private static final ResourceLocation STICK_CLOTH_LOCATION = new ResourceLocation("textures/entity/sheep/sheep_fur.png");

    public StickClothLayer(RenderLayerParent<StickLongEntity, stick_long_3d_cloth<StickLongEntity>> renderer, EntityModelSet modelSet) {
        super(renderer);
        this.model = new stick_long_3d_cloth<>(modelSet.bakeLayer(OugaBougaModelLayers.STICK_LONG_CLOTH));
    }



    public void render(PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, StickLongEntity pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        float[] rgb = Sheep.getColorArray(pLivingEntity.getColor());
        float red = rgb[0];
        float green = rgb[1];
        float blue = rgb[2];

     // Get the default render type for the entity
        RenderType renderType = model.renderType(STICK_CLOTH_LOCATION);

        // Get a VertexConsumer from the MultiBufferSource
        VertexConsumer vertexConsumer = pBuffer.getBuffer(renderType);

        // Render the model
        this.model.renderToBuffer(pPoseStack, vertexConsumer, pPackedLight, LivingEntityRenderer.getOverlayCoords(null, OverlayTexture.NO_OVERLAY), red, green, blue, 1.0F);
    }
}