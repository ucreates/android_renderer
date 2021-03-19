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
import android.opengl.Matrix;
public class GLES2ProjectonTransfomMatrix {
    public float[] matrix = new float[16];
    public void transform2D(float aspectRatio, float near, float far) {
        float left = aspectRatio * -1.0f;
        float right = aspectRatio;
        float top = 1.0f;
        float bottom = -1.0f;
        Matrix.orthoM(this.matrix, 0, left, right, bottom, top, near, far);
        return;
    }
    public void transform3D(float fov, float aspectRatio, float near, float far) {
        Matrix.perspectiveM(this.matrix, 0, fov, aspectRatio, near, far);
        return;
    }
}
