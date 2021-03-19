// ======================================================================
// Project Name    : android_renderer
//
// Copyright © 2020 U-CREATES. All rights reserved.
//
// This source code is the property of U-CREATES.
// If such findings are accepted at any time.
// We hope the tips and helpful in developing.
// ======================================================================
package com.ucreates.renderer.timer;
public class GLES2TimeInterval {
    private static GLES2TimeInterval instance = null;
    private long previewTime;
    private long delta;
    private GLES2TimeInterval() {
        this.previewTime = 0;
        this.delta = 0;
    }
    public static GLES2TimeInterval getInstance() {
        if (null == GLES2TimeInterval.instance) {
            GLES2TimeInterval.instance = new GLES2TimeInterval();
        }
        return GLES2TimeInterval.instance;
    }
    public void update() {
        long currentTime = System.currentTimeMillis();
        this.delta = currentTime - this.previewTime;
        this.previewTime = currentTime;
    }
    public long getDelta() {
        return this.delta;
    }
}