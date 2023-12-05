package metal.ougabouga.world.entity.custom;

//import org.w3c.dom.Entity;

import metal.ougabouga.world.entity.OugaBougaEntities;
import metal.ougabouga.world.item.OugaBougaItems;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
//import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
//import net.minecraft.world.phys.EntityHitResult;
//import net.minecraft.world.damagesource.DamageSource;

public class RockProjectileEntity extends ThrowableItemProjectile {

	public RockProjectileEntity(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
		super(pEntityType, pLevel);
		// TODO Auto-generated constructor stub
		
	}
	public final AnimationState ThrowAnimState = new AnimationState();
	private float yHeadRot;
	private float yBodyRot;
	
    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide()) {
            // Check if the entity is alive before animating
            if (isAlive()) {
            	//System.out.println("blabla");
            	float animationSpeed = 10.0F;
            	this.ThrowAnimState.animateWhen(true, (int) (this.tickCount * animationSpeed));
            }
        }
    }
	
	public RockProjectileEntity(Level pLevel) {
        super(OugaBougaEntities.ROCK_PROJECTILE.get(), pLevel);
    }

    public RockProjectileEntity(Level pLevel, LivingEntity livingEntity) {
        super(OugaBougaEntities.ROCK_PROJECTILE.get(), livingEntity, pLevel);
    }

	@Override
	protected Item getDefaultItem() {
		return OugaBougaItems.ROCK.get();
	}
	
	protected void onHitBlock(BlockHitResult pResult) {
		if(this.level().isClientSide()) {
			this.level().broadcastEntityEvent(this, ((byte) 3));
		}
		
		super.onHitBlock(pResult);

        if (this.getOwner() instanceof LivingEntity) {
            LivingEntity thrower = (LivingEntity) this.getOwner();

            // Face the thrower
            double lookX = thrower.getX() - this.getX();
            double lookZ = thrower.getZ() - this.getZ();
            double angle = Math.atan2(lookZ, lookX);
            this.setYRot((float) Math.toDegrees(angle) + 90.0F);

            // Additional adjustments based on your requirements
            // For example, you may want to adjust the pitch as well
            this.setXRot(0.0F); // Set pitch to 0

            // Notify clients about the rotation changes
            if (this.level().isClientSide()) {
                this.yHeadRot = this.getYRot();
                this.yBodyRot = this.getYRot();
            }
        }
    }
	
	//@Override
	//protected void onHitEntity(EntityHitResult pResult) {
		//	 LivingEntity hitEntity = (LivingEntity) pResult.getEntity();

		 //    if (hitEntity instanceof LivingEntity) {
	        // Specify the damage amount you want to deal
	    	//  float damageAmount = 10.0F; // Adjust this value as needed

	        // Use the damageTarget method
	        //    damageTarget(hitEntity, damageAmount);

	        // Drop the rock item
	        //    Item rockItem = OugaBougaItems.ROCK.get();
	        //   ItemStack rockStack = new ItemStack(rockItem);
	        //    this.spawnAtLocation(rockStack);

	        // Destroy the projectile
	        //    this.discard();
	        //    super.onHitEntity(pResult);
	//    }
	//}



		
}
