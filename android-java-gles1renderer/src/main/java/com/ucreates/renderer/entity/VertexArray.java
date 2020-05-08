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
import java.util.ArrayList;
import java.util.Random;
public class VertexArray {
    private static final int VERTEX_COUNT_PER_TRIANGLE = 3;
    public int dimension;
    public int count;
    public FloatBuffer vertices;
    public FloatBuffer colors;
    public FloatBuffer normals;
    public FloatBuffer uvs;
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
    public void setVertices(ArrayList<Float> vertexArray) {
        Float[] vertexFloatArray = vertexArray.toArray(new Float[0]);
        float tmpVertices[] = new float[vertexFloatArray.length];
        for (int i = 0; i < vertexFloatArray.length; i++) {
            tmpVertices[i] = vertexFloatArray[i];
        }
        this.setVertices(tmpVertices);
        return;
    }
    public void setColors(float[] vertexColors) {
        this.colors = Allocator.allocateFloat(vertexColors.length);
        this.colors.put(vertexColors).position(0);
        return;
    }
    public void setColors(ArrayList<Float> colorArray) {
        Float[] colorFloatArray = colorArray.toArray(new Float[0]);
        float tmpColors[] = new float[colorFloatArray.length];
        for (int i = 0; i < colorFloatArray.length; i++) {
            tmpColors[i] = colorFloatArray[i];
        }
        this.setColors(tmpColors);
        return;
    }
    public void setNormals(float[] normalArray) {
        this.normals = Allocator.allocateFloat(normalArray.length);
        this.normals.put(normalArray).position(0);
        return;
    }
    public void setNormals(ArrayList<Float> normalArray) {
        Float[] normalFloatArray = normalArray.toArray(new Float[0]);
        float tmpNormals[] = new float[normalFloatArray.length];
        for (int i = 0; i < normalFloatArray.length; i++) {
            tmpNormals[i] = normalFloatArray[i];
        }
        this.setNormals(tmpNormals);
        return;
    }
    public void setUVs(float[] uvArray) {
        this.uvs = Allocator.allocateFloat(uvArray.length);
        this.uvs.put(uvArray).position(0);
        return;
    }
    public void setUVs(ArrayList<Float> uvArray) {
        Float[] uvFloatArray = uvArray.toArray(new Float[0]);
        float tmpUVs[] = new float[uvFloatArray.length];
        for (int i = 0; i < uvFloatArray.length; i++) {
            tmpUVs[i] = uvFloatArray[i];
        }
        this.setUVs(tmpUVs);
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
    public void setAlpha(float alpha) {
        float[] tmpColors = new float[this.colors.capacity()];
        int index = 1;
        for (int i = 0; i < tmpColors.length; i++) {
            if (0 == index % 4) {
                tmpColors[i] = alpha;
            } else {
                tmpColors[i] = this.colors.get(i);
            }
            index++;
        }
        this.colors.put(tmpColors).position(0);
        return;
    }
}
