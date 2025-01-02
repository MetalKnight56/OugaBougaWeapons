package metal.ougabouga.world.block.entity;

import com.google.common.collect.ImmutableSet;

import metal.ougabouga.main.OugaBougaWeapons;
import metal.ougabouga.world.block.OugaBougaBlocks;
import net.minecraft.Util;
import net.minecraft.util.datafix.fixes.References;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class OugaBougaBlockEntities {
	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, OugaBougaWeapons.MOD_ID);
	
	public static final RegistryObject<BlockEntityType<BasketBlockEntity>> BASKET = BLOCK_ENTITIES.register("basket", () -> new BlockEntityType<>(BasketBlockEntity::new, ImmutableSet.of(OugaBougaBlocks.BASKET.get()), Util.fetchChoiceType(References.BLOCK_ENTITY, "basket")));
	
	public static void register(IEventBus eventBus) {
		BLOCK_ENTITIES.register(eventBus);
    }
}
