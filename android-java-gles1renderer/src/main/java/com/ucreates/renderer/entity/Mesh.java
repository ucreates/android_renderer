// ======================================================================
// Project Name    : android_renderer
//
// Copyright © 2020 U-CREATES. All rights reserved.
//
// This source code is the property of U-CREATES.
// If such findings are accepted at any time.
// We hope the tips and helpful in developing.
// ======================================================================
package com.ucreates.renderer.entity;
import com.ucreates.renderer.asset.BaseAsset;
import com.ucreates.renderer.renderer.GLES1Renderer;
public class Mesh extends BaseAsset {
    public String name;
    public String useMaterial;
    public Mesh() {
        this.vertex = new VertexArray(GLES1Renderer.DIMENSION3D);
    }
}
