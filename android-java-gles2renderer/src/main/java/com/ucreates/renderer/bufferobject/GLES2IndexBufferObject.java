package com.ucreates.renderer.bufferobject;
import android.opengl.GLES20;
import com.ucreates.renderer.io.memory.GLES2Allocator;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
public class GLES2IndexBufferObject extends GLES2BaseVertexBufferObject {
    public int indiciesMemsize;
    public ShortBuffer indicies;
    public void allocateBuffer() {
        IntBuffer intBuffer = IntBuffer.allocate(1);
        GLES20.glGenBuffers(1, intBuffer);
        int bid = intBuffer.get(0);
        GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, bid);
        GLES20.glBufferData(GLES20.GL_ELEMENT_ARRAY_BUFFER, this.indiciesMemsize, this.indicies, GLES20.GL_STATIC_DRAW);
        this.bufferId = bid;
        return;
    }
    public void bind() {
        GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, this.bufferId);
        return;
    }
    public void setIndicies(short[] indiciesArray) {
        this.indicies = GLES2Allocator.allocateShort(indiciesArray.length);
        this.indicies.put(indiciesArray).position(0);
        this.count = indiciesArray.length;
        this.indiciesMemsize = this.count * GLES2Allocator.GL_SHORT_SIZE;
        return;
    }
}
