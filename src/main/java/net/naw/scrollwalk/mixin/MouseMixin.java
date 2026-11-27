package net.naw.scrollwalk.mixin;

import net.naw.scrollwalk.ScrollWalk;
import net.naw.scrollwalk.ScrollWalkClient;
import net.minecraft.client.Mouse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// Mixin into the Mouse class to intercept scroll input
@Mixin(Mouse.class)
public class MouseMixin {
    // Inject into onMouseScroll to detect scroll wheel movement
    @Inject(method = "onMouseScroll", at = @At("HEAD"), cancellable = true)
    private void onMouseScroll(long window, double horizontal, double vertical, CallbackInfo ci) {

        // 1. Check if the mod is disabled in config
        if (ScrollWalk.config == null || !ScrollWalk.config.modEnabled) {
            // Reset to default speed if disabled
            if (ScrollWalk.currentSpeed != 0.1) {
                ScrollWalk.currentSpeed = 0.1;
            }
            return;
        }

        // While flying, leave Alt + scroll to the fly speed mods (Creative Fly changes the fly speed
        // through the hotbar scroll); walking speed does not matter in the air
        var player = net.minecraft.client.MinecraftClient.getInstance().player;
        if (player != null && player.getAbilities().flying) {
            return;
        }

        // 2. Check if the walking speed key is held ([Hiveko port] a key binding, Left Alt by default,
        // read from the window the scroll came from)
        boolean isSpeedKeyDown = ScrollWalkClient.isSpeedKeyDown(window);

        // Only process scroll if the walking speed key is being held
        if (isSpeedKeyDown) {
            double step = ScrollWalk.config.scrollStep;
            double max = ScrollWalk.config.maxSpeed;
            double min = ScrollWalk.config.minSpeed;

            // Increase speed on scroll up, decrease on scroll down
            if (vertical > 0) {
                ScrollWalk.currentSpeed += step;
            } else if (vertical < 0) {
                ScrollWalk.currentSpeed -= step;
            }

            // Keep the math clean by rounding to 2 decimal places to avoid floating point errors
            ScrollWalk.currentSpeed = Math.round(ScrollWalk.currentSpeed * 100.0) / 100.0;

            // Clamp the speed to ensure it stays within the defined config limits
            if (ScrollWalk.currentSpeed > max) ScrollWalk.currentSpeed = max;
            if (ScrollWalk.currentSpeed < min) ScrollWalk.currentSpeed = min;

            // Cancel the scroll event so the inventory doesn't scroll while changing speed
            ci.cancel();
        }
    }
}