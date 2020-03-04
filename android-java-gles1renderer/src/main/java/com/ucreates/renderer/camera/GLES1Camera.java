// ======================================================================
// Project Name    : android_renderer
//
// Copyright © 2020 U-CREATES. All rights reserved.
//
// This source code is the property of U-CREATES.
// If such findings are accepted at any time.
// We hope the tips and helpful in developing.
// ======================================================================
package com.ucreates.renderer.camera;
import android.renderscript.Float3;
import com.ucreates.renderer.entity.GLESColor;
public class GLES1Camera {
    public float fov;
    public float near;
    public float far;
    public Float3 eye;
    public Float3 center;
    public Float3 up;
    public GLESColor clearColor;
    public GLES1Camera() {
        this.clearColor = GLESColor.black;
        this.near = 1.0f;
        this.far = -1.0f;
        this.fov = 0.0f;
        this.eye = new Float3();
        this.center = new Float3();
        this.up = new Float3();
    }
    public void setClippingPlane(float nearPlane, float farPlane) {
        this.near = nearPlane;
        this.far = farPlane;
        return;
    }
    public void setClear(GLESColor clearColor) {
        this.clearColor = clearColor;
        return;
    }
    public void setFOV(float fov) {
        this.fov = fov;
        return;
    }
    public void setLookAt(Float3 eye, Float3 center, Float3 up) {
        this.eye = eye;
        this.center = center;
        this.up = up;
        return;
    }
}
