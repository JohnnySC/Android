package github.johnnysc.shopslist;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hovhannes Asatryan on 22.07.16.
 */
public class Shops {
    @SerializedName("data")
    List<ShopItem> shopsList;

    public Shops() {
        this.shopsList = new ArrayList<>();
    }

    public List<ShopItem> getShopsList() {
        return shopsList;
    }
}
