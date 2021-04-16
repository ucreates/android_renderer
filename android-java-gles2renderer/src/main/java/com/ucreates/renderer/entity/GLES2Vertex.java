package com.ucreates.renderer.entity;
import com.ucreates.renderer.bufferobject.GLES2BaseVertexBufferObject;
import com.ucreates.renderer.bufferobject.GLES2IndexBufferObject;
import com.ucreates.renderer.bufferobject.GLES2VertexBufferObject;
import com.ucreates.renderer.shader.GLES2ProgramObject;
import java.nio.FloatBuffer;
public class GLES2Vertex {
    public class Attributes {
        public static final int POSITION = 0;
        public static final int COLOR = 1;
        public static final int TEXCOORD = 2;
        public static final int NORMAL = 3;
    }
    private GLES2BaseVertexBufferObject vo;
    public GLES2IndexBufferObject ibo;
    public GLES2Vertex(int dimension) {
        this.vo = new GLES2VertexBufferObject(dimension);
    }
    public void allocateBuffer() {
        this.vo.allocateBuffer();
        if (null != this.ibo) {
            this.ibo.allocateBuffer();
        }
        return;
    }
    public void bind(GLES2ProgramObject programObject) {
        this.vo.bind(programObject);
        if (null != this.ibo) {
            this.ibo.bind();
        }
        return;
    }
    public void setVertexCount(int count) {
        this.vo.setVertexCount(count);
    }
    public void setVertices(float[] vertices) {
        this.vo.setVertices(vertices);
    }
    public void setColors(float[] colors) {
        this.vo.setColors(colors);
    }
    public void setUVs(float[] uvs) {
        this.vo.setUVs(uvs);
    }
    public void setNormals(float[] normals) {
        this.vo.setNormals(normals);
    }
    public void setIndicies(short[] indiciesArray) {
        this.ibo = new GLES2IndexBufferObject();
        this.ibo.setIndicies(indiciesArray);
        return;
    }
    public void setRandomColor(String uniformName, GLES2ProgramObject programObject) {
        this.vo.setRandomColor(uniformName, programObject);
    }
    public int getDimension() {
        return this.vo.dimension;
    }
    public int getCount() {
        return this.vo.count;
    }
    public int getIndexCount() {
        int count = 0;
        if (null != this.ibo) {
            count = this.ibo.count;
        }
        return count;
    }
    public FloatBuffer getVerticies() {
        return this.vo.elements;
    }
}
