// ======================================================================
// Project Name    : android_renderer
//
// Copyright © 2020 U-CREATES. All rights reserved.
//
// This source code is the property of U-CREATES.
// If such findings are accepted at any time.
// We hope the tips and helpful in developing.
// ======================================================================
package com.ucreates.renderer.renderer;
import android.content.Context;
import android.opengl.GLES11;
import android.renderscript.Float3;
import com.ucreates.renderer.asset.BaseAsset;
import com.ucreates.renderer.camera.GLES1Camera;
import com.ucreates.renderer.screen.Viewport;
import com.ucreates.renderer.transform.matrix.ModelViewTransformMatrix;
import com.ucreates.renderer.transform.matrix.ProjectonTransfomMatrix;
import javax.microedition.khronos.opengles.GL10;
public class GLES1Renderer {
    public static final int DIMENSION2D = 2;
    public static final int DIMENSION3D = 3;
    public static final int RGBA = 4;
    private ProjectonTransfomMatrix projectonTransformMatrix;
    private ModelViewTransformMatrix modelViewTransformMatrix;
    public Viewport viewport;
    public GLES1Camera camera;
    public void create() {
        this.projectonTransformMatrix = new ProjectonTransfomMatrix();
        this.modelViewTransformMatrix = new ModelViewTransformMatrix();
        this.viewport = new Viewport();
        this.camera = new GLES1Camera();
    }
    public void rebind(Context context, int width, int height) {
        this.viewport.setScreenSize(context, width, height);
    }
    public void transform(GL10 gl, int dimension) {
        if (DIMENSION2D == dimension) {
            float aspectRatio = this.viewport.getAspectRatio();
            float near = this.camera.near;
            float far = this.camera.far;
            this.viewport.update(this.camera);
            this.projectonTransformMatrix.transform2D(aspectRatio, near, far);
            this.modelViewTransformMatrix.transform2D();
        } else {
            float aspectRatio = this.viewport.getAspectRatio();
            float fov = this.camera.fov;
            float near = this.camera.near;
            float far = this.camera.far;
            Float3 eye = this.camera.eye;
            Float3 center = this.camera.center;
            Float3 up = this.camera.up;
            this.viewport.update(this.camera);
            this.projectonTransformMatrix.transform3D(gl, fov, aspectRatio, near, far);
            this.modelViewTransformMatrix.transform3D(gl, eye, center, up);
        }
        return;
    }
    public void render(BaseAsset asset) {
        GLES11.glEnable(GLES11.GL_DEPTH_TEST);
        GLES11.glEnable(GLES11.GL_CULL_FACE);
        GLES11.glEnableClientState(GLES11.GL_VERTEX_ARRAY);
        GLES11.glEnableClientState(GLES11.GL_COLOR_ARRAY);
        GLES11.glCullFace(GLES11.GL_BACK);
        GLES11.glVertexPointer(asset.vertex.dimension, GLES11.GL_FLOAT, 0, asset.vertex.vertices);
        GLES11.glColorPointer(RGBA, GLES11.GL_FLOAT, 0, asset.vertex.colors);
        float tx = asset.transform.position.x;
        float ty = asset.transform.position.y;
        float tz = asset.transform.position.z;
        float sx = asset.transform.scale.x;
        float sy = asset.transform.scale.y;
        float sz = asset.transform.scale.z;
        float rx = asset.transform.rotation.x;
        float ry = asset.transform.rotation.y;
        float rz = asset.transform.rotation.z;
        GLES11.glPushMatrix();
        GLES11.glTranslatef(tx, ty, tz);
        GLES11.glScalef(sx, sy, sz);
        GLES11.glRotatef(rx, 1.0f, 0.0f, 0.0f);
        GLES11.glRotatef(ry, 0.0f, 1.0f, 0.0f);
        GLES11.glRotatef(rz, 0.0f, 0.0f, 1.0f);
        GLES11.glDrawArrays(asset.renderMode, 0, asset.vertex.count);
        GLES11.glPopMatrix();
        GLES11.glDisableClientState(GLES11.GL_VERTEX_ARRAY);
        GLES11.glDisableClientState(GLES11.GL_COLOR_ARRAY);
        GLES11.glDisable(GLES11.GL_CULL_FACE);
        GLES11.glDisable(GLES11.GL_DEPTH_TEST);
        return;
    }
}