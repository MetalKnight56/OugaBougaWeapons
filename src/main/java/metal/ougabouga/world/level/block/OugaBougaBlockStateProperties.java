package metal.ougabouga.world.level.block;

import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public class OugaBougaBlockStateProperties {
	public static final EnumProperty<LitLevel> LIT_LEVEL = EnumProperty.create("lit_level", LitLevel.class);
	
	public enum LitLevel implements StringRepresentable {
		SMALL("small"), MEDIUM("medium"), LARGE("large");
		
		public String name;
		
		LitLevel(String name) {
			this.name = name;
		}
		
		@Override
		public String getSerializedName() {
			return this.name;
		}
	}
}
