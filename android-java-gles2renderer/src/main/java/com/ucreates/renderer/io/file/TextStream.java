// ======================================================================
// Project Name    : behaviour
//
// Copyright © 2017 U-CREATES. All rights reserved.
//
// This source code is the property of U-CREATES.
// If such findings are accepted at any time.
// We hope the tips and helpful in developing.
// ======================================================================
package com.ucreates.renderer.io.file;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
public class TextStream {
    private Context context;
    public TextStream(Context context) {
        this.context = context;
    }
    public String read(int resourceId) {
        Resources resources = this.context.getResources();
        InputStream stream = resources.openRawResource(resourceId);
        final InputStreamReader reader = new InputStreamReader(stream);
        final BufferedReader bufferedReader = new BufferedReader(reader);
        String nextLine;
        final StringBuilder body = new StringBuilder();
        try {
            while ((nextLine = bufferedReader.readLine()) != null) {
                body.append(nextLine);
                body.append("\n");
            }
            return body.toString();
        } catch (IOException e) {
            return "";
        }
    }
    public String read(String assetPath) {
        AssetManager assetManager = this.context.getAssets();
        String nextLine;
        final StringBuilder body = new StringBuilder();
        try {
            InputStream stream = assetManager.open(assetPath);
            final InputStreamReader reader = new InputStreamReader(stream);
            final BufferedReader bufferedReader = new BufferedReader(reader);
            while ((nextLine = bufferedReader.readLine()) != null) {
                body.append(nextLine);
                body.append("\n");
            }
            return body.toString();
        } catch (IOException e) {
            return "";
        }
    }
}