package metal.ougabouga.model.animations;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;

// Save this class in your mod and generate all required imports

/**
 * Made with Blockbench 4.9.1
 * Exported for Minecraft version 1.19 or later with Mojang mappings
 * @author Author
 */
public class rock_3d_animation {
	public static final AnimationDefinition THROW = AnimationDefinition.Builder.withLength(9.0F).looping()
			.addAnimation("bone", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(45.0F, 0.0F, 45.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.0F, KeyframeAnimations.degreeVec(90.0F, 0.0F, 90.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(2.0F, KeyframeAnimations.degreeVec(135.0F, 0.0F, 135.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(4.0F, KeyframeAnimations.degreeVec(180.0F, 0.0F, 180.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(6.0F, KeyframeAnimations.degreeVec(225.0F, 0.0F, 225.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(7.5F, KeyframeAnimations.degreeVec(270.0F, 0.0F, 270.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(8.0F, KeyframeAnimations.degreeVec(315.0F, 0.0F, 315.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(9.0F, KeyframeAnimations.degreeVec(360.0F, 0.0F, 360.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.build();
}