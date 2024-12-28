package metal.ougabouga.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import cech12.unlitcampfire.config.ServerConfig;
import cech12.unlitcampfire.mixinaccess.ICampfireBlockEntityMixin;
import cech12.unlitcampfire.mixinaccess.ICampfireBlockMixin;
import metal.ougabouga.world.level.block.OugaBougaBlockStateProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.CampfireBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

@Mixin(value = CampfireBlockEntity.class)
public class MixinCampfireBlockEntity {
	private static final int MEDIUM_LARGE_BOUNDARY_TICK = 1000;
	private static final int SAMLL_MEDIUM_BOUNDARY_TICK = 500;
	
	@Inject(at = @At("HEAD"), method = "cookTick")
    private static void ougabouga_cookTick(Level level, BlockPos pos, BlockState state, CampfireBlockEntity blockEntity, CallbackInfo info) {
		ICampfireBlockEntityMixin mixinEntity = (ICampfireBlockEntityMixin) (BlockEntity) blockEntity;
		int litTime = mixinEntity.getLitTime();
		int remainTime;
		OugaBougaBlockStateProperties.LitLevel currentLitLevel = state.getValue(OugaBougaBlockStateProperties.LIT_LEVEL);
		
		if (((ICampfireBlockMixin)state.getBlock()).burnsInfinite(state)) {
			remainTime = Integer.MAX_VALUE;
		} else if (mixinEntity.isSoulCampfire()) {
			remainTime = ServerConfig.SOUL_CAMPFIRE_LIT_TIME.get() - litTime;
		} else {
			remainTime = ServerConfig.CAMPFIRE_LIT_TIME.get() - litTime;
		}
		
		if (remainTime > MEDIUM_LARGE_BOUNDARY_TICK) {
			if (currentLitLevel != OugaBougaBlockStateProperties.LitLevel.LARGE) {
				level.setBlockAndUpdate(pos, state.setValue(OugaBougaBlockStateProperties.LIT_LEVEL, OugaBougaBlockStateProperties.LitLevel.LARGE));
			}
		} else if (remainTime <= MEDIUM_LARGE_BOUNDARY_TICK && remainTime > SAMLL_MEDIUM_BOUNDARY_TICK) {
			if (currentLitLevel != OugaBougaBlockStateProperties.LitLevel.MEDIUM) {
				level.playSound((Player)null, pos, SoundEvents.FIRE_EXTINGUISH, SoundSource.NEUTRAL);
				level.setBlockAndUpdate(pos, state.setValue(OugaBougaBlockStateProperties.LIT_LEVEL, OugaBougaBlockStateProperties.LitLevel.MEDIUM));
			}
		} else if (remainTime <= SAMLL_MEDIUM_BOUNDARY_TICK) {
			if (currentLitLevel != OugaBougaBlockStateProperties.LitLevel.SMALL) {
				level.playSound((Player)null, pos, SoundEvents.FIRE_EXTINGUISH, SoundSource.NEUTRAL);
				level.setBlockAndUpdate(pos, state.setValue(OugaBougaBlockStateProperties.LIT_LEVEL, OugaBougaBlockStateProperties.LitLevel.SMALL));
			}
		}
    }
}
