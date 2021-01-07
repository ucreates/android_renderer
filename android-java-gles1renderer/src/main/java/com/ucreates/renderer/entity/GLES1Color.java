// ======================================================================
// Project Name    : android_renderer
//
// Copyright © 2020 U-CREATES. All rights reserved.
//
// This source code is the property of U-CREATES.
// If such findings are accepted at any time.
// We hope the tips and helpful in developing.
// ======================================================================
package com.ucreates.renderer.entity;
public class GLES1Color {
    public float r;
    public float g;
    public float b;
    public float a;
    public static final GLES1Color white = new GLES1Color(1.0f, 1.0f, 1.0f, 1.0f);
    public static final GLES1Color black = new GLES1Color(0.0f, 0.0f, 0.0f, 1.0f);
    public static final GLES1Color red = new GLES1Color(1.0f, 0.0f, 0.0f, 1.0f);
    public static final GLES1Color green = new GLES1Color(0.0f, 1.0f, 0.0f, 1.0f);
    public static final GLES1Color blue = new GLES1Color(0.0f, 0.0f, 1.0f, 1.0f);
    public GLES1Color() {
        this.r = 1.0f;
        this.g = 1.0f;
        this.b = 1.0f;
        this.a = 1.0f;
    }
    public GLES1Color(float r, float g, float b, float a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }
    public static GLES1Color red(float alpha) {
        return new GLES1Color(1.0f, 0.0f, 0.0f, alpha);
    }
    public static GLES1Color green(float alpha) {
        return new GLES1Color(0.0f, 1.0f, 0.0f, alpha);
    }
    public static GLES1Color blue(float alpha) {
        return new GLES1Color(0.0f, 0.0f, 1.0f, alpha);
    }
}
