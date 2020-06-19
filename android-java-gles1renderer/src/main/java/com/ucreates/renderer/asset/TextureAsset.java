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
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES11;
import android.renderscript.Float2;
import android.util.Log;
import com.ucreates.renderer.io.memory.Allocator;
import com.ucreates.renderer.math.GLES1Exponentiation;
import com.ucreates.renderer.renderer.GLES1Renderer;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
public class TextureAsset {
    public int textureId;
    public Float2 uvRatio;
    public Float2 size;
    public int alphaComparisonFunction;
    public float alphaReferenceValue;
    public TextureAsset() {
        this.alphaComparisonFunction = GLES11.GL_GREATER;
        this.alphaReferenceValue = 0.1f;
    }
    public void load(String path, Context context) {
        AssetManager assetManager = context.getAssets();
        try {
            InputStream stream = assetManager.open(path);
            Bitmap bitmap = BitmapFactory.decodeStream(stream);
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            int edge = width >= height ? width : height;
            edge = GLES1Exponentiation.getExponentiation(edge);
            int fileSize = width * height * GLES1Renderer.RGBA;
            ByteBuffer bytes = Allocator.allocate(fileSize);
            bitmap.copyPixelsToBuffer(bytes);
            bytes.position(0);
            int textureName[] = new int[1];
            GLES11.glGenTextures(1, textureName, 0);
            GLES11.glBindTexture(GLES11.GL_TEXTURE_2D, textureName[0]);
            GLES11.glTexParameterf(GLES11.GL_TEXTURE_2D, GLES11.GL_TEXTURE_WRAP_S, GLES11.GL_CLAMP_TO_EDGE);
            GLES11.glTexParameterf(GLES11.GL_TEXTURE_2D, GLES11.GL_TEXTURE_WRAP_T, GLES11.GL_CLAMP_TO_EDGE);
            GLES11.glTexParameterf(GLES11.GL_TEXTURE_2D, GLES11.GL_TEXTURE_MIN_FILTER, GLES11.GL_NEAREST);
            GLES11.glTexParameterf(GLES11.GL_TEXTURE_2D, GLES11.GL_TEXTURE_MAG_FILTER, GLES11.GL_NEAREST);
            GLES11.glTexImage2D(GLES11.GL_TEXTURE_2D, 0, GLES11.GL_RGBA, width, height, 0, GLES11.GL_RGBA, GLES11.GL_UNSIGNED_BYTE, bytes);
            bitmap.recycle();
            float widthRate = (float) width / (float) edge;
            float heightRate = (float) height / (float) edge;
            this.textureId = textureName[0];
            this.size = new Float2(width, height);
            this.uvRatio = new Float2(widthRate, heightRate);
        } catch (IOException e) {
            Log.i("ANDROID_RENDERER, %s", e.getMessage());
        }
        return;
    }
    public void loadMipmap(ArrayList<String> paths, Context context) {
        int width = 0;
        int height = 0;
        int edge = 0;
        int textureName[] = new int[1];
        GLES11.glGenTextures(1, textureName, 0);
        GLES11.glBindTexture(GLES11.GL_TEXTURE_2D, textureName[0]);
        for (int i = 0; i < paths.size(); i++) {
            AssetManager assetManager = context.getAssets();
            try {
                String path = paths.get(i);
                InputStream stream = assetManager.open(path);
                Bitmap bitmap = BitmapFactory.decodeStream(stream);
                int tmpWidth = bitmap.getWidth();
                int tmpHeight = bitmap.getHeight();
                int tmpEdge = tmpWidth >= tmpHeight ? tmpWidth : tmpHeight;
                tmpEdge = GLES1Exponentiation.getExponentiation(tmpEdge);
                width = 0 == width ? tmpWidth : width;
                height = 0 == height ? tmpHeight : height;
                edge = 0 == edge ? tmpEdge : edge;
                int fileSize = tmpWidth * tmpHeight * GLES1Renderer.RGBA;
                ByteBuffer bytes = Allocator.allocate(fileSize);
                bitmap.copyPixelsToBuffer(bytes);
                bytes.position(0);
                GLES11.glTexParameteri(GLES11.GL_TEXTURE_2D, GLES11.GL_GENERATE_MIPMAP, GLES11.GL_TRUE);
                GLES11.glTexParameteri(GLES11.GL_TEXTURE_2D, GLES11.GL_TEXTURE_WRAP_S, GLES11.GL_CLAMP_TO_EDGE);
                GLES11.glTexParameteri(GLES11.GL_TEXTURE_2D, GLES11.GL_TEXTURE_WRAP_T, GLES11.GL_CLAMP_TO_EDGE);
                GLES11.glTexParameteri(GLES11.GL_TEXTURE_2D, GLES11.GL_TEXTURE_MAG_FILTER, GLES11.GL_NEAREST);
                GLES11.glTexParameteri(GLES11.GL_TEXTURE_2D, GLES11.GL_TEXTURE_MIN_FILTER, GLES11.GL_NEAREST_MIPMAP_NEAREST);
                GLES11.glTexImage2D(GLES11.GL_TEXTURE_2D, i, GLES11.GL_RGBA, tmpEdge, tmpEdge, 0, GLES11.GL_RGBA, GLES11.GL_UNSIGNED_BYTE, bytes);
                bitmap.recycle();
            } catch (IOException e) {
                Log.i("ANDROID_RENDERER, %s", e.getMessage());
            }
        }
        float widthRate = (float) width / (float) edge;
        float heightRate = (float) height / (float) edge;
        this.textureId = textureName[0];
        this.size = new Float2(width, height);
        this.uvRatio = new Float2(widthRate, heightRate);
        return;
    }
}
