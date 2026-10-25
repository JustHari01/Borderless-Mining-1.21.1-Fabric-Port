package link.infra.borderlessmining.mixin;

import net.minecraft.client.gui.screen.option.VideoOptionsScreen;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * This Mixin fixes a bug in Keyboard that causes video mode changes to not be applied when pressing Esc.
 * See: https://bugs.mojang.com/browse/MC-175437
 */
@Mixin(VideoOptionsScreen.class)
public abstract class VideoModeFixMixin {
	@Inject(at = @At("HEAD"), method = "removed()V")
	private void borderlessmining$screenRemoved(CallbackInfo ci) {
		MinecraftClient client = MinecraftClient.getInstance();
		if (client != null) {
			client.getWindow().applyFullscreenVideoMode();
		}
	}
}
