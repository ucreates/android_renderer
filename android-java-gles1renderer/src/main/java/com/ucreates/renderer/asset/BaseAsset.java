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
import com.ucreates.renderer.entity.GLESColor;
import com.ucreates.renderer.entity.Transform;
import com.ucreates.renderer.entity.VertexArray;
public abstract class BaseAsset {
    public int renderMode;
    public Transform transform;
    public VertexArray vertex;
    protected float width;
    protected float height;
    protected float depth;
    protected GLESColor color;
    public BaseAsset() {
        this.transform = new Transform();
        this.vertex = null;
        this.renderMode = 0;
    }
    public void create() {
        return;
    }
}