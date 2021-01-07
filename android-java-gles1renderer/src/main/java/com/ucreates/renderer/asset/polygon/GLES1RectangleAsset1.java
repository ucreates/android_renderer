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
import com.ucreates.renderer.asset.GLES1BaseAsset;
import com.ucreates.renderer.asset.GLES1TextureAsset;
import com.ucreates.renderer.entity.GLES1Color;
import com.ucreates.renderer.entity.GLES1VertexArray;
public class GLES1RectangleAsset1 extends GLES1BaseAsset {
    public GLES1RectangleAsset1(float width, float height, GLES1Color color) {
        this.width = width;
        this.height = height;
        this.color = color;
        this.renderMode = GLES11.GL_TRIANGLES;
        this.vertex = new GLES1VertexArray(2);
    }
    @Override
    public void create() {
        float x = 0.5f * this.width;
        float y = 0.5f * this.height;
        float vertices[] = {
            // left down
            -x,
            -y,
            // right down
            x,
            -y,
            // left top
            -x,
            y,
            // right down
            x,
            -y,
            // right top
            x,
            y,
            // left top
            -x,
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
            // left top
            this.color.r,
            this.color.g,
            this.color.b,
            this.color.a,
            // right down
            this.color.r,
            this.color.g,
            this.color.b,
            this.color.a,
            // right top
            this.color.r,
            this.color.g,
            this.color.b,
            this.color.a,
            // left top
            this.color.r,
            this.color.g,
            this.color.b,
            this.color.a,
        };
        int vertexCount = vertices.length / this.vertex.dimension;
        this.vertex.setVertexCount(vertexCount);
        this.vertex.setVertices(vertices);
        this.vertex.setColors(colors);
        return;
    }
    @Override
    public void create(String texturePath, Context context) {
        this.create(texturePath, GLES11.GL_TEXTURE0, context);
        return;
    }
    @Override
    public void create(String texturePath, int textureUnit, Context context) {
        this.texture = new GLES1TextureAsset();
        this.texture.load(texturePath, textureUnit, context);
        float x = this.width;
        float y = this.height;
        float vertices[] = {
            // left down
            -x,
            -y,
            // right down
            x,
            -y,
            // left top
            -x,
            y,
            // right down
            x,
            -y,
            // right top
            x,
            y,
            // left top
            -x,
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
            // left top
            this.color.r,
            this.color.g,
            this.color.b,
            this.color.a,
            // right down
            this.color.r,
            this.color.g,
            this.color.b,
            this.color.a,
            // right top
            this.color.r,
            this.color.g,
            this.color.b,
            this.color.a,
            // left top
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
            // left up
            0.0f,
            0.0f,
            // right down
            1.0f,
            1.0f,
            // right up
            1.0f,
            0.0f,
            // left up
            0.0f,
            0.0f,
        };
        int vertexCount = vertices.length / this.vertex.dimension;
        this.vertex.setVertexCount(vertexCount);
        this.vertex.setVertices(vertices);
        this.vertex.setColors(colors);
        this.vertex.setUVs(uvs);
        return;
    }
}
