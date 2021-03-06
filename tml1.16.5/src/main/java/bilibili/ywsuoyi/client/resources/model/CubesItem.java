package bilibili.ywsuoyi.client.resources.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CubesItem {
    @SerializedName("uv")
    private List<Float> uv;

    @SerializedName("mirror")
    private boolean mirror;

    @SerializedName("inflate")
    private float inflate;

    @SerializedName("size")
    private List<Float> size;

    @SerializedName("origin")
    private List<Float> origin;

    public List<Float> getUv() {
        return uv;
    }

    public boolean isMirror() {
        return mirror;
    }

    public float getInflate() {
        return inflate;
    }

    /**
     * 基岩版这货居然可以为浮点数，服了
     */
    public List<Float> getSize() {
        return size;
    }

    public List<Float> getOrigin() {
        return origin;
    }

    @Override
    public String toString() {
        return
                "CubesItem{" +
                        "uv = '" + uv + '\'' +
                        ",inflate = '" + inflate + '\'' +
                        ",mirror = '" + mirror + '\'' +
                        ",size = '" + size + '\'' +
                        ",origin = '" + origin + '\'' +
                        "}";
    }
}