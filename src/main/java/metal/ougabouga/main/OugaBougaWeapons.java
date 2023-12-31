package metal.ougabouga.main;

import com.mojang.logging.LogUtils;

import metal.ougabouga.client.renderer.entity.RockProjectileRenderer;
import metal.ougabouga.world.entity.OugaBougaEntities;
import metal.ougabouga.world.item.OugaBougaCreativeModeTabs;
import metal.ougabouga.world.item.OugaBougaItems;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(OugaBougaWeapons.MOD_ID)
public class OugaBougaWeapons {
    public static final String MOD_ID = "ougabougaweapons";
    public static final Logger LOGGER = LogUtils.getLogger();

    public OugaBougaWeapons() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        
        OugaBougaCreativeModeTabs.register(modEventBus);
        OugaBougaItems.register(modEventBus);
        OugaBougaEntities.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
    	if (event.getTabKey() == CreativeModeTabs.COMBAT) {
    		event.accept(OugaBougaItems.CLUB);
    	}
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
        	
        	EntityRenderers.register(OugaBougaEntities.ROCK_PROJECTILE.get(), RockProjectileRenderer::new);

        }
    }
}