package metal.ougabouga.world.item;

import metal.ougabouga.main.OugaBougaWeapons;

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
    public static final RegistryObject<Item> ROCK = ITEMS.register("rock", () ->  new Item(new Item.Properties()));
    
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
    
}


