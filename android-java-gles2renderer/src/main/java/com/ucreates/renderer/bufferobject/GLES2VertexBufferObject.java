package com.ucreates.renderer.bufferobject;
import android.opengl.GLES20;
import com.ucreates.renderer.shader.GLES2ProgramObject;
public class GLES2VertexBufferObject extends GLES2BaseVertexBufferObject {
    public GLES2VertexBufferObject(int dimension) {
        super(dimension);
    }
    @Override
    public void bind(GLES2ProgramObject programObject) {
        super.bind(programObject);
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, this.bufferId);
        return;
    }
}
