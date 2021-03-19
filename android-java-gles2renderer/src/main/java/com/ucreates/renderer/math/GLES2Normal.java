// ======================================================================
// Project Name    : android_renderer
//
// Copyright © 2020 U-CREATES. All rights reserved.
//
// This source code is the property of U-CREATES.
// If such findings are accepted at any time.
// We hope the tips and helpful in developing.
// ======================================================================
package com.ucreates.renderer.math;
import android.renderscript.Float3;
public class GLES2Normal {
    public static Float3 toNormal(float x1, float y1, float z1, float x2, float y2, float z2, float x3, float y3, float z3) {
        float vx1 = x2 - x1;
        float vy1 = y2 - y1;
        float vz1 = z2 - z1;
        float vx2 = x3 - x1;
        float vy2 = y3 - y1;
        float vz2 = z3 - z1;
        float nx = vy1 * vz2 - vz1 * vy2;
        float ny = vz1 * vx2 - vx1 * vz2;
        float nz = vx1 * vy2 - vy1 * vx2;
        return new Float3(nx, ny, nz);
    }
    public static Float3 add(Float3 v1, Float3 v2) {
        float x = v1.x + v2.x;
        float y = v1.y + v2.y;
        float z = v1.z + v2.z;
        return new Float3(x, y, z);
    }
    public static Float3 toOne(Float3 vector) {
        float x = 0.0f;
        float y = 0.0f;
        float z = 0.0f;
        if (0 < Math.abs(vector.x)) {
            x = vector.x / Math.abs(vector.x);
        }
        if (0 < Math.abs(vector.y)) {
            y = vector.y / Math.abs(vector.y);
        }
        if (0 < Math.abs(vector.z)) {
            z = vector.z / Math.abs(vector.z);
        }
        return new Float3(x, y, z);
    }
}
