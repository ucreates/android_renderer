package com.ucreates.renderer.asset.wipe;
import com.ucreates.renderer.asset.BaseAsset;
import com.ucreates.renderer.entity.GLESColor;
import com.ucreates.renderer.renderer.GLES1Renderer;
public class BaseWipeAsset extends BaseAsset {
    protected int divideCount;
    protected float radius;
    protected float maxScale;
    private float time;
    public BaseWipeAsset() {
        this.color = GLESColor.black;
    }
    public void create(int dimension) {
        if (dimension == GLES1Renderer.DIMENSION2D) {
            this.create2D();
        } else {
            this.create3D();
        }
        return;
    }
    protected void create2D() {
        return;
    }
    protected void create3D() {
        return;
    }
    public float wipeIn(float delta, float totalTime) {
        float scale = this.easeIn(this.time, 0.0f, this.maxScale, totalTime);
        this.time += delta;
        return scale;
    }
    public float wipeOut(float delta, float totalTime) {
        float scale = this.easeIn(this.time, this.maxScale, 0.0f, totalTime);
        this.time += delta;
        return scale;
    }
    private float easeIn(float currentTime, float start, float end, float totalTime) {
        float rate = currentTime / totalTime;
        float diff = end - start;
        if (1.0f <= rate) {
            rate = 1.0f;
        }
        return diff * (float) Math.pow(2, 10.0f * (rate - 1.0f)) + start;
    }
}
