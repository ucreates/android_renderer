// ======================================================================
// Project Name    : android_renderer
//
// Copyright © 2020 U-CREATES. All rights reserved.
//
// This source code is the property of U-CREATES.
// If such findings are accepted at any time.
// We hope the tips and helpful in developing.
// ======================================================================
package com.ucreates.renderer.asset.mesh;
import android.content.Context;
import android.content.res.AssetManager;
import android.opengl.GLES11;
import android.util.Log;
import com.ucreates.renderer.asset.GLES1BaseAsset;
import com.ucreates.renderer.asset.GLES1MaterialAsset;
import com.ucreates.renderer.entity.GLES1Material;
import com.ucreates.renderer.entity.GLES1Mesh;
import com.ucreates.renderer.entity.GLES1VertexArray;
import com.ucreates.renderer.renderer.GLES1Renderer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
public class GLES1ObjAsset extends GLES1BaseAsset {
    private HashMap<String, GLES1Material> materials = null;
    public ArrayList<GLES1Mesh> subMeshes;
    public GLES1ObjAsset() {
        this.vertex = new GLES1VertexArray(GLES1Renderer.DIMENSION3D);
        this.subMeshes = new ArrayList<GLES1Mesh>();
    }
    @Override
    public void create(String objPath, Context context) {
        int vertexCount = 0;
        GLES1Mesh mesh = null;
        ArrayList<Float> vertexOriginArray = new ArrayList<Float>();
        ArrayList<Float> uvOriginArray = new ArrayList<Float>();
        ArrayList<Float> normalOriginArray = new ArrayList<Float>();
        ArrayList<Float> vertexArray = new ArrayList<Float>();
        ArrayList<Float> colorArray = new ArrayList<Float>();
        ArrayList<Float> normalArray = new ArrayList<Float>();
        ArrayList<Float> uvArray = new ArrayList<Float>();
        AssetManager assetManager = context.getAssets();
        try {
            InputStream stream = assetManager.open(objPath);
            InputStreamReader sr = new InputStreamReader(stream);
            BufferedReader br = new BufferedReader(sr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(" ");
                if (1 >= data.length) {
                    continue;
                }
                String command = data[0];
                if (false != command.equals("g")) {
                    String name = data[1];
                    if (false != name.equals("default")) {
                        this.addSubMesh(mesh, vertexArray, colorArray, normalArray, uvArray, vertexCount);
                        vertexCount = 0;
                        vertexArray.clear();
                        colorArray.clear();
                        normalArray.clear();
                        uvArray.clear();
                    } else {
                        mesh = new GLES1Mesh();
                        mesh.name = name;
                    }
                } else if (false != command.equals("usemtl")) {
                    String usemtl = data[1];
                    mesh.useMaterial = usemtl;
                } else if (false != command.equals("mtllib")) {
                    String path = data[1];
                    GLES1MaterialAsset materialAsset = new GLES1MaterialAsset();
                    this.materials = materialAsset.create(path, context);
                } else if (false != command.equals("v")) {
                    float x = Float.valueOf(data[1]);
                    float y = Float.valueOf(data[2]);
                    float z = Float.valueOf(data[3]);
                    vertexOriginArray.add(x);
                    vertexOriginArray.add(y);
                    vertexOriginArray.add(z);
                } else if (false != command.equals("vt")) {
                    float u = Float.valueOf(data[1]);
                    float v = Float.valueOf(data[2]);
                    uvOriginArray.add(u);
                    uvOriginArray.add(v);
                    if (4 == data.length) {
                        float w = Float.valueOf(data[3]);
                        uvOriginArray.add(w);
                    }
                } else if (false != command.equals("vn")) {
                    float x = Float.valueOf(data[1]);
                    float y = Float.valueOf(data[2]);
                    float z = Float.valueOf(data[3]);
                    normalOriginArray.add(x);
                    normalOriginArray.add(y);
                    normalOriginArray.add(z);
                } else if (false != command.equals("f")) {
                    ArrayList<Integer> tmpVertexArray = new ArrayList<Integer>();
                    ArrayList<Integer> tmpNormalArray = new ArrayList<Integer>();
                    ArrayList<Integer> tmpUvArray = new ArrayList<Integer>();
                    for (int i = 1; i < data.length; i++) {
                        String indexData = data[i];
                        String[] indexes = indexData.split("/");
                        int vertex = Integer.valueOf(indexes[0]);
                        int uv = Integer.valueOf(indexes[1]);
                        int normal = Integer.valueOf(indexes[2]);
                        tmpVertexArray.add(vertex);
                        tmpUvArray.add(uv);
                        tmpNormalArray.add(normal);
                    }
                    if (3 == tmpVertexArray.size()) {
                        int t1 = tmpVertexArray.get(0);
                        int t2 = tmpVertexArray.get(1);
                        int t3 = tmpVertexArray.get(2);
                        int tidx1 = t1 - 1;
                        int tidx2 = t2 - 1;
                        int tidx3 = t3 - 1;
                        tidx1 *= 3;
                        tidx2 *= 3;
                        tidx3 *= 3;
                        float x1 = vertexOriginArray.get(tidx1);
                        float y1 = vertexOriginArray.get(tidx1 + 1);
                        float z1 = vertexOriginArray.get(tidx1 + 2);
                        float x2 = vertexOriginArray.get(tidx2);
                        float y2 = vertexOriginArray.get(tidx2 + 1);
                        float z2 = vertexOriginArray.get(tidx2 + 2);
                        float x3 = vertexOriginArray.get(tidx3);
                        float y3 = vertexOriginArray.get(tidx3 + 1);
                        float z3 = vertexOriginArray.get(tidx3 + 2);
                        vertexArray.add(x1);
                        vertexArray.add(y1);
                        vertexArray.add(z1);
                        vertexArray.add(x2);
                        vertexArray.add(y2);
                        vertexArray.add(z2);
                        vertexArray.add(x3);
                        vertexArray.add(y3);
                        vertexArray.add(z3);
                        int count = 3 * GLES1Renderer.RGBA;
                        for (int i = 0; i < count; i++) {
                            colorArray.add(1.0f);
                        }
                        vertexCount += 3;
                    } else if (4 == tmpVertexArray.size()) {
                        int t1 = tmpVertexArray.get(0);
                        int t2 = tmpVertexArray.get(1);
                        int t3 = tmpVertexArray.get(2);
                        int t4 = tmpVertexArray.get(3);
                        int tidx1 = t1 - 1;
                        int tidx2 = t2 - 1;
                        int tidx3 = t3 - 1;
                        int tidx4 = t4 - 1;
                        tidx1 *= 3;
                        tidx2 *= 3;
                        tidx3 *= 3;
                        tidx4 *= 3;
                        float x1 = vertexOriginArray.get(tidx1);
                        float y1 = vertexOriginArray.get(tidx1 + 1);
                        float z1 = vertexOriginArray.get(tidx1 + 2);
                        float x2 = vertexOriginArray.get(tidx2);
                        float y2 = vertexOriginArray.get(tidx2 + 1);
                        float z2 = vertexOriginArray.get(tidx2 + 2);
                        float x3 = vertexOriginArray.get(tidx3);
                        float y3 = vertexOriginArray.get(tidx3 + 1);
                        float z3 = vertexOriginArray.get(tidx3 + 2);
                        float x4 = vertexOriginArray.get(tidx4);
                        float y4 = vertexOriginArray.get(tidx4 + 1);
                        float z4 = vertexOriginArray.get(tidx4 + 2);
                        vertexArray.add(x1);
                        vertexArray.add(y1);
                        vertexArray.add(z1);
                        vertexArray.add(x2);
                        vertexArray.add(y2);
                        vertexArray.add(z2);
                        vertexArray.add(x4);
                        vertexArray.add(y4);
                        vertexArray.add(z4);
                        vertexArray.add(x4);
                        vertexArray.add(y4);
                        vertexArray.add(z4);
                        vertexArray.add(x2);
                        vertexArray.add(y2);
                        vertexArray.add(z2);
                        vertexArray.add(x3);
                        vertexArray.add(y3);
                        vertexArray.add(z3);
                        int count = 6 * GLES1Renderer.RGBA;
                        for (int i = 0; i < count; i++) {
                            colorArray.add(1.0f);
                        }
                        vertexCount += 6;
                    }
                    if (3 == tmpUvArray.size()) {
                        int t1 = tmpUvArray.get(0);
                        int t2 = tmpUvArray.get(1);
                        int t3 = tmpUvArray.get(2);
                        int tidx1 = t1 - 1;
                        int tidx2 = t2 - 1;
                        int tidx3 = t3 - 1;
                        tidx1 *= 2;
                        tidx2 *= 2;
                        tidx3 *= 2;
                        float u1 = uvOriginArray.get(tidx1);
                        float v1 = uvOriginArray.get(tidx1 + 1);
                        float u2 = uvOriginArray.get(tidx2);
                        float v2 = uvOriginArray.get(tidx2 + 1);
                        float u3 = uvOriginArray.get(tidx3);
                        float v3 = uvOriginArray.get(tidx3 + 1);
                        v1 = 1.0f - v1;
                        v2 = 1.0f - v2;
                        v3 = 1.0f - v3;
                        uvArray.add(u1);
                        uvArray.add(v1);
                        uvArray.add(u2);
                        uvArray.add(v2);
                        uvArray.add(u3);
                        uvArray.add(v3);
                    } else if (4 == tmpUvArray.size()) {
                        int t1 = tmpUvArray.get(0);
                        int t2 = tmpUvArray.get(1);
                        int t3 = tmpUvArray.get(2);
                        int t4 = tmpUvArray.get(3);
                        int tidx1 = t1 - 1;
                        int tidx2 = t2 - 1;
                        int tidx3 = t3 - 1;
                        int tidx4 = t4 - 1;
                        tidx1 *= 2;
                        tidx2 *= 2;
                        tidx3 *= 2;
                        tidx4 *= 2;
                        float u1 = uvOriginArray.get(tidx1);
                        float v1 = uvOriginArray.get(tidx1 + 1);
                        float u2 = uvOriginArray.get(tidx2);
                        float v2 = uvOriginArray.get(tidx2 + 1);
                        float u3 = uvOriginArray.get(tidx3);
                        float v3 = uvOriginArray.get(tidx3 + 1);
                        float u4 = uvOriginArray.get(tidx4);
                        float v4 = uvOriginArray.get(tidx4 + 1);
                        v1 = 1.0f - v1;
                        v2 = 1.0f - v2;
                        v3 = 1.0f - v3;
                        v4 = 1.0f - v4;
                        uvArray.add(u1);
                        uvArray.add(v1);
                        uvArray.add(u2);
                        uvArray.add(v2);
                        uvArray.add(u4);
                        uvArray.add(v4);
                        uvArray.add(u4);
                        uvArray.add(v4);
                        uvArray.add(u2);
                        uvArray.add(v2);
                        uvArray.add(u3);
                        uvArray.add(v3);
                    }
                    if (3 == tmpNormalArray.size()) {
                        int t1 = tmpNormalArray.get(0);
                        int t2 = tmpNormalArray.get(1);
                        int t3 = tmpNormalArray.get(2);
                        int tidx1 = t1 - 1;
                        int tidx2 = t2 - 1;
                        int tidx3 = t3 - 1;
                        tidx1 *= 3;
                        tidx2 *= 3;
                        tidx3 *= 3;
                        float x1 = normalOriginArray.get(tidx1);
                        float y1 = normalOriginArray.get(tidx1 + 1);
                        float z1 = normalOriginArray.get(tidx1 + 2);
                        float x2 = normalOriginArray.get(tidx2);
                        float y2 = normalOriginArray.get(tidx2 + 1);
                        float z2 = normalOriginArray.get(tidx2 + 2);
                        float x3 = normalOriginArray.get(tidx3);
                        float y3 = normalOriginArray.get(tidx3 + 1);
                        float z3 = normalOriginArray.get(tidx3 + 2);
                        normalArray.add(x1);
                        normalArray.add(y1);
                        normalArray.add(z1);
                        normalArray.add(x2);
                        normalArray.add(y2);
                        normalArray.add(z2);
                        normalArray.add(x3);
                        normalArray.add(y3);
                        normalArray.add(z3);
                    } else if (4 == tmpNormalArray.size()) {
                        int t1 = tmpNormalArray.get(0);
                        int t2 = tmpNormalArray.get(1);
                        int t3 = tmpNormalArray.get(2);
                        int t4 = tmpNormalArray.get(3);
                        int tidx1 = t1 - 1;
                        int tidx2 = t2 - 1;
                        int tidx3 = t3 - 1;
                        int tidx4 = t4 - 1;
                        tidx1 *= 3;
                        tidx2 *= 3;
                        tidx3 *= 3;
                        tidx4 *= 3;
                        float x1 = normalOriginArray.get(tidx1);
                        float y1 = normalOriginArray.get(tidx1 + 1);
                        float z1 = normalOriginArray.get(tidx1 + 2);
                        float x2 = normalOriginArray.get(tidx2);
                        float y2 = normalOriginArray.get(tidx2 + 1);
                        float z2 = normalOriginArray.get(tidx2 + 2);
                        float x3 = normalOriginArray.get(tidx3);
                        float y3 = normalOriginArray.get(tidx3 + 1);
                        float z3 = normalOriginArray.get(tidx3 + 2);
                        float x4 = normalOriginArray.get(tidx4);
                        float y4 = normalOriginArray.get(tidx4 + 1);
                        float z4 = normalOriginArray.get(tidx4 + 2);
                        normalArray.add(x1);
                        normalArray.add(y1);
                        normalArray.add(z1);
                        normalArray.add(x2);
                        normalArray.add(y2);
                        normalArray.add(z2);
                        normalArray.add(x4);
                        normalArray.add(y4);
                        normalArray.add(z4);
                        normalArray.add(x4);
                        normalArray.add(y4);
                        normalArray.add(z4);
                        normalArray.add(x2);
                        normalArray.add(y2);
                        normalArray.add(z2);
                        normalArray.add(x3);
                        normalArray.add(y3);
                        normalArray.add(z3);
                    }
                }
            }
            br.close();
        } catch (IOException e) {
            Log.i("ANDROID_RENDERER, %s", e.getMessage());
        }
        this.addSubMesh(mesh, vertexArray, colorArray, normalArray, uvArray, vertexCount);
        return;
    }
    private void addSubMesh(GLES1Mesh mesh, ArrayList<Float> vertexArray, ArrayList<Float> colorArray, ArrayList<Float> normalArray, ArrayList<Float> uvArray, int vertexCount) {
        if (null == mesh) {
            return;
        }
        GLES1VertexArray vertex = new GLES1VertexArray(GLES1Renderer.DIMENSION3D);
        vertex.setVertexCount(vertexCount);
        vertex.setVertices(vertexArray);
        vertex.setColors(colorArray);
        vertex.setNormals(normalArray);
        vertex.setUVs(uvArray);
        mesh.vertex = vertex;
        mesh.renderMode = GLES11.GL_TRIANGLES;
        if (null != this.materials) {
            GLES1Material material = this.materials.get(mesh.useMaterial);
            if (null != material) {
                mesh.setMaterial(material);
            }
        }
        this.subMeshes.add(mesh);
        return;
    }
}
