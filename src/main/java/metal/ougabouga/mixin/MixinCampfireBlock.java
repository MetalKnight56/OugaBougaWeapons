package metal.ougabouga.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import metal.ougabouga.world.level.block.OugaBougaBlockStateProperties;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

@Mixin(CampfireBlock.class)
public class MixinCampfireBlock {
	@Inject(at = @At("HEAD"), method = "createBlockStateDefinition")
    protected void ougabouga_createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> stateBuilder, CallbackInfo info) {
        stateBuilder.add(OugaBougaBlockStateProperties.LIT_LEVEL);
    }
}
