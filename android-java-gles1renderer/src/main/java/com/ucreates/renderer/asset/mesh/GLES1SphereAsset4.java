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
public class GLES1SphereAsset4 extends GLES1BaseAsset {
    private int divideCount;
    private float radius;
    public GLES1SphereAsset4(float radius, int divideCount, GLES1Color color) {
        this.radius = radius;
        this.color = color;
        this.divideCount = divideCount;
        this.renderMode = GLES11.GL_TRIANGLE_STRIP;
        this.vertex = new GLES1VertexArray(GLES1Renderer.DIMENSION3D);
    }
    @Override
    public void create() {
        int vertexCount = this.divideCount * this.divideCount;
        int verticesLength = vertexCount * GLES1Renderer.DIMENSION3D;
        int colorsLength = vertexCount * GLES1Renderer.RGBA;
        int normalsLength = verticesLength;
        int surfaceNormalsLength = this.divideCount * this.divideCount;
        int indiciesLength = (this.divideCount - 1) * this.divideCount * 4;
        int verticesMemsize = verticesLength * GLES1Allocator.GL_FLOAT_SIZE;
        int colorsMemsize = colorsLength * GLES1Allocator.GL_FLOAT_SIZE;
        int normalsMemsize = normalsLength * GLES1Allocator.GL_FLOAT_SIZE;
        int surfaceNormalsMemsize = surfaceNormalsLength * GLES1Allocator.GL_VECTOR3_SIZE;
        int indiciesMemsize = indiciesLength * GLES1Allocator.GL_SHORT_SIZE;
        float[] verticies = new float[verticesMemsize];
        float[] colors = new float[colorsMemsize];
        float[] normals = new float[normalsMemsize];
        Float3[] surfaceNormals = new Float3[surfaceNormalsMemsize];
        short[] indicies = new short[indiciesMemsize];
        int vidx = 0;
        for (int i = 0; i < this.divideCount; i++) {
            float v = (float) i / (float) (this.divideCount - 1);
            double vradian = Math.PI * v;
            float y = (float) Math.cos(vradian);
            float r = (float) Math.sin(vradian);
            for (int j = 0; j < this.divideCount; j++) {
                float u = (float) j / (float) (this.divideCount - 1);
                double uradian = 2 * Math.PI * u;
                float x = (float) Math.cos(uradian) * r;
                float z = (float) Math.sin(uradian) * r;
                verticies[vidx] = x * this.radius;
                verticies[vidx + 1] = y * this.radius;
                verticies[vidx + 2] = z * this.radius;
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
                indicies[iidx + 3] = (short) v4;
                float x1 = verticies[v1idx];
                float y1 = verticies[v1idx + 1];
                float z1 = verticies[v1idx + 2];
                float x2 = verticies[v2idx];
                float y2 = verticies[v2idx + 1];
                float z2 = verticies[v2idx + 2];
                float x3 = verticies[v3idx];
                float y3 = verticies[v3idx + 1];
                float z3 = verticies[v3idx + 2];
                float x4 = verticies[v3idx];
                float y4 = verticies[v3idx + 1];
                float z4 = verticies[v3idx + 2];
                float x5 = verticies[v2idx];
                float y5 = verticies[v2idx + 1];
                float z5 = verticies[v2idx + 2];
                float x6 = verticies[v4idx];
                float y6 = verticies[v4idx + 1];
                float z6 = verticies[v4idx + 2];
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
                iidx += 4;
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
        for (int i = 0; i < colorsLength; i += 4) {
            colors[i] = this.color.r;
            colors[i + 1] = this.color.g;
            colors[i + 2] = this.color.b;
            colors[i + 3] = this.color.a;
        }
        this.vertex.setVertexCount(vertexCount);
        this.vertex.setVertices(verticies);
        this.vertex.setColors(colors);
        this.vertex.setNormals(normals);
        this.vertex.setIndicies(indicies);
        return;
    }
    @Override
    public void create(String texturePath, Context context) {
        this.texture = new GLES1TextureAsset();
        this.texture.load(texturePath, context);
        int vertexCount = this.divideCount * this.divideCount;
        int verticesLength = vertexCount * GLES1Renderer.DIMENSION3D;
        int colorsLength = vertexCount * GLES1Renderer.RGBA;
        int normalsLength = verticesLength;
        int surfaceNormalsLength = this.divideCount * this.divideCount;
        int indiciesLength = (this.divideCount - 1) * this.divideCount * 4;
        int uvsLength = vertexCount * GLES1Renderer.DIMENSION2D;
        int verticesMemsize = verticesLength * GLES1Allocator.GL_FLOAT_SIZE;
        int colorsMemsize = colorsLength * GLES1Allocator.GL_FLOAT_SIZE;
        int uvsMemsize = uvsLength * GLES1Allocator.GL_FLOAT_SIZE;
        int normalsMemsize = normalsLength * GLES1Allocator.GL_FLOAT_SIZE;
        int surfaceNormalsMemsize = surfaceNormalsLength * GLES1Allocator.GL_VECTOR3_SIZE;
        int indiciesMemsize = indiciesLength * GLES1Allocator.GL_SHORT_SIZE;
        float[] verticies = new float[verticesMemsize];
        float[] colors = new float[colorsMemsize];
        float[] normals = new float[normalsMemsize];
        float[] uvs = new float[uvsMemsize];
        Float3[] surfaceNormals = new Float3[surfaceNormalsMemsize];
        short[] indicies = new short[indiciesMemsize];
        int vidx = 0;
        int uvidx = 0;
        for (int i = 0; i < this.divideCount; i++) {
            float v = (float) i / (float) (this.divideCount - 1);
            double vradian = Math.PI * v;
            float y = (float) Math.cos(vradian);
            float r = (float) Math.sin(vradian);
            for (int j = 0; j < this.divideCount; j++) {
                float u = (float) j / (float) (this.divideCount - 1);
                double uradian = 2 * Math.PI * u;
                float x = (float) Math.cos(uradian) * r;
                float z = (float) Math.sin(uradian) * r;
                float v1ratio = this.texture.uvRatio.x;
                float v2ratio = 1.0f - this.texture.uvRatio.y;
                verticies[vidx] = x * this.radius;
                verticies[vidx + 1] = y * this.radius;
                verticies[vidx + 2] = z * this.radius;
                uvs[uvidx] = (float) u * this.texture.uvRatio.x;
                uvs[uvidx + 1] = (float) v * v1ratio + v2ratio;
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
                indicies[iidx + 3] = (short) v4;
                float x1 = verticies[v1idx];
                float y1 = verticies[v1idx + 1];
                float z1 = verticies[v1idx + 2];
                float x2 = verticies[v2idx];
                float y2 = verticies[v2idx + 1];
                float z2 = verticies[v2idx + 2];
                float x3 = verticies[v3idx];
                float y3 = verticies[v3idx + 1];
                float z3 = verticies[v3idx + 2];
                float x4 = verticies[v3idx];
                float y4 = verticies[v3idx + 1];
                float z4 = verticies[v3idx + 2];
                float x5 = verticies[v2idx];
                float y5 = verticies[v2idx + 1];
                float z5 = verticies[v2idx + 2];
                float x6 = verticies[v4idx];
                float y6 = verticies[v4idx + 1];
                float z6 = verticies[v4idx + 2];
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
                iidx += 4;
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
        for (int i = 0; i < colorsLength; i += 4) {
            colors[i] = this.color.r;
            colors[i + 1] = this.color.g;
            colors[i + 2] = this.color.b;
            colors[i + 3] = this.color.a;
        }
        this.vertex.setVertexCount(vertexCount);
        this.vertex.setVertices(verticies);
        this.vertex.setColors(colors);
        this.vertex.setUVs(uvs);
        this.vertex.setNormals(normals);
        this.vertex.setIndicies(indicies);
        return;
    }
}
