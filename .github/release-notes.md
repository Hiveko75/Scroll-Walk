**Unofficial port** of [Scroll-Walk](https://github.com/Naw7k/Scroll-Walk) by Naw7k to Minecraft 26.3 (Fabric). Not affiliated with or supported by Naw7k — please don't report issues with this build upstream.

### Requirements
- Minecraft 26.3, Fabric Loader, Fabric API
- Cloth Config (26.3) and Mod Menu (26.3)

### Changes since 1.2+26.3-hiveko.2
- The walking speed key is **Caps Lock** by default (was Left Alt, which clashes with other mods that use Alt). A key you already set stays as it is.

### Changes in 1.2+26.3-hiveko.2
- The walking speed key is a normal key binding: **Walking speed (hold + mouse wheel)** in Options → Controls → Key Binds (category Scroll Walk), Caps Lock by default. The original reads Left and Right Alt directly, which cannot be changed and clashes with other mods that use Alt. A mouse button works too.
- English and Hungarian names for the key binding.

### Port
- Ported to Minecraft 26.3: input uses the new SDL-based layer (GLFW was removed in 26.3).
- Updated dependencies (Cloth Config 26.3.159, Mod Menu 21.0.0).

Client-side only.

License: MIT (same as Scroll-Walk).
