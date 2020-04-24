package com.ucreates.renderer.asset.wipe;
import android.opengl.GLES11;
import com.ucreates.renderer.entity.VertexArray;
import com.ucreates.renderer.io.memory.Allocator;
import com.ucreates.renderer.math.GLES1Angle;
import com.ucreates.renderer.renderer.GLES1Renderer;
public class WipeAsset1 extends BaseWipeAsset {
    public WipeAsset1(float radius, int divideCount, float maxScale) {
        this.radius = radius;
        this.divideCount = divideCount;
        this.maxScale = maxScale;
        this.renderMode = GLES11.GL_TRIANGLES;
    }
    @Override
    public void create2D() {
        int vertexCount = this.divideCount * 3;
        int verticesLength = vertexCount * GLES1Renderer.DIMENSION2D;
        int vertexMemsize = verticesLength * Allocator.GL_FLOAT_SIZE;
        int colorsLength = vertexCount * GLES1Renderer.RGBA;
        int colorsMemsize = colorsLength * Allocator.GL_FLOAT_SIZE;
        float[] vertices = new float[vertexMemsize];
        float[] colors = new float[colorsMemsize];
        int didx = 0;
        for (int i = 0; i < verticesLength; i += 6) {
            double unitDegree = (360.0d / this.divideCount);
            double degree1 = didx * unitDegree;
            double degree2 = (didx + 1) * unitDegree;
            double radian1 = GLES1Angle.toRadian(degree1);
            double radian2 = GLES1Angle.toRadian(degree2);
            float x1 = (float) Math.cos(radian1) * this.radius;
            float y1 = (float) Math.sin(radian1) * this.radius;
            float x2 = (float) Math.cos(radian2) * this.radius;
            float y2 = (float) Math.sin(radian2) * this.radius;
            vertices[i] = 0;
            vertices[i + 1] = 0;
            vertices[i + 2] = x1;
            vertices[i + 3] = y1;
            vertices[i + 4] = x2;
            vertices[i + 5] = y2;
            didx++;
        }
        for (int i = 0; i < colorsLength; i += 12) {
            colors[i] = this.color.r;
            colors[i + 1] = this.color.g;
            colors[i + 2] = this.color.b;
            colors[i + 3] = this.color.a;
            colors[i + 4] = this.color.r;
            colors[i + 5] = this.color.g;
            colors[i + 6] = this.color.b;
            colors[i + 7] = this.color.a;
            colors[i + 8] = this.color.r;
            colors[i + 9] = this.color.g;
            colors[i + 10] = this.color.b;
            colors[i + 11] = this.color.a;
        }
        this.vertex = new VertexArray(GLES1Renderer.DIMENSION2D);
        this.vertex.setVertexCount(vertexCount);
        this.vertex.setVertices(vertices);
        this.vertex.setColors(colors);
        return;
    }
    @Override
    public void create3D() {
        int vertexCount = this.divideCount * 3;
        int verticesLength = vertexCount * GLES1Renderer.DIMENSION3D;
        int vertexMemsize = verticesLength * Allocator.GL_FLOAT_SIZE;
        int colorsLength = vertexCount * GLES1Renderer.RGBA;
        int colorsMemsize = colorsLength * Allocator.GL_FLOAT_SIZE;
        float[] vertices = new float[vertexMemsize];
        float[] colors = new float[colorsMemsize];
        int didx = 0;
        for (int i = 0; i < verticesLength; i += 9) {
            double unitDegree = (360.0d / this.divideCount);
            double degree1 = didx * unitDegree;
            double degree2 = (didx + 1) * unitDegree;
            double radian1 = GLES1Angle.toRadian(degree1);
            double radian2 = GLES1Angle.toRadian(degree2);
            float x1 = (float) Math.cos(radian1) * this.radius;
            float y1 = (float) Math.sin(radian1) * this.radius;
            float x2 = (float) Math.cos(radian2) * this.radius;
            float y2 = (float) Math.sin(radian2) * this.radius;
            vertices[i] = 0;
            vertices[i + 1] = 0;
            vertices[i + 2] = 0;
            vertices[i + 3] = x2;
            vertices[i + 4] = y2;
            vertices[i + 5] = 0;
            vertices[i + 6] = x1;
            vertices[i + 7] = y1;
            vertices[i + 8] = 0;
            didx++;
        }
        for (int i = 0; i < colorsLength; i += 12) {
            colors[i] = this.color.r;
            colors[i + 1] = this.color.g;
            colors[i + 2] = this.color.b;
            colors[i + 3] = this.color.a;
            colors[i + 4] = this.color.r;
            colors[i + 5] = this.color.g;
            colors[i + 6] = this.color.b;
            colors[i + 7] = this.color.a;
            colors[i + 8] = this.color.r;
            colors[i + 9] = this.color.g;
            colors[i + 10] = this.color.b;
            colors[i + 11] = this.color.a;
        }
        this.vertex = new VertexArray(GLES1Renderer.DIMENSION3D);
        this.vertex.setVertexCount(vertexCount);
        this.vertex.setVertices(vertices);
        this.vertex.setColors(colors);
        return;
    }
}
