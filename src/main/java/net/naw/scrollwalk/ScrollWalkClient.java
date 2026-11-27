package net.naw.scrollwalk;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;

// [Hiveko port] The walking speed key is a normal key binding (Options -> Controls -> Key Binds), Left Alt by
// default, instead of Left / Right Alt written into the code, so it can be moved when Alt is used elsewhere.
public class ScrollWalkClient implements ClientModInitializer {
    /** 1.21.1 key categories are translation keys (the same text as 26.x's scrollwalk:main category). */
    public static final String CATEGORY = "key.category.scrollwalk.main";
    public static KeyMapping speedKey;

    @Override
    public void onInitializeClient() {
        speedKey = KeyBindingHelper.registerKeyBinding(
                new KeyMapping("key.scrollwalk.speed", InputConstants.Type.KEYSYM, InputConstants.KEY_LALT, CATEGORY));
    }

    /**
     * True while the walking speed key is held: a keyboard key is read from the window the scroll came from,
     * a mouse button through Minecraft.
     */
    public static boolean isSpeedKeyDown(long window) {
        if (speedKey == null || speedKey.isUnbound()) return false;
        InputConstants.Key key = KeyBindingHelper.getBoundKeyOf(speedKey);
        if (key.getType() == InputConstants.Type.KEYSYM) return InputConstants.isKeyDown(window, key.getValue());
        return speedKey.isDown();
    }
}
