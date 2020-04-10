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
public class GLES1Fog {
    private int fogMode;
    private int hint;
    private float density;
    private FloatBuffer position = null;
    private FloatBuffer color = null;
    public GLES1Fog(int fogMode) {
        this.fogMode = fogMode;
        this.density = 1.0f;
        this.hint = GLES11.GL_DONT_CARE;
        float[] positions = new float[2];
        positions[1] = 0.0f;
        positions[1] = 1.0f;
        this.position = Allocator.allocateFloat(positions.length);
        this.position.put(positions).position(0);
    }
    public void mist() {
        float start = this.position.get(0);
        float end = this.position.get(1);
        GLES11.glFogx(GLES11.GL_FOG_MODE, this.fogMode);
        GLES11.glFogfv(GLES11.GL_FOG_COLOR, this.color);
        GLES11.glFogf(GLES11.GL_FOG_DENSITY, this.density);
        GLES11.glHint(GLES11.GL_FOG_HINT, this.hint);
        GLES11.glFogf(GLES11.GL_FOG_START, start);
        GLES11.glFogf(GLES11.GL_FOG_END, end);
        return;
    }
    public void setPosition(float start, float end) {
        this.position.put(0, start);
        this.position.put(1, end);
        this.position.position(0);
        return;
    }
    public void setColor(GLESColor color) {
        if (null == this.color) {
            float[] fogColor = new float[4];
            this.color = Allocator.allocateFloat(fogColor.length);
            this.color.put(fogColor).position(0);
        }
        this.color.put(0, color.r);
        this.color.put(1, color.g);
        this.color.put(2, color.b);
        this.color.put(3, color.a);
        this.color.position(0);
        return;
    }
    public void setDensity(float density) {
        this.density = density;
        return;
    }
    public void setHint(int hint) {
        this.hint = hint;
        return;
    }
}
