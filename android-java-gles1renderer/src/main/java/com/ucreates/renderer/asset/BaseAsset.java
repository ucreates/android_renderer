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
import com.ucreates.renderer.entity.GLESBlend;
import com.ucreates.renderer.entity.GLESColor;
import com.ucreates.renderer.entity.Material;
import com.ucreates.renderer.entity.Transform;
import com.ucreates.renderer.entity.VertexArray;
import java.util.ArrayList;
public abstract class BaseAsset {
    public int renderMode;
    public Transform transform;
    public VertexArray vertex;
    public Material material;
    public TextureAsset texture;
    public GLESBlend blend;
    public GLES1ShaderAsset shader;
    protected float width;
    protected float height;
    protected float depth;
    protected GLESColor color;
    public BaseAsset() {
        this.transform = new Transform();
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
    public void createMipmap(ArrayList<String> texturePaths, Context context) {
        return;
    }
    public void setMaterial(Material material) {
        this.material = material;
        return;
    }
}
