package metal.ougabouga.world.entity;

import metal.ougabouga.main.OugaBougaWeapons;
import metal.ougabouga.world.entity.custom.RockProjectileEntity;
import metal.ougabouga.world.entity.custom.StickLongEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class OugaBougaEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, OugaBougaWeapons.MOD_ID);

    public static final RegistryObject<EntityType<RockProjectileEntity>> ROCK_PROJECTILE =
            ENTITY_TYPES.register("rock_projectile", () -> EntityType.Builder.<RockProjectileEntity>of(RockProjectileEntity::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f).build("rock_projectile"));
    
    public static final RegistryObject<EntityType<StickLongEntity>> STICKLONG_ENTITY =
            ENTITY_TYPES.register("sticklong_entity", () -> EntityType.Builder.<StickLongEntity>of(StickLongEntity::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f).build("sticklong_entity"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
