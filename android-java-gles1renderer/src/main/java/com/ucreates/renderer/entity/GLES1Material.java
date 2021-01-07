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
import android.content.Context;
import android.opengl.GLES11;
import com.ucreates.renderer.asset.GLES1TextureAsset;
import com.ucreates.renderer.io.memory.GLES1Allocator;
import java.nio.FloatBuffer;
public class GLES1Material {
    private FloatBuffer ambient = null;
    private FloatBuffer diffuse = null;
    private FloatBuffer specular = null;
    public String name = "";
    public boolean hasTexture = false;
    public GLES1TextureAsset diffuseTexture = null;
    public GLES1TextureAsset ambientTexture = null;
    public GLES1TextureAsset normalTexture = null;
    public GLES1Material() {}
    public void enable() {
        if (false != this.hasTexture) {
            GLES11.glClientActiveTexture(GLES11.GL_TEXTURE0);
            GLES11.glActiveTexture(GLES11.GL_TEXTURE0);
            GLES11.glEnable(GLES11.GL_TEXTURE_2D);
        }
        if (null != this.ambientTexture) {
            GLES11.glClientActiveTexture(GLES11.GL_TEXTURE1);
            GLES11.glActiveTexture(GLES11.GL_TEXTURE1);
            GLES11.glEnable(GLES11.GL_TEXTURE_2D);
        }
        if (null != this.diffuseTexture) {
            GLES11.glClientActiveTexture(GLES11.GL_TEXTURE2);
            GLES11.glActiveTexture(GLES11.GL_TEXTURE2);
            GLES11.glEnable(GLES11.GL_TEXTURE_2D);
        }
    }
    public void disable() {
        if (false != this.hasTexture) {
            GLES11.glClientActiveTexture(GLES11.GL_TEXTURE0);
            GLES11.glActiveTexture(GLES11.GL_TEXTURE0);
            GLES11.glDisableClientState(GLES11.GL_TEXTURE_COORD_ARRAY);
            GLES11.glDisable(GLES11.GL_TEXTURE_2D);
        }
        if (null != this.ambientTexture) {
            GLES11.glClientActiveTexture(GLES11.GL_TEXTURE1);
            GLES11.glActiveTexture(GLES11.GL_TEXTURE1);
            GLES11.glDisableClientState(GLES11.GL_TEXTURE_COORD_ARRAY);
            GLES11.glDisable(GLES11.GL_TEXTURE_2D);
        }
        if (null != this.diffuseTexture) {
            GLES11.glClientActiveTexture(GLES11.GL_TEXTURE2);
            GLES11.glActiveTexture(GLES11.GL_TEXTURE2);
            GLES11.glDisableClientState(GLES11.GL_TEXTURE_COORD_ARRAY);
            GLES11.glDisable(GLES11.GL_TEXTURE_2D);
        }
    }
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
        if (null != this.normalTexture) {
            GLES11.glClientActiveTexture(GLES11.GL_TEXTURE0);
            GLES11.glActiveTexture(GLES11.GL_TEXTURE0);
            GLES11.glBindTexture(GLES11.GL_TEXTURE_2D, this.normalTexture.textureId);
            GLES11.glTexEnvi(GLES11.GL_TEXTURE_ENV, GLES11.GL_TEXTURE_ENV_MODE, GLES11.GL_COMBINE);
            GLES11.glTexEnvi(GLES11.GL_TEXTURE_ENV, GLES11.GL_COMBINE_RGB, GLES11.GL_DOT3_RGB);
            GLES11.glTexEnvi(GLES11.GL_TEXTURE_ENV, GLES11.GL_SRC0_RGB, GLES11.GL_PREVIOUS);
            GLES11.glTexEnvi(GLES11.GL_TEXTURE_ENV, GLES11.GL_OPERAND0_RGB, GLES11.GL_SRC_COLOR);
            GLES11.glTexEnvi(GLES11.GL_TEXTURE_ENV, GLES11.GL_SRC1_RGB, GLES11.GL_TEXTURE);
            GLES11.glTexEnvi(GLES11.GL_TEXTURE_ENV, GLES11.GL_OPERAND1_RGB, GLES11.GL_SRC_COLOR);
        }
        if (null != this.ambientTexture) {
            GLES11.glClientActiveTexture(GLES11.GL_TEXTURE1);
            GLES11.glActiveTexture(GLES11.GL_TEXTURE1);
            GLES11.glBindTexture(GLES11.GL_TEXTURE_2D, this.ambientTexture.textureId);
            GLES11.glTexEnvi(GLES11.GL_TEXTURE_ENV, GLES11.GL_TEXTURE_ENV_MODE, GLES11.GL_MODULATE);
        }
        if (null != this.diffuseTexture) {
            GLES11.glClientActiveTexture(GLES11.GL_TEXTURE2);
            GLES11.glActiveTexture(GLES11.GL_TEXTURE2);
            GLES11.glBindTexture(GLES11.GL_TEXTURE_2D, this.diffuseTexture.textureId);
            GLES11.glTexEnvi(GLES11.GL_TEXTURE_ENV, GLES11.GL_TEXTURE_ENV_MODE, GLES11.GL_MODULATE);
        }
        GLES11.glActiveTexture(GLES11.GL_TEXTURE0);
        return;
    }
    public void setAmbient(GLES1Color color) {
        if (null == this.ambient) {
            float[] ambients = new float[4];
            this.ambient = GLES1Allocator.allocateFloat(ambients.length);
            this.ambient.put(ambients).position(0);
        }
        this.ambient.put(0, color.r);
        this.ambient.put(1, color.g);
        this.ambient.put(2, color.b);
        this.ambient.put(3, 1.0f);
        this.ambient.position(0);
        return;
    }
    public void setDiffuse(GLES1Color color) {
        if (null == this.diffuse) {
            float[] diffuses = new float[4];
            this.diffuse = GLES1Allocator.allocateFloat(diffuses.length);
            this.diffuse.put(diffuses).position(0);
        }
        this.diffuse.put(0, color.r);
        this.diffuse.put(1, color.g);
        this.diffuse.put(2, color.b);
        this.diffuse.put(3, 1.0f);
        this.diffuse.position(0);
        return;
    }
    public void setSpecular(GLES1Color color) {
        if (null == this.specular) {
            float[] specular = new float[4];
            this.specular = GLES1Allocator.allocateFloat(specular.length);
            this.specular.put(specular).position(0);
        }
        this.specular.put(0, color.r);
        this.specular.put(1, color.g);
        this.specular.put(2, color.b);
        this.specular.put(3, 1.0f);
        this.specular.position(0);
        return;
    }
    public void setUVs(FloatBuffer uvs) {
        if (false != this.hasTexture) {
            GLES11.glClientActiveTexture(GLES11.GL_TEXTURE0);
            GLES11.glActiveTexture(GLES11.GL_TEXTURE0);
            GLES11.glEnableClientState(GLES11.GL_TEXTURE_COORD_ARRAY);
            GLES11.glTexCoordPointer(2, GLES11.GL_FLOAT, 0, uvs);
        }
        if (null != this.ambientTexture) {
            GLES11.glClientActiveTexture(GLES11.GL_TEXTURE1);
            GLES11.glActiveTexture(GLES11.GL_TEXTURE1);
            GLES11.glEnableClientState(GLES11.GL_TEXTURE_COORD_ARRAY);
            GLES11.glTexCoordPointer(2, GLES11.GL_FLOAT, 0, uvs);
        }
        if (null != this.diffuseTexture) {
            GLES11.glClientActiveTexture(GLES11.GL_TEXTURE2);
            GLES11.glActiveTexture(GLES11.GL_TEXTURE2);
            GLES11.glEnableClientState(GLES11.GL_TEXTURE_COORD_ARRAY);
            GLES11.glTexCoordPointer(2, GLES11.GL_FLOAT, 0, uvs);
        }
        return;
    }
    public void setDiffuseTexture(String path, Context context) {
        GLES1TextureAsset texture = new GLES1TextureAsset();
        texture.load(path, GLES11.GL_TEXTURE2, context);
        this.diffuseTexture = texture;
        this.hasTexture = true;
        return;
    }
    public void setAmbientTexture(String path, Context context) {
        GLES1TextureAsset texture = new GLES1TextureAsset();
        texture.load(path, GLES11.GL_TEXTURE1, context);
        this.hasTexture = true;
        return;
    }
    public void setNormalTexture(String path, Context context) {
        GLES1TextureAsset texture = new GLES1TextureAsset();
        texture.load(path, GLES11.GL_TEXTURE0, context);
        this.hasTexture = true;
        return;
    }
}
