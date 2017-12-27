package net.labymod.addons.betterhat;

import net.labymod.addons.betterhat.model.ModelBoxUV;
import net.labymod.addons.betterhat.model.ModelRendererUV;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import static net.labymod.addons.betterhat.Reference.MODID;
import static net.labymod.addons.betterhat.Reference.VERSION;

@Mod(modid = MODID, version = VERSION, useMetadata = true)
public class BetterHat {

    @Mod.Instance(MODID)
    private static BetterHat instance;

    public static final String[] fTextureOffsetX;
    public static final String[] fTextureOffsetY;

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
    }

    public static BetterHat getInstance() {
        return instance;
    }

    public void initModel(final ModelRendererUV model, final float size) {
        ModelBoxUV box = null;
        final float scale = 1.135f;
        for (int x = -4; x < 4; ++x) {
            for (int z = -4; z < 4; ++z) {
                box = ModelBoxUV.addBox(model, x * 1.135f, -8.5225f, z * 1.135f, 1, 1, 1, size + 0.07f);
                box.setAllUV(44 + x, 3 - z);
                box = ModelBoxUV.addBox(model, x * 1.135f, -0.5575f, z * 1.135f, 1, 1, 1, size + 0.07f);
                box.setAllUV(52 + x, 3 - z);
            }
        }
        for (int x = -4; x < 4; ++x) {
            for (int y = -8; y < 0; ++y) {
                box = ModelBoxUV.addBox(model, x * 1.135f, (y + 0.5f) * 1.135f, -4.55f, 1, 1, 1, size + 0.07f);
                box.setAllUV(44 + x, 16 + y);
                box = ModelBoxUV.addBox(model, x * 1.135f, (y + 0.5f) * 1.135f, 3.415f, 1, 1, 1, size + 0.07f);
                box.setAllUV(60 + x, 16 + y);
            }
        }
        for (int z2 = -3; z2 < 4; ++z2) {
            for (int y = -8; y < 0; ++y) {
                box = ModelBoxUV.addBox(model, -4.55f, (y + 0.5f) * 1.135f, z2 * 1.135f, 1, 1, 1, size + 0.07f);
                box.setAllUV(36 - z2 - 1, 16 + y);
                box = ModelBoxUV.addBox(model, 3.415f, (y + 0.5f) * 1.135f, z2 * 1.135f, 1, 1, 1, size + 0.07f);
                box.setAllUV(52 + z2, 16 + y);
            }
        }
        box.initQuads();
    }
    
    static {
        fTextureOffsetX = new String[] { "field_78803_o", "r", "textureOffsetX" };
        fTextureOffsetY = new String[] { "field_78813_p", "s", "textureOffsetY" };
    }
}
