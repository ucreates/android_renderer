package com.ucreates.renderer.shader;
import android.content.Context;
import android.opengl.GLES20;
import com.ucreates.renderer.entity.GLES2Vertex;
import java.nio.IntBuffer;
public class GLES2ProgramObject {
    public int handle;
    public int positionLocation;
    public int colorLocation;
    public int uvLocation;
    public int normalLocation;
    public String positionName;
    public String colorName;
    public String uvName;
    public String normalName;
    private GLES2Shader vertexShader;
    private GLES2Shader fragmentShader;
    public GLES2ProgramObject() {}
    public void link() {
        if (null == this.vertexShader) {
            return;
        }
        if (null == this.fragmentShader) {
            return;
        }
        this.vertexShader.compile();
        this.fragmentShader.compile();
        int handle = GLES20.glCreateProgram();
        GLES20.glAttachShader(handle, this.vertexShader.handle);
        GLES20.glAttachShader(handle, this.fragmentShader.handle);
        if (null != this.positionName) {
            GLES20.glBindAttribLocation(handle, GLES2Vertex.Attributes.POSITION, this.positionName);
        }
        if (null != this.colorName) {
            GLES20.glBindAttribLocation(handle, GLES2Vertex.Attributes.COLOR, this.colorName);
        }
        if (null != this.uvName) {
            GLES20.glBindAttribLocation(handle, GLES2Vertex.Attributes.TEXCOORD, this.uvName);
        }
        if (null != this.normalName) {
            GLES20.glBindAttribLocation(handle, GLES2Vertex.Attributes.NORMAL, this.normalName);
        }
        IntBuffer linkStatusBuffer = IntBuffer.allocate(1);
        GLES20.glLinkProgram(handle);
        GLES20.glGetProgramiv(handle, GLES20.GL_LINK_STATUS, linkStatusBuffer);
        int linkStatus = linkStatusBuffer.get(0);
        if (GLES20.GL_FALSE == linkStatus) {
            String description = GLES20.glGetProgramInfoLog(handle);
            GLES20.glDeleteProgram(handle);
            this.handle = 0;
            return;
        }
        if (null != this.positionName) {
            this.positionLocation = GLES20.glGetAttribLocation(handle, this.positionName);
        }
        if (null != this.colorName) {
            this.colorLocation = GLES20.glGetAttribLocation(handle, this.colorName);
        }
        if (null != this.uvName) {
            this.uvLocation = GLES20.glGetAttribLocation(handle, this.uvName);
        }
        if (null != this.normalName) {
            this.normalLocation = GLES20.glGetAttribLocation(handle, this.normalName);
        }
        this.handle = handle;
        return;
    }
    public void setVertexShader(String path, Context context) {
        this.vertexShader = new GLES2Shader(context);
        this.vertexShader.setShaderType(GLES20.GL_VERTEX_SHADER);
        this.vertexShader.setPath(path);
        return;
    }
    public void setFragmentShader(String path, Context context) {
        this.fragmentShader = new GLES2Shader(context);
        this.fragmentShader.setShaderType(GLES20.GL_FRAGMENT_SHADER);
        this.fragmentShader.setPath(path);
        return;
    }
    public void setPositionName(String positionName) {
        this.positionName = positionName;
        return;
    }
    public void setColorName(String colorName) {
        this.colorName = colorName;
        return;
    }
    public void setUVName(String uvName) {
        this.uvName = uvName;
        return;
    }
    public void setNormalName(String normalName) {
        this.normalName = normalName;
        return;
    }
}