// ======================================================================
// Project Name    : android_renderer
//
// Copyright © 2020 U-CREATES. All rights reserved.
//
// This source code is the property of U-CREATES.
// If such findings are accepted at any time.
// We hope the tips and helpful in developing.
// ======================================================================
package com.ucreates.renderer.entity;
import com.ucreates.renderer.io.memory.Allocator;
import com.ucreates.renderer.renderer.GLES1Renderer;
import java.nio.FloatBuffer;
import java.util.Random;
public class VertexArray {
    private static final int VERTEX_COUNT_PER_TRIANGLE = 3;
    public int dimension;
    public int count;
    public FloatBuffer vertices;
    public FloatBuffer colors;
    public FloatBuffer normals;
    public VertexArray(int dimension) {
        this.dimension = dimension;
    }
    public void setVertexCount(int vertexCount) {
        this.count = vertexCount;
        return;
    }
    public void setVertices(float[] vertexArray) {
        this.vertices = Allocator.allocateFloat(vertexArray.length);
        this.vertices.put(vertexArray).position(0);
        return;
    }
    public void setColors(float[] vertexColors) {
        this.colors = Allocator.allocateFloat(vertexColors.length);
        this.colors.put(vertexColors).position(0);
        return;
    }
    public void setNormals(float[] vertexArray) {
        if (0 == this.count || GLES1Renderer.DIMENSION2D == this.dimension) {
            return;
        }
        int memsize = this.count * GLES1Renderer.DIMENSION3D * 4;
        float[] normalArray = new float[memsize];
        int triangleCount = vertexArray.length / (VertexArray.VERTEX_COUNT_PER_TRIANGLE * this.dimension);
        int vidx = 0;
        for (int i = 0; i < triangleCount; i++) {
            float x1 = vertexArray[vidx];
            float y1 = vertexArray[vidx + 1];
            float z1 = vertexArray[vidx + 2];
            float x2 = vertexArray[vidx + 3];
            float y2 = vertexArray[vidx + 4];
            float z2 = vertexArray[vidx + 5];
            float x3 = vertexArray[vidx + 6];
            float y3 = vertexArray[vidx + 7];
            float z3 = vertexArray[vidx + 8];
            float vx1 = x2 - x1;
            float vy1 = y2 - y1;
            float vz1 = z2 - z1;
            float vx2 = x3 - x1;
            float vy2 = y3 - y1;
            float vz2 = z3 - z1;
            float nx = vy1 * vz2 - vz1 * vy2;
            float ny = vz1 * vx2 - vx1 * vz2;
            float nz = vx1 * vy2 - vy1 * vx2;
            normalArray[vidx] = nx;
            normalArray[vidx + 1] = ny;
            normalArray[vidx + 2] = nz;
            normalArray[vidx + 3] = nx;
            normalArray[vidx + 4] = ny;
            normalArray[vidx + 5] = nz;
            normalArray[vidx + 6] = nx;
            normalArray[vidx + 7] = ny;
            normalArray[vidx + 8] = nz;
            vidx += 9;
            String log = String.format("normal vector.x::%f,vector.y::%f,vector.z::%f", nx, ny, nz);
            android.util.Log.v("ANDROID_RENDERER", log);
        }
        this.normals = Allocator.allocateFloat(normalArray.length);
        this.normals.put(normalArray).position(0);
        return;
    }
    public void setRandomColor() {
        float[] tmpColors = new float[this.colors.capacity()];
        Random rand = new Random();
        for (int i = 0; i < tmpColors.length; i++) {
            int color = rand.nextInt(255);
            tmpColors[i] = (float) color / 255.0f;
        }
        this.colors.put(tmpColors).position(0);
        return;
    }
}
