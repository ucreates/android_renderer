// ======================================================================
// Project Name    : behaviour
//
// Copyright © 2017 U-CREATES. All rights reserved.
//
// This source code is the property of U-CREATES.
// If such findings are accepted at any time.
// We hope the tips and helpful in developing.
// ======================================================================
package com.ucreates.renderer.shader;
import android.content.Context;
import android.opengl.GLES20;
import com.ucreates.renderer.io.file.TextStream;
import com.ucreates.renderer.io.memory.GLES2Allocator;
import java.nio.FloatBuffer;
public class GLES2Shader {
    public int handle;
    private Context context;
    private String path;
    private int shaderType;
    public GLES2Shader(Context context) {
        this.handle = 0;
        this.context = context;
    }
    public void compile() {
        TextStream stream = new TextStream(this.context);
        String source = stream.read(this.path);
        int handle = GLES20.glCreateShader(this.shaderType);
        if (0 == handle) {
            return;
        }
        GLES20.glShaderSource(handle, source);
        GLES20.glCompileShader(handle);
        int[] status = new int[1];
        GLES20.glGetShaderiv(handle, GLES20.GL_COMPILE_STATUS, status, 0);
        if (GLES20.GL_FALSE == status[0]) {
            String description = GLES20.glGetShaderInfoLog(handle);
            GLES20.glDeleteShader(handle);
            this.handle = 0;
            return;
        }
        this.handle = handle;
        return;
    }
    void setPath(String path) {
        this.path = path;
        return;
    }
    void setShaderType(int shaderType) {
        this.shaderType = shaderType;
        return;
    }
    public static void setUniform1i(int programHandle, String uniformName, int value) {
        int location = GLES20.glGetUniformLocation(programHandle, uniformName);
        GLES20.glUniform1i(location, value);
        return;
    }
    public static void setUniform1f(int programHandle, String uniformName, float value) {
        int location = GLES20.glGetUniformLocation(programHandle, uniformName);
        GLES20.glUniform1f(location, value);
        return;
    }
    public static void setUniform3f(int programHandle, String uniformName, float value1, float value2, float value3) {
        int location = GLES20.glGetUniformLocation(programHandle, uniformName);
        GLES20.glUniform3f(location, value1, value2, value3);
        return;
    }
    public static void setUniform4f(int programHandle, String uniformName, float value1, float value2, float value3, float value4) {
        int location = GLES20.glGetUniformLocation(programHandle, uniformName);
        GLES20.glUniform4f(location, value1, value2, value3, value4);
        return;
    }
    public static void setUniform3fv(int programHandle, String uniformName, float[] value) {
        int location = GLES20.glGetUniformLocation(programHandle, uniformName);
        FloatBuffer buffer = GLES2Allocator.allocateFloat(value.length);
        buffer.put(value);
        buffer.position(0);
        GLES20.glUniform3fv(location, value.length, buffer);
        return;
    }
    public static void setUniform4fv(int programHandle, String uniformName, float[] value) {
        int location = GLES20.glGetUniformLocation(programHandle, uniformName);
        FloatBuffer buffer = GLES2Allocator.allocateFloat(value.length);
        buffer.put(value);
        buffer.position(0);
        GLES20.glUniform4fv(location, value.length, buffer);
        return;
    }
    public static void setUniformMatrix3fv(int programHandle, String uniformName, float[] matrixArray) {
        int location = GLES20.glGetUniformLocation(programHandle, uniformName);
        GLES20.glUniformMatrix3fv(location, 1, false, matrixArray, 0);
        return;
    }
    public static void setUniformMatrix4fv(int programHandle, String uniformName, float[] matrixArray) {
        int location = GLES20.glGetUniformLocation(programHandle, uniformName);
        GLES20.glUniformMatrix4fv(location, 1, false, matrixArray, 0);
        return;
    }
    public static void setAttribPointer(int programHandle, String attributeName, int size, int type, int stride, int pointer) {
        int location = GLES20.glGetAttribLocation(programHandle, attributeName);
        GLES20.glEnableVertexAttribArray(location);
        GLES20.glVertexAttribPointer(location, size, type, false, stride, pointer);
        return;
    }
}