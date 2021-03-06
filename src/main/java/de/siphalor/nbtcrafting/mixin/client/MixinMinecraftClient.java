package de.siphalor.nbtcrafting.mixin.client;

import de.siphalor.nbtcrafting.client.NbtCraftingClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MixinMinecraftClient {
	@Inject(method = "disconnect(Lnet/minecraft/client/gui/screen/Screen;)V", at = @At("HEAD"))
	public void onDisconnect(Screen screen, CallbackInfo callbackInfo) {
		NbtCraftingClient.sentModPresent = false;
	}
}
