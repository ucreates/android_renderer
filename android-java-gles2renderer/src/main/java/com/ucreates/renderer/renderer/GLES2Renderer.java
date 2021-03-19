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
import android.opengl.GLES20;
import android.opengl.Matrix;
import android.renderscript.Float3;
import android.renderscript.Matrix3f;
import android.renderscript.Matrix4f;
import com.ucreates.renderer.asset.GLES2BaseAsset;
import com.ucreates.renderer.camera.GLES2Camera;
import com.ucreates.renderer.screen.GLES2Viewport;
import com.ucreates.renderer.shader.GLES2Shader;
import com.ucreates.renderer.transform.matrix.GLES2ProjectonTransfomMatrix;
import com.ucreates.renderer.transform.matrix.GLES2ViewTransformMatrix;
import javax.microedition.khronos.opengles.GL10;
public class GLES2Renderer {
    public static final int DIMENSION2D = 2;
    public static final int DIMENSION3D = 3;
    public static final int RGBA = 4;
    public GLES2Viewport viewport;
    public GLES2Camera camera;
    private GLES2ProjectonTransfomMatrix projectonTransformMatrix;
    private GLES2ViewTransformMatrix viewTransformMatrix;
    private int dimension;
    private boolean enableStencil;
    private String projectionAttributeName;
    private String viewAttributeName;
    private String modelAttributeName;
    public void create() {
        this.projectonTransformMatrix = new GLES2ProjectonTransfomMatrix();
        this.viewTransformMatrix = new GLES2ViewTransformMatrix();
        this.viewport = new GLES2Viewport();
        this.camera = new GLES2Camera();
        return;
    }
    public void rebind(Context context, int width, int height) {
        this.viewport.setScreenSize(context, width, height);
        return;
    }
    public void clear() {
        this.clear(false);
        return;
    }
    public void clear(boolean depth) {
        if (false == depth) {
            this.viewport.update(this.camera);
        } else {
            GLES20.glClear(GLES20.GL_DEPTH_BUFFER_BIT);
        }
        return;
    }
    public void transform(int dimension) {
        if (DIMENSION2D == dimension) {
            float aspectRatio = this.viewport.getAspectRatio();
            float near = this.camera.orthoNear;
            float far = this.camera.orthoFar;
            this.projectonTransformMatrix.transform2D(aspectRatio, near, far);
            this.viewTransformMatrix.transform2D();
        } else {
            float aspectRatio = this.viewport.getAspectRatio();
            float fov = this.camera.fov;
            float near = this.camera.perspectiveNear;
            float far = this.camera.perspectiveFar;
            Float3 eye = this.camera.eye;
            Float3 center = this.camera.center;
            Float3 up = this.camera.up;
            this.projectonTransformMatrix.transform3D(fov, aspectRatio, near, far);
            this.viewTransformMatrix.transform3D(eye, center, up);
        }
        this.dimension = dimension;
        return;
    }
    public void render(float delta, GLES2BaseAsset asset) {
        GLES20.glUseProgram(asset.programObject.handle);
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);
        GLES20.glEnable(GLES20.GL_CULL_FACE);
        GLES20.glCullFace(GLES20.GL_BACK);
        Matrix4f modelMatrix = asset.transform.getMatrix();
        GLES2Shader.setUniformMatrix4fv(asset.programObject.handle, this.projectionAttributeName, this.projectonTransformMatrix.matrix);
        GLES2Shader.setUniformMatrix4fv(asset.programObject.handle, this.viewAttributeName, this.viewTransformMatrix.matrix);
        GLES2Shader.setUniformMatrix4fv(asset.programObject.handle, this.modelAttributeName, modelMatrix.getArray());
        asset.bind(delta);
        GLES20.glDrawArrays(asset.renderMode, 0, asset.vertex.getCount());
        GLES20.glDisable(GLES20.GL_DEPTH_TEST);
        GLES20.glDisable(GLES20.GL_CULL_FACE);
        GLES20.glUseProgram(0);
        return;
    }
    public void setProjectionMatrixAttributeName(String projectionAttributeName) {
        this.projectionAttributeName = projectionAttributeName;
        return;
    }
    public void setModelMatrixAttributeName(String modelAttributeName) {
        this.modelAttributeName = modelAttributeName;
        return;
    }
    public void setViewMatrixAttributeName(String viewAttributeName) {
        this.viewAttributeName = viewAttributeName;
        return;
    }
}
