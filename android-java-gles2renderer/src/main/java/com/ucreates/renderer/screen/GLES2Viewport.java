// ======================================================================
// Project Name    : android_renderer
//
// Copyright © 2020 U-CREATES. All rights reserved.
//
// This source code is the property of U-CREATES.
// If such findings are accepted at any time.
// We hope the tips and helpful in developing.
// ======================================================================
package com.ucreates.renderer.screen;
import android.content.Context;
import android.content.res.Configuration;
import android.opengl.GLES20;
import com.ucreates.renderer.camera.GLES2Camera;
public class GLES2Viewport {
    public int width;
    public int height;
    public void update(GLES2Camera camera) {
        GLES20.glViewport(0, 0, this.width, this.height);
        float r = camera.clearColor.r;
        float g = camera.clearColor.g;
        float b = camera.clearColor.b;
        float a = camera.clearColor.a;
        GLES20.glClearStencil(0);
        GLES20.glClearColor(r, g, b, a);
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT | GLES20.GL_STENCIL_BUFFER_BIT);
        return;
    }
    public void setScreenSize(Context context, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
        this.width = width;
        this.height = height;
        return;
    }
    public float getAspectRatio() {
        float aspectRatio = (float) this.width / (float) this.height;
        return aspectRatio;
    }
}
