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
public class CircleAsset2 extends BaseAsset {
    private int divideCount;
    private float radius;
    public CircleAsset2(float radius, int divideCount, GLESColor color) {
        this.radius = radius;
        this.color = color;
        this.divideCount = divideCount;
        this.renderMode = GLES11.GL_TRIANGLES;
        this.vertex = new VertexArray(GLES1Renderer.DIMENSION2D);
    }
    @Override
    public void create() {
        int vertexCount = this.divideCount * 3;
        int verticesLength = vertexCount * GLES1Renderer.DIMENSION2D;
        int vertexMemsize = verticesLength * Allocator.GL_FLOAT_SIZE;
        int colorsLength = vertexCount * GLES1Renderer.RGBA;
        int colorsMemsize = colorsLength * Allocator.GL_FLOAT_SIZE;
        float[] vertices = new float[vertexMemsize];
        float[] colors = new float[colorsMemsize];
        int didx = 0;
        vertices[0] = 0.0f;
        vertices[1] = 0.0f;
        for (int i = 2; i < verticesLength; i += 4) {
            double unitDegree = (360.0d / this.divideCount);
            double degree1 = didx * unitDegree;
            double degree2 = (didx + 1) * unitDegree;
            double radian1 = GLES1Angle.toRadian(degree1);
            double radian2 = GLES1Angle.toRadian(degree2);
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
        int indiciesMemsize = indiciesLength * Allocator.GL_FLOAT_SIZE;
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
        return;
    }
}
