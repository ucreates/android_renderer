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
import android.opengl.GLES20;
import com.ucreates.renderer.asset.GLES2BaseAsset;
import com.ucreates.renderer.asset.GLES2TextureAsset;
import com.ucreates.renderer.entity.GLES2Color;
import com.ucreates.renderer.entity.GLES2Vertex;
import com.ucreates.renderer.renderer.GLES2Renderer;
public class GLES2TriangleAsset1 extends GLES2BaseAsset {
    public GLES2TriangleAsset1(float width, float height, GLES2Color color) {
        this.width = width;
        this.height = height;
        this.color = color;
        this.renderMode = GLES20.GL_TRIANGLES;
        this.vertex = new GLES2Vertex(GLES2Renderer.DIMENSION2D);
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
        int vertexCount = vertices.length / this.vertex.getDimension();
        this.vertex.setVertexCount(vertexCount);
        this.vertex.setVertices(vertices);
        this.vertex.setColors(vertexColors);
        this.vertex.allocateBuffer();
        return;
    }
    @Override
    public void create(String texturePath, String textureUnitName, Context context) {
        this.create(texturePath, GLES20.GL_TEXTURE0, textureUnitName, context);
        return;
    }
    @Override
    public void create(String texturePath, int textureUnit, String textureUnitName, Context context) {
        this.texture = new GLES2TextureAsset();
        this.texture.setTextureUnit(textureUnitName, textureUnit);
        this.texture.load(texturePath, textureUnit, context);
        float x = 0.5f * this.width;
        float y = 0.5f * this.height;
        float vertices[] = {
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
        int vertexCount = vertices.length / this.vertex.getDimension();
        this.vertex.setVertexCount(vertexCount);
        this.vertex.setVertices(vertices);
        this.vertex.setColors(vertexColors);
        this.vertex.setUVs(uvs);
        this.vertex.allocateBuffer();
        return;
    }
}
