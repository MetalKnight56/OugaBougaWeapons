package metal.ougabouga.world.block.entity;

import java.util.List;

import metal.ougabouga.world.inventory.BasketContainerMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class BasketBlockEntity extends BaseContainerBlockEntity {
	private ItemStack itemstack = ItemStack.EMPTY;
	
	public BasketBlockEntity(BlockPos pPos, BlockState pBlockState) {
		super(OugaBougaBlockEntities.BASKET.get(), pPos, pBlockState);
	}
	
	@Override
	public void load(CompoundTag pTag) {
		super.load(pTag);
		
		if (pTag.contains("Item")) {
			this.itemstack = ItemStack.of(pTag.getCompound("Item"));
		}
	}
	
	@Override
	protected void saveAdditional(CompoundTag pTag) {
		super.saveAdditional(pTag);
		
		if (this.itemstack != ItemStack.EMPTY) {
			CompoundTag compoundtag = new CompoundTag();
            this.itemstack.save(compoundtag);
			pTag.put("Item", compoundtag);
		}
	}
	
	@Override
	public int getContainerSize() {
		return 1;
	}
	
	@Override
	public boolean isEmpty() {
		return this.itemstack.isEmpty();
	}
	
	@Override
	public ItemStack getItem(int pSlot) {
		return this.itemstack;
	}
	
	public static ItemStack removeItem(List<ItemStack> pStacks, int pIndex, int pAmount) {
	      return pIndex >= 0 && pIndex < pStacks.size() && !pStacks.get(pIndex).isEmpty() && pAmount > 0 ? pStacks.get(pIndex).split(pAmount) : ItemStack.EMPTY;
	   }
	
	@Override
	public ItemStack removeItem(int pSlot, int pAmount) {
		if (!this.itemstack.isEmpty() && pAmount > 0) {
			ItemStack stack = this.itemstack.split(pAmount);
			
			if (this.itemstack.getItem() != stack.getItem()) {
				this.markUpdated();
			}
			
			return stack;
		}
		
		return ItemStack.EMPTY;
	}

	@Override
	public ItemStack removeItemNoUpdate(int pSlot) {
		ItemStack prevItemStack = this.itemstack;
		this.itemstack = ItemStack.EMPTY;
		
		return prevItemStack;
	}

	@Override
	public void setItem(int pSlot, ItemStack pStack) {
		this.itemstack = pStack;
		this.markUpdated();
	}
	
	@Override
	public boolean stillValid(Player pPlayer) {
		return Container.stillValidBlockEntity(this, pPlayer);
	}
	
	@Override
	public void clearContent() {
		this.itemstack = ItemStack.EMPTY;
		this.markUpdated();
	}

	@Override
	protected Component getDefaultName() {
		return Component.translatable("container.basket");
	}
	
	@Override
	protected AbstractContainerMenu createMenu(int pId, Inventory pPlayer) {
		return new BasketContainerMenu(pId, pPlayer, this);
	}
	
	private void markUpdated() {
		this.setChanged();
		this.getLevel().sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 3);
	}
	
	@Override
	public ClientboundBlockEntityDataPacket getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}
	
	/**
	 * Get an NBT compound to sync to the client with SPacketChunkData, used for
	 * initial loading of the chunk or when many blocks change at once. This
	 * compound comes back to you clientside in {@link handleUpdateTag}
	 */
	@Override
	public CompoundTag getUpdateTag() {
		CompoundTag itemtag = new CompoundTag();
		this.itemstack.save(itemtag);
		
		CompoundTag compoundtag = new CompoundTag();
		compoundtag.put("Item", itemtag);
		
		return compoundtag;
	}
}
