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
import android.content.Context;
import com.ucreates.renderer.entity.GLES1Blend;
import com.ucreates.renderer.entity.GLES1Color;
import com.ucreates.renderer.entity.GLES1Material;
import com.ucreates.renderer.entity.GLES1Transform;
import com.ucreates.renderer.entity.GLES1VertexArray;
import java.util.ArrayList;
public abstract class GLES1BaseAsset {
    public int renderMode;
    public GLES1Transform transform;
    public GLES1VertexArray vertex;
    public GLES1Material material;
    public GLES1TextureAsset texture;
    public GLES1Blend blend;
    public GLES1ShaderAsset shader;
    protected float width;
    protected float height;
    protected float depth;
    protected GLES1Color color;
    public GLES1BaseAsset() {
        this.transform = new GLES1Transform();
        this.vertex = null;
        this.material = null;
        this.texture = null;
        this.blend = null;
        this.shader = null;
        this.renderMode = 0;
    }
    public void create() {
        return;
    }
    public void create(String texturePath, Context context) {
        return;
    }
    public void create(String texturePath, int textureUnit, Context context) {
        return;
    }
    public void createMipmap(ArrayList<String> texturePaths, Context context) {
        return;
    }
    public void setMaterial(GLES1Material material) {
        this.material = material;
        return;
    }
}
