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
import com.ucreates.renderer.math.GLES1Normal;
import com.ucreates.renderer.renderer.GLES1Renderer;
public class CubeAsset2 extends BaseAsset {
    public CubeAsset2(float width, float height, float depth, GLESColor color) {
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.color = color;
        this.renderMode = GLES11.GL_TRIANGLES;
        this.vertex = new VertexArray(GLES1Renderer.DIMENSION3D);
    }
    @Override
    public void create() {
        float x = 0.5f * this.width;
        float y = 0.5f * this.height;
        float z = 0.5f * this.depth;
        float verticies[] = {
            // left up front
            -x,
            y,
            -z,
            // right up front
            x,
            y,
            -z,
            // left down front
            -x,
            -y,
            -z,
            // right down front
            x,
            -y,
            -z,
            // left up back
            -x,
            y,
            z,
            // right up back
            x,
            y,
            z,
            // left down back
            -x,
            -y,
            z,
            // right down back
            x,
            -y,
            z,
        };
        float colors[] = {
            this.color.r,
            this.color.g,
            this.color.b,
            this.color.a,
            this.color.r,
            this.color.g,
            this.color.b,
            this.color.a,
            this.color.r,
            this.color.g,
            this.color.b,
            this.color.a,
            this.color.r,
            this.color.g,
            this.color.b,
            this.color.a,
            this.color.r,
            this.color.g,
            this.color.b,
            this.color.a,
            this.color.r,
            this.color.g,
            this.color.b,
            this.color.a,
            this.color.r,
            this.color.g,
            this.color.b,
            this.color.a,
            this.color.r,
            this.color.g,
            this.color.b,
            this.color.a,
        };
        short indicies[] = {// front
            0, 1, 2, 2, 1, 3,
            // back
            4, 6, 5, 5, 6, 7,
            // right
            1, 5, 3, 3, 5, 7,
            // left
            0, 2, 4, 4, 2, 6,
            // top
            0, 4, 1, 1, 4, 5,
            // bottom
            2, 3, 6, 6, 3, 7};
        int vertexCount = verticies.length / this.vertex.dimension;
        int normalsLength = verticies.length;
        int indiciesLength = indicies.length;
        int surfaceNormalsMemsize = vertexCount;
        int vertexNormalsMemsize = normalsLength;
        Float3[] surfaceNormals = new Float3[surfaceNormalsMemsize];
        float[] vertexNormals = new float[vertexNormalsMemsize];
        for (int i = 0; i < indiciesLength; i += GLES1Renderer.DIMENSION3D) {
            int idx1 = indicies[i];
            int idx2 = indicies[i + 1];
            int idx3 = indicies[i + 2];
            int v1idx = idx1 * GLES1Renderer.DIMENSION3D;
            int v2idx = idx2 * GLES1Renderer.DIMENSION3D;
            int v3idx = idx3 * GLES1Renderer.DIMENSION3D;
            float x1 = verticies[v1idx];
            float y1 = verticies[v1idx + 1];
            float z1 = verticies[v1idx + 2];
            float x2 = verticies[v2idx];
            float y2 = verticies[v2idx + 1];
            float z2 = verticies[v2idx + 2];
            float x3 = verticies[v3idx];
            float y3 = verticies[v3idx + 1];
            float z3 = verticies[v3idx + 2];
            Float3 surfaceNormal1 = null != surfaceNormals[idx1] ? surfaceNormals[idx1] : new Float3();
            Float3 surfaceNormal2 = null != surfaceNormals[idx2] ? surfaceNormals[idx2] : new Float3();
            Float3 surfaceNormal3 = null != surfaceNormals[idx3] ? surfaceNormals[idx3] : new Float3();
            Float3 triangleNormal = GLES1Normal.toNormal(x1, y1, z1, x2, y2, z2, x3, y3, z3);
            surfaceNormals[idx1] = GLES1Normal.add(surfaceNormal1, triangleNormal);
            surfaceNormals[idx2] = GLES1Normal.add(surfaceNormal2, triangleNormal);
            surfaceNormals[idx3] = GLES1Normal.add(surfaceNormal3, triangleNormal);
        }
        int vnidx = 0;
        for (int i = 0; i < vertexCount; i++) {
            Float3 surfaceNormal = GLES1Normal.toOne(surfaceNormals[i]);
            vertexNormals[vnidx] = surfaceNormal.x;
            vertexNormals[vnidx + 1] = surfaceNormal.y;
            vertexNormals[vnidx + 2] = surfaceNormal.z;
            vnidx += GLES1Renderer.DIMENSION3D;
        }
        this.vertex.setVertexCount(vertexCount);
        this.vertex.setVertices(verticies);
        this.vertex.setColors(colors);
        this.vertex.setNormals(vertexNormals);
        this.vertex.setIndicies(indicies);
        return;
    }
    @Override
    public void create(String texturePath, Context context) {
        this.texture = new TextureAsset();
        this.texture.load(texturePath, context);
        float x = 0.5f * this.width;
        float y = 0.5f * this.height;
        float z = 0.5f * this.depth;
        float verticies[] = {
            // front
            -x,
            y,
            -z,
            x,
            y,
            -z,
            -x,
            -y,
            -z,
            x,
            -y,
            -z,
            // back
            -x,
            y,
            z,
            x,
            y,
            z,
            -x,
            -y,
            z,
            x,
            -y,
            z,
            // right
            x,
            y,
            z,
            x,
            y,
            -z,
            x,
            -y,
            z,
            x,
            -y,
            -z,
            // left
            -x,
            y,
            z,
            -x,
            y,
            -z,
            -x,
            -y,
            z,
            -x,
            -y,
            -z,
            // top
            -x,
            y,
            -z,
            x,
            y,
            -z,
            -x,
            y,
            z,
            x,
            y,
            z,
            // bottom
            -x,
            -y,
            -z,
            x,
            -y,
            -z,
            -x,
            -y,
            z,
            x,
            -y,
            z,
        };
        float colors[] = {
            this.color.r,
            this.color.g,
            this.color.b,
            this.color.a,
            this.color.r,
            this.color.g,
            this.color.b,
            this.color.a,
            this.color.r,
            this.color.g,
            this.color.b,
            this.color.a,
            this.color.r,
            this.color.g,
            this.color.b,
            this.color.a,
            this.color.r,
            this.color.g,
            this.color.b,
            this.color.a,
            this.color.r,
            this.color.g,
            this.color.b,
            this.color.a,
            this.color.r,
            this.color.g,
            this.color.b,
            this.color.a,
            this.color.r,
            this.color.g,
            this.color.b,
            this.color.a,
            this.color.r,
            this.color.g,
            this.color.b,
            this.color.a,
            this.color.r,
            this.color.g,
            this.color.b,
            this.color.a,
            this.color.r,
            this.color.g,
            this.color.b,
            this.color.a,
            this.color.r,
            this.color.g,
            this.color.b,
            this.color.a,
            this.color.r,
            this.color.g,
            this.color.b,
            this.color.a,
            this.color.r,
            this.color.g,
            this.color.b,
            this.color.a,
            this.color.r,
            this.color.g,
            this.color.b,
            this.color.a,
            this.color.r,
            this.color.g,
            this.color.b,
            this.color.a,
            this.color.r,
            this.color.g,
            this.color.b,
            this.color.a,
            this.color.r,
            this.color.g,
            this.color.b,
            this.color.a,
            this.color.r,
            this.color.g,
            this.color.b,
            this.color.a,
            this.color.r,
            this.color.g,
            this.color.b,
            this.color.a,
            this.color.r,
            this.color.g,
            this.color.b,
            this.color.a,
            this.color.r,
            this.color.g,
            this.color.b,
            this.color.a,
            this.color.r,
            this.color.g,
            this.color.b,
            this.color.a,
            this.color.r,
            this.color.g,
            this.color.b,
            this.color.a,
        };
        float uvs[] = {
            // front
            1.0f,
            0.0f,
            0.0f,
            0.0f,
            1.0f,
            1.0f,
            0.0f,
            1.0f,
            // back
            0.0f,
            0.0f,
            1.0f,
            0.0f,
            0.0f,
            1.0f,
            1.0f,
            1.0f,
            // right
            0.0f,
            0.0f,
            1.0f,
            0.0f,
            0.0f,
            1.0f,
            1.0f,
            1.0f,
            // left
            1.0f,
            0.0f,
            0.0f,
            0.0f,
            1.0f,
            1.0f,
            0.0f,
            1.0f,
            // top
            0.0f,
            0.0f,
            1.0f,
            0.0f,
            0.0f,
            1.0f,
            1.0f,
            1.0f,
            // bottom
            1.0f,
            0.0f,
            0.0f,
            0.0f,
            1.0f,
            1.0f,
            0.0f,
            1.0f,
        };
        short indicies[] = {// front
            0, 1, 2, 2, 1, 3,
            // back
            4, 6, 5, 5, 6, 7,
            // right
            8, 10, 9, 9, 10, 11,
            // left
            12, 13, 14, 14, 13, 15,
            // top
            16, 18, 17, 17, 18, 19,
            // bottom
            20, 21, 22, 22, 21, 23};
        int vertexCount = verticies.length / this.vertex.dimension;
        int normalsLength = verticies.length;
        int indiciesLength = indicies.length;
        int surfaceNormalsMemsize = vertexCount;
        int vertexNormalsMemsize = normalsLength;
        Float3[] surfaceNormals = new Float3[surfaceNormalsMemsize];
        float[] vertexNormals = new float[vertexNormalsMemsize];
        for (int i = 0; i < indiciesLength; i += GLES1Renderer.DIMENSION3D) {
            int idx1 = indicies[i];
            int idx2 = indicies[i + 1];
            int idx3 = indicies[i + 2];
            int v1idx = idx1 * GLES1Renderer.DIMENSION3D;
            int v2idx = idx2 * GLES1Renderer.DIMENSION3D;
            int v3idx = idx3 * GLES1Renderer.DIMENSION3D;
            float x1 = verticies[v1idx];
            float y1 = verticies[v1idx + 1];
            float z1 = verticies[v1idx + 2];
            float x2 = verticies[v2idx];
            float y2 = verticies[v2idx + 1];
            float z2 = verticies[v2idx + 2];
            float x3 = verticies[v3idx];
            float y3 = verticies[v3idx + 1];
            float z3 = verticies[v3idx + 2];
            Float3 surfaceNormal1 = null != surfaceNormals[idx1] ? surfaceNormals[idx1] : new Float3();
            Float3 surfaceNormal2 = null != surfaceNormals[idx2] ? surfaceNormals[idx2] : new Float3();
            Float3 surfaceNormal3 = null != surfaceNormals[idx3] ? surfaceNormals[idx3] : new Float3();
            Float3 triangleNormal = GLES1Normal.toNormal(x1, y1, z1, x2, y2, z2, x3, y3, z3);
            surfaceNormals[idx1] = GLES1Normal.add(surfaceNormal1, triangleNormal);
            surfaceNormals[idx2] = GLES1Normal.add(surfaceNormal2, triangleNormal);
            surfaceNormals[idx3] = GLES1Normal.add(surfaceNormal3, triangleNormal);
        }
        int vnidx = 0;
        for (int i = 0; i < vertexCount; i++) {
            Float3 surfaceNormal = GLES1Normal.toOne(surfaceNormals[i]);
            vertexNormals[vnidx] = surfaceNormal.x;
            vertexNormals[vnidx + 1] = surfaceNormal.y;
            vertexNormals[vnidx + 2] = surfaceNormal.z;
            vnidx += GLES1Renderer.DIMENSION3D;
        }
        this.vertex.setVertexCount(vertexCount);
        this.vertex.setVertices(verticies);
        this.vertex.setColors(colors);
        this.vertex.setUVs(uvs);
        this.vertex.setNormals(vertexNormals);
        this.vertex.setIndicies(indicies);
        return;
    }
}
