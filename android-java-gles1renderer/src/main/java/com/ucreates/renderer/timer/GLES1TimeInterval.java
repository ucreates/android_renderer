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
public class GLES1TimeInterval {
    private static GLES1TimeInterval instance = null;
    private long previewTime;
    private long delta;
    private GLES1TimeInterval() {
        this.previewTime = 0;
        this.delta = 0;
    }
    public static GLES1TimeInterval getInstance() {
        if (null == GLES1TimeInterval.instance) {
            GLES1TimeInterval.instance = new GLES1TimeInterval();
        }
        return GLES1TimeInterval.instance;
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