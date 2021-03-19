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
import android.renderscript.Float3;
public class GLES2ViewTransformMatrix {
    public float[] matrix = new float[16];
    public void transform2D() {
        Matrix.setIdentityM(this.matrix, 0);
        return;
    }
    public void transform3D(Float3 eye, Float3 center, Float3 up) {
        Matrix.setLookAtM(this.matrix, 0, eye.x, eye.y, eye.z, center.x, center.y, center.z, up.x, up.y, up.z);
        return;
    }
}
