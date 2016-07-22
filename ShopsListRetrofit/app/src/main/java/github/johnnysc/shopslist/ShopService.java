package github.johnnysc.shopslist;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Hovhannes Asatryan on 22.07.16.
 */
public interface ShopService {
    @GET("shops/list")
    Call<Shops> getAllShops();
}
