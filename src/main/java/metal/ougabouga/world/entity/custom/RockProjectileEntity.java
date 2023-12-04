package metal.ougabouga.world.entity.custom;

import metal.ougabouga.world.entity.OugaBougaEntities;
import metal.ougabouga.world.item.OugaBougaItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;

public class RockProjectileEntity extends ThrowableItemProjectile {

	public RockProjectileEntity(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
		super(pEntityType, pLevel);
		// TODO Auto-generated constructor stub
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
	}
	
}
