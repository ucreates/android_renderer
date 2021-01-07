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
import android.opengl.GLES11;
public class GLES1Blend {
    public int source;
    public int destination;
    public GLES1Blend() {
        this.source = GLES11.GL_ZERO;
        this.destination = GLES11.GL_ZERO;
    }
    public GLES1Blend(int source, int destination) {
        this.source = source;
        this.destination = destination;
    }
    public void normal() {
        this.source = GLES11.GL_SRC_ALPHA;
        this.destination = GLES11.GL_ONE_MINUS_SRC_ALPHA;
        return;
    }
    public void additive() {
        this.source = GLES11.GL_SRC_ALPHA;
        this.destination = GLES11.GL_ONE;
        return;
    }
    public void multiplication() {
        this.source = GLES11.GL_ZERO;
        this.destination = GLES11.GL_SRC_COLOR;
        return;
    }
}
