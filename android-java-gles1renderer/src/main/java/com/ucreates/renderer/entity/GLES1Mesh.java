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
import com.ucreates.renderer.asset.GLES1BaseAsset;
import com.ucreates.renderer.renderer.GLES1Renderer;
public class GLES1Mesh extends GLES1BaseAsset {
    public String name;
    public String useMaterial;
    public GLES1Mesh() {
        this.vertex = new GLES1VertexArray(GLES1Renderer.DIMENSION3D);
    }
}
