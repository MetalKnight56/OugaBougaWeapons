package metal.ougabouga.world.item;

import metal.ougabouga.main.OugaBougaWeapons;
import metal.ougabouga.world.block.OugaBougaBlocks;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class OugaBougaItems {
    public static final DeferredRegister<Item> ITEMS = 
            DeferredRegister.create(ForgeRegistries.ITEMS, OugaBougaWeapons.MOD_ID);
    
    public static final RegistryObject<Item> CLUB = ITEMS.register("club", () ->  new ClubItem(Tiers.WOOD, 1, -2.6f, new Item.Properties().durability(200)));
    public static final RegistryObject<Item> ROCK = ITEMS.register("rock", () ->  new RockItem(new Item.Properties() .stacksTo(1)));
    public static final RegistryObject<Item> STICK_LONG = ITEMS.register("stick_long", () ->  new StickLongItem(new Item.Properties().durability(150)));
    public static final RegistryObject<Item> BAMBOO_SPEAR = ITEMS.register("bamboo_spear", () ->  new StickLongItem(new Item.Properties().durability(150)));
    public static final RegistryObject<Item> BASKET = ITEMS.register("basket", () ->  new BlockItem(OugaBougaBlocks.BASKET.get(), new Item.Properties()));
    
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
    
}


