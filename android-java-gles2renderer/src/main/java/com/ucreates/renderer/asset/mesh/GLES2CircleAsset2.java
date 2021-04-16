// ======================================================================
// Project Name    : android_renderer
//
// Copyright © 2020 U-CREATES. All rights reserved.
//
// This source code is the property of U-CREATES.
// If such findings are accepted at any time.
// We hope the tips and helpful in developing.
// ======================================================================
package com.ucreates.renderer.asset.mesh;
import android.content.Context;
import android.opengl.GLES20;
import com.ucreates.renderer.asset.GLES2BaseAsset;
import com.ucreates.renderer.asset.GLES2TextureAsset;
import com.ucreates.renderer.entity.GLES2Color;
import com.ucreates.renderer.entity.GLES2Vertex;
import com.ucreates.renderer.io.memory.GLES2Allocator;
import com.ucreates.renderer.math.GLES2Angle;
import com.ucreates.renderer.renderer.GLES2Renderer;
public class GLES2CircleAsset2 extends GLES2BaseAsset {
    private int divideCount;
    private float radius;
    public GLES2CircleAsset2(float radius, int divideCount, GLES2Color color) {
        this.radius = radius;
        this.color = color;
        this.divideCount = divideCount;
        this.renderMode = GLES20.GL_TRIANGLES;
        this.vertex = new GLES2Vertex(GLES2Renderer.DIMENSION2D);
    }
    @Override
    public void create() {
        int vertexCount = this.divideCount * 3;
        int verticesLength = vertexCount * GLES2Renderer.DIMENSION2D;
        int vertexMemsize = verticesLength * GLES2Allocator.GL_FLOAT_SIZE;
        int colorsLength = vertexCount * GLES2Renderer.RGBA;
        int colorsMemsize = colorsLength * GLES2Allocator.GL_FLOAT_SIZE;
        float[] vertices = new float[vertexMemsize];
        float[] colors = new float[colorsMemsize];
        int didx = 0;
        vertices[0] = 0.0f;
        vertices[1] = 0.0f;
        for (int i = 2; i < verticesLength; i += 4) {
            double unitDegree = (360.0d / this.divideCount);
            double degree1 = didx * unitDegree;
            double degree2 = (didx + 1) * unitDegree;
            double radian1 = GLES2Angle.toRadian(degree1);
            double radian2 = GLES2Angle.toRadian(degree2);
            float x1 = (float) Math.cos(radian1) * this.radius;
            float y1 = (float) Math.sin(radian1) * this.radius;
            float x2 = (float) Math.cos(radian2) * this.radius;
            float y2 = (float) Math.sin(radian2) * this.radius;
            vertices[i] = x1;
            vertices[i + 1] = y1;
            vertices[i + 2] = x2;
            vertices[i + 3] = y2;
            didx++;
        }
        colors[0] = this.color.r;
        colors[1] = this.color.g;
        colors[2] = this.color.b;
        colors[3] = this.color.a;
        for (int i = 4; i < colorsLength; i += 8) {
            colors[i] = this.color.r;
            colors[i + 1] = this.color.g;
            colors[i + 2] = this.color.b;
            colors[i + 3] = this.color.a;
            colors[i + 4] = this.color.r;
            colors[i + 5] = this.color.g;
            colors[i + 6] = this.color.b;
            colors[i + 7] = this.color.a;
        }
        int indiciesLength = this.divideCount * 3;
        int indiciesMemsize = indiciesLength * GLES2Allocator.GL_FLOAT_SIZE;
        short[] indicies = new short[indiciesMemsize];
        short currentIndex = 1;
        for (int i = 0; i < indiciesLength; i += 3) {
            indicies[i] = 0;
            indicies[i + 1] = currentIndex;
            indicies[i + 2] = (short) (currentIndex + 1);
            currentIndex += 2;
        }
        this.vertex.setVertexCount(vertexCount);
        this.vertex.setVertices(vertices);
        this.vertex.setColors(colors);
        this.vertex.setIndicies(indicies);
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
        float uratio = this.texture.uvRatio.x;
        float v1ratio = this.texture.uvRatio.y;
        float v2ratio = 1.0f - this.texture.uvRatio.y;
        float curatio = 0.5f * uratio;
        float cvratio = 0.5f * v1ratio + v2ratio;
        int vertexCount = this.divideCount * 3;
        int verticesLength = vertexCount * GLES2Renderer.DIMENSION2D;
        int vertexMemsize = verticesLength * GLES2Allocator.GL_FLOAT_SIZE;
        int colorsLength = vertexCount * GLES2Renderer.RGBA;
        int colorsMemsize = colorsLength * GLES2Allocator.GL_FLOAT_SIZE;
        int uvsLength = verticesLength;
        int uvsMemsize = uvsLength * GLES2Allocator.GL_FLOAT_SIZE;
        float[] vertices = new float[vertexMemsize];
        float[] colors = new float[colorsMemsize];
        float[] uvs = new float[uvsMemsize];
        int didx = 0;
        vertices[0] = 0.0f;
        vertices[1] = 0.0f;
        uvs[0] = curatio;
        uvs[1] = cvratio;
        for (int i = 2; i < verticesLength; i += 4) {
            double unitDegree = (360.0d / this.divideCount);
            double degree1 = didx * unitDegree;
            double degree2 = (didx + 1) * unitDegree;
            double radian1 = GLES2Angle.toRadian(degree1);
            double radian2 = GLES2Angle.toRadian(degree2);
            float x1 = (float) Math.cos(radian1) * this.radius;
            float y1 = (float) Math.sin(radian1) * this.radius;
            float x2 = (float) Math.cos(radian2) * this.radius;
            float y2 = (float) Math.sin(radian2) * this.radius;
            float u1 = (float) Math.cos(radian1) * 0.5f;
            float v1 = (float) Math.sin(radian1) * 0.5f;
            float u2 = (float) Math.cos(radian2) * 0.5f;
            float v2 = (float) Math.sin(radian2) * 0.5f;
            vertices[i] = x1;
            vertices[i + 1] = y1;
            vertices[i + 2] = x2;
            vertices[i + 3] = y2;
            uvs[i] = u1 * uratio + curatio;
            uvs[i + 1] = -1 * v1 * v1ratio + cvratio;
            uvs[i + 2] = u2 * uratio + curatio;
            uvs[i + 3] = -1 * v2 * v1ratio + cvratio;
            didx++;
        }
        colors[0] = this.color.r;
        colors[1] = this.color.g;
        colors[2] = this.color.b;
        colors[3] = this.color.a;
        for (int i = 4; i < colorsLength; i += 8) {
            colors[i] = this.color.r;
            colors[i + 1] = this.color.g;
            colors[i + 2] = this.color.b;
            colors[i + 3] = this.color.a;
            colors[i + 4] = this.color.r;
            colors[i + 5] = this.color.g;
            colors[i + 6] = this.color.b;
            colors[i + 7] = this.color.a;
        }
        int indiciesLength = this.divideCount * 3;
        int indiciesMemsize = indiciesLength * GLES2Allocator.GL_FLOAT_SIZE;
        short[] indicies = new short[indiciesMemsize];
        short currentIndex = 1;
        for (int i = 0; i < indiciesLength; i += 3) {
            indicies[i] = 0;
            indicies[i + 1] = currentIndex;
            indicies[i + 2] = (short) (currentIndex + 1);
            currentIndex += 2;
        }
        this.vertex.setVertexCount(vertexCount);
        this.vertex.setVertices(vertices);
        this.vertex.setColors(colors);
        this.vertex.setUVs(uvs);
        this.vertex.setIndicies(indicies);
        this.vertex.allocateBuffer();
        return;
    }
}