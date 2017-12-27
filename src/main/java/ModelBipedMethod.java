import net.labymod.addons.betterhat.model.*;
import net.labymod.addons.betterhat.*;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;


public class ModelBipedMethod
{
    public static void onRender(final Object objModel, final Object objEntity, final float var6, final float scale) {
        final Entity entity = (Entity)objEntity;
        final ModelBiped modelBiped = (ModelBiped)objModel;
        if (modelBiped.bipedHeadwear instanceof ModelRendererUV) {
            GlStateManager.resetColor();
            GlStateManager.enableDepth();
            if (entity.isSneaking()) {
                GlStateManager.translate(0.0f, 0.2f, 0.0f);
            }
            ((ModelRendererUV)modelBiped.bipedHeadwear).isHidden = false;
            ((ModelRendererUV)modelBiped.bipedHeadwear).applyRotation(modelBiped.bipedHead);
            ((ModelRendererUV)modelBiped.bipedHeadwear).renderBetterHat(scale);
            ((ModelRendererUV)modelBiped.bipedHeadwear).isHidden = true;
            if (entity.isSneaking()) {
                GlStateManager.translate(0.0f, -0.2f, 0.0f);
            }
        } else {
            final ModelRendererUV model = new ModelRendererUV((ModelBase)modelBiped, 32, 0);
            BetterHat.getInstance().initModel(model, 0.001f);
            model.setRotationPoint(0.0f, 0.0f + var6, 0.0f);
            modelBiped.bipedHeadwear = model;
        }
    }
}
