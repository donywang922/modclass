package bilibili.ywsuoyi.client.resources.model;

import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

public class ModelList {
    @SerializedName("date")
    private String date;

    @SerializedName("model_list")
    private List<ModelInfo> modelList;

    @SerializedName("pack_name")
    private String packName;

    @SerializedName("author")
    private List<String> author;

    @SerializedName("description")
    private List<String> description;

    @SerializedName("version")
    private String version;

    @SerializedName("icon")
    private Identifier icon;

    @SerializedName("icon_delay")
    private int iconDelay = 2;

    @Expose(deserialize = false)
    private AnimationState iconAnimation = AnimationState.UNCHECK;

    @Expose(deserialize = false)
    private int iconAspectRatio = 1;

    @Nullable
    public String getDate() {
        return date;
    }

    public List<ModelInfo> getModelList() {
        return modelList;
    }

    public String getPackName() {
        return packName;
    }

    public List<String> getAuthor() {
        return author;
    }

    public List<String> getDescription() {
        return description;
    }

    @Nullable
    public String getVersion() {
        return version;
    }

    @Nullable
    public Identifier getIcon() {
        return icon;
    }

    public AnimationState getIconAnimation() {
        return iconAnimation;
    }

    public void setIconAnimation(AnimationState iconAnimation) {
        this.iconAnimation = iconAnimation;
    }

    public int getIconAspectRatio() {
        return iconAspectRatio;
    }

    public void setIconAspectRatio(int iconAspectRatio) {
        this.iconAspectRatio = iconAspectRatio;
    }

    public int getIconDelay() {
        return iconDelay;
    }

    public ModelList decorate() {
        // ????????? model list ????????????
        if (packName == null) {
            throw new JsonSyntaxException("Expected \"pack_name\" in pack");
        }
        if (modelList == null || modelList.isEmpty()) {
            throw new JsonSyntaxException("Expected \"model_list\" in pack");
        }
        if (description == null) {
            description = Collections.emptyList();
        }
        if (author == null) {
            author = Collections.emptyList();
        }
        if (iconDelay <= 0) {
            iconDelay = 1;
        }

        // ??????????????????????????????????????????
        modelList.forEach(ModelInfo::decorate);
        return this;
    }

    public enum AnimationState {
        // ????????????
        TRUE,
        // ????????????
        FALSE,
        // ?????????????????????????????????
        UNCHECK
    }

}
