package metal.ougabouga.world.item;

import java.util.List;

import org.jetbrains.annotations.NotNull;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import metal.ougabouga.main.OugaBougaWeapons;
import metal.ougabouga.world.entity.custom.StickLongEntity;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.fml.ModLoader;
import metal.ougabouga.api.utils.ModUtils;

public class StickLongItem extends Item implements DyeableLeatherItem {
	public static final ResourceLocation THROWING_PREDICATE = ModUtils.modLocation("throwing");
	public static final int THROW_THRESHOLD_TIME = 10;
	public static final float BASE_DAMAGE = 2.0F;
	public static final float SHOOT_POWER = 2.5F;
	private final Multimap<Attribute, AttributeModifier> defaultModifiers;
	

	public StickLongItem(Item.Properties pProperties) {
		
		super(pProperties);
		ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
		builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Tool modifier", 4.0D,
				AttributeModifier.Operation.ADDITION));
		builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Tool modifier",
				(double) -2.9F, AttributeModifier.Operation.ADDITION));
		this.defaultModifiers = builder.build();
		
		
	}

	@Override
	public int getColor(ItemStack pStack) {
		CompoundTag compoundtag = pStack.getTagElement("display");
		// System.out.println(compoundtag);

		return compoundtag != null && compoundtag.contains("color", 99) ? compoundtag.getInt("color") : 0xFFFFFF;
	}

	public static ItemStack dyeStickLong(ItemStack pStack, List<DyeItem> pDyes) {
		ItemStack itemstack = ItemStack.EMPTY;
		int[] aint = new int[3];
		int i = 0;
		int j = 0;
		Item item = pStack.getItem();
		DyeableLeatherItem dyeableleatheritem = null;
		if (item instanceof DyeableLeatherItem) {
			dyeableleatheritem = (DyeableLeatherItem) item;
			itemstack = pStack.copyWithCount(1);
			if (dyeableleatheritem.hasCustomColor(pStack)) {
				int k = dyeableleatheritem.getColor(itemstack);
				float f = (float) (k >> 16 & 255) / 255.0F;
				float f1 = (float) (k >> 8 & 255) / 255.0F;
				float f2 = (float) (k & 255) / 255.0F;
				i += (int) (Math.max(f, Math.max(f1, f2)) * 255.0F);
				aint[0] += (int) (f * 255.0F);
				aint[1] += (int) (f1 * 255.0F);
				aint[2] += (int) (f2 * 255.0F);
				++j;
			}

			for (DyeItem dyeitem : pDyes) {
				float[] afloat = dyeitem.getDyeColor().getTextureDiffuseColors();
				int i2 = (int) (afloat[0] * 255.0F);
				int l = (int) (afloat[1] * 255.0F);
				int i1 = (int) (afloat[2] * 255.0F);
				i += Math.max(i2, Math.max(l, i1));
				aint[0] += i2;
				aint[1] += l;
				aint[2] += i1;
				++j;
			}
		}

		if (dyeableleatheritem == null) {
			return ItemStack.EMPTY;
		} else {
			int j1 = aint[0] / j;
			int k1 = aint[1] / j;
			int l1 = aint[2] / j;
			float f3 = (float) i / (float) j;
			float f4 = (float) Math.max(j1, Math.max(k1, l1));
			j1 = (int) ((float) j1 * f3 / f4);
			k1 = (int) ((float) k1 * f3 / f4);
			l1 = (int) ((float) l1 * f3 / f4);
			int j2 = (j1 << 8) + k1;
			j2 = (j2 << 8) + l1;

			// System.out.println("dyed " + j2);
			dyeableleatheritem.setColor(itemstack, j2);
			return itemstack;
		}
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
		// Check if the enchantment can be applied to the item
		return false;
	}

	@Override
	public boolean isEnchantable(ItemStack stack) {
		// Make the item not enchantable
		return false;
	}

	public boolean canAttackBlock(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer) {
		return !pPlayer.isCreative();
	}

	public UseAnim getUseAnimation(ItemStack pStack) {
		return UseAnim.SPEAR;
	}

	/**
	 * How long it takes to use or consume an item
	 */
	public int getUseDuration(ItemStack pStack) {
		return 72000;
	}

	public void releaseUsing(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving, int pTimeLeft) {
		if (pEntityLiving instanceof Player player) {
			int i = this.getUseDuration(pStack) - pTimeLeft;
			if (i >= 10) {
				if (true) {
					StickLongEntity thrownstick = new StickLongEntity(pLevel, player, pStack);
					thrownstick.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 2.5F * 0.5F, 1.0F);
					if (player.getAbilities().instabuild) {
						thrownstick.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
					}

					pLevel.addFreshEntity(thrownstick);
					pLevel.playSound((Player) null, thrownstick, SoundEvents.TRIDENT_THROW, SoundSource.PLAYERS, 1.0F,
							1.0F);
					if (!player.getAbilities().instabuild) {
						player.getInventory().removeItem(pStack);
					}
				}
			}
		}
	}

	public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
	    ItemStack itemstack = pPlayer.getItemInHand(pHand);
	    if (itemstack.getDamageValue() >= itemstack.getMaxDamage() - 1) {
	        return InteractionResultHolder.fail(itemstack);
	    } else if (!pPlayer.isAlive()) {
	        return InteractionResultHolder.fail(itemstack);
	    } else {

	        pPlayer.startUsingItem(pHand);
	        return InteractionResultHolder.consume(itemstack);
	    }
	}

	public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
		pStack.hurtAndBreak(1, pAttacker, (p_43414_) -> {
			p_43414_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
		});
		return true;
	}

	/**
	 * Called when a {@link net.minecraft.world.level.block.Block} is destroyed
	 * using this Item. Return {@code true} to trigger the "Use Item" statistic.
	 */
	public boolean mineBlock(ItemStack pStack, Level pLevel, BlockState pState, BlockPos pPos,
			LivingEntity pEntityLiving) {
		if ((double) pState.getDestroySpeed(pLevel, pPos) != 0.0D) {
			pStack.hurtAndBreak(2, pEntityLiving, (p_43385_) -> {
				p_43385_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
			});
		}

		return true;
	}

	public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot pEquipmentSlot) {
		return pEquipmentSlot == EquipmentSlot.MAINHAND ? this.defaultModifiers
				: super.getDefaultAttributeModifiers(pEquipmentSlot);
	}

}
