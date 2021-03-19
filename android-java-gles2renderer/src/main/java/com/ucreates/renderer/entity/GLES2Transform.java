// ======================================================================
// Project Name    :
//
// Copyright © 2017 U-CREATES. All rights reserved.
//
// This source code is the property of U-CREATES.
// If such findings are accepted at any time.
// We hope the tips and helpful in developing.
// ======================================================================
package com.ucreates.renderer.entity;
import android.renderscript.Float3;
import android.renderscript.Matrix4f;
public class GLES2Transform {
    public Float3 position = new Float3(0f, 0f, 0f);
    public Float3 scale = new Float3(1f, 1f, 1f);
    public Float3 rotation = new Float3(0f, 0f, 0f);
    public Matrix4f getMatrix() {
        Matrix4f matrix = new Matrix4f();
        matrix.translate(this.position.x, this.position.y, this.position.z);
        matrix.rotate(this.rotation.x, 1.0f, 0.0f, 0.0f);
        matrix.rotate(this.rotation.y, 0.0f, 1.0f, 0.0f);
        matrix.rotate(this.rotation.z, 0.0f, 0.0f, 1.0f);
        matrix.scale(this.scale.x, this.scale.y, this.scale.z);
        return matrix;
    }
    public void setPosition(float x, float y, float z) {
        this.position.x = x;
        this.position.y = y;
        this.position.z = z;
        return;
    }
    public void setScale(float x, float y, float z) {
        this.scale.x = x;
        this.scale.y = y;
        this.scale.z = z;
        return;
    }
    public void setRotation(float x, float y, float z) {
        this.rotation.x = x;
        this.rotation.y = y;
        this.rotation.z = z;
        return;
    }
}