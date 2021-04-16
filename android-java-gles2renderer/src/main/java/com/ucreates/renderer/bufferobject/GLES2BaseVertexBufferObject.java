package com.ucreates.renderer.bufferobject;
import android.opengl.GLES20;
import android.util.Log;
import com.ucreates.renderer.entity.GLES2Color;
import com.ucreates.renderer.entity.GLES2Vertex;
import com.ucreates.renderer.io.memory.GLES2Allocator;
import com.ucreates.renderer.renderer.GLES2Renderer;
import com.ucreates.renderer.shader.GLES2ProgramObject;
import com.ucreates.renderer.shader.GLES2Shader;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import java.util.ArrayList;
import java.util.Random;
public class GLES2BaseVertexBufferObject extends GLES2BaseBufferObject {
    public int dimension;
    public int count;
    public int stride;
    public int elementsCount;
    public int elementsCountPerVertex;
    public float[] vertices = new float[0];
    public float[] colors = new float[0];
    public float[] normals = new float[0];
    public float[] uvs = new float[0];
    public FloatBuffer elements;
    public GLES2BaseVertexBufferObject() {
        this.dimension = 0;
    }
    public GLES2BaseVertexBufferObject(int dimension) {
        this.dimension = dimension;
    }
    public void setVertexCount(int vertexCount) {
        this.count = vertexCount;
        return;
    }
    public void setVertices(float[] vertexArray) {
        this.vertices = vertexArray;
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
        this.colors = vertexColors;
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
        this.normals = normalArray;
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
        this.uvs = uvArray;
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
    public void setRandomColor(String unitformName, GLES2ProgramObject programObject) {
        float r = GLES2Color.getRandomColor();
        float g = GLES2Color.getRandomColor();
        float b = GLES2Color.getRandomColor();
        GLES2Shader.setUniform4f(programObject.handle, unitformName, r, g, b, 1.0f);
        return;
    }
    public void bind(GLES2ProgramObject programObject) {
        if (null != this.vertices && 0 < this.vertices.length) {
            GLES20.glEnableVertexAttribArray(programObject.positionLocation);
            GLES20.glVertexAttribPointer(programObject.positionLocation, this.dimension, GLES20.GL_FLOAT, false, this.stride, 0);
        }
        if (null != this.colors && 0 < this.colors.length) {
            int colorStartPointer = this.dimension * GLES2Allocator.GL_FLOAT_SIZE;
            GLES20.glEnableVertexAttribArray(programObject.colorLocation);
            GLES20.glVertexAttribPointer(programObject.colorLocation, GLES2Renderer.RGBA, GLES20.GL_FLOAT, false, this.stride, colorStartPointer);
        }
        if (null != this.uvs && 0 < this.uvs.length) {
            int uvStartPointer = (this.dimension + GLES2Renderer.RGBA) * GLES2Allocator.GL_FLOAT_SIZE;
            GLES20.glEnableVertexAttribArray(programObject.uvLocation);
            GLES20.glVertexAttribPointer(programObject.uvLocation, GLES2Renderer.DIMENSION2D, GLES20.GL_FLOAT, false, this.stride, uvStartPointer);
        }
        if (null != this.normals && 0 < this.normals.length) {
            int position = null != this.uvs && 0 < this.uvs.length ? this.dimension + GLES2Renderer.RGBA + GLES2Renderer.DIMENSION2D : this.dimension + GLES2Renderer.RGBA;
            int normalStartPointer = (position) *GLES2Allocator.GL_FLOAT_SIZE;
            GLES20.glEnableVertexAttribArray(programObject.normalLocation);
            GLES20.glVertexAttribPointer(programObject.normalLocation, GLES2Renderer.DIMENSION3D, GLES20.GL_FLOAT, false, this.stride, normalStartPointer);
        }
        return;
    }
    public void allocateBuffer() {
        IntBuffer intBuffer = IntBuffer.allocate(1);
        GLES20.glGenBuffers(1, intBuffer);
        int bid = intBuffer.get(0);
        int elementsCount = 0;
        int strideCount = 0;
        if (null != this.vertices && 0 < this.vertices.length) {
            strideCount += this.dimension;
            elementsCount += this.vertices.length;
        }
        if (null != this.colors && 0 < this.colors.length) {
            strideCount += GLES2Renderer.RGBA;
            elementsCount += this.colors.length;
        }
        if (null != this.uvs && 0 < this.uvs.length) {
            strideCount += GLES2Renderer.DIMENSION2D;
            elementsCount += this.uvs.length;
        }
        if (null != this.normals && 0 < this.normals.length) {
            strideCount += GLES2Renderer.DIMENSION3D;
            elementsCount += this.normals.length;
        }
        int vidx = 0;
        int cidx = 0;
        int uvidx = 0;
        int nidx = 0;
        int memsize = elementsCount;
        float[] elements = new float[memsize];
        for (int i = 0; i < elementsCount; i += strideCount) {
            if (null != this.vertices && 0 < this.vertices.length) {
                for (int idx = 0; idx < this.dimension; idx++) {
                    elements[i + idx] = this.vertices[vidx + idx];
                }
            }
            if (null != this.colors && 0 < this.colors.length) {
                int vboIndex = this.dimension;
                for (int idx = 0; idx < GLES2Renderer.RGBA; idx++) {
                    elements[i + vboIndex + idx] = this.colors[cidx + idx];
                }
            }
            if (null != this.uvs && 0 < this.uvs.length) {
                int vboIndex = this.dimension + GLES2Renderer.RGBA;
                for (int idx = 0; idx < GLES2Renderer.DIMENSION2D; idx++) {
                    elements[i + vboIndex + idx] = this.uvs[uvidx + idx];
                }
            }
            if (null != this.normals && 0 < this.normals.length) {
                int vboIndex = null != this.uvs && 0 < this.uvs.length ? this.dimension + GLES2Renderer.RGBA + GLES2Renderer.DIMENSION2D : this.dimension + GLES2Renderer.RGBA;
                for (int idx = 0; idx < GLES2Renderer.DIMENSION3D; idx++) {
                    elements[i + vboIndex + idx] = this.normals[nidx + idx];
                }
            }
            vidx += this.dimension;
            cidx += GLES2Renderer.RGBA;
            uvidx += GLES2Renderer.DIMENSION2D;
            nidx += GLES2Renderer.DIMENSION3D;
        }
        FloatBuffer elementsBuffer = GLES2Allocator.allocateFloat(elements.length);
        elementsBuffer.put(elements);
        elementsBuffer.position(0);
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, bid);
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, memsize * GLES2Allocator.GL_FLOAT_SIZE, elementsBuffer, GLES20.GL_STATIC_DRAW);
        if (null != this.vertices && 0 < this.vertices.length) {
            GLES20.glEnableVertexAttribArray(GLES2Vertex.Attributes.POSITION);
            GLES20.glVertexAttribPointer(GLES2Vertex.Attributes.POSITION, this.dimension, GLES20.GL_FLOAT, false, GLES2Allocator.GL_FLOAT_SIZE, 0);
        }
        if (null != this.colors && 0 < this.colors.length) {
            int colorStartPointer = this.dimension * GLES2Allocator.GL_FLOAT_SIZE;
            GLES20.glEnableVertexAttribArray(GLES2Vertex.Attributes.COLOR);
            GLES20.glVertexAttribPointer(GLES2Vertex.Attributes.COLOR, GLES2Renderer.RGBA, GLES20.GL_FLOAT, false, GLES2Allocator.GL_FLOAT_SIZE, colorStartPointer);
        }
        if (null != this.uvs && 0 < this.uvs.length) {
            int uvsStartPointer = (this.dimension + GLES2Renderer.RGBA) * GLES2Allocator.GL_FLOAT_SIZE;
            GLES20.glEnableVertexAttribArray(GLES2Vertex.Attributes.TEXCOORD);
            GLES20.glVertexAttribPointer(GLES2Vertex.Attributes.TEXCOORD, GLES2Renderer.DIMENSION2D, GLES20.GL_FLOAT, false, GLES2Allocator.GL_FLOAT_SIZE, uvsStartPointer);
        }
        if (null != this.normals && 0 < this.normals.length) {
            int position = null != this.uvs && 0 < this.uvs.length ? this.dimension + GLES2Renderer.RGBA + GLES2Renderer.DIMENSION2D : this.dimension + GLES2Renderer.RGBA;
            int normalStartPointer = position * GLES2Allocator.GL_FLOAT_SIZE;
            GLES20.glEnableVertexAttribArray(GLES2Vertex.Attributes.NORMAL);
            GLES20.glVertexAttribPointer(GLES2Vertex.Attributes.NORMAL, GLES2Renderer.DIMENSION3D, GLES20.GL_FLOAT, false, GLES2Allocator.GL_FLOAT_SIZE, normalStartPointer);
        }
        this.bufferId = bid;
        this.stride = strideCount * GLES2Allocator.GL_FLOAT_SIZE;
        this.elementsCount = elementsCount;
        this.elementsCountPerVertex = strideCount;
        this.elements = elementsBuffer;
        return;
    }
}
