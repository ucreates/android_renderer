// ======================================================================
// Project Name    : android_renderer
//
// Copyright © 2020 U-CREATES. All rights reserved.
//
// This source code is the property of U-CREATES.
// If such findings are accepted at any time.
// We hope the tips and helpful in developing.
// ======================================================================
package com.ucreates.renderer.io.memory;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
public class Allocator {
    public static final int GL_SHORT_SIZE = 2;
    public static final int GL_FLOAT_SIZE = 4;
    public static final int GL_VECTOR3_SIZE = 12;
    public static ByteBuffer allocate(int count) {
        ByteBuffer buffer = ByteBuffer.allocateDirect(count);
        ByteOrder nativeOrder = ByteOrder.nativeOrder();
        buffer.order(nativeOrder);
        return buffer;
    }
    public static FloatBuffer allocateFloat(int length) {
        return Allocator.allocate(length * Allocator.GL_FLOAT_SIZE).asFloatBuffer();
    }
    public static ShortBuffer allocateShort(int length) {
        return Allocator.allocate(length * Allocator.GL_SHORT_SIZE).asShortBuffer();
    }
}