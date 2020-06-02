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
import com.ucreates.renderer.asset.BaseAsset;
public class BaseAnimatorAsset extends BaseAsset {
    protected int frameSpan;
    protected int currentFrame;
    protected int currentTextureIndex;
    public BaseAsset getCurrentFrame() {
        return null;
    }
    public void setFrameSpan(int frameSpan) {
        this.frameSpan = frameSpan;
        return;
    }
}
