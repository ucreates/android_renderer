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
import com.ucreates.renderer.io.memory.GLES1Allocator;
import java.nio.FloatBuffer;
import java.util.ArrayList;
public class GLES1TextureAtlasAnimatorAsset extends GLES1BaseAnimatorAsset {
    private GLES1BaseAsset textureAtlasAsset;
    private ArrayList<FloatBuffer> animationFrameUVs = new ArrayList<FloatBuffer>();
    public void setTextureAtlas(GLES1BaseAsset asset) {
        this.textureAtlasAsset = asset;
        return;
    }
    public void addFrameUVs(float[] uvs) {
        FloatBuffer tmpUVs = GLES1Allocator.allocateFloat(uvs.length);
        tmpUVs.put(uvs).position(0);
        this.animationFrameUVs.add(tmpUVs);
        return;
    }
    @Override
    public GLES1BaseAsset getCurrentFrame() {
        FloatBuffer uvs = null;
        int index = this.currentTextureIndex % (int) this.animationFrameUVs.size();
        if (this.frameSpan != this.currentFrame) {
            uvs = this.animationFrameUVs.get(index);
            this.currentFrame++;
        } else {
            uvs = this.animationFrameUVs.get(index);
            if (this.currentTextureIndex == this.animationFrameUVs.size() - 1) {
                this.currentTextureIndex = 0;
            } else {
                this.currentTextureIndex++;
            }
            this.currentFrame = 0;
        }
        this.textureAtlasAsset.transform = this.transform;
        this.textureAtlasAsset.vertex.uvs = uvs;
        return this.textureAtlasAsset;
    }
}
