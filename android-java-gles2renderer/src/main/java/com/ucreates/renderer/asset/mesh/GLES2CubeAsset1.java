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
import android.opengl.GLES20;
import android.renderscript.Float3;
import com.ucreates.renderer.asset.GLES2BaseAsset;
import com.ucreates.renderer.asset.GLES2TextureAsset;
import com.ucreates.renderer.entity.GLES2Color;
import com.ucreates.renderer.entity.GLES2Vertex;
import com.ucreates.renderer.io.memory.GLES2Allocator;
import com.ucreates.renderer.math.GLES2Normal;
import com.ucreates.renderer.renderer.GLES2Renderer;
import java.util.ArrayList;
public class GLES2CubeAsset1 extends GLES2BaseAsset {
    public GLES2CubeAsset1(float width, float height, float depth, GLES2Color color) {
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.color = color;
        this.renderMode = GLES20.GL_TRIANGLES;
        this.vertex = new GLES2Vertex(GLES2Renderer.DIMENSION3D);
    }
    @Override
    public void create() {
        float x = 0.5f * this.width;
        float y = 0.5f * this.height;
        float z = 0.5f * this.depth;
        float vertices[] = {
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
        int vertexCount = vertices.length / this.vertex.getDimension();
        int normalsLength = vertices.length;
        int normalsMemsize = normalsLength;
        float[] normals = new float[normalsMemsize];
        for (int i = 0; i < vertices.length; i += 9) {
            float x1 = vertices[i];
            float y1 = vertices[i + 1];
            float z1 = vertices[i + 2];
            float x2 = vertices[i + 3];
            float y2 = vertices[i + 4];
            float z2 = vertices[i + 5];
            float x3 = vertices[i + 6];
            float y3 = vertices[i + 7];
            float z3 = vertices[i + 8];
            Float3 triangleNormal = GLES2Normal.toNormal(x1, y1, z1, x2, y2, z2, x3, y3, z3);
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
        this.vertex.setVertices(vertices);
        this.vertex.setColors(vertexColors);
        this.vertex.setNormals(normals);
        this.vertex.allocateBuffer();
        return;
    }
    @Override
    public void create(String texturePath, String textureUnitName, Context context) {
        this.create(texturePath, GLES20.GL_TEXTURE0, textureUnitName, context);
        return;
    }
    @Override
    public void create(String texturePath, int textureUnit, String textureUnitName, Context context) {
        this.texture = new GLES2TextureAsset();
        this.texture.setTextureUnit(textureUnitName, textureUnit);
        this.texture.load(texturePath, textureUnit, context);
        float x = 0.5f * this.width;
        float y = 0.5f * this.height;
        float z = 0.5f * this.depth;
        float vertices[] = {
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
        int vertexCount = vertices.length / this.vertex.getDimension();
        int normalsLength = vertices.length;
        int normalsMemsize = normalsLength;
        float[] normals = new float[normalsMemsize];
        for (int i = 0; i < vertices.length; i += 9) {
            float x1 = vertices[i];
            float y1 = vertices[i + 1];
            float z1 = vertices[i + 2];
            float x2 = vertices[i + 3];
            float y2 = vertices[i + 4];
            float z2 = vertices[i + 5];
            float x3 = vertices[i + 6];
            float y3 = vertices[i + 7];
            float z3 = vertices[i + 8];
            Float3 triangleNormal = GLES2Normal.toNormal(x1, y1, z1, x2, y2, z2, x3, y3, z3);
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
        this.vertex.setVertices(vertices);
        this.vertex.setColors(vertexColors);
        this.vertex.setNormals(normals);
        this.vertex.setUVs(uvs);
        this.vertex.allocateBuffer();
        return;
    }
    @Override
    public void createMipmap(ArrayList<String> texturePaths, Context context) {
        this.texture = new GLES2TextureAsset();
        this.texture.loadMipmap(texturePaths, context);
        float x = 0.5f * this.width;
        float y = 0.5f * this.height;
        float z = 0.5f * this.depth;
        float vertices[] = {
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
        int vertexCount = vertices.length / this.vertex.getDimension();
        int normalsLength = vertices.length;
        int normalsMemsize = normalsLength;
        float[] normals = new float[normalsMemsize];
        for (int i = 0; i < vertices.length; i += 9) {
            float x1 = vertices[i];
            float y1 = vertices[i + 1];
            float z1 = vertices[i + 2];
            float x2 = vertices[i + 3];
            float y2 = vertices[i + 4];
            float z2 = vertices[i + 5];
            float x3 = vertices[i + 6];
            float y3 = vertices[i + 7];
            float z3 = vertices[i + 8];
            Float3 triangleNormal = GLES2Normal.toNormal(x1, y1, z1, x2, y2, z2, x3, y3, z3);
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
        this.vertex.setVertices(vertices);
        this.vertex.setColors(vertexColors);
        this.vertex.setNormals(normals);
        this.vertex.setUVs(uvs);
        this.vertex.allocateBuffer();
        return;
    }
}
