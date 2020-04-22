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
import android.opengl.GLES11;
public class GLES1ShaderAsset {
    private int shaderType = GLES11.GL_SMOOTH;
    public void setFlat() {
        this.shaderType = GLES11.GL_FLAT;
        return;
    }
    public void setPhong() {
        this.shaderType = GLES11.GL_SMOOTH;
        return;
    }
    public void shade() {
        GLES11.glShadeModel(this.shaderType);
        return;
    }
}
