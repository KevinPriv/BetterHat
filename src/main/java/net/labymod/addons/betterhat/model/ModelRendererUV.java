package net.labymod.addons.betterhat.model;

import java.util.*;
import com.google.common.collect.*;
import net.minecraft.client.model.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.*;

public class ModelRendererUV extends ModelRenderer
{
    public float textureWidth;
    public float textureHeight;
    public int textureOffsetX;
    public int textureOffsetY;
    public float rotationPointX;
    public float rotationPointY;
    public float rotationPointZ;
    public float rotateAngleX;
    public float rotateAngleY;
    public float rotateAngleZ;
    public boolean compiled;
    public int displayList;
    public boolean mirror;
    public List<ModelBox> cubeList;
    public List<ModelRenderer> childModels;
    public final String boxName;
    public ModelBase baseModel;
    public float offsetX;
    public float offsetY;
    public float offsetZ;

    public ModelRendererUV(final ModelBase model, final String boxNameIn) {
        super(model, boxNameIn);
        this.textureWidth = 64.0f;
        this.textureHeight = 32.0f;
        this.showModel = true;
        this.cubeList = Lists.newArrayList();
        this.baseModel = model;
        model.boxList.add(this);
        this.boxName = boxNameIn;
        this.setTextureSize(model.textureWidth, model.textureHeight);
    }

    public ModelRendererUV(final ModelBase model) {
        this(model, null);
    }

    public ModelRendererUV(final ModelBase model, final int texOffX, final int texOffY) {
        this(model);
        this.setTextureOffset(texOffX, texOffY);
    }

    public void addChild(final ModelRenderer renderer) {
        if (this.childModels == null) {
            this.childModels = Lists.newArrayList();
        }
        this.childModels.add(renderer);
    }

    public ModelRenderer setTextureOffset(final int x, final int y) {
        this.textureOffsetX = x;
        this.textureOffsetY = y;
        return this;
    }

    public ModelRenderer addBox(String partName, final float offX, final float offY, final float offZ, final int width, final int height, final int depth) {
        partName = this.boxName + "." + partName;
        final TextureOffset textureoffset = this.baseModel.getTextureOffset(partName);
        this.setTextureOffset(textureoffset.textureOffsetX, textureoffset.textureOffsetY);
        this.cubeList.add(new ModelBoxUV(this, this.textureOffsetX, this.textureOffsetY, offX, offY, offZ, width, height, depth, 0.0f).setBoxName(partName));
        return this;
    }

    public ModelRenderer addBox(final float offX, final float offY, final float offZ, final int width, final int height, final int depth) {
        this.cubeList.add(new ModelBoxUV(this, this.textureOffsetX, this.textureOffsetY, offX, offY, offZ, width, height, depth, 0.0f));
        return this;
    }

    public ModelRenderer addBox(final float p_178769_1_, final float p_178769_2_, final float p_178769_3_, final int p_178769_4_, final int p_178769_5_, final int p_178769_6_, final boolean p_178769_7_) {
        this.cubeList.add(new ModelBoxUV(this, this.textureOffsetX, this.textureOffsetY, p_178769_1_, p_178769_2_, p_178769_3_, p_178769_4_, p_178769_5_, p_178769_6_, 0.0f));
        return this;
    }

    public void addBox(final float p_78790_1_, final float p_78790_2_, final float p_78790_3_, final int width, final int height, final int depth, final float scaleFactor) {
        this.cubeList.add(new ModelBoxUV(this, this.textureOffsetX, this.textureOffsetY, p_78790_1_, p_78790_2_, p_78790_3_, width, height, depth, scaleFactor));
    }

    public void setRotationPoint(final float rotationPointXIn, final float rotationPointYIn, final float rotationPointZIn) {
        this.rotationPointX = rotationPointXIn;
        this.rotationPointY = rotationPointYIn;
        this.rotationPointZ = rotationPointZIn;
    }

    public void renderBetterHat(final float p_78791_1_) {
        if (!this.isHidden && this.showModel) {
            if (!this.compiled) {
                this.compileDisplayList(p_78791_1_);
            }
            GlStateManager.pushMatrix();
            GlStateManager.translate(this.rotationPointX * p_78791_1_, this.rotationPointY * p_78791_1_, this.rotationPointZ * p_78791_1_);
            if (this.rotateAngleY != 0.0f) {
                GlStateManager.rotate(this.rotateAngleY * 57.295776f, 0.0f, 1.0f, 0.0f);
            }
            if (this.rotateAngleX != 0.0f) {
                GlStateManager.rotate(this.rotateAngleX * 57.295776f, 1.0f, 0.0f, 0.0f);
            }
            if (this.rotateAngleZ != 0.0f) {
                GlStateManager.rotate(this.rotateAngleZ * 57.295776f, 0.0f, 0.0f, 1.0f);
            }
            GlStateManager.callList(this.displayList);
            GlStateManager.popMatrix();
        }
    }

    public void compileDisplayList(final float scale) {
        GL11.glNewList(this.displayList = GLAllocation.generateDisplayLists(1), 4864);
        for (int i = 0; i < this.cubeList.size(); ++i) {
            ((ModelBoxUV)this.cubeList.get(i)).render(Tessellator.getInstance(), scale);
        }
        GL11.glEndList();
        this.compiled = true;
    }

    public void applyRotation(final ModelRenderer source) {
        this.rotateAngleX = source.rotateAngleX;
        this.rotateAngleY = source.rotateAngleY;
        this.rotateAngleZ = source.rotateAngleZ;
        this.rotationPointX = source.rotationPointX;
        this.rotationPointY = source.rotationPointY;
        this.rotationPointZ = source.rotationPointZ;
    }

    public ModelRenderer setTextureSize(final int textureWidthIn, final int textureHeightIn) {
        this.textureWidth = textureWidthIn;
        this.textureHeight = textureHeightIn;
        return this;
    }
}
