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
import com.ucreates.renderer.asset.GLES1BaseAsset;
import com.ucreates.renderer.asset.wipe.GLES1BaseWipeAsset;
import com.ucreates.renderer.camera.GLES1Camera;
import com.ucreates.renderer.entity.GLES1Mesh;
import com.ucreates.renderer.enviroment.GLES1Fog;
import com.ucreates.renderer.enviroment.GLES1Light;
import com.ucreates.renderer.screen.GLES1Viewport;
import com.ucreates.renderer.transform.matrix.GLES1ModelViewTransformMatrix;
import com.ucreates.renderer.transform.matrix.GLES1ProjectonTransfomMatrix;
import java.util.ArrayList;
import javax.microedition.khronos.opengles.GL10;
public class GLES1Renderer {
    public static final int DIMENSION2D = 2;
    public static final int DIMENSION3D = 3;
    public static final int RGBA = 4;
    public static final int WIPEIN = 1;
    public static final int WIPEOUT = 2;
    public ArrayList<GLES1Light> lights;
    private GLES1ProjectonTransfomMatrix projectonTransformMatrix;
    private GLES1ModelViewTransformMatrix modelViewTransformMatrix;
    public GLES1Viewport viewport;
    public GLES1Camera camera;
    private GLES1Fog fog = null;
    private int dimension;
    private boolean enableStencil;
    public void create() {
        this.lights = new ArrayList<GLES1Light>();
        this.projectonTransformMatrix = new GLES1ProjectonTransfomMatrix();
        this.modelViewTransformMatrix = new GLES1ModelViewTransformMatrix();
        this.viewport = new GLES1Viewport();
        this.camera = new GLES1Camera();
        this.enableStencil = false;
    }
    public void rebind(Context context, int width, int height) {
        this.viewport.setScreenSize(context, width, height);
    }
    public void clear() {
        this.clear(false);
        return;
    }
    public void clear(boolean depth) {
        if (false == depth) {
            this.viewport.update(this.camera);
        } else {
            GLES11.glClear(GLES11.GL_DEPTH_BUFFER_BIT);
        }
        return;
    }
    public void transform(GL10 gl, int dimension) {
        if (DIMENSION2D == dimension) {
            float aspectRatio = this.viewport.getAspectRatio();
            float near = this.camera.orthoNear;
            float far = this.camera.orthoFar;
            this.projectonTransformMatrix.transform2D(aspectRatio, near, far);
            this.modelViewTransformMatrix.transform2D();
        } else {
            float aspectRatio = this.viewport.getAspectRatio();
            float fov = this.camera.fov;
            float near = this.camera.perspectiveNear;
            float far = this.camera.perspectiveFar;
            Float3 eye = this.camera.eye;
            Float3 center = this.camera.center;
            Float3 up = this.camera.up;
            this.projectonTransformMatrix.transform3D(gl, fov, aspectRatio, near, far);
            this.modelViewTransformMatrix.transform3D(gl, eye, center, up);
        }
        this.dimension = dimension;
        return;
    }
    public void render(GLES1BaseWipeAsset asset, int wipeType, float delta, float totalTime) {
        GLES11.glEnable(GLES11.GL_STENCIL_TEST);
        GLES11.glEnable(GLES11.GL_CULL_FACE);
        GLES11.glCullFace(GLES11.GL_BACK);
        GLES11.glStencilFunc(GLES11.GL_ALWAYS, 1, 0xFF);
        GLES11.glStencilOp(GLES11.GL_REPLACE, GLES11.GL_REPLACE, GLES11.GL_REPLACE);
        GLES11.glEnableClientState(GLES11.GL_VERTEX_ARRAY);
        GLES11.glEnableClientState(GLES11.GL_COLOR_ARRAY);
        GLES11.glVertexPointer(asset.vertex.dimension, GLES11.GL_FLOAT, 0, asset.vertex.vertices);
        GLES11.glColorPointer(RGBA, GLES11.GL_FLOAT, 0, asset.vertex.colors);
        float scale = 1.0f;
        if (WIPEIN == wipeType) {
            scale = asset.wipeIn(delta, totalTime);
        } else {
            scale = asset.wipeOut(delta, totalTime);
        }
        GLES11.glPushMatrix();
        GLES11.glTranslatef(0.0f, 0.0f, 0.0f);
        GLES11.glScalef(scale, scale, scale);
        GLES11.glDepthMask(false);
        GLES11.glColorMask(false, false, false, false);
        GLES11.glDrawArrays(GLES11.GL_TRIANGLES, 0, asset.vertex.count);
        GLES11.glColorMask(true, true, true, true);
        GLES11.glDepthMask(true);
        GLES11.glPopMatrix();
        GLES11.glDisableClientState(GLES11.GL_VERTEX_ARRAY);
        GLES11.glDisableClientState(GLES11.GL_COLOR_ARRAY);
        GLES11.glStencilFunc(GLES11.GL_EQUAL, 1, 0xFF);
        GLES11.glStencilOp(GLES11.GL_KEEP, GLES11.GL_KEEP, GLES11.GL_KEEP);
        GLES11.glDisable(GLES11.GL_CULL_FACE);
        GLES11.glDisable(GLES11.GL_STENCIL_TEST);
        this.enableStencil = true;
        return;
    }
    public void render(ArrayList<GLES1Mesh> meshes) {
        for (GLES1Mesh mesh : meshes) {
            this.render(mesh);
        }
        return;
    }
    public void render(GLES1BaseAsset asset) {
        if (null == asset.blend) {
            GLES11.glEnable(GLES11.GL_DEPTH_TEST);
        } else {
            GLES11.glEnable(GLES11.GL_BLEND);
        }
        if (false != this.enableStencil) {
            GLES11.glEnable(GLES11.GL_STENCIL_TEST);
        }
        GLES11.glEnable(GLES11.GL_CULL_FACE);
        if (null != this.fog) {
            GLES11.glEnable(GLES11.GL_FOG);
        }
        if (null != asset.material && false != asset.material.hasTexture) {
            asset.material.enable();
            if (null != asset.blend) {
                GLES11.glEnable(GLES11.GL_ALPHA_TEST);
            }
        }
        if (0 < this.lights.size() && this.dimension == GLES1Renderer.DIMENSION3D) {
            GLES11.glEnable(GLES11.GL_NORMALIZE);
            GLES11.glEnable(GLES11.GL_LIGHTING);
            if (null == asset.material) {
                GLES11.glEnable(GLES11.GL_COLOR_MATERIAL);
            }
            for (GLES1Light light : this.lights) {
                light.enable();
            }
        }
        GLES11.glEnableClientState(GLES11.GL_VERTEX_ARRAY);
        GLES11.glEnableClientState(GLES11.GL_COLOR_ARRAY);
        if (0 < this.lights.size() && this.dimension == GLES1Renderer.DIMENSION3D) {
            GLES11.glEnableClientState(GLES11.GL_NORMAL_ARRAY);
        }
        GLES11.glCullFace(GLES11.GL_BACK);
        if (this.dimension == GLES1Renderer.DIMENSION3D) {
            for (GLES1Light light : this.lights) {
                light.illuminate();
            }
        }
        if (null != asset.blend) {
            GLES11.glBlendFunc(asset.blend.source, asset.blend.destination);
        }
        GLES11.glVertexPointer(asset.vertex.dimension, GLES11.GL_FLOAT, 0, asset.vertex.vertices);
        GLES11.glColorPointer(RGBA, GLES11.GL_FLOAT, 0, asset.vertex.colors);
        if (null != asset.texture) {
            GLES11.glEnableClientState(asset.texture.textureUnit);
            GLES11.glActiveTexture(asset.texture.textureUnit);
            GLES11.glEnable(GLES11.GL_TEXTURE_2D);
            if (null != asset.blend) {
                GLES11.glEnable(GLES11.GL_ALPHA_TEST);
            }
            GLES11.glEnableClientState(GLES11.GL_TEXTURE_COORD_ARRAY);
            GLES11.glTexCoordPointer(2, GLES11.GL_FLOAT, 0, asset.vertex.uvs);
            GLES11.glBindTexture(GLES11.GL_TEXTURE_2D, asset.texture.textureId);
            if (null != asset.blend) {
                GLES11.glAlphaFunc(asset.texture.alphaComparisonFunction, asset.texture.alphaReferenceValue);
            }
        }
        if (null != asset.material) {
            asset.material.setUVs(asset.vertex.uvs);
        }
        if (0 < this.lights.size() && this.dimension == GLES1Renderer.DIMENSION3D) {
            GLES11.glNormalPointer(GLES11.GL_FLOAT, 0, asset.vertex.normals);
        }
        float tx = asset.transform.position.x;
        float ty = asset.transform.position.y;
        float tz = asset.transform.position.z;
        float sx = asset.transform.scale.x;
        float sy = asset.transform.scale.y;
        float sz = asset.transform.scale.z;
        float rx = asset.transform.rotation.x;
        float ry = asset.transform.rotation.y;
        float rz = asset.transform.rotation.z;
        if (null != asset.texture && this.dimension == GLES1Renderer.DIMENSION2D) {
            float aspectRatio = this.viewport.getAspectRatio();
            float txr = (float) asset.texture.size.x / (float) this.viewport.width;
            float tyr = (float) asset.texture.size.y / (float) this.viewport.height;
            sx *= txr * aspectRatio;
            sy *= tyr;
        }
        GLES11.glPushMatrix();
        GLES11.glTranslatef(tx, ty, tz);
        GLES11.glScalef(sx, sy, sz);
        GLES11.glRotatef(rx, 1.0f, 0.0f, 0.0f);
        GLES11.glRotatef(ry, 0.0f, 1.0f, 0.0f);
        GLES11.glRotatef(rz, 0.0f, 0.0f, 1.0f);
        if (null != this.fog) {
            this.fog.mist();
        }
        if (null != asset.shader) {
            asset.shader.shade();
        }
        if (null != asset.material) {
            asset.material.reflect();
        }
        if (null == asset.vertex.indicies) {
            GLES11.glDrawArrays(asset.renderMode, 0, asset.vertex.count);
        } else {
            GLES11.glDrawElements(asset.renderMode, asset.vertex.indexCount, GLES11.GL_UNSIGNED_SHORT, asset.vertex.indicies);
        }
        GLES11.glPopMatrix();
        GLES11.glDisableClientState(GLES11.GL_VERTEX_ARRAY);
        GLES11.glDisableClientState(GLES11.GL_COLOR_ARRAY);
        if (0 < this.lights.size() && this.dimension == GLES1Renderer.DIMENSION3D) {
            GLES11.glDisableClientState(GLES11.GL_NORMAL_ARRAY);
            for (GLES1Light light : this.lights) {
                light.disable();
            }
            if (null == asset.material) {
                GLES11.glDisable(GLES11.GL_COLOR_MATERIAL);
            }
            GLES11.glDisable(GLES11.GL_LIGHTING);
            GLES11.glDisable(GLES11.GL_NORMALIZE);
        }
        if (null != asset.texture) {
            GLES11.glEnableClientState(asset.texture.textureUnit);
            GLES11.glActiveTexture(asset.texture.textureUnit);
            GLES11.glDisableClientState(GLES11.GL_TEXTURE_COORD_ARRAY);
            GLES11.glDisable(GLES11.GL_TEXTURE_2D);
            if (null != asset.blend) {
                GLES11.glDisable(GLES11.GL_ALPHA_TEST);
            }
        } else if (null != asset.material && false != asset.material.hasTexture) {
            asset.material.disable();
            if (null != asset.blend) {
                GLES11.glDisable(GLES11.GL_ALPHA_TEST);
            }
        }
        if (null != this.fog) {
            GLES11.glDisable(GLES11.GL_FOG);
        }
        GLES11.glDisable(GLES11.GL_CULL_FACE);
        if (false != this.enableStencil) {
            GLES11.glDisable(GLES11.GL_STENCIL_TEST);
        }
        if (null == asset.blend) {
            GLES11.glDisable(GLES11.GL_DEPTH_TEST);
        } else {
            GLES11.glDisable(GLES11.GL_BLEND);
        }
        this.enableStencil = false;
        return;
    }
    public void addLight(GLES1Light light) {
        if (GLES11.GL_MAX_LIGHTS < this.lights.size()) {
            return;
        }
        this.lights.add(light);
        return;
    }
    public void setFog(GLES1Fog fog) {
        this.fog = fog;
        return;
    }
}
