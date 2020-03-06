// ======================================================================
// Project Name    : android_renderer
//
// Copyright © 2020 U-CREATES. All rights reserved.
//
// This source code is the property of U-CREATES.
// If such findings are accepted at any time.
// We hope the tips and helpful in developing.
// ======================================================================
package com.ucreates.renderer.enviroment;
import android.opengl.GLES11;
import com.ucreates.renderer.entity.GLESColor;
import com.ucreates.renderer.io.memory.Allocator;
import java.nio.FloatBuffer;
public class GLES1Light {
    private int lightId;
    private FloatBuffer position = null;
    private FloatBuffer direction = null;
    private FloatBuffer ambient = null;
    private FloatBuffer diffuse = null;
    public GLES1Light(int lightId) {
        this.lightId = lightId;
        float[] positions = new float[4];
        positions[3] = 1.0f;
        this.position = Allocator.allocateFloat(positions.length);
        this.position.put(positions).position(0);
    }
    public void enable() {
        GLES11.glEnable(this.lightId);
        return;
    }
    public void disable() {
        GLES11.glDisable(this.lightId);
        return;
    }
    public void illuminate() {
        GLES11.glLightfv(this.lightId, GLES11.GL_POSITION, this.position);
        if (null != this.ambient) {
            GLES11.glLightfv(this.lightId, GLES11.GL_AMBIENT, this.ambient);
        }
        if (null != this.diffuse) {
            GLES11.glLightfv(this.lightId, GLES11.GL_DIFFUSE, this.diffuse);
        }
        if (null != this.direction) {
            GLES11.glLightfv(this.lightId, GLES11.GL_SPOT_DIRECTION, this.direction);
        }
        return;
    }
    public void setPosition(float x, float y, float z) {
        this.position.put(0, x);
        this.position.put(1, y);
        this.position.put(2, z);
        this.position.position(0);
        return;
    }
    public void setDirection(float x, float y, float z) {
        if (null == this.direction) {
            float[] directions = new float[4];
            this.direction = Allocator.allocateFloat(directions.length);
            this.direction.put(directions).position(0);
        }
        this.direction.put(0, x);
        this.direction.put(1, y);
        this.direction.put(2, z);
        this.direction.position(0);
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
}
