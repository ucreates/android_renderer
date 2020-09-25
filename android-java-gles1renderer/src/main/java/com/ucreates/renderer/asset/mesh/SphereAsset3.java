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
import android.renderscript.Float3;
import com.ucreates.renderer.asset.BaseAsset;
import com.ucreates.renderer.asset.TextureAsset;
import com.ucreates.renderer.entity.GLESColor;
import com.ucreates.renderer.entity.VertexArray;
import com.ucreates.renderer.io.memory.Allocator;
import com.ucreates.renderer.math.GLES1Normal;
import com.ucreates.renderer.renderer.GLES1Renderer;
public class SphereAsset3 extends BaseAsset {
    private int divideCount;
    private float radius;
    public SphereAsset3(float radius, int divideCount, GLESColor color) {
        this.radius = radius;
        this.color = color;
        this.divideCount = divideCount;
        this.renderMode = GLES11.GL_TRIANGLE_STRIP;
        this.vertex = new VertexArray(GLES1Renderer.DIMENSION3D);
    }
    @Override
    public void create() {
        int vertexCount = (this.divideCount - 1) * this.divideCount * 4;
        int originVertexCount = this.divideCount * this.divideCount;
        int verticesLength = vertexCount * GLES1Renderer.DIMENSION3D;
        int originVerticesLength = originVertexCount * GLES1Renderer.DIMENSION3D;
        int colorsLength = vertexCount * GLES1Renderer.RGBA;
        int vertexNormalsLength = verticesLength;
        int surfaceNormalsLength = vertexCount;
        int verticesMemsize = verticesLength * Allocator.GL_FLOAT_SIZE;
        int originVerticesMemsize = originVerticesLength * Allocator.GL_FLOAT_SIZE;
        int colorsMemsize = colorsLength * Allocator.GL_FLOAT_SIZE;
        int vertexNormalsMemsize = vertexNormalsLength * Allocator.GL_FLOAT_SIZE;
        int surfaceNormalsMemsize = surfaceNormalsLength * Allocator.GL_FLOAT_SIZE;
        float[] verticies = new float[verticesMemsize];
        float[] originVerticies = new float[originVerticesMemsize];
        float[] colors = new float[colorsMemsize];
        float[] vertexNormals = new float[vertexNormalsMemsize];
        Float3[] surfaceNormals = new Float3[surfaceNormalsMemsize];
        int vidx = 0;
        for (int i = 0; i < this.divideCount; i++) {
            double v = (double) i / (double) (this.divideCount - 1);
            double vradian = Math.PI * v;
            float y = (float) Math.cos(vradian);
            float r = (float) Math.sin(vradian);
            for (int j = 0; j < this.divideCount; j++) {
                double u = (double) j / (double) (this.divideCount - 1);
                double uradian = 2 * Math.PI * u;
                float x = (float) Math.cos(uradian) * r;
                float z = (float) Math.sin(uradian) * r;
                originVerticies[vidx] = x * this.radius;
                originVerticies[vidx + 1] = y * this.radius;
                originVerticies[vidx + 2] = z * this.radius;
                vidx += GLES1Renderer.DIMENSION3D;
            }
        }
        vidx = 0;
        for (int j = 0; j < this.divideCount - 1; ++j) {
            int stack = j * this.divideCount;
            for (int i = 0; i < this.divideCount; ++i) {
                int v1 = stack + i;
                int v2 = stack + i + 1;
                int v3 = stack + i + this.divideCount;
                int v4 = stack + i + this.divideCount + 1;
                int v1idx = v1 * GLES1Renderer.DIMENSION3D;
                int v2idx = v2 * GLES1Renderer.DIMENSION3D;
                int v3idx = v3 * GLES1Renderer.DIMENSION3D;
                int v4idx = v4 * GLES1Renderer.DIMENSION3D;
                if (originVerticesLength == v4idx) {
                    verticies[vidx] = originVerticies[v1idx];
                    verticies[vidx + 1] = originVerticies[v1idx + 1];
                    verticies[vidx + 2] = originVerticies[v1idx + 2];
                    verticies[vidx + 3] = originVerticies[v2idx];
                    verticies[vidx + 4] = originVerticies[v2idx + 1];
                    verticies[vidx + 5] = originVerticies[v2idx + 2];
                    verticies[vidx + 6] = originVerticies[v3idx];
                    verticies[vidx + 7] = originVerticies[v3idx + 1];
                    verticies[vidx + 8] = originVerticies[v3idx + 2];
                    vidx += GLES1Renderer.DIMENSION3D * 3;
                } else {
                    verticies[vidx] = originVerticies[v1idx];
                    verticies[vidx + 1] = originVerticies[v1idx + 1];
                    verticies[vidx + 2] = originVerticies[v1idx + 2];
                    verticies[vidx + 3] = originVerticies[v2idx];
                    verticies[vidx + 4] = originVerticies[v2idx + 1];
                    verticies[vidx + 5] = originVerticies[v2idx + 2];
                    verticies[vidx + 6] = originVerticies[v3idx];
                    verticies[vidx + 7] = originVerticies[v3idx + 1];
                    verticies[vidx + 8] = originVerticies[v3idx + 2];
                    verticies[vidx + 9] = originVerticies[v4idx];
                    verticies[vidx + 10] = originVerticies[v4idx + 1];
                    verticies[vidx + 11] = originVerticies[v4idx + 2];
                    vidx += GLES1Renderer.DIMENSION3D * 4;
                }
            }
        }
        int v1 = 0;
        int v2 = 1;
        int v3 = 2;
        int v4 = 3;
        for (int i = 0; i < originVertexCount; i++) {
            if (v1 == originVertexCount - 1) {
                v1 = 0;
            }
            if (v2 == originVertexCount - 1) {
                v2 = 0;
            }
            if (v3 == originVertexCount - 1) {
                v3 = 0;
            }
            if (v4 == originVertexCount - 1) {
                v4 = 0;
            }
            int vidx1 = v1 * GLES1Renderer.DIMENSION3D;
            int vidx2 = v2 * GLES1Renderer.DIMENSION3D;
            int vidx3 = v3 * GLES1Renderer.DIMENSION3D;
            int vidx4 = v4 * GLES1Renderer.DIMENSION3D;
            float x1 = verticies[vidx1];
            float y1 = verticies[vidx1 + 1];
            float z1 = verticies[vidx1 + 2];
            float x2 = verticies[vidx2];
            float y2 = verticies[vidx2 + 1];
            float z2 = verticies[vidx2 + 2];
            float x3 = verticies[vidx3];
            float y3 = verticies[vidx3 + 1];
            float z3 = verticies[vidx3 + 2];
            float x4 = verticies[vidx4];
            float y4 = verticies[vidx4 + 1];
            float z4 = verticies[vidx4 + 2];
            Float3 triangleNormal1 = GLES1Normal.toNormal(x1, y1, z1, x2, y2, z2, x3, y3, z3);
            Float3 triangleNormal2 = GLES1Normal.toNormal(x2, y2, z2, x3, y3, z3, x4, y4, z4);
            Float3 surfaceNormal1 = null != surfaceNormals[v1] ? surfaceNormals[v1] : new Float3();
            Float3 surfaceNormal2 = null != surfaceNormals[v2] ? surfaceNormals[v2] : new Float3();
            Float3 surfaceNormal3 = null != surfaceNormals[v3] ? surfaceNormals[v3] : new Float3();
            Float3 surfaceNormal4 = null != surfaceNormals[v4] ? surfaceNormals[v4] : new Float3();
            surfaceNormals[v1] = GLES1Normal.add(surfaceNormal1, triangleNormal1);
            surfaceNormals[v2] = GLES1Normal.add(surfaceNormal2, triangleNormal1);
            surfaceNormals[v3] = GLES1Normal.add(surfaceNormal3, triangleNormal1);
            surfaceNormals[v2] = GLES1Normal.add(surfaceNormal2, triangleNormal2);
            surfaceNormals[v3] = GLES1Normal.add(surfaceNormal3, triangleNormal2);
            surfaceNormals[v4] = GLES1Normal.add(surfaceNormal4, triangleNormal2);
            v1++;
            v2++;
            v3++;
            v4++;
        }
        int snidx = 0;
        for (int i = 0; i < originVertexCount; i += GLES1Renderer.DIMENSION3D) {
            Float3 vn = GLES1Normal.toOne(surfaceNormals[snidx]);
            vertexNormals[i] = vn.x;
            vertexNormals[i + 1] = vn.y;
            vertexNormals[i + 2] = vn.z;
            snidx++;
        }
        for (int i = 0; i < colorsLength; i += GLES1Renderer.RGBA) {
            colors[i] = this.color.r;
            colors[i + 1] = this.color.g;
            colors[i + 2] = this.color.b;
            colors[i + 3] = this.color.a;
        }
        this.vertex.setVertexCount(vertexCount);
        this.vertex.setVertices(verticies);
        this.vertex.setColors(colors);
        this.vertex.setNormals(vertexNormals);
        return;
    }
}
