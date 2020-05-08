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
public class GLESColor {
    public float r;
    public float g;
    public float b;
    public float a;
    public static final GLESColor white = new GLESColor(1.0f, 1.0f, 1.0f, 1.0f);
    public static final GLESColor black = new GLESColor(0.0f, 0.0f, 0.0f, 1.0f);
    public static final GLESColor red = new GLESColor(1.0f, 0.0f, 0.0f, 1.0f);
    public static final GLESColor green = new GLESColor(0.0f, 1.0f, 0.0f, 1.0f);
    public static final GLESColor blue = new GLESColor(0.0f, 0.0f, 1.0f, 1.0f);
    public GLESColor() {
        this.r = 1.0f;
        this.g = 1.0f;
        this.b = 1.0f;
        this.a = 1.0f;
    }
    public GLESColor(float r, float g, float b, float a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }
    public static GLESColor red(float alpha) {
        return new GLESColor(1.0f, 0.0f, 0.0f, alpha);
    }
    public static GLESColor green(float alpha) {
        return new GLESColor(0.0f, 1.0f, 0.0f, alpha);
    }
    public static GLESColor blue(float alpha) {
        return new GLESColor(0.0f, 0.0f, 1.0f, alpha);
    }
}
