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
import java.util.ArrayList;
public class GLES1CubeAsset1 extends GLES1BaseAsset {
    public GLES1CubeAsset1(float width, float height, float depth, GLES1Color color) {
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.color = color;
        this.renderMode = GLES11.GL_TRIANGLES;
        this.vertex = new GLES1VertexArray(3);
    }
    @Override
    public void create() {
        float x = 0.5f * this.width;
        float y = 0.5f * this.height;
        float z = 0.5f * this.depth;
        float verticies[] = {
            // front1
            -x,
            y,
            -z,
            x,
            y,
            -z,
            -x,
            -y,
            -z,
            // front2
            x,
            y,
            -z,
            x,
            -y,
            -z,
            -x,
            -y,
            -z,
            // back1
            -x,
            y,
            z,
            -x,
            -y,
            z,
            x,
            y,
            z,
            // back2
            x,
            y,
            z,
            -x,
            -y,
            z,
            x,
            -y,
            z,
            // right1
            x,
            y,
            z,
            x,
            -y,
            z,
            x,
            y,
            -z,
            // right2
            x,
            y,
            -z,
            x,
            -y,
            z,
            x,
            -y,
            -z,
            // left1
            -x,
            y,
            z,
            -x,
            y,
            -z,
            -x,
            -y,
            z,
            // left2
            -x,
            y,
            -z,
            -x,
            -y,
            -z,
            -x,
            -y,
            z,
            // top1
            -x,
            y,
            -z,
            -x,
            y,
            z,
            x,
            y,
            -z,
            // top2
            x,
            y,
            -z,
            -x,
            y,
            z,
            x,
            y,
            z,
            // bottom1
            -x,
            -y,
            -z,
            x,
            -y,
            -z,
            -x,
            -y,
            z,
            // bottom2
            x,
            -y,
            -z,
            x,
            -y,
            z,
            -x,
            -y,
            z,
        };
        float vertexColors[] = {
            // front
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
            // back
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
            // left
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
            // right
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
            // top
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
            // bottom
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
        int normalsMemsize = normalsLength * GLES1Allocator.GL_FLOAT_SIZE;
        float[] normals = new float[normalsMemsize];
        for (int i = 0; i < verticies.length; i += 9) {
            float x1 = verticies[i];
            float y1 = verticies[i + 1];
            float z1 = verticies[i + 2];
            float x2 = verticies[i + 3];
            float y2 = verticies[i + 4];
            float z2 = verticies[i + 5];
            float x3 = verticies[i + 6];
            float y3 = verticies[i + 7];
            float z3 = verticies[i + 8];
            Float3 triangleNormal = GLES1Normal.toNormal(x1, y1, z1, x2, y2, z2, x3, y3, z3);
            normals[i] = triangleNormal.x;
            normals[i + 1] = triangleNormal.y;
            normals[i + 2] = triangleNormal.z;
            normals[i + 3] = triangleNormal.x;
            normals[i + 4] = triangleNormal.y;
            normals[i + 5] = triangleNormal.z;
            normals[i + 6] = triangleNormal.x;
            normals[i + 7] = triangleNormal.y;
            normals[i + 8] = triangleNormal.z;
        }
        this.vertex.setVertexCount(vertexCount);
        this.vertex.setVertices(verticies);
        this.vertex.setColors(vertexColors);
        this.vertex.setNormals(normals);
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
            // front1
            -x,
            y,
            -z,
            x,
            y,
            -z,
            -x,
            -y,
            -z,
            // front2
            x,
            y,
            -z,
            x,
            -y,
            -z,
            -x,
            -y,
            -z,
            // back1
            -x,
            y,
            z,
            -x,
            -y,
            z,
            x,
            y,
            z,
            // back2
            x,
            y,
            z,
            -x,
            -y,
            z,
            x,
            -y,
            z,
            // right1
            x,
            y,
            z,
            x,
            -y,
            z,
            x,
            y,
            -z,
            // right2
            x,
            y,
            -z,
            x,
            -y,
            z,
            x,
            -y,
            -z,
            // left1
            -x,
            y,
            z,
            -x,
            y,
            -z,
            -x,
            -y,
            z,
            // left2
            -x,
            y,
            -z,
            -x,
            -y,
            -z,
            -x,
            -y,
            z,
            // top1
            -x,
            y,
            -z,
            -x,
            y,
            z,
            x,
            y,
            -z,
            // top2
            x,
            y,
            -z,
            -x,
            y,
            z,
            x,
            y,
            z,
            // bottom1
            -x,
            -y,
            -z,
            x,
            -y,
            -z,
            -x,
            -y,
            z,
            // bottom2
            x,
            -y,
            -z,
            x,
            -y,
            z,
            -x,
            -y,
            z,
        };
        float vertexColors[] = {
            // front
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
            // back
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
            // left
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
            // right
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
            // top
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
            // bottom
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
            0.0f,
            0.0f,
            1.0f,
            0.0f,
            0.0f,
            1.0f,
            1.0f,
            0.0f,
            1.0f,
            1.0f,
            0.0f,
            1.0f,
            // back
            0.0f,
            0.0f,
            0.0f,
            1.0f,
            1.0f,
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
            0.0f,
            1.0f,
            1.0f,
            0.0f,
            1.0f,
            0.0f,
            0.0f,
            1.0f,
            1.0f,
            1.0f,
            // left
            0.0f,
            0.0f,
            1.0f,
            0.0f,
            0.0f,
            1.0f,
            1.0f,
            0.0f,
            1.0f,
            1.0f,
            0.0f,
            1.0f,
            // top
            0.0f,
            0.0f,
            0.0f,
            1.0f,
            1.0f,
            0.0f,
            1.0f,
            0.0f,
            0.0f,
            1.0f,
            1.0f,
            1.0f,
            // bottom
            0.0f,
            0.0f,
            1.0f,
            0.0f,
            0.0f,
            1.0f,
            1.0f,
            0.0f,
            1.0f,
            1.0f,
            0.0f,
            1.0f,
        };
        int vertexCount = verticies.length / this.vertex.dimension;
        int normalsLength = verticies.length;
        int normalsMemsize = normalsLength * GLES1Allocator.GL_FLOAT_SIZE;
        float[] normals = new float[normalsMemsize];
        for (int i = 0; i < verticies.length; i += 9) {
            float x1 = verticies[i];
            float y1 = verticies[i + 1];
            float z1 = verticies[i + 2];
            float x2 = verticies[i + 3];
            float y2 = verticies[i + 4];
            float z2 = verticies[i + 5];
            float x3 = verticies[i + 6];
            float y3 = verticies[i + 7];
            float z3 = verticies[i + 8];
            Float3 triangleNormal = GLES1Normal.toNormal(x1, y1, z1, x2, y2, z2, x3, y3, z3);
            normals[i] = triangleNormal.x;
            normals[i + 1] = triangleNormal.y;
            normals[i + 2] = triangleNormal.z;
            normals[i + 3] = triangleNormal.x;
            normals[i + 4] = triangleNormal.y;
            normals[i + 5] = triangleNormal.z;
            normals[i + 6] = triangleNormal.x;
            normals[i + 7] = triangleNormal.y;
            normals[i + 8] = triangleNormal.z;
        }
        this.vertex.setVertexCount(vertexCount);
        this.vertex.setVertices(verticies);
        this.vertex.setColors(vertexColors);
        this.vertex.setNormals(normals);
        this.vertex.setUVs(uvs);
        return;
    }
    @Override
    public void createMipmap(ArrayList<String> texturePaths, Context context) {
        this.texture = new GLES1TextureAsset();
        this.texture.loadMipmap(texturePaths, context);
        float x = 0.5f * this.width;
        float y = 0.5f * this.height;
        float z = 0.5f * this.depth;
        float verticies[] = {
            // front1
            -x,
            y,
            -z,
            x,
            y,
            -z,
            -x,
            -y,
            -z,
            // front2
            x,
            y,
            -z,
            x,
            -y,
            -z,
            -x,
            -y,
            -z,
            // back1
            -x,
            y,
            z,
            -x,
            -y,
            z,
            x,
            y,
            z,
            // back2
            x,
            y,
            z,
            -x,
            -y,
            z,
            x,
            -y,
            z,
            // right1
            x,
            y,
            z,
            x,
            -y,
            z,
            x,
            y,
            -z,
            // right2
            x,
            y,
            -z,
            x,
            -y,
            z,
            x,
            -y,
            -z,
            // left1
            -x,
            y,
            z,
            -x,
            y,
            -z,
            -x,
            -y,
            z,
            // left2
            -x,
            y,
            -z,
            -x,
            -y,
            -z,
            -x,
            -y,
            z,
            // top1
            -x,
            y,
            -z,
            -x,
            y,
            z,
            x,
            y,
            -z,
            // top2
            x,
            y,
            -z,
            -x,
            y,
            z,
            x,
            y,
            z,
            // bottom1
            -x,
            -y,
            -z,
            x,
            -y,
            -z,
            -x,
            -y,
            z,
            // bottom2
            x,
            -y,
            -z,
            x,
            -y,
            z,
            -x,
            -y,
            z,
        };
        float vertexColors[] = {
            // front
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
            // back
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
            // left
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
            // right
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
            // top
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
            // bottom
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
            0.0f,
            0.0f,
            1.0f,
            0.0f,
            0.0f,
            1.0f,
            1.0f,
            0.0f,
            1.0f,
            1.0f,
            0.0f,
            1.0f,
            // back
            0.0f,
            0.0f,
            0.0f,
            1.0f,
            1.0f,
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
            0.0f,
            1.0f,
            1.0f,
            0.0f,
            1.0f,
            0.0f,
            0.0f,
            1.0f,
            1.0f,
            1.0f,
            // left
            0.0f,
            0.0f,
            1.0f,
            0.0f,
            0.0f,
            1.0f,
            1.0f,
            0.0f,
            1.0f,
            1.0f,
            0.0f,
            1.0f,
            // top
            0.0f,
            0.0f,
            0.0f,
            1.0f,
            1.0f,
            0.0f,
            1.0f,
            0.0f,
            0.0f,
            1.0f,
            1.0f,
            1.0f,
            // bottom
            0.0f,
            0.0f,
            1.0f,
            0.0f,
            0.0f,
            1.0f,
            1.0f,
            0.0f,
            1.0f,
            1.0f,
            0.0f,
            1.0f,
        };
        int vertexCount = verticies.length / this.vertex.dimension;
        int normalsLength = verticies.length;
        int normalsMemsize = normalsLength * GLES1Allocator.GL_FLOAT_SIZE;
        float[] normals = new float[normalsMemsize];
        for (int i = 0; i < verticies.length; i += 9) {
            float x1 = verticies[i];
            float y1 = verticies[i + 1];
            float z1 = verticies[i + 2];
            float x2 = verticies[i + 3];
            float y2 = verticies[i + 4];
            float z2 = verticies[i + 5];
            float x3 = verticies[i + 6];
            float y3 = verticies[i + 7];
            float z3 = verticies[i + 8];
            Float3 triangleNormal = GLES1Normal.toNormal(x1, y1, z1, x2, y2, z2, x3, y3, z3);
            normals[i] = triangleNormal.x;
            normals[i + 1] = triangleNormal.y;
            normals[i + 2] = triangleNormal.z;
            normals[i + 3] = triangleNormal.x;
            normals[i + 4] = triangleNormal.y;
            normals[i + 5] = triangleNormal.z;
            normals[i + 6] = triangleNormal.x;
            normals[i + 7] = triangleNormal.y;
            normals[i + 8] = triangleNormal.z;
        }
        this.vertex.setVertexCount(vertexCount);
        this.vertex.setVertices(verticies);
        this.vertex.setColors(vertexColors);
        this.vertex.setNormals(normals);
        this.vertex.setUVs(uvs);
        return;
    }
}
