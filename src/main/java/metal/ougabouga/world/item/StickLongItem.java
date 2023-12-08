package metal.ougabouga.world.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
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
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class StickLongItem extends Item {
	   public static final int THROW_THRESHOLD_TIME = 10;
	   public static final float BASE_DAMAGE = 2.0F;
	   public static final float SHOOT_POWER = 2.5F;
	   private final Multimap<Attribute, AttributeModifier> defaultModifiers;
	   

public StickLongItem(Item.Properties pProperties) {
	super(pProperties);
    ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
    builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Tool modifier", 4.0D, AttributeModifier.Operation.ADDITION));
    builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Tool modifier", (double)-2.9F, AttributeModifier.Operation.ADDITION));
    this.defaultModifiers = builder.build();
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
	   
	   
	   public void setModel(ItemStack stack, String model) {
	        if (stack.getTag() == null) {
	            stack.setTag(new CompoundTag());
	        }
	        stack.getTag().putString("models/item/stick_long_throw.json", model);
	    }
	   
	   public void releaseUsing(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving, int pTimeLeft) {
		      if (pEntityLiving instanceof Player player) {
		         int i = this.getUseDuration(pStack) - pTimeLeft;
		         if (i >= 10) {
		                  if (true) {
		                     ThrownTrident throwntrident = new ThrownTrident(pLevel, player, pStack);
		                     throwntrident.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 2.5F * 0.5F, 1.0F);
		                     if (player.getAbilities().instabuild) {
		                        throwntrident.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
		                     }

		                     pLevel.addFreshEntity(throwntrident);
		                     pLevel.playSound((Player)null, throwntrident, SoundEvents.TRIDENT_THROW, SoundSource.PLAYERS, 1.0F, 1.0F);
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
		    * Called when a {@link net.minecraft.world.level.block.Block} is destroyed using this Item. Return {@code true} to
		    * trigger the "Use Item" statistic.
		    */
		   public boolean mineBlock(ItemStack pStack, Level pLevel, BlockState pState, BlockPos pPos, LivingEntity pEntityLiving) {
		      if ((double)pState.getDestroySpeed(pLevel, pPos) != 0.0D) {
		         pStack.hurtAndBreak(2, pEntityLiving, (p_43385_) -> {
		            p_43385_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
		         });
		      }

		      return true;
		   }
		   
		   public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot pEquipmentSlot) {
			      return pEquipmentSlot == EquipmentSlot.MAINHAND ? this.defaultModifiers : super.getDefaultAttributeModifiers(pEquipmentSlot);
			   }

}
