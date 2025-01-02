package metal.ougabouga.client.renderer.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import metal.ougabouga.world.block.entity.BasketBlockEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BasketRenderer implements BlockEntityRenderer<BasketBlockEntity> {
	private final ItemRenderer itemRenderer;
	
	public BasketRenderer(BlockEntityRendererProvider.Context context) {
		this.itemRenderer = context.getItemRenderer();
	}
	
	@Override
	public boolean shouldRender(BasketBlockEntity p_173568_, Vec3 p_173569_) {
		return Vec3.atCenterOf(p_173568_.getBlockPos()).closerThan(p_173569_, this.getViewDistance());
	}
	
	@Override
	public void render(BasketBlockEntity blockEntity, float partialTicks, PoseStack poseStack, MultiBufferSource multiBufferSource, int lightColor, int overlayColor) {
		Direction direction = blockEntity.getBlockState().getValue(CampfireBlock.FACING);
		ItemStack itemstack = blockEntity.getItem(-1);
		
		if (itemstack != ItemStack.EMPTY) {
			poseStack.pushPose();
			poseStack.translate(0.5F, 0.64921875F, 0.5F);
			float f = -direction.toYRot() - 90.0F;
			poseStack.mulPose(Axis.YP.rotationDegrees(f));
			poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
			poseStack.scale(0.375F, 0.375F, 0.375F);
			this.itemRenderer.renderStatic(itemstack, ItemDisplayContext.FIXED, lightColor, overlayColor, poseStack, multiBufferSource, blockEntity.getLevel(), 0);
			poseStack.popPose();
		}
	}
}