// ======================================================================
// Project Name    : android_renderer
//
// Copyright © 2020 U-CREATES. All rights reserved.
//
// This source code is the property of U-CREATES.
// If such findings are accepted at any time.
// We hope the tips and helpful in developing.
// ======================================================================
package com.ucreates.renderer.asset;
import android.opengl.GLES11;
import com.ucreates.renderer.entity.GLESColor;
import com.ucreates.renderer.io.memory.Allocator;
import java.nio.FloatBuffer;
public class Material {
    private FloatBuffer ambient = null;
    private FloatBuffer diffuse = null;
    private FloatBuffer specular = null;
    public Material() {}
    public void reflect() {
        if (null != this.ambient) {
            GLES11.glMaterialfv(GLES11.GL_FRONT_AND_BACK, GLES11.GL_AMBIENT, this.ambient);
        }
        if (null != this.diffuse) {
            GLES11.glMaterialfv(GLES11.GL_FRONT_AND_BACK, GLES11.GL_DIFFUSE, this.diffuse);
        }
        if (null != this.specular) {
            GLES11.glMaterialfv(GLES11.GL_FRONT_AND_BACK, GLES11.GL_SPECULAR, this.specular);
        }
        return;
    }
    public void setAmbient(GLESColor color) {
        if (null == this.ambient) {
            float[] ambients = new float[4];
            this.ambient = Allocator.allocateFloat(ambients.length);
            this.ambient.put(ambients).position(0);
        }
        this.ambient.put(0, color.r);
        this.ambient.put(1, color.g);
        this.ambient.put(2, color.b);
        this.ambient.put(3, 1.0f);
        this.ambient.position(0);
        return;
    }
    public void setDiffuse(GLESColor color) {
        if (null == this.diffuse) {
            float[] diffuses = new float[4];
            this.diffuse = Allocator.allocateFloat(diffuses.length);
            this.diffuse.put(diffuses).position(0);
        }
        this.diffuse.put(0, color.r);
        this.diffuse.put(1, color.g);
        this.diffuse.put(2, color.b);
        this.diffuse.put(3, 1.0f);
        this.diffuse.position(0);
        return;
    }
    public void setSpecular(GLESColor color) {
        if (null == this.specular) {
            float[] specular = new float[4];
            this.specular = Allocator.allocateFloat(specular.length);
            this.specular.put(specular).position(0);
        }
        this.specular.put(0, color.r);
        this.specular.put(1, color.g);
        this.specular.put(2, color.b);
        this.specular.put(3, 1.0f);
        this.specular.position(0);
        return;
    }
}
