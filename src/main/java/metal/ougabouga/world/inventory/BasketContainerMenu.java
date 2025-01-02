package metal.ougabouga.world.inventory;

import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class BasketContainerMenu extends AbstractContainerMenu {
	private final Container basketContainer;
	
	public BasketContainerMenu(int id, Inventory pPlayerInventory) {
		this(id, pPlayerInventory, new SimpleContainer(1));
	}
	
	public BasketContainerMenu(int id, Inventory pPlayerInventory, Container basket) {
		super(OugaBougaMenuTypes.BASKET.get(), id);
		
		this.basketContainer = basket;
		this.basketContainer.startOpen(pPlayerInventory.player);
		
		this.addSlot(new Slot(this.basketContainer, 0, 84, 5));

		for (int l = 0; l < 3; ++l) {
			for (int k = 0; k < 9; ++k) {
				this.addSlot(new Slot(pPlayerInventory, k + l * 9 + 9, 12 + k * 18, l * 18 + 63));
			}
		}

		for (int i1 = 0; i1 < 9; ++i1) {
			this.addSlot(new Slot(pPlayerInventory, i1, 12 + i1 * 18, 121));
		}
	}
	
	@Override
	public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.slots.get(pIndex);
		
		if (slot != null && slot.hasItem()) {
			ItemStack itemstack1 = slot.getItem();
			itemstack = itemstack1.copy();
			
			if (pIndex < this.basketContainer.getContainerSize()) {
				if (!this.moveItemStackTo(itemstack1, this.basketContainer.getContainerSize(), this.slots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.moveItemStackTo(itemstack1, 0, this.basketContainer.getContainerSize(), false)) {
				return ItemStack.EMPTY;
			}

			if (itemstack1.isEmpty()) {
				slot.setByPlayer(ItemStack.EMPTY);
			} else {
				slot.setChanged();
			}
		}

		return itemstack;
	}
	
	@Override
	public boolean stillValid(Player pPlayer) {
		return basketContainer.stillValid(pPlayer);
	}
	
	@Override
	public void removed(Player pPlayer) {
		super.removed(pPlayer);
		
		this.basketContainer.stopOpen(pPlayer);
	}
}
