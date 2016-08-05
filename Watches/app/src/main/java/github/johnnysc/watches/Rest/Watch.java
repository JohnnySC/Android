package github.johnnysc.watches.Rest;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Hovhannes Asatryan on 04.08.16.
 */
public class Watch {
    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("brand")
    private String brand;

    @SerializedName("collection")
    private String collection;

    @SerializedName("img")
    private String img;

    @SerializedName("ref")
    private String ref;

    @SerializedName("usd")
    private String usd;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public String getCollection() {
        return collection;
    }

    public String getImg() {
        return img;
    }

    public String getRef() {
        return ref;
    }

    public String getUsd() {
        return usd;
    }
}
