// ======================================================================
// Project Name    : android_renderer
//
// Copyright © 2020 U-CREATES. All rights reserved.
//
// This source code is the property of U-CREATES.
// If such findings are accepted at any time.
// We hope the tips and helpful in developing.
// ======================================================================
package com.ucreates.renderer.asset.polygon;
import android.content.Context;
import android.opengl.GLES11;
import com.ucreates.renderer.asset.BaseAsset;
import com.ucreates.renderer.asset.TextureAsset;
import com.ucreates.renderer.entity.GLESColor;
import com.ucreates.renderer.entity.VertexArray;
import com.ucreates.renderer.renderer.GLES1Renderer;
public class TriangleAsset4 extends BaseAsset {
    public TriangleAsset4(float width, float height, GLESColor color) {
        this.width = width;
        this.height = height;
        this.color = color;
        this.renderMode = GLES11.GL_TRIANGLE_STRIP;
        this.vertex = new VertexArray(GLES1Renderer.DIMENSION2D);
    }
    @Override
    public void create() {
        float x = 0.5f * this.width;
        float y = 0.5f * this.height;
        float verticies[] = {
            // left down
            -x,
            -y,
            // right down
            x,
            -y,
            // center top
            0.0f,
            y,
        };
        float vertexColors[] = {
            // left down
            this.color.r,
            this.color.g,
            this.color.b,
            this.color.a,
            // right down
            this.color.r,
            this.color.g,
            this.color.b,
            this.color.a,
            // center top
            this.color.r,
            this.color.g,
            this.color.b,
            this.color.a,
        };
        short indicies[] = {0, 1, 2};
        int vertexCount = verticies.length / this.vertex.dimension;
        this.vertex.setVertexCount(vertexCount);
        this.vertex.setVertices(verticies);
        this.vertex.setColors(vertexColors);
        this.vertex.setIndicies(indicies);
        return;
    }
    @Override
    public void create(String texturePath, Context context) {
        this.create(texturePath, GLES11.GL_TEXTURE0, context);
        return;
    }
    @Override
    public void create(String texturePath, int textureUnit, Context context) {
        this.texture = new TextureAsset();
        this.texture.load(texturePath, textureUnit, context);
        float x = this.width;
        float y = this.height;
        float verticies[] = {
            // left down
            -x,
            -y,
            // right down
            x,
            -y,
            // center top
            0.0f,
            y,
        };
        float colors[] = {
            // left down
            this.color.r,
            this.color.g,
            this.color.b,
            this.color.a,
            // right down
            this.color.r,
            this.color.g,
            this.color.b,
            this.color.a,
            // center top
            this.color.r,
            this.color.g,
            this.color.b,
            this.color.a,
        };
        float uvs[] = {
            //左下
            0.0f,
            1.0f,
            //右下
            1.0f,
            1.0f,
            //中央上
            0.5f,
            0.0f,
        };
        short indicies[] = {0, 1, 2};
        int vertexCount = verticies.length / this.vertex.dimension;
        this.vertex.setVertexCount(vertexCount);
        this.vertex.setVertices(verticies);
        this.vertex.setColors(colors);
        this.vertex.setIndicies(indicies);
        this.vertex.setUVs(uvs);
        return;
    }
}
