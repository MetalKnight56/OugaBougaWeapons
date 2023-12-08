package metal.ougabouga.world.entity.custom;

//import org.w3c.dom.Entity;

import metal.ougabouga.world.entity.OugaBougaEntities;
import metal.ougabouga.world.item.OugaBougaItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fml.ModList;
import yesman.epicfight.api.utils.LevelUtil;

public class RockProjectileEntity extends ThrowableItemProjectile {

	public RockProjectileEntity(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
		super(pEntityType, pLevel);
		
	}
	
	public final AnimationState ThrowAnimState = new AnimationState();
	private float yHeadRot;
	private float yBodyRot;
	SoundEvent soundEvent = this.getDefaultHitGroundSoundEvent();
	
    @Override
    public void tick() {
        super.tick();
        
        if (!this.onGround()) {
            this.setDeltaMovement(this.getDeltaMovement().add(0, -0.07, 0)); // Adjust the Y component as needed
        }

        if (this.level().isClientSide()) {
            // Check if the entity is alive before animating
            if (isAlive()) {
            	//System.out.println("blabla");
            	float animationSpeed = 0F;
            	this.ThrowAnimState.animateWhen(isAlive(), (int) (this.tickCount * animationSpeed));
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
		

        if (this.getOwner() instanceof LivingEntity) {
   
            this.playSound(this.getHitGroundSoundEvent(), 1.0F, 0.0F / (this.random.nextFloat() * 0.2F + 0.9F));
            
            
            // Notify clients about the rotation changes
            if (this.level().isClientSide()) {
                this.yHeadRot = this.getYRot();
                this.yBodyRot = this.getYRot();
            } else {
            	Vec3 PresultVec = new Vec3(pResult.getBlockPos().getX(), pResult.getBlockPos().getY() -1, pResult.getBlockPos().getZ());
            	
            	if (ModList.get().isLoaded("epicfight")) {
    				
                	boolean b = LevelUtil.circleSlamFracture(null, (ServerLevel)level(), pResult.getLocation().subtract(0, 1, 0), 1.1);
                	BlockState originBlockState = this.level().getBlockState(new BlockPos(
                			(int)pResult.getLocation().x, (int)pResult.getLocation().y, (int)pResult.getLocation().z));
                	
                	//System.out.println(originBlockState);
                	
                	//if (b) {
                	//	System.out.println("Hit " + PresultVec +" " + pResult.getLocation());
                	//}
                	
                }
            }
            
            
        }
        
     // Destroy the projectile
        this.discard();
        super.onHitBlock(pResult);
    }
	
	@Override
	protected void onHitEntity(EntityHitResult pResult) {
	      super.onHitEntity(pResult);
	        Entity entity = this.getOwner();
	        if (entity instanceof LivingEntity livingEntity){
	            pResult.getEntity().hurt(this.damageSources().mobProjectile(this, livingEntity), 6.0F);

	        // Use the damageTarget method
	        //    damageTarget(hitEntity, damageAmount);

	        // Drop the rock item
	            this.playSound(this.getHitGroundSoundEvent(), 1.0F, 0.0F / (this.random.nextFloat() * 0.2F + 0.9F));
	            
	            Item rockItem = OugaBougaItems.ROCK.get();
	            ItemStack rockStack = new ItemStack(rockItem);
	            this.spawnAtLocation(rockStack);

	        // Destroy the projectile
	            this.discard();
	            super.onHitEntity(pResult);
	    }
	}

	
	   protected SoundEvent getDefaultHitGroundSoundEvent() {
		      return SoundEvents.ANCIENT_DEBRIS_BREAK;
		   }

	   protected final SoundEvent getHitGroundSoundEvent() {
		      return this.soundEvent;
		   }
		
}
