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
import com.ucreates.renderer.math.GLES1Angle;
import com.ucreates.renderer.math.GLES1Normal;
import com.ucreates.renderer.renderer.GLES1Renderer;
public class SphereAsset1 extends BaseAsset {
    private int divideCount;
    private float radius;
    public SphereAsset1(float radius, int divideCount, GLESColor color) {
        this.radius = radius;
        this.color = color;
        this.divideCount = divideCount;
        this.renderMode = GLES11.GL_TRIANGLES;
        this.vertex = new VertexArray(GLES1Renderer.DIMENSION3D);
    }
    @Override
    public void create() {
        int vertexCount = (this.divideCount - 1) * this.divideCount * 2 * 3;
        int originVerticesLength = this.divideCount * this.divideCount * 3;
        int verticesLength = vertexCount * 3;
        int colorsLength = vertexCount * 4;
        int normalsLength = verticesLength;
        int originVerticesMemsize = originVerticesLength * Allocator.GL_FLOAT_SIZE;
        int verticesMemsize = verticesLength * Allocator.GL_FLOAT_SIZE;
        int colorsMemsize = colorsLength * Allocator.GL_FLOAT_SIZE;
        int normalsMemsize = normalsLength * Allocator.GL_FLOAT_SIZE;
        float[] originVertices = new float[originVerticesMemsize];
        float[] vertices = new float[verticesMemsize];
        float[] colors = new float[colorsMemsize];
        float[] normals = new float[normalsMemsize];
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
                originVertices[vidx] = x * this.radius;
                originVertices[vidx + 1] = y * this.radius;
                originVertices[vidx + 2] = z * this.radius;
                vidx += 3;
            }
        }
        int iidx = 0;
        for (int j = 0; j < this.divideCount - 1; ++j) {
            int stack = j * this.divideCount;
            for (int i = 0; i < this.divideCount; ++i) {
                int vidx1 = (stack + i) * 3;
                int vidx2 = (stack + i + 1) * 3;
                int vidx3 = (stack + i + this.divideCount) * 3;
                int vidx4 = (stack + i + this.divideCount + 1) * 3;
                float x1 = originVertices[vidx1];
                float y1 = originVertices[vidx1 + 1];
                float z1 = originVertices[vidx1 + 2];
                float x2 = originVertices[vidx2];
                float y2 = originVertices[vidx2 + 1];
                float z2 = originVertices[vidx2 + 2];
                float x3 = originVertices[vidx3];
                float y3 = originVertices[vidx3 + 1];
                float z3 = originVertices[vidx3 + 2];
                float x4 = originVertices[vidx3];
                float y4 = originVertices[vidx3 + 1];
                float z4 = originVertices[vidx3 + 2];
                float x5 = originVertices[vidx2];
                float y5 = originVertices[vidx2 + 1];
                float z5 = originVertices[vidx2 + 2];
                float x6 = originVertices[vidx4];
                float y6 = originVertices[vidx4 + 1];
                float z6 = originVertices[vidx4 + 2];
                Float3 triangleNormal1 = GLES1Normal.toNormal(x1, y1, z1, x2, y2, z2, x3, y3, z3);
                Float3 triangleNormal2 = GLES1Normal.toNormal(x4, y4, z4, x5, y5, z5, x6, y6, z6);
                vertices[iidx] = x1;
                vertices[iidx + 1] = y1;
                vertices[iidx + 2] = z1;
                vertices[iidx + 3] = x2;
                vertices[iidx + 4] = y2;
                vertices[iidx + 5] = z2;
                vertices[iidx + 6] = x3;
                vertices[iidx + 7] = y3;
                vertices[iidx + 8] = z3;
                vertices[iidx + 9] = x4;
                vertices[iidx + 10] = y4;
                vertices[iidx + 11] = z4;
                vertices[iidx + 12] = x5;
                vertices[iidx + 13] = y5;
                vertices[iidx + 14] = z5;
                vertices[iidx + 15] = x6;
                vertices[iidx + 16] = y6;
                vertices[iidx + 17] = z6;
                normals[iidx] = triangleNormal1.x;
                normals[iidx + 1] = triangleNormal1.y;
                normals[iidx + 2] = triangleNormal1.z;
                normals[iidx + 3] = triangleNormal1.x;
                normals[iidx + 4] = triangleNormal1.y;
                normals[iidx + 5] = triangleNormal1.z;
                normals[iidx + 6] = triangleNormal1.x;
                normals[iidx + 7] = triangleNormal1.y;
                normals[iidx + 8] = triangleNormal1.z;
                normals[iidx + 9] = triangleNormal2.x;
                normals[iidx + 10] = triangleNormal2.y;
                normals[iidx + 11] = triangleNormal2.z;
                normals[iidx + 12] = triangleNormal2.x;
                normals[iidx + 13] = triangleNormal2.y;
                normals[iidx + 14] = triangleNormal2.z;
                normals[iidx + 15] = triangleNormal2.x;
                normals[iidx + 16] = triangleNormal2.y;
                normals[iidx + 17] = triangleNormal2.z;
                iidx += 18;
            }
        }
        for (int i = 0; i < colorsLength; i += 4) {
            colors[i] = this.color.r;
            colors[i + 1] = this.color.g;
            colors[i + 2] = this.color.b;
            colors[i + 3] = this.color.a;
        }
        this.vertex.setVertexCount(vertexCount);
        this.vertex.setVertices(vertices);
        this.vertex.setColors(colors);
        this.vertex.setNormals(normals);
        return;
    }
    @Override
    public void create(String texturePath, Context context) {
        this.texture = new TextureAsset();
        this.texture.load(texturePath, context);
        int vertexCount = (this.divideCount - 1) * this.divideCount * 2 * 3;
        int originVerticesLength = this.divideCount * this.divideCount * 3;
        int originUVsLength = this.divideCount * this.divideCount * 2;
        int verticesLength = vertexCount * 3;
        int colorsLength = vertexCount * 4;
        int normalsLength = verticesLength;
        int uvsLength = vertexCount * 2;
        int originVerticesMemsize = originVerticesLength * Allocator.GL_FLOAT_SIZE;
        int originUVsMemsize = originUVsLength * Allocator.GL_FLOAT_SIZE;
        int verticesMemsize = verticesLength * Allocator.GL_FLOAT_SIZE;
        int colorsMemsize = colorsLength * Allocator.GL_FLOAT_SIZE;
        int normalsMemsize = normalsLength * Allocator.GL_FLOAT_SIZE;
        int uvsMemsize = uvsLength * Allocator.GL_FLOAT_SIZE;
        float[] originVertices = new float[originVerticesMemsize];
        float[] originUVs = new float[originUVsMemsize];
        float[] vertices = new float[verticesMemsize];
        float[] colors = new float[colorsMemsize];
        float[] normals = new float[normalsMemsize];
        float[] uvs = new float[uvsMemsize];
        int vidx = 0;
        int uvidx = 0;
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
                originVertices[vidx] = x * this.radius;
                originVertices[vidx + 1] = y * this.radius;
                originVertices[vidx + 2] = z * this.radius;
                originUVs[uvidx] = (float) u;
                originUVs[uvidx + 1] = (float) v;
                vidx += 3;
                uvidx += 2;
            }
        }
        int iidx = 0;
        int uviidx = 0;
        for (int j = 0; j < this.divideCount - 1; ++j) {
            int stack = j * this.divideCount;
            for (int i = 0; i < this.divideCount; ++i) {
                int vidx1 = (stack + i) * 3;
                int vidx2 = (stack + i + 1) * 3;
                int vidx3 = (stack + i + this.divideCount) * 3;
                int vidx4 = (stack + i + this.divideCount + 1) * 3;
                int uidx1 = (stack + i) * 2;
                int uidx2 = (stack + i + 1) * 2;
                int uidx3 = (stack + i + this.divideCount) * 2;
                int uidx4 = (stack + i + this.divideCount + 1) * 2;
                float x1 = originVertices[vidx1];
                float y1 = originVertices[vidx1 + 1];
                float z1 = originVertices[vidx1 + 2];
                float x2 = originVertices[vidx2];
                float y2 = originVertices[vidx2 + 1];
                float z2 = originVertices[vidx2 + 2];
                float x3 = originVertices[vidx3];
                float y3 = originVertices[vidx3 + 1];
                float z3 = originVertices[vidx3 + 2];
                float x4 = originVertices[vidx3];
                float y4 = originVertices[vidx3 + 1];
                float z4 = originVertices[vidx3 + 2];
                float x5 = originVertices[vidx2];
                float y5 = originVertices[vidx2 + 1];
                float z5 = originVertices[vidx2 + 2];
                float x6 = originVertices[vidx4];
                float y6 = originVertices[vidx4 + 1];
                float z6 = originVertices[vidx4 + 2];
                Float3 triangleNormal1 = GLES1Normal.toNormal(x1, y1, z1, x2, y2, z2, x3, y3, z3);
                Float3 triangleNormal2 = GLES1Normal.toNormal(x4, y4, z4, x5, y5, z5, x6, y6, z6);
                vertices[iidx] = x1;
                vertices[iidx + 1] = y1;
                vertices[iidx + 2] = z1;
                vertices[iidx + 3] = x2;
                vertices[iidx + 4] = y2;
                vertices[iidx + 5] = z2;
                vertices[iidx + 6] = x3;
                vertices[iidx + 7] = y3;
                vertices[iidx + 8] = z3;
                vertices[iidx + 9] = x4;
                vertices[iidx + 10] = y4;
                vertices[iidx + 11] = z4;
                vertices[iidx + 12] = x5;
                vertices[iidx + 13] = y5;
                vertices[iidx + 14] = z5;
                vertices[iidx + 15] = x6;
                vertices[iidx + 16] = y6;
                vertices[iidx + 17] = z6;
                normals[iidx] = triangleNormal1.x;
                normals[iidx + 1] = triangleNormal1.y;
                normals[iidx + 2] = triangleNormal1.z;
                normals[iidx + 3] = triangleNormal1.x;
                normals[iidx + 4] = triangleNormal1.y;
                normals[iidx + 5] = triangleNormal1.z;
                normals[iidx + 6] = triangleNormal1.x;
                normals[iidx + 7] = triangleNormal1.y;
                normals[iidx + 8] = triangleNormal1.z;
                normals[iidx + 9] = triangleNormal2.x;
                normals[iidx + 10] = triangleNormal2.y;
                normals[iidx + 11] = triangleNormal2.z;
                normals[iidx + 12] = triangleNormal2.x;
                normals[iidx + 13] = triangleNormal2.y;
                normals[iidx + 14] = triangleNormal2.z;
                normals[iidx + 15] = triangleNormal2.x;
                normals[iidx + 16] = triangleNormal2.y;
                normals[iidx + 17] = triangleNormal2.z;
                uvs[uviidx] = originUVs[uidx1];
                uvs[uviidx + 1] = originUVs[uidx1 + 1];
                uvs[uviidx + 2] = originUVs[uidx2];
                uvs[uviidx + 3] = originUVs[uidx2 + 1];
                uvs[uviidx + 4] = originUVs[uidx3];
                uvs[uviidx + 5] = originUVs[uidx3 + 1];
                uvs[uviidx + 6] = originUVs[uidx3];
                uvs[uviidx + 7] = originUVs[uidx3 + 1];
                uvs[uviidx + 8] = originUVs[uidx2];
                uvs[uviidx + 9] = originUVs[uidx2 + 1];
                uvs[uviidx + 10] = originUVs[uidx4];
                uvs[uviidx + 11] = originUVs[uidx4 + 1];
                iidx += 18;
                uviidx += 12;
            }
        }
        for (int i = 0; i < colorsLength; i += 4) {
            colors[i] = this.color.r;
            colors[i + 1] = this.color.g;
            colors[i + 2] = this.color.b;
            colors[i + 3] = this.color.a;
        }
        this.vertex.setVertexCount(vertexCount);
        this.vertex.setVertices(vertices);
        this.vertex.setColors(colors);
        this.vertex.setNormals(normals);
        this.vertex.setUVs(uvs);
        return;
    }
}
