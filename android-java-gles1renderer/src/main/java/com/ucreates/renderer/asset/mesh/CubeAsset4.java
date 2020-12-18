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
public class CubeAsset4 extends BaseAsset {
    public CubeAsset4(float width, float height, float depth, GLESColor color) {
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.color = color;
        this.renderMode = GLES11.GL_TRIANGLE_STRIP;
        this.vertex = new VertexArray(GLES1Renderer.DIMENSION3D);
    }
    @Override
    public void create() {
        float x = 0.5f * this.width;
        float y = 0.5f * this.height;
        float z = 0.5f * this.depth;
        float verticies[] = {
            // 1:LTF
            -x,
            y,
            -z,
            // 2:RTF
            x,
            y,
            -z,
            // 3:LBF
            -x,
            -y,
            -z,
            // 4:RBF
            x,
            -y,
            -z,
            // 5:RBR
            x,
            -y,
            z,
            // 6:RTF
            x,
            y,
            -z,
            // 7:RTR
            x,
            y,
            z,
            // 8:LTF
            -x,
            y,
            -z,
            // 9:LTR
            -x,
            y,
            z,
            // 10:LBF
            -x,
            -y,
            -z,
            // 11:LBR
            -x,
            -y,
            z,
            // 12:RBR
            x,
            -y,
            z,
            // 13:LTR
            -x,
            y,
            z,
            // 14:RTR
            x,
            y,
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
        };
        short indicies[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        int indiciesLength = indicies.length;
        int vertexCount = verticies.length / this.vertex.dimension;
        int normalsLength = verticies.length;
        int surfaceNomalsLength = normalsLength / GLES1Renderer.DIMENSION3D;
        int vertexNomalsMemsize = normalsLength * Allocator.GL_FLOAT_SIZE;
        int surfaceNormalsMemsize = normalsLength * Allocator.GL_VECTOR3_SIZE;
        float[] vertexNormals = new float[vertexNomalsMemsize];
        Float3[] surfaceNormals = new Float3[surfaceNormalsMemsize];
        int vnidx = 0;
        int iidx1 = 0;
        int iidx2 = 1;
        int iidx3 = 2;
        for (int i = 0; i < indiciesLength; i++) {
            if (indiciesLength == iidx1) {
                iidx1 = 0;
            }
            if (indiciesLength == iidx2) {
                iidx2 = 0;
            }
            if (indiciesLength == iidx3) {
                iidx3 = 0;
            }
            int n1idx = indicies[iidx1];
            int n2idx = indicies[iidx2];
            int n3idx = indicies[iidx3];
            int vidx1 = n1idx * GLES1Renderer.DIMENSION3D;
            int vidx2 = n2idx * GLES1Renderer.DIMENSION3D;
            int vidx3 = n3idx * GLES1Renderer.DIMENSION3D;
            float x1 = verticies[vidx1];
            float y1 = verticies[vidx1 + 1];
            float z1 = verticies[vidx1 + 2];
            float x2 = verticies[vidx2];
            float y2 = verticies[vidx2 + 1];
            float z2 = verticies[vidx2 + 2];
            float x3 = verticies[vidx3];
            float y3 = verticies[vidx3 + 1];
            float z3 = verticies[vidx3 + 2];
            Float3 triangleNormal = GLES1Normal.toNormal(x1, y1, z1, x2, y2, z2, x3, y3, z3);
            Float3 surfaceNormal1 = null != surfaceNormals[n1idx] ? surfaceNormals[n1idx] : new Float3();
            Float3 surfaceNormal2 = null != surfaceNormals[n2idx] ? surfaceNormals[n2idx] : new Float3();
            Float3 surfaceNormal3 = null != surfaceNormals[n3idx] ? surfaceNormals[n3idx] : new Float3();
            surfaceNormals[n1idx] = GLES1Normal.add(surfaceNormal1, triangleNormal);
            surfaceNormals[n2idx] = GLES1Normal.add(surfaceNormal2, triangleNormal);
            surfaceNormals[n3idx] = GLES1Normal.add(surfaceNormal3, triangleNormal);
            surfaceNormals[n1idx] = GLES1Normal.toOne(surfaceNormals[n1idx]);
            surfaceNormals[n2idx] = GLES1Normal.toOne(surfaceNormals[n2idx]);
            surfaceNormals[n3idx] = GLES1Normal.toOne(surfaceNormals[n3idx]);
            vnidx++;
            iidx1++;
            iidx2++;
            iidx3++;
        }
        vnidx = 0;
        for (int i = 0; i < surfaceNomalsLength; i++) {
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
            // 1:LTF
            -x,
            y,
            -z,
            // 2:RTF
            x,
            y,
            -z,
            // 3:LBF
            -x,
            -y,
            -z,
            // 4:RBF
            x,
            -y,
            -z,
            // 5:RBR
            x,
            -y,
            z,
            // 6:RTF
            x,
            y,
            -z,
            // 7:RTR
            x,
            y,
            z,
            // 8:LTF
            -x,
            y,
            -z,
            // 9:LTR
            -x,
            y,
            z,
            // 10:LBF
            -x,
            -y,
            -z,
            // 11:LBR
            -x,
            -y,
            z,
            // 12:RBR
            x,
            -y,
            z,
            // 13:LTR
            -x,
            y,
            z,
            // 14:RTR
            x,
            y,
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
        };
        float uvs[] = {0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f};
        short indicies[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        int indiciesLength = indicies.length;
        int vertexCount = verticies.length / this.vertex.dimension;
        int normalsLength = verticies.length;
        int surfaceNomalsLength = normalsLength / GLES1Renderer.DIMENSION3D;
        int vertexNomalsMemsize = normalsLength * Allocator.GL_FLOAT_SIZE;
        int surfaceNormalsMemsize = surfaceNomalsLength * Allocator.GL_VECTOR3_SIZE;
        float[] vertexNormals = new float[vertexNomalsMemsize];
        Float3[] surfaceNormals = new Float3[surfaceNormalsMemsize];
        int vnidx = 0;
        int iidx1 = 0;
        int iidx2 = 1;
        int iidx3 = 2;
        for (int i = 0; i < indiciesLength; i++) {
            if (indiciesLength == iidx1) {
                iidx1 = 0;
            }
            if (indiciesLength == iidx2) {
                iidx2 = 0;
            }
            if (indiciesLength == iidx3) {
                iidx3 = 0;
            }
            int n1idx = indicies[iidx1];
            int n2idx = indicies[iidx2];
            int n3idx = indicies[iidx3];
            int vidx1 = n1idx * GLES1Renderer.DIMENSION3D;
            int vidx2 = n2idx * GLES1Renderer.DIMENSION3D;
            int vidx3 = n3idx * GLES1Renderer.DIMENSION3D;
            float x1 = verticies[vidx1];
            float y1 = verticies[vidx1 + 1];
            float z1 = verticies[vidx1 + 2];
            float x2 = verticies[vidx2];
            float y2 = verticies[vidx2 + 1];
            float z2 = verticies[vidx2 + 2];
            float x3 = verticies[vidx3];
            float y3 = verticies[vidx3 + 1];
            float z3 = verticies[vidx3 + 2];
            Float3 triangleNormal = GLES1Normal.toNormal(x1, y1, z1, x2, y2, z2, x3, y3, z3);
            Float3 surfaceNormal1 = null != surfaceNormals[n1idx] ? surfaceNormals[n1idx] : new Float3();
            Float3 surfaceNormal2 = null != surfaceNormals[n2idx] ? surfaceNormals[n2idx] : new Float3();
            Float3 surfaceNormal3 = null != surfaceNormals[n3idx] ? surfaceNormals[n3idx] : new Float3();
            surfaceNormals[n1idx] = GLES1Normal.add(surfaceNormal1, triangleNormal);
            surfaceNormals[n2idx] = GLES1Normal.add(surfaceNormal2, triangleNormal);
            surfaceNormals[n3idx] = GLES1Normal.add(surfaceNormal3, triangleNormal);
            vnidx++;
            iidx1++;
            iidx2++;
            iidx3++;
        }
        vnidx = 0;
        for (int i = 0; i < surfaceNomalsLength; i++) {
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
        return;
    }
}