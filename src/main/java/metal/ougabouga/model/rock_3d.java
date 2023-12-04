package metal.ougabouga.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import metal.ougabouga.world.entity.custom.RockProjectileEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

public class rock_3d<T extends RockProjectileEntity> extends EntityModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("ougabougaweapons", "rock_3d"), "main");
    private ModelPart bone;

    public rock_3d(ModelPart root) {
        super(RenderType::entityCutout); // Set the render type during construction
        this.bone = root.getChild("bone");
        this.bone = root;
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(0, 16).addBox(-16.0F, -10.0F, 4.0F, 9.0F, 7.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-13.0F, -12.0F, 3.5F, 10.0F, 7.0F, 9.0F, new CubeDeformation(0.0F))
                .texOffs(9, 9).mirror().addBox(-10.0F, -9.0F, 4.5F, 10.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(8.0F, 24.0F, -8.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        bone.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        // TODO Auto-generated method stub
    }

	public RenderType renderType() {
		// TODO Auto-generated method stub
		return null;
	}
}
