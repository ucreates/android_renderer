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
import java.util.Random;
public class GLES2Color {
    public float r;
    public float g;
    public float b;
    public float a;
    public static final GLES2Color white = new GLES2Color(1.0f, 1.0f, 1.0f, 1.0f);
    public static final GLES2Color black = new GLES2Color(0.0f, 0.0f, 0.0f, 1.0f);
    public static final GLES2Color red = new GLES2Color(1.0f, 0.0f, 0.0f, 1.0f);
    public static final GLES2Color green = new GLES2Color(0.0f, 1.0f, 0.0f, 1.0f);
    public static final GLES2Color blue = new GLES2Color(0.0f, 0.0f, 1.0f, 1.0f);
    public GLES2Color() {
        this.r = 1.0f;
        this.g = 1.0f;
        this.b = 1.0f;
        this.a = 1.0f;
    }
    public GLES2Color(float r, float g, float b, float a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }
    public static float getRandomColor() {
        Random rand = new Random();
        int color = rand.nextInt(255);
        return (float) color / 255.0f;
    }
    public static GLES2Color red(float alpha) {
        return new GLES2Color(1.0f, 0.0f, 0.0f, alpha);
    }
    public static GLES2Color green(float alpha) {
        return new GLES2Color(0.0f, 1.0f, 0.0f, alpha);
    }
    public static GLES2Color blue(float alpha) {
        return new GLES2Color(0.0f, 0.0f, 1.0f, alpha);
    }
}
