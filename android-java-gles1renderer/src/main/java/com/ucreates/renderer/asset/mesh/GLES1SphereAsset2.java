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
import com.ucreates.renderer.asset.GLES1BaseAsset;
import com.ucreates.renderer.asset.GLES1TextureAsset;
import com.ucreates.renderer.entity.GLES1Color;
import com.ucreates.renderer.entity.GLES1VertexArray;
import com.ucreates.renderer.io.memory.GLES1Allocator;
import com.ucreates.renderer.math.GLES1Normal;
import com.ucreates.renderer.renderer.GLES1Renderer;
public class GLES1SphereAsset2 extends GLES1BaseAsset {
    private int divideCount;
    private float radius;
    public GLES1SphereAsset2(float radius, int divideCount, GLES1Color color) {
        this.radius = radius;
        this.color = color;
        this.divideCount = divideCount;
        this.renderMode = GLES11.GL_TRIANGLES;
        this.vertex = new GLES1VertexArray(GLES1Renderer.DIMENSION3D);
    }
    @Override
    public void create() {
        int vertexCount = (this.divideCount - 1) * this.divideCount * 2 * GLES1Renderer.DIMENSION3D;
        int verticesLength = this.divideCount * this.divideCount * GLES1Renderer.DIMENSION3D;
        int colorsLength = vertexCount * GLES1Renderer.RGBA;
        int normalsLength = verticesLength;
        int surfaceNormalsLength = this.divideCount * this.divideCount;
        int indiciesLength = vertexCount;
        int verticesMemsize = verticesLength * GLES1Allocator.GL_FLOAT_SIZE;
        int colorsMemsize = colorsLength * GLES1Allocator.GL_FLOAT_SIZE;
        int surfaceNormalsMemsize = surfaceNormalsLength * GLES1Allocator.GL_VECTOR3_SIZE;
        int normalsMemsize = normalsLength * GLES1Allocator.GL_FLOAT_SIZE;
        int indiciesMemsize = indiciesLength;
        float[] vertices = new float[verticesMemsize];
        float[] colors = new float[colorsMemsize];
        float[] normals = new float[normalsMemsize];
        Float3[] surfaceNormals = new Float3[surfaceNormalsMemsize];
        short[] indicies = new short[indiciesMemsize];
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
                vertices[vidx] = x * this.radius;
                vertices[vidx + 1] = y * this.radius;
                vertices[vidx + 2] = z * this.radius;
                vidx += GLES1Renderer.DIMENSION3D;
            }
        }
        int iidx = 0;
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
                indicies[iidx] = (short) v1;
                indicies[iidx + 1] = (short) v2;
                indicies[iidx + 2] = (short) v3;
                indicies[iidx + 3] = (short) v3;
                indicies[iidx + 4] = (short) v2;
                indicies[iidx + 5] = (short) v4;
                float x1 = vertices[v1idx];
                float y1 = vertices[v1idx + 1];
                float z1 = vertices[v1idx + 2];
                float x2 = vertices[v2idx];
                float y2 = vertices[v2idx + 1];
                float z2 = vertices[v2idx + 2];
                float x3 = vertices[v3idx];
                float y3 = vertices[v3idx + 1];
                float z3 = vertices[v3idx + 2];
                float x4 = vertices[v3idx];
                float y4 = vertices[v3idx + 1];
                float z4 = vertices[v3idx + 2];
                float x5 = vertices[v2idx];
                float y5 = vertices[v2idx + 1];
                float z5 = vertices[v2idx + 2];
                float x6 = vertices[v4idx];
                float y6 = vertices[v4idx + 1];
                float z6 = vertices[v4idx + 2];
                Float3 surfaceNormal1 = null != surfaceNormals[v1] ? surfaceNormals[v1] : new Float3();
                Float3 surfaceNormal2 = null != surfaceNormals[v2] ? surfaceNormals[v2] : new Float3();
                Float3 surfaceNormal3 = null != surfaceNormals[v3] ? surfaceNormals[v3] : new Float3();
                Float3 surfaceNormal4 = null != surfaceNormals[v4] ? surfaceNormals[v4] : new Float3();
                Float3 triangleNormal1 = GLES1Normal.toNormal(x1, y1, z1, x2, y2, z2, x3, y3, z3);
                Float3 triangleNormal2 = GLES1Normal.toNormal(x4, y4, z4, x5, y5, z5, x6, y6, z6);
                surfaceNormals[v1] = GLES1Normal.add(surfaceNormal1, triangleNormal1);
                surfaceNormals[v2] = GLES1Normal.add(surfaceNormal2, triangleNormal1);
                surfaceNormals[v3] = GLES1Normal.add(surfaceNormal3, triangleNormal1);
                surfaceNormals[v3] = GLES1Normal.add(surfaceNormal3, triangleNormal2);
                surfaceNormals[v2] = GLES1Normal.add(surfaceNormal2, triangleNormal2);
                surfaceNormals[v4] = GLES1Normal.add(surfaceNormal4, triangleNormal2);
                iidx += 6;
            }
        }
        int nidx = 0;
        for (int i = 0; i < surfaceNormalsLength; i++) {
            Float3 vn = surfaceNormals[i];
            normals[nidx] = vn.x;
            normals[nidx + 1] = vn.y;
            normals[nidx + 2] = vn.z;
            nidx += GLES1Renderer.DIMENSION3D;
        }
        for (int i = 0; i < colorsLength; i += GLES1Renderer.RGBA) {
            colors[i] = this.color.r;
            colors[i + 1] = this.color.g;
            colors[i + 2] = this.color.b;
            colors[i + 3] = this.color.a;
        }
        this.vertex.setVertexCount(vertexCount);
        this.vertex.setVertices(vertices);
        this.vertex.setColors(colors);
        this.vertex.setNormals(normals);
        this.vertex.setIndicies(indicies);
        return;
    }
    @Override
    public void create(String texturePath, Context context) {
        this.texture = new GLES1TextureAsset();
        this.texture.load(texturePath, context);
        int vertexCount = (this.divideCount - 1) * this.divideCount * 2 * GLES1Renderer.DIMENSION3D;
        int verticesLength = this.divideCount * this.divideCount * GLES1Renderer.DIMENSION3D;
        int colorsLength = vertexCount * GLES1Renderer.RGBA;
        int uvsLength = this.divideCount * this.divideCount * GLES1Renderer.DIMENSION2D;
        int normalsLength = verticesLength;
        int surfaceNormalsLength = this.divideCount * this.divideCount;
        int indiciesLength = vertexCount;
        int verticesMemsize = verticesLength * GLES1Allocator.GL_FLOAT_SIZE;
        int colorsMemsize = colorsLength * GLES1Allocator.GL_FLOAT_SIZE;
        int uvsMemsize = uvsLength * GLES1Allocator.GL_FLOAT_SIZE;
        int surfaceNormalsMemsize = surfaceNormalsLength * GLES1Allocator.GL_VECTOR3_SIZE;
        int normalsMemsize = normalsLength * GLES1Allocator.GL_FLOAT_SIZE;
        int indiciesMemsize = indiciesLength;
        float[] vertices = new float[verticesMemsize];
        float[] colors = new float[colorsMemsize];
        float[] uvs = new float[uvsMemsize];
        float[] normals = new float[normalsMemsize];
        Float3[] surfaceNormals = new Float3[surfaceNormalsMemsize];
        short[] indicies = new short[indiciesMemsize];
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
                vertices[vidx] = x * this.radius;
                vertices[vidx + 1] = y * this.radius;
                vertices[vidx + 2] = z * this.radius;
                uvs[uvidx] = (float) u;
                uvs[uvidx + 1] = (float) v;
                vidx += GLES1Renderer.DIMENSION3D;
                uvidx += GLES1Renderer.DIMENSION2D;
            }
        }
        int iidx = 0;
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
                indicies[iidx] = (short) v1;
                indicies[iidx + 1] = (short) v2;
                indicies[iidx + 2] = (short) v3;
                indicies[iidx + 3] = (short) v3;
                indicies[iidx + 4] = (short) v2;
                indicies[iidx + 5] = (short) v4;
                float x1 = vertices[v1idx];
                float y1 = vertices[v1idx + 1];
                float z1 = vertices[v1idx + 2];
                float x2 = vertices[v2idx];
                float y2 = vertices[v2idx + 1];
                float z2 = vertices[v2idx + 2];
                float x3 = vertices[v3idx];
                float y3 = vertices[v3idx + 1];
                float z3 = vertices[v3idx + 2];
                float x4 = vertices[v3idx];
                float y4 = vertices[v3idx + 1];
                float z4 = vertices[v3idx + 2];
                float x5 = vertices[v2idx];
                float y5 = vertices[v2idx + 1];
                float z5 = vertices[v2idx + 2];
                float x6 = vertices[v4idx];
                float y6 = vertices[v4idx + 1];
                float z6 = vertices[v4idx + 2];
                Float3 surfaceNormal1 = null != surfaceNormals[v1] ? surfaceNormals[v1] : new Float3();
                Float3 surfaceNormal2 = null != surfaceNormals[v2] ? surfaceNormals[v2] : new Float3();
                Float3 surfaceNormal3 = null != surfaceNormals[v3] ? surfaceNormals[v3] : new Float3();
                Float3 surfaceNormal4 = null != surfaceNormals[v4] ? surfaceNormals[v4] : new Float3();
                Float3 triangleNormal1 = GLES1Normal.toNormal(x1, y1, z1, x2, y2, z2, x3, y3, z3);
                Float3 triangleNormal2 = GLES1Normal.toNormal(x4, y4, z4, x5, y5, z5, x6, y6, z6);
                surfaceNormals[v1] = GLES1Normal.add(surfaceNormal1, triangleNormal1);
                surfaceNormals[v2] = GLES1Normal.add(surfaceNormal2, triangleNormal1);
                surfaceNormals[v3] = GLES1Normal.add(surfaceNormal3, triangleNormal1);
                surfaceNormals[v3] = GLES1Normal.add(surfaceNormal3, triangleNormal2);
                surfaceNormals[v2] = GLES1Normal.add(surfaceNormal2, triangleNormal2);
                surfaceNormals[v4] = GLES1Normal.add(surfaceNormal4, triangleNormal2);
                iidx += 6;
            }
        }
        int nidx = 0;
        for (int i = 0; i < surfaceNormalsLength; i++) {
            Float3 vn = surfaceNormals[i];
            normals[nidx] = vn.x;
            normals[nidx + 1] = vn.y;
            normals[nidx + 2] = vn.z;
            nidx += GLES1Renderer.DIMENSION3D;
        }
        for (int i = 0; i < colorsLength; i += GLES1Renderer.RGBA) {
            colors[i] = this.color.r;
            colors[i + 1] = this.color.g;
            colors[i + 2] = this.color.b;
            colors[i + 3] = this.color.a;
        }
        this.vertex.setVertexCount(vertexCount);
        this.vertex.setVertices(vertices);
        this.vertex.setColors(colors);
        this.vertex.setUVs(uvs);
        this.vertex.setNormals(normals);
        this.vertex.setIndicies(indicies);
        return;
    }
}
