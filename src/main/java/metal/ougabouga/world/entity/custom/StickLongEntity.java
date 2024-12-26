package metal.ougabouga.world.entity.custom;

import javax.annotation.Nullable;

import metal.ougabouga.world.entity.OugaBougaEntities;
import metal.ougabouga.world.item.OugaBougaItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeableHorseArmorItem;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class StickLongEntity extends AbstractArrow {
	private static final EntityDataAccessor<Integer> COLOR = SynchedEntityData.defineId(StickLongEntity.class,
			EntityDataSerializers.INT);

	private ItemStack StickLongItem = new ItemStack(OugaBougaItems.STICK_LONG.get());
	private boolean dealtDamage;
	public int clientSideReturnTridentTickCount;

	// Ctrl + Shift + F
	// Fake constructor that is only used by registry
	public StickLongEntity(EntityType<? extends StickLongEntity> pEntityType, Level pLevel) {
		super(pEntityType, pLevel);
	}

	public StickLongEntity(Level pLevel, LivingEntity pShooter, ItemStack pStack) {
		super(OugaBougaEntities.STICKLONG_ENTITY.get(), pShooter, pLevel);
		this.StickLongItem = pStack.copy();

		this.entityData.set(COLOR, ((DyeableLeatherItem) StickLongItem.getItem()).getColor(StickLongItem));

		// System.out.println(pLevel.isClientSide() +" "+
		// pStack.getTagElement("display"));
	}

	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(COLOR, 0);
	}

	public int getItemStackColor() {
		return this.entityData.get(COLOR);
	}

	public ItemStack getPickupItem() {
		return this.StickLongItem.copy();
	}

	/**
	 * Gets the EntityHitResult representing the entity hit
	 */
	@Nullable
	protected EntityHitResult findHitEntity(Vec3 pStartVec, Vec3 pEndVec) {
		return this.dealtDamage ? null : super.findHitEntity(pStartVec, pEndVec);
	}

	/**
	 * Called when the arrow hits an entity
	 */
	protected void onHitEntity(EntityHitResult pResult) {
		Entity entity = pResult.getEntity();
		float f = 8.0F;
		if (entity instanceof LivingEntity livingentity) {
			f += EnchantmentHelper.getDamageBonus(this.StickLongItem, livingentity.getMobType());
		}

		Entity entity1 = this.getOwner();
		DamageSource damagesource = this.damageSources().trident(this, (Entity) (entity1 == null ? this : entity1));
		this.dealtDamage = true;
		SoundEvent soundevent = SoundEvents.TRIDENT_HIT;
		if (entity.hurt(damagesource, f)) {
			if (entity.getType() == EntityType.ENDERMAN) {
				return;
			}

			if (entity instanceof LivingEntity) {
				LivingEntity livingentity1 = (LivingEntity) entity;
				if (entity1 instanceof LivingEntity) {
					EnchantmentHelper.doPostHurtEffects(livingentity1, entity1);
					EnchantmentHelper.doPostDamageEffects((LivingEntity) entity1, livingentity1);
				}

				this.doPostHurtEffects(livingentity1);
			}
		}

		this.setDeltaMovement(this.getDeltaMovement().multiply(-0.01D, -0.1D, -0.01D));
		float f1 = 1.0F;

		this.playSound(soundevent, f1, 1.0F);
	}

	protected boolean tryPickup(Player pPlayer) {
		return super.tryPickup(pPlayer)
				|| this.isNoPhysics() && this.ownedBy(pPlayer) && pPlayer.getInventory().add(this.getPickupItem());
	}

	/**
	 * The sound made when an entity is hit by this projectile
	 */
	protected SoundEvent getDefaultHitGroundSoundEvent() {
		return SoundEvents.TRIDENT_HIT_GROUND;
	}

	/**
	 * Called by a player entity when they collide with an entity
	 */
	public void playerTouch(Player pEntity) {
		if (this.ownedBy(pEntity) || this.getOwner() == null) {
			super.playerTouch(pEntity);
		}

	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	public void readAdditionalSaveData(CompoundTag pCompound) {
		super.readAdditionalSaveData(pCompound);
		if (pCompound.contains("StickLong", 10)) {
			this.StickLongItem = ItemStack.of(pCompound.getCompound("StickLong"));
		}
	}

	public void addAdditionalSaveData(CompoundTag pCompound) {
		super.addAdditionalSaveData(pCompound);
		pCompound.put("StickLong", this.StickLongItem.save(new CompoundTag()));
	}

	public boolean shouldRender(double pX, double pY, double pZ) {
		return true;
	}
}
