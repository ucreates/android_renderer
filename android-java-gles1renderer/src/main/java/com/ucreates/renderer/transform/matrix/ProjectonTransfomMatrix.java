// ======================================================================
// Project Name    : android_renderer
//
// Copyright © 2020 U-CREATES. All rights reserved.
//
// This source code is the property of U-CREATES.
// If such findings are accepted at any time.
// We hope the tips and helpful in developing.
// ======================================================================
package com.ucreates.renderer.transform.matrix;
import android.opengl.GLES11;
import android.opengl.GLU;
import javax.microedition.khronos.opengles.GL10;
public class ProjectonTransfomMatrix {
    public void transform2D(float aspectRatio, float near, float far) {
        float left = aspectRatio * -1.0f;
        float right = aspectRatio;
        float top = 1.0f;
        float bottom = -1.0f;
        GLES11.glMatrixMode(GLES11.GL_PROJECTION);
        GLES11.glLoadIdentity();
        GLES11.glOrthof(left, right, bottom, top, near, far);
        return;
    }
    public void transform3D(GL10 gl, float fov, float aspectRatio, float near, float far) {
        GLES11.glMatrixMode(GLES11.GL_PROJECTION);
        GLES11.glLoadIdentity();
        GLU.gluPerspective(gl, fov, aspectRatio, near, far);
        return;
    }
}
