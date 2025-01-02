package metal.ougabouga.world.block;

import metal.ougabouga.main.OugaBougaWeapons;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class OugaBougaBlocks {
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, OugaBougaWeapons.MOD_ID);
	
	public static final RegistryObject<BasketBlock> BASKET = BLOCKS.register("basket", () -> new BasketBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOL).noOcclusion().isSuffocating(OugaBougaBlocks::never).isViewBlocking(OugaBougaBlocks::never).strength(0.1F).ignitedByLava()));
	
	private static boolean never(BlockState p_50806_, BlockGetter p_50807_, BlockPos p_50808_) {
		return false;
	}
	
	public static void register(IEventBus eventBus) {
		BLOCKS.register(eventBus);
    }
}
