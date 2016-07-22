package github.johnnysc.shopslist;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Hovhannes Asatryan on 22.07.16.
 */
public class ShopItem {
    @SerializedName("address")
    private String address;

    @SerializedName("town")
    private String town;

    @SerializedName("phone")
    private String phone;

    public ShopItem(String address, String town, String phone) {
        this.address = address;
        this.town = town;
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public String getTown() {
        return town;
    }

    public String getPhone() {
        return phone;
    }
}
