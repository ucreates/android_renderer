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
import android.renderscript.Float3;
import javax.microedition.khronos.opengles.GL10;
public class GLES1ModelViewTransformMatrix {
    public void transform2D() {
        GLES11.glMatrixMode(GLES11.GL_MODELVIEW);
        GLES11.glLoadIdentity();
        return;
    }
    public void transform3D(GL10 gl, Float3 eye, Float3 center, Float3 up) {
        GLES11.glMatrixMode(GLES11.GL_MODELVIEW);
        GLES11.glLoadIdentity();
        GLU.gluLookAt(gl, eye.x, eye.y, eye.z, center.x, center.y, center.z, up.x, up.y, up.z);
        return;
    }
}
