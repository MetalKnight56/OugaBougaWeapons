package metal.ougabouga.world.item;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class ClubItem extends SwordItem {

    // Constructor for your custom club item
    public ClubItem(Tier tier, int attackDamage, float attackSpeed, Properties properties) {
        super(tier, attackDamage, attackSpeed, properties);
    }
    
    public boolean canAttackBlock(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer) {
        return !pPlayer.isCreative();
     }

    @Override
    public boolean canPerformAction(ItemStack stack, net.minecraftforge.common.ToolAction toolAction) {
    	return net.minecraftforge.common.ToolActions.DEFAULT_AXE_ACTIONS.contains(toolAction);
    }
    
    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        // Check if the enchantment can be applied to the item
        return false;
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        // Make the item not enchantable
        return false;
    }

    
    
    // Create a new method to get the ClubItem with custom properties
    public static ClubItem createCustomClub(Tier customTier, int customAttackDamage, float customAttackSpeed, int customDurability) {
        return new ClubItem(customTier, customAttackDamage, customAttackSpeed, new Properties()
                .durability(customDurability)
                // Add any additional properties here if needed
        );
    }
    
}






