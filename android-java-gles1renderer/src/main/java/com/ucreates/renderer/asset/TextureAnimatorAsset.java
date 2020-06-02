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
import java.util.ArrayList;
public class TextureAnimatorAsset extends BaseAnimatorAsset {
    private ArrayList<BaseAsset> animationFrameAssets = new ArrayList<BaseAsset>();
    public void add(BaseAsset asset) {
        this.animationFrameAssets.add(asset);
        return;
    }
    @Override
    public BaseAsset getCurrentFrame() {
        BaseAsset asset = null;
        int index = this.currentTextureIndex % (int) this.animationFrameAssets.size();
        if (this.frameSpan != this.currentFrame) {
            asset = this.animationFrameAssets.get(index);
            this.currentFrame++;
        } else {
            asset = this.animationFrameAssets.get(index);
            if (this.currentTextureIndex == this.animationFrameAssets.size() - 1) {
                this.currentTextureIndex = 0;
            } else {
                this.currentTextureIndex++;
            }
            this.currentFrame = 0;
        }
        asset.transform = this.transform;
        return asset;
    }
}
