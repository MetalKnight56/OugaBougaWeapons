package metal.ougabouga.world.capabilities.provider;

import metal.ougabouga.api.utils.ModUtils;
import metal.ougabouga.main.OugaBougaWeapons;
import metal.ougabouga.world.item.OugaBougaItems;
import metal.ougabouga.world.item.StickLongItem;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class OugaItemModelProvider extends ItemModelProvider {

	public OugaItemModelProvider(PackOutput output, String modid, ExistingFileHelper existingFileHelper) {
		super(output, OugaBougaWeapons.MOD_ID, existingFileHelper);
		// TODO Auto-generated constructor stub
	}
	
	private void StickItem() {
		
			RegistryObject<?> item = OugaBougaItems.STICK_LONG;
            String texture = ModUtils.modItemLoc("spears/stick_long").getPath();
            

            getBuilder(ModUtils.getPath(item))
                    .parent(new ModelFile.UncheckedModelFile("item/generated"))
                    .texture("layer0", texture);

            ItemModelBuilder throwing = getBuilder(ModUtils.getPath(item) + "_throwing")
                    .parent(new ModelFile.UncheckedModelFile("builtin/entity"))
                    .guiLight(BlockModel.GuiLight.FRONT)
                    .texture("particle", texture)
                    .transforms()
                    .transform(ItemDisplayContext.THIRD_PERSON_RIGHT_HAND).rotation(0, 90, 180).translation(8, -17, 9).end()
                    .transform(ItemDisplayContext.THIRD_PERSON_LEFT_HAND).rotation(0, 90, 180).translation(8, -17, -7).end()
                    .transform(ItemDisplayContext.FIRST_PERSON_RIGHT_HAND).rotation(0, -90, 25).translation(-3, 17, 1).end()
                    .transform(ItemDisplayContext.FIRST_PERSON_LEFT_HAND).rotation(0, 90, -25).translation(13, 17, 1).end()
                    .transform(ItemDisplayContext.GUI).rotation(15, -25, -5).translation(2, 3, 0).scale(0.65F).end()
                    .transform(ItemDisplayContext.FIXED).rotation(0, 180, 0).translation(-2, 4, -5).scale(0.5F).end()
                    .transform(ItemDisplayContext.GROUND).translation(4, 4, 2).scale(0.25F).end()
                    .end();

            getBuilder(ModUtils.getPath(item) + "_in_hand")
                    .parent(new ModelFile.UncheckedModelFile("builtin/entity"))
                    .guiLight(BlockModel.GuiLight.FRONT)
                    .texture("particle", texture)
                    .transforms()
                    .transform(ItemDisplayContext.THIRD_PERSON_RIGHT_HAND).rotation(0, 60, 0).translation(11, 17, -2).end()
                    .transform(ItemDisplayContext.THIRD_PERSON_LEFT_HAND).rotation(0, 60, 0).translation(3, 17, 12).end()
                    .transform(ItemDisplayContext.FIRST_PERSON_RIGHT_HAND).rotation(0, -90, 25).translation(-3, 17, 1).end()
                    .transform(ItemDisplayContext.FIRST_PERSON_LEFT_HAND).rotation(0, 90, -25).translation(13, 17, 1).end()
                    .transform(ItemDisplayContext.GUI).rotation(15, -25, -5).translation(2, 3, 0).scale(0.65F).end()
                    .transform(ItemDisplayContext.FIXED).rotation(0, 180, 0).translation(-2, 4, -5).scale(0.5F).end()
                    .transform(ItemDisplayContext.GROUND).translation(4, 4, 2).scale(0.25F).end()
                    .end()
                    .override().predicate(StickLongItem.THROWING_PREDICATE, 1).model(throwing).end();
        }

	@Override
	protected void registerModels() {
		// TODO Auto-generated method stub
		
	};
	}
