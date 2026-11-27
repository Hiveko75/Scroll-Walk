package net.naw.scrollwalk;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;

// [Hiveko port] The walking speed key is a normal key binding (Options -> Controls -> Key Binds), Left Alt by
// default, instead of Left / Right Alt written into the code, so it can be moved when Alt is used elsewhere.
public class ScrollWalkClient implements ClientModInitializer {
    public static final KeyMapping.Category CATEGORY =
            KeyMapping.Category.register(Identifier.fromNamespaceAndPath("scrollwalk", "main"));
    public static KeyMapping speedKey;

    @Override
    public void onInitializeClient() {
        speedKey = KeyMappingHelper.registerKeyMapping(
                new KeyMapping("key.scrollwalk.speed", InputConstants.Type.KEYBOARD, InputConstants.KEY_LALT, CATEGORY));
    }

    /** True while the walking speed key is held (a keyboard key is asked directly, a mouse button through Minecraft). */
    public static boolean isSpeedKeyDown() {
        if (speedKey == null || speedKey.isUnbound()) return false;
        InputConstants.Key key = KeyMappingHelper.getBoundKeyOf(speedKey);
        if (key.getType() == InputConstants.Type.KEYBOARD) return InputConstants.isKeyDown(key.getValue());
        return speedKey.isDown();
    }
}
