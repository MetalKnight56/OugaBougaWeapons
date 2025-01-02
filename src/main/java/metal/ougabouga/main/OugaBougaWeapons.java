package metal.ougabouga.main;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import metal.ougabouga.client.gui.screens.inventory.BasketContainerScreen;
import metal.ougabouga.client.renderer.entity.RockProjectileRenderer;
import metal.ougabouga.client.renderer.item.OugaBougaItemProperties;
import metal.ougabouga.world.block.OugaBougaBlocks;
import metal.ougabouga.world.block.entity.OugaBougaBlockEntities;
import metal.ougabouga.world.entity.OugaBougaEntities;
import metal.ougabouga.world.inventory.OugaBougaMenuTypes;
import metal.ougabouga.world.item.OugaBougaCreativeModeTabs;
import metal.ougabouga.world.item.OugaBougaItems;
import metal.ougabouga.world.item.StickLongItem;
import metal.ougabouga.world.item.crafting.OugaBougaRecipes;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.item.ItemProperties;
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

// The value here should match an entry in the META-INF/mods.toml file
@Mod(OugaBougaWeapons.MOD_ID)
public class OugaBougaWeapons {
    public static final String MOD_ID = "ougabougaweapons";
    public static final Logger LOGGER = LogUtils.getLogger();

    public OugaBougaWeapons() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        
        OugaBougaCreativeModeTabs.register(modEventBus);
        OugaBougaBlocks.register(modEventBus);
        OugaBougaItems.register(modEventBus);
        OugaBougaBlockEntities.register(modEventBus);
        OugaBougaEntities.register(modEventBus);
        OugaBougaRecipes.register(modEventBus);
        OugaBougaMenuTypes.register(modEventBus);
        
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
        	ItemProperties.register(OugaBougaItems.STICK_LONG.get(), StickLongItem.THROWING_PREDICATE,
                    (stack, level, entity, seed) -> entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F);

        	//Register item properties
        	OugaBougaItemProperties.registerProperties();
        	MenuScreens.register(OugaBougaMenuTypes.BASKET.get(), BasketContainerScreen::new);
        }
    }
}