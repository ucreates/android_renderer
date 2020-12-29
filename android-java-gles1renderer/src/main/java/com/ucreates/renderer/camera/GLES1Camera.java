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
import android.opengl.GLES11;
import android.renderscript.Float3;
import com.ucreates.renderer.entity.GLESColor;
import com.ucreates.renderer.renderer.GLES1Renderer;
public class GLES1Camera {
    public float fov;
    public float orthoNear;
    public float orthoFar;
    public float perspectiveNear;
    public float perspectiveFar;
    public Float3 eye;
    public Float3 center;
    public Float3 up;
    public GLESColor clearColor;
    public GLES1Camera() {
        this.clearColor = GLESColor.black;
        this.orthoNear = 1.0f;
        this.orthoFar = -1.0f;
        this.perspectiveNear = 0.1f;
        this.perspectiveFar = 100.0f;
        this.fov = 0.0f;
        this.eye = new Float3();
        this.center = new Float3();
        this.up = new Float3();
    }
    public void setClippingPlane(float nearPlane, float farPlane, int dimension) {
        if (dimension == GLES1Renderer.DIMENSION2D) {
            this.orthoNear = nearPlane;
            this.orthoFar = farPlane;
        } else {
            this.perspectiveNear = nearPlane;
            this.perspectiveFar = farPlane;
        }
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
