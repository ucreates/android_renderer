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
import android.opengl.GLES11;
import com.ucreates.renderer.asset.BaseAsset;
import com.ucreates.renderer.asset.TextureAsset;
import com.ucreates.renderer.entity.GLESColor;
import com.ucreates.renderer.entity.VertexArray;
import com.ucreates.renderer.io.memory.Allocator;
import com.ucreates.renderer.math.GLES1Angle;
import com.ucreates.renderer.renderer.GLES1Renderer;
public class CircleAsset3 extends BaseAsset {
    private int divideCount;
    private float radius;
    public CircleAsset3(float radius, int divideCount, GLESColor color) {
        this.radius = radius;
        this.color = color;
        this.divideCount = divideCount;
        this.renderMode = GLES11.GL_TRIANGLE_STRIP;
        this.vertex = new VertexArray(GLES1Renderer.DIMENSION2D);
    }
    @Override
    public void create() {
        int vertexCount = (this.divideCount * 3) + 1;
        int verticesLength = vertexCount * GLES1Renderer.DIMENSION2D;
        int vertexMemsize = verticesLength * Allocator.GL_FLOAT_SIZE;
        int colorsLength = vertexCount * GLES1Renderer.RGBA;
        int colorsMemsize = colorsLength * Allocator.GL_FLOAT_SIZE;
        float[] vertices = new float[vertexMemsize];
        float[] colors = new float[colorsMemsize];
        int didx = 0;
        vertices[verticesLength - 2] = 0.0f;
        vertices[verticesLength - 1] = 0.0f;
        for (int i = 0; i < verticesLength; i += 6) {
            double unitDegree = (360.0d / this.divideCount);
            double degree1 = didx * unitDegree;
            double degree2 = (didx + 1) * unitDegree;
            double radian1 = GLES1Angle.toRadian(degree1);
            double radian2 = GLES1Angle.toRadian(degree2);
            float x1 = (float) Math.cos(radian1) * this.radius;
            float y1 = (float) Math.sin(radian1) * this.radius;
            float x2 = (float) Math.cos(radian2) * this.radius;
            float y2 = (float) Math.sin(radian2) * this.radius;
            vertices[i] = 0.0f;
            vertices[i + 1] = 0.0f;
            vertices[i + 2] = x1;
            vertices[i + 3] = y1;
            vertices[i + 4] = x2;
            vertices[i + 5] = y2;
            didx++;
        }
        colors[colorsLength - 4] = this.color.r;
        colors[colorsLength - 3] = this.color.g;
        colors[colorsLength - 2] = this.color.b;
        colors[colorsLength - 1] = this.color.a;
        for (int i = 0; i < colorsLength; i += 12) {
            colors[i] = this.color.r;
            colors[i + 1] = this.color.g;
            colors[i + 2] = this.color.b;
            colors[i + 3] = this.color.a;
            colors[i + 4] = this.color.r;
            colors[i + 5] = this.color.g;
            colors[i + 6] = this.color.b;
            colors[i + 7] = this.color.a;
            colors[i + 8] = this.color.r;
            colors[i + 9] = this.color.g;
            colors[i + 10] = this.color.b;
            colors[i + 11] = this.color.a;
        }
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
        this.texture = new TextureAsset();
        this.texture.load(texturePath, textureUnit, context);
        float uratio = this.texture.uvRatio.x;
        float v1ratio = this.texture.uvRatio.y;
        float v2ratio = 1.0f - this.texture.uvRatio.y;
        float curatio = 0.5f * uratio;
        float cvratio = 0.5f * v1ratio + v2ratio;
        int vertexCount = (this.divideCount * 3) + 1;
        int verticesLength = vertexCount * GLES1Renderer.DIMENSION2D;
        int vertexMemsize = verticesLength * Allocator.GL_FLOAT_SIZE;
        int colorsLength = vertexCount * GLES1Renderer.RGBA;
        int colorsMemsize = colorsLength * Allocator.GL_FLOAT_SIZE;
        int uvMemsize = verticesLength * Allocator.GL_FLOAT_SIZE;
        float[] vertices = new float[vertexMemsize];
        float[] colors = new float[colorsMemsize];
        float[] uvs = new float[uvMemsize];
        int didx = 0;
        vertices[verticesLength - 2] = 0.0f;
        vertices[verticesLength - 1] = 0.0f;
        uvs[verticesLength - 2] = curatio;
        uvs[verticesLength - 1] = cvratio;
        for (int i = 0; i < verticesLength; i += 6) {
            double unitDegree = (360.0d / this.divideCount);
            double degree1 = didx * unitDegree;
            double degree2 = (didx + 1) * unitDegree;
            double radian1 = GLES1Angle.toRadian(degree1);
            double radian2 = GLES1Angle.toRadian(degree2);
            float x1 = (float) Math.cos(radian1) * this.radius;
            float y1 = (float) Math.sin(radian1) * this.radius;
            float x2 = (float) Math.cos(radian2) * this.radius;
            float y2 = (float) Math.sin(radian2) * this.radius;
            float u1 = (float) Math.cos(radian1) * 0.5f;
            float v1 = (float) Math.sin(radian1) * 0.5f;
            float u2 = (float) Math.cos(radian2) * 0.5f;
            float v2 = (float) Math.sin(radian2) * 0.5f;
            vertices[i] = 0.0f;
            vertices[i + 1] = 0.0f;
            vertices[i + 2] = x1;
            vertices[i + 3] = y1;
            vertices[i + 4] = x2;
            vertices[i + 5] = y2;
            uvs[i] = curatio;
            uvs[i + 1] = cvratio;
            uvs[i + 2] = u1 * uratio + curatio;
            uvs[i + 3] = -1 * v1 * v1ratio + cvratio;
            uvs[i + 4] = u2 * uratio + curatio;
            uvs[i + 5] = -1 * v2 * v1ratio + cvratio;
            didx++;
        }
        colors[colorsLength - 4] = this.color.r;
        colors[colorsLength - 3] = this.color.g;
        colors[colorsLength - 2] = this.color.b;
        colors[colorsLength - 1] = this.color.a;
        for (int i = 0; i < colorsLength; i += 12) {
            colors[i] = this.color.r;
            colors[i + 1] = this.color.g;
            colors[i + 2] = this.color.b;
            colors[i + 3] = this.color.a;
            colors[i + 4] = this.color.r;
            colors[i + 5] = this.color.g;
            colors[i + 6] = this.color.b;
            colors[i + 7] = this.color.a;
            colors[i + 8] = this.color.r;
            colors[i + 9] = this.color.g;
            colors[i + 10] = this.color.b;
            colors[i + 11] = this.color.a;
        }
        this.vertex.setVertexCount(vertexCount);
        this.vertex.setVertices(vertices);
        this.vertex.setColors(colors);
        this.vertex.setUVs(uvs);
        return;
    }
}