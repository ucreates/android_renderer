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
import com.ucreates.renderer.math.GLES1Normal;
import com.ucreates.renderer.renderer.GLES1Renderer;
public class GLES1CubeAsset3 extends GLES1BaseAsset {
    public GLES1CubeAsset3(float width, float height, float depth, GLES1Color color) {
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.color = color;
        this.renderMode = GLES11.GL_TRIANGLE_STRIP;
        this.vertex = new GLES1VertexArray(GLES1Renderer.DIMENSION3D);
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
        int vertexCount = verticies.length / this.vertex.dimension;
        int normalsLength = verticies.length;
        int surfaceNormalsMemsize = vertexCount;
        Float3[] surfaceNormals = new Float3[surfaceNormalsMemsize];
        int n1idx = 0;
        int n2idx = 1;
        int n3idx = 2;
        for (int i = 0; i < normalsLength; i++) {
            if (vertexCount == n1idx) {
                n1idx = 0;
            }
            if (vertexCount == n2idx) {
                n2idx = 0;
            }
            if (vertexCount == n3idx) {
                n3idx = 0;
            }
            int v1idx = n1idx * GLES1Renderer.DIMENSION3D;
            int v2idx = n2idx * GLES1Renderer.DIMENSION3D;
            int v3idx = n3idx * GLES1Renderer.DIMENSION3D;
            float x1 = verticies[v1idx];
            float y1 = verticies[v1idx + 1];
            float z1 = verticies[v1idx + 2];
            float x2 = verticies[v2idx];
            float y2 = verticies[v2idx + 1];
            float z2 = verticies[v2idx + 2];
            float x3 = verticies[v3idx];
            float y3 = verticies[v3idx + 1];
            float z3 = verticies[v3idx + 2];
            Float3 triangleNormal = null;
            if (0 == i % 2) {
                triangleNormal = GLES1Normal.toNormal(x1, y1, z1, x2, y2, z2, x3, y3, z3);
            } else {
                triangleNormal = GLES1Normal.toNormal(x1, y1, z1, x3, y3, z3, x2, y2, z2);
            }
            Float3 surfaceNormal1 = null != surfaceNormals[n1idx] ? surfaceNormals[n1idx] : new Float3();
            Float3 surfaceNormal2 = null != surfaceNormals[n2idx] ? surfaceNormals[n2idx] : new Float3();
            Float3 surfaceNormal3 = null != surfaceNormals[n3idx] ? surfaceNormals[n3idx] : new Float3();
            surfaceNormals[n1idx] = GLES1Normal.add(surfaceNormal1, triangleNormal);
            surfaceNormals[n2idx] = GLES1Normal.add(surfaceNormal2, triangleNormal);
            surfaceNormals[n3idx] = GLES1Normal.add(surfaceNormal3, triangleNormal);
            surfaceNormals[n1idx] = GLES1Normal.toOne(surfaceNormals[n1idx]);
            surfaceNormals[n2idx] = GLES1Normal.toOne(surfaceNormals[n2idx]);
            surfaceNormals[n3idx] = GLES1Normal.toOne(surfaceNormals[n3idx]);
            n1idx++;
            n2idx++;
            n3idx++;
        }
        Float3 vn4 = surfaceNormals[3];
        Float3 vn11 = surfaceNormals[10];
        Float3 vn18 = GLES1Normal.add(surfaceNormals[0], surfaceNormals[7]);
        Float3 vn26 = GLES1Normal.add(surfaceNormals[1], surfaceNormals[5]);
        Float3 vn310 = GLES1Normal.add(surfaceNormals[2], surfaceNormals[9]);
        Float3 vn512 = GLES1Normal.add(surfaceNormals[4], surfaceNormals[11]);
        Float3 vn913 = GLES1Normal.add(surfaceNormals[8], surfaceNormals[12]);
        Float3 vn714 = GLES1Normal.add(surfaceNormals[6], surfaceNormals[13]);
        float vertexNormals[] = {
            vn18.x,
            vn18.y,
            vn18.z,
            vn26.x,
            vn26.y,
            vn26.z,
            vn310.x,
            vn310.y,
            vn310.z,
            vn4.x,
            vn4.y,
            vn4.z,
            vn512.x,
            vn512.y,
            vn512.z,
            vn26.x,
            vn26.y,
            vn26.z,
            vn714.x,
            vn714.y,
            vn714.z,
            vn18.x,
            vn18.y,
            vn18.z,
            vn913.x,
            vn913.y,
            vn913.z,
            vn310.x,
            vn310.y,
            vn310.z,
            vn11.x,
            vn11.y,
            vn11.z,
            vn512.x,
            vn512.y,
            vn512.z,
            vn913.x,
            vn913.y,
            vn913.z,
            vn714.x,
            vn714.y,
            vn714.z,
        };
        this.vertex.setVertexCount(vertexCount);
        this.vertex.setVertices(verticies);
        this.vertex.setColors(colors);
        this.vertex.setNormals(vertexNormals);
        return;
    }
    @Override
    public void create(String texturePath, Context context) {
        this.texture = new GLES1TextureAsset();
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
        int vertexCount = verticies.length / this.vertex.dimension;
        int normalsLength = verticies.length;
        int surfaceNormalsMemsize = vertexCount;
        Float3[] surfaceNormals = new Float3[surfaceNormalsMemsize];
        int n1idx = 0;
        int n2idx = 1;
        int n3idx = 2;
        for (int i = 0; i < normalsLength; i++) {
            if (vertexCount == n1idx) {
                n1idx = 0;
            }
            if (vertexCount == n2idx) {
                n2idx = 0;
            }
            if (vertexCount == n3idx) {
                n3idx = 0;
            }
            int v1idx = n1idx * GLES1Renderer.DIMENSION3D;
            int v2idx = n2idx * GLES1Renderer.DIMENSION3D;
            int v3idx = n3idx * GLES1Renderer.DIMENSION3D;
            float x1 = verticies[v1idx];
            float y1 = verticies[v1idx + 1];
            float z1 = verticies[v1idx + 2];
            float x2 = verticies[v2idx];
            float y2 = verticies[v2idx + 1];
            float z2 = verticies[v2idx + 2];
            float x3 = verticies[v3idx];
            float y3 = verticies[v3idx + 1];
            float z3 = verticies[v3idx + 2];
            Float3 triangleNormal = null;
            if (0 == i % 2) {
                triangleNormal = GLES1Normal.toNormal(x1, y1, z1, x2, y2, z2, x3, y3, z3);
            } else {
                triangleNormal = GLES1Normal.toNormal(x1, y1, z1, x3, y3, z3, x2, y2, z2);
            }
            Float3 surfaceNormal1 = null != surfaceNormals[n1idx] ? surfaceNormals[n1idx] : new Float3();
            Float3 surfaceNormal2 = null != surfaceNormals[n2idx] ? surfaceNormals[n2idx] : new Float3();
            Float3 surfaceNormal3 = null != surfaceNormals[n3idx] ? surfaceNormals[n3idx] : new Float3();
            surfaceNormals[n1idx] = GLES1Normal.add(surfaceNormal1, triangleNormal);
            surfaceNormals[n2idx] = GLES1Normal.add(surfaceNormal2, triangleNormal);
            surfaceNormals[n3idx] = GLES1Normal.add(surfaceNormal3, triangleNormal);
            surfaceNormals[n1idx] = GLES1Normal.toOne(surfaceNormals[n1idx]);
            surfaceNormals[n2idx] = GLES1Normal.toOne(surfaceNormals[n2idx]);
            surfaceNormals[n3idx] = GLES1Normal.toOne(surfaceNormals[n3idx]);
            n1idx++;
            n2idx++;
            n3idx++;
        }
        Float3 vn4 = surfaceNormals[3];
        Float3 vn11 = surfaceNormals[10];
        Float3 vn18 = GLES1Normal.add(surfaceNormals[0], surfaceNormals[7]);
        Float3 vn26 = GLES1Normal.add(surfaceNormals[1], surfaceNormals[5]);
        Float3 vn310 = GLES1Normal.add(surfaceNormals[2], surfaceNormals[9]);
        Float3 vn512 = GLES1Normal.add(surfaceNormals[4], surfaceNormals[11]);
        Float3 vn913 = GLES1Normal.add(surfaceNormals[8], surfaceNormals[12]);
        Float3 vn714 = GLES1Normal.add(surfaceNormals[6], surfaceNormals[13]);
        float vertexNormals[] = {
            vn18.x,
            vn18.y,
            vn18.z,
            vn26.x,
            vn26.y,
            vn26.z,
            vn310.x,
            vn310.y,
            vn310.z,
            vn4.x,
            vn4.y,
            vn4.z,
            vn512.x,
            vn512.y,
            vn512.z,
            vn26.x,
            vn26.y,
            vn26.z,
            vn714.x,
            vn714.y,
            vn714.z,
            vn18.x,
            vn18.y,
            vn18.z,
            vn913.x,
            vn913.y,
            vn913.z,
            vn310.x,
            vn310.y,
            vn310.z,
            vn11.x,
            vn11.y,
            vn11.z,
            vn512.x,
            vn512.y,
            vn512.z,
            vn913.x,
            vn913.y,
            vn913.z,
            vn714.x,
            vn714.y,
            vn714.z,
        };
        this.vertex.setVertexCount(vertexCount);
        this.vertex.setVertices(verticies);
        this.vertex.setColors(colors);
        this.vertex.setUVs(uvs);
        this.vertex.setNormals(vertexNormals);
        return;
    }
}
