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
import android.util.Log;
import com.ucreates.renderer.entity.GLESColor;
import com.ucreates.renderer.entity.Material;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
public class MaterialAsset {
    public HashMap<String, Material> create(String mtlPath, Context context) {
        HashMap<String, Material> ret = new HashMap<String, Material>();
        Material material = null;
        GLESColor ambient = null;
        GLESColor diffuse = null;
        GLESColor specular = null;
        AssetManager assetManager = context.getAssets();
        try {
            InputStream stream = assetManager.open(mtlPath);
            if (null == stream) {
                return ret;
            }
            InputStreamReader sr = new InputStreamReader(stream);
            BufferedReader br = new BufferedReader(sr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(" ");
                if (1 >= data.length) {
                    continue;
                }
                String command = data[0];
                if (false != command.equals("newmtl")) {
                    material = new Material();
                    material.name = data[1];
                    ret.put(material.name, material);
                } else if (false != command.equals("Ka")) {
                    ambient = new GLESColor();
                    ambient.r = Float.valueOf(data[1]);
                    ambient.g = Float.valueOf(data[2]);
                    ambient.b = Float.valueOf(data[3]);
                } else if (false != command.equals("Kd")) {
                    diffuse = new GLESColor();
                    diffuse.r = Float.valueOf(data[1]);
                    diffuse.g = Float.valueOf(data[2]);
                    diffuse.b = Float.valueOf(data[3]);
                } else if (false != command.equals("Ks")) {
                    specular = new GLESColor();
                    specular.r = Float.valueOf(data[1]);
                    specular.g = Float.valueOf(data[2]);
                    specular.b = Float.valueOf(data[3]);
                } else if (false != command.equals("Tf")) {
                    float tfa = Float.valueOf(data[1]);
                    float tfd = Float.valueOf(data[2]);
                    float tfs = Float.valueOf(data[3]);
                    if (null != ambient) {
                        ambient.a = tfa;
                        material.setAmbient(ambient);
                    }
                    if (null != diffuse) {
                        diffuse.a = tfd;
                        material.setDiffuse(diffuse);
                    }
                    if (null != specular) {
                        specular.a = tfs;
                        material.setSpecular(specular);
                    }
                } else if (false != command.equals("map_Kd")) {
                    String path = data[1];
                    TextureAsset texture = new TextureAsset();
                    texture.load(path, context);
                    material.setDiffuseTexture(texture);
                } else if (false != command.equals("map_Ka")) {
                    String path = data[1];
                    TextureAsset texture = new TextureAsset();
                    texture.load(path, context);
                    material.setAmbientTexture(texture);
                } else if (false != command.equals("map_Bump")) {
                    String path = data[1];
                    TextureAsset texture = new TextureAsset();
                    texture.load(path, context);
                    material.setNormalTexture(texture);
                }
            }
        } catch (IOException e) {
            Log.i("ANDROID_RENDERER, %s", e.getMessage());
        }
        return ret;
    }
}
