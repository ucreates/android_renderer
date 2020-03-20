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
import android.opengl.GLES11;
import android.renderscript.Float3;
import com.ucreates.renderer.asset.BaseAsset;
import com.ucreates.renderer.entity.GLESColor;
import com.ucreates.renderer.entity.VertexArray;
import com.ucreates.renderer.io.memory.Allocator;
import com.ucreates.renderer.math.GLES1Normal;
public class CubeAsset1 extends BaseAsset {
    public CubeAsset1(float width, float height, float depth, GLESColor color) {
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.color = color;
        this.renderMode = GLES11.GL_TRIANGLES;
        this.vertex = new VertexArray(3);
    }
    @Override
    public void create() {
        float verticies[] = {
            // front1
            -0.5f * this.width,
            0.5f * this.height,
            -0.5f * this.depth,
            0.5f * this.width,
            0.5f * this.height,
            -0.5f * this.depth,
            -0.5f * this.width,
            -0.5f * this.height,
            -0.5f * this.depth,
            // front2
            0.5f * this.width,
            0.5f * this.height,
            -0.5f * this.depth,
            0.5f * this.width,
            -0.5f * this.height,
            -0.5f * this.depth,
            -0.5f * this.width,
            -0.5f * this.height,
            -0.5f * this.depth,
            // back1
            -0.5f * this.width,
            0.5f * this.height,
            0.5f * this.depth,
            -0.5f * this.width,
            -0.5f * this.height,
            0.5f * this.depth,
            0.5f * this.width,
            0.5f * this.height,
            0.5f * this.depth,
            // back2
            0.5f * this.width,
            0.5f * this.height,
            0.5f * this.depth,
            -0.5f * this.width,
            -0.5f * this.height,
            0.5f * this.depth,
            0.5f * this.width,
            -0.5f * this.height,
            0.5f * this.depth,
            // right1
            0.5f * this.width,
            0.5f * this.height,
            0.5f * this.depth,
            0.5f * this.width,
            -0.5f * this.height,
            0.5f * this.depth,
            0.5f * this.width,
            0.5f * this.height,
            -0.5f * this.depth,
            // right2
            0.5f * this.width,
            0.5f * this.height,
            -0.5f * this.depth,
            0.5f * this.width,
            -0.5f * this.height,
            0.5f * this.depth,
            0.5f * this.width,
            -0.5f * this.height,
            -0.5f * this.depth,
            // left1
            -0.5f * this.width,
            0.5f * this.height,
            0.5f * this.depth,
            -0.5f * this.width,
            0.5f * this.height,
            -0.5f * this.depth,
            -0.5f * this.width,
            -0.5f * this.height,
            0.5f * this.depth,
            // left2
            -0.5f * this.width,
            0.5f * this.height,
            -0.5f * this.depth,
            -0.5f * this.width,
            -0.5f * this.height,
            -0.5f * this.depth,
            -0.5f * this.width,
            -0.5f * this.height,
            0.5f * this.depth,
            // top1
            -0.5f * this.width,
            0.5f * this.height,
            -0.5f * this.depth,
            -0.5f * this.width,
            0.5f * this.height,
            0.5f * this.depth,
            0.5f * this.width,
            0.5f * this.height,
            -0.5f * this.depth,
            // top2
            0.5f * this.width,
            0.5f * this.height,
            -0.5f * this.depth,
            -0.5f * this.width,
            0.5f * this.height,
            0.5f * this.depth,
            0.5f * this.width,
            0.5f * this.height,
            0.5f * this.depth,
            // bottom1
            -0.5f * this.width,
            -0.5f * this.height,
            -0.5f * this.depth,
            0.5f * this.width,
            -0.5f * this.height,
            -0.5f * this.depth,
            -0.5f * this.width,
            -0.5f * this.height,
            0.5f * this.depth,
            // bottom2
            0.5f * this.width,
            -0.5f * this.height,
            -0.5f * this.depth,
            0.5f * this.width,
            -0.5f * this.height,
            0.5f * this.depth,
            -0.5f * this.width,
            -0.5f * this.height,
            0.5f * this.depth,
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
        int normalsMemsize = normalsLength * Allocator.GL_FLOAT_SIZE;
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
}
