package metal.ougabouga.client.gui.screens.inventory;

import metal.ougabouga.main.OugaBougaWeapons;
import metal.ougabouga.world.inventory.BasketContainerMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BasketContainerScreen extends AbstractContainerScreen<BasketContainerMenu> {
	public static final ResourceLocation BASKET_INVENTORY_LOCATION = new ResourceLocation(OugaBougaWeapons.MOD_ID, "textures/gui/basket_container.png");
	
	public BasketContainerScreen(BasketContainerMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
		super(pMenu, pPlayerInventory, pTitle);
		
		this.imageWidth = 184;
		this.imageHeight = 145;
		this.inventoryLabelX = 11;
		this.inventoryLabelY = this.imageHeight - 92;
	}
	
	@Override
	public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
		this.renderBackground(pGuiGraphics);
		super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
		this.renderTooltip(pGuiGraphics, pMouseX, pMouseY);
	}
	
	@Override
	protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
		int i = (this.width - this.imageWidth) / 2;
		int j = (this.height - this.imageHeight) / 2;
		
		pGuiGraphics.blit(BASKET_INVENTORY_LOCATION, i, j, 0, 0, this.imageWidth, this.imageHeight);
	}
	
	@Override
	protected void renderLabels(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY) {
		pGuiGraphics.drawString(this.font, this.playerInventoryTitle, this.inventoryLabelX, this.inventoryLabelY, 4210752, false);
	}
}
