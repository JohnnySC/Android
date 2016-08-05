package github.johnnysc.watches.Rest;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Hovhannes Asatryan on 04.08.16.
 */
public class BannerList {
    @SerializedName("data")
    List<Banner> bannersList;

    public BannerList(List<Banner> banners){
        this.bannersList = banners;
    }

    public List<Banner> getBannersList(){
        return bannersList;
    }
}
