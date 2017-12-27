package net.labymod.addons.betterhat.model;

import net.minecraft.client.model.*;
import net.labymod.addons.betterhat.*;
import net.labymod.addons.betterhat.util.*;
import net.minecraft.client.renderer.*;

public class ModelBoxUV extends ModelBox
{
    public final float textureWidth;
    public final float textureHeight;
    public final float field_78252_a;
    public final float field_78250_b;
    public final float field_78251_c;
    public final float field_78248_d;
    public final float field_78249_e;
    public final float field_78246_f;
    public String boxName;
    public boolean mirror;
    public PositionTextureVertex[] vertexPositions;
    public QuadData[] quadDataList;
    public TexturedQuad[] quadList;
    public static final int[][] vectorIndex;

    public static ModelBoxUV addBox(final ModelRendererUV renderer, final float x, final float y, final float z, final int w, final int h, final int d, final float add) {
        final int offsetX = ReflectionHelper.getPrivateValue(ModelRenderer.class, renderer, BetterHat.fTextureOffsetX);
        final int offsetY = ReflectionHelper.getPrivateValue(ModelRenderer.class, renderer, BetterHat.fTextureOffsetY);
        final ModelBoxUV box = new ModelBoxUV(renderer, offsetX, offsetY, x, y, z, w, h, d, add);
        renderer.cubeList.add(box);
        return box;
    }

    public ModelBoxUV(final ModelRendererUV modelRenderer, final int textureX, final int textureY, float x1, float y1, float z1, final int w, final int h, final int d, final float add) {
        super((ModelRenderer)modelRenderer, textureX, textureY, x1, y1, z1, w, h, d, add);
        this.textureWidth = modelRenderer.textureWidth;
        this.textureHeight = modelRenderer.textureHeight;
        this.field_78252_a = x1;
        this.field_78250_b = y1;
        this.field_78251_c = z1;
        this.field_78248_d = x1 + w;
        this.field_78249_e = y1 + h;
        this.field_78246_f = z1 + d;
        this.mirror = modelRenderer.mirror;
        this.vertexPositions = new PositionTextureVertex[8];
        this.quadDataList = new QuadData[6];
        this.quadList = null;
        float x2 = x1 + w;
        float y2 = y1 + h;
        float z2 = z1 + d;
        x1 -= add;
        y1 -= add;
        z1 -= add;
        x2 += add;
        y2 += add;
        z2 += add;
        if (this.mirror) {
            final float f7 = x2;
            x2 = x1;
            x1 = f7;
        }
        final PositionTextureVertex vertex0 = new PositionTextureVertex(x1, y1, z1, 0.0f, 0.0f);
        final PositionTextureVertex vertex2 = new PositionTextureVertex(x2, y1, z1, 0.0f, 8.0f);
        final PositionTextureVertex vertex3 = new PositionTextureVertex(x2, y2, z1, 8.0f, 8.0f);
        final PositionTextureVertex vertex4 = new PositionTextureVertex(x1, y2, z1, 8.0f, 0.0f);
        final PositionTextureVertex vertex5 = new PositionTextureVertex(x1, y1, z2, 0.0f, 0.0f);
        final PositionTextureVertex vertex6 = new PositionTextureVertex(x2, y1, z2, 0.0f, 8.0f);
        final PositionTextureVertex vertex7 = new PositionTextureVertex(x2, y2, z2, 8.0f, 8.0f);
        final PositionTextureVertex vertex8 = new PositionTextureVertex(x1, y2, z2, 8.0f, 0.0f);
        this.vertexPositions[0] = vertex0;
        this.vertexPositions[1] = vertex2;
        this.vertexPositions[2] = vertex3;
        this.vertexPositions[3] = vertex4;
        this.vertexPositions[4] = vertex5;
        this.vertexPositions[5] = vertex6;
        this.vertexPositions[6] = vertex7;
        this.vertexPositions[7] = vertex8;
        for (int i = 0; i < this.quadDataList.length; ++i) {
            this.quadDataList[i] = new QuadData(textureX, textureY, w, h, d, i);
        }
    }

    public ModelBoxUV setSideUV(final int side, final int textureX, final int textureY) {
        final QuadData data = this.quadDataList[side];
        final int deltaX = Math.abs(data.uvPos[2] - data.uvPos[0]);
        final int deltaY = Math.abs(data.uvPos[3] - data.uvPos[1]);
        data.uvPos[0] = textureX;
        data.uvPos[1] = textureY;
        data.uvPos[2] = textureX + deltaX;
        data.uvPos[3] = textureY + deltaY;
        if (side == 3) {
            data.uvPos[1] = textureY + deltaY;
            data.uvPos[3] = textureY;
        }
        return this;
    }

    public ModelBoxUV setAllUV(final int textureX, final int textureY) {
        for (int i = 0; i < this.quadDataList.length; ++i) {
            this.setSideUV(i, textureX, textureY);
        }
        return this;
    }

    public ModelBoxUV initQuads() {
        this.quadList = new TexturedQuad[6];
        for (int i = 0; i < this.quadList.length; ++i) {
            final QuadData data = this.quadDataList[i];
            this.quadList[i] = new TexturedQuad(this.getVertexes(i), data.uvPos[0], data.uvPos[1], data.uvPos[2], data.uvPos[3], this.textureWidth, this.textureHeight);
        }
        if (this.mirror) {
            for (int j1 = 0; j1 < this.quadList.length; ++j1) {
                this.quadList[j1].flipFace();
            }
        }
        this.quadDataList = null;
        return this;
    }

    public PositionTextureVertex[] getVertexes(final int side) {
        final int i0 = ModelBoxUV.vectorIndex[side][0];
        final int i2 = ModelBoxUV.vectorIndex[side][1];
        final int i3 = ModelBoxUV.vectorIndex[side][2];
        final int i4 = ModelBoxUV.vectorIndex[side][3];
        return new PositionTextureVertex[] { this.vertexPositions[i0], this.vertexPositions[i2], this.vertexPositions[i3], this.vertexPositions[i4] };
    }

    public void render(final Tessellator par1Tessellator, final float par2) {
        if (this.quadList == null) {
            this.initQuads();
        }
        for (int i = 0; i < this.quadList.length; ++i) {
            this.quadList[i].draw(par1Tessellator.getWorldRenderer(), par2);
        }
    }

    public ModelBox func_78244_a(final String name) {
        this.boxName = name;
        return this;
    }

    static {
        vectorIndex = new int[][] { { 5, 1, 2, 6 }, { 0, 4, 7, 3 }, { 5, 4, 0, 1 }, { 2, 3, 7, 6 }, { 1, 0, 3, 2 }, { 4, 5, 6, 7 } };
    }

    public static class QuadData
    {
        public int[] uvPos;

        public QuadData(final int textureX, final int textureY, final int w, final int h, final int d, final int side) {
            this.uvPos = new int[4];
            if (side == 0) {
                this.uvPos[0] = textureX + d + w;
                this.uvPos[1] = textureY + d;
                this.uvPos[2] = textureX + d + w + d;
                this.uvPos[3] = textureY + d + h;
            }
            else if (side == 1) {
                this.uvPos[0] = textureX;
                this.uvPos[1] = textureY + d;
                this.uvPos[2] = textureX + d;
                this.uvPos[3] = textureY + d + h;
            }
            else if (side == 2) {
                this.uvPos[0] = textureX + d;
                this.uvPos[1] = textureY;
                this.uvPos[2] = textureX + d + w;
                this.uvPos[3] = textureY + d;
            }
            else if (side == 3) {
                this.uvPos[0] = textureX + d + w;
                this.uvPos[1] = textureY + d;
                this.uvPos[2] = textureX + d + w + w;
                this.uvPos[3] = textureY;
            }
            else if (side == 4) {
                this.uvPos[0] = textureX + d;
                this.uvPos[1] = textureY + d;
                this.uvPos[2] = textureX + d + w;
                this.uvPos[3] = textureY + d + h;
            }
            else if (side == 5) {
                this.uvPos[0] = textureX + d + w + d;
                this.uvPos[1] = textureY + d;
                this.uvPos[2] = textureX + d + w + d + w;
                this.uvPos[3] = textureY + d + h;
            }
        }
    }
}
