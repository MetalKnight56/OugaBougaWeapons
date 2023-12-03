package metal.ougabouga.world.item;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class ClubItem extends SwordItem {

    // Constructor for your custom club item
    public ClubItem(Tier tier, int attackDamage, float attackSpeed, Properties properties) {
        super(tier, attackDamage, attackSpeed, properties);
    }

    @Override
    public boolean canPerformAction(ItemStack stack, net.minecraftforge.common.ToolAction toolAction) {
    	return net.minecraftforge.common.ToolActions.DEFAULT_AXE_ACTIONS.contains(toolAction);
    }

    
    
    // Create a new method to get the ClubItem with custom properties
    public static ClubItem createCustomClub(Tier customTier, int customAttackDamage, float customAttackSpeed, int customDurability) {
        return new ClubItem(customTier, customAttackDamage, customAttackSpeed, new Properties()
                .durability(customDurability)
                // Add any additional properties here if needed
        );
    }
    
}






