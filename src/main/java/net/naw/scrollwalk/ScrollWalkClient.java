package net.naw.scrollwalk;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

// [Hiveko port] The walking speed key is a normal key binding (Options -> Controls -> Key Binds), Left Alt by
// default, instead of Left / Right Alt written into the code, so it can be moved when Alt is used elsewhere.
public class ScrollWalkClient implements ClientModInitializer {
    public static final KeyBinding.Category CATEGORY = KeyBinding.Category.create(Identifier.of("scrollwalk", "main"));
    public static KeyBinding speedKey;

    @Override
    public void onInitializeClient() {
        speedKey = KeyBindingHelper.registerKeyBinding(
                new KeyBinding("key.scrollwalk.speed", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_LEFT_ALT, CATEGORY));
    }

    /**
     * True while the walking speed key is held: a keyboard key is read from the window the scroll came from,
     * a mouse button through Minecraft.
     */
    public static boolean isSpeedKeyDown(long window) {
        if (speedKey == null || speedKey.isUnbound()) return false;
        InputUtil.Key key = KeyBindingHelper.getBoundKeyOf(speedKey);
        if (key.getCategory() == InputUtil.Type.KEYSYM) return GLFW.glfwGetKey(window, key.getCode()) == GLFW.GLFW_PRESS;
        return speedKey.isPressed();
    }
}
