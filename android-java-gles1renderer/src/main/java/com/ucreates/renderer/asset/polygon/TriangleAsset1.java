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
public class TriangleAsset1 extends BaseAsset {
    public TriangleAsset1(float width, float height, GLESColor color) {
        this.width = width;
        this.height = height;
        this.color = color;
        this.renderMode = GLES11.GL_TRIANGLES;
        this.vertex = new VertexArray(2);
    }
    @Override
    public void create() {
        float verticies[] = {
            // left down
            -0.5f * this.width,
            -0.5f * this.height,
            // right down
            0.5f * this.width,
            -0.5f * this.height,
            // center top
            0.0f,
            0.5f,
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
        int vertexCount = verticies.length / this.vertex.dimension;
        this.vertex.setVertexCount(vertexCount);
        this.vertex.setVertices(verticies);
        this.vertex.setColors(vertexColors);
        return;
    }
    @Override
    public void create(String texturePath, Context context) {
        this.texture = new TextureAsset();
        this.texture.load(texturePath, context);
        float verticies[] = {
            // left down
            -0.5f * this.width * this.texture.uvRatio.x,
            -0.5f * this.height * this.texture.uvRatio.y,
            // right down
            0.5f * this.width * this.texture.uvRatio.x,
            -0.5f * this.height * this.texture.uvRatio.y,
            // center top
            0.0f,
            0.5f * this.height * this.texture.uvRatio.y,
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
        float uvs[] = {
            // left down
            0.0f,
            1.0f,
            // right down
            1.0f,
            1.0f,
            // center top
            0.5f,
            0.0f,
        };
        int vertexCount = verticies.length / this.vertex.dimension;
        this.vertex.setVertexCount(vertexCount);
        this.vertex.setVertices(verticies);
        this.vertex.setColors(vertexColors);
        this.vertex.setUVs(uvs);
        return;
    }
}
