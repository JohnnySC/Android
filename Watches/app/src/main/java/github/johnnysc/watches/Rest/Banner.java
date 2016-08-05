package github.johnnysc.watches.Rest;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Hovhannes Asatryan on 04.08.16.
 */
public class Banner {
    @SerializedName("id")
    private String id;

    @SerializedName("img")
    private String img;

    @SerializedName("type")
    private String type;

    public String getId() {
        return id;
    }

    public String getImg() {
        return img;
    }

    public String getType() {
        return type;
    }
}
