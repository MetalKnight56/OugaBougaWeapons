package metal.ougabouga.world.entity.custom;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class RockProjectile extends ThrowableItemProjectile {

	public RockProjectile(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
		super(pEntityType, pLevel);
		// TODO Auto-generated constructor stub
	}
	
	public RockProjectile(Level pLevel) {
		super(, pLevel);
		// TODO Auto-generated constructor stub
	}
	
	public RockProjectile(Level pLevel, LivingEntity livingEntity) {
		super(livingEntity, pLevel);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Item getDefaultItem() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
