// ======================================================================
// Project Name    : android_renderer
//
// Copyright © 2020 U-CREATES. All rights reserved.
//
// This source code is the property of U-CREATES.
// If such findings are accepted at any time.
// We hope the tips and helpful in developing.
// ======================================================================
package com.ucreates.renderer.asset;
import android.content.Context;
import android.opengl.GLES20;
import com.ucreates.renderer.entity.GLES2Color;
import com.ucreates.renderer.entity.GLES2Transform;
import com.ucreates.renderer.entity.GLES2Vertex;
import com.ucreates.renderer.shader.GLES2ProgramObject;
import com.ucreates.renderer.shader.GLES2Shader;
import java.util.ArrayList;
public class GLES2BaseAsset {
    public static class BindCallback {
        public void OnBind(double delta) {
            return;
        }
    }
    public int renderMode;
    public GLES2Transform transform;
    public GLES2Vertex vertex;
    public GLES2TextureAsset texture;
    public GLES2ProgramObject programObject;
    protected GLES2Color color;
    protected float width;
    protected float height;
    protected float depth;
    private BindCallback callback;
    public GLES2BaseAsset() {
        this.transform = new GLES2Transform();
        this.vertex = null;
        this.texture = null;
        this.renderMode = 0;
    }
    public void create() {
        return;
    }
    public void create(String texturePath, String textureUnitName, Context context) {
        return;
    }
    public void create(String texturePath, int textureUnit, String textureUnitName, Context context) {
        return;
    }
    public void createMipmap(ArrayList<String> texturePaths, Context context) {
        return;
    }
    public void bind(double delta) {
        if (null != this.texture) {
            GLES20.glActiveTexture(this.texture.textureUnit);
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, this.texture.textureId);
            GLES2Shader.setUniform1i(this.programObject.handle, this.texture.textureUnitName, this.texture.textureNumber);
        }
        if (null != this.callback) {
            this.callback.OnBind(delta);
        }
        this.vertex.bind(this.programObject);
    }
    public void setProgramObject(GLES2ProgramObject programObject) {
        this.programObject = programObject;
        return;
    }
    public void setBindCallback(BindCallback callback) {
        this.callback = callback;
        return;
    }
}
