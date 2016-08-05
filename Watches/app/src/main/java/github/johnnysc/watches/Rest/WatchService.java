package github.johnnysc.watches.Rest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Hovhannes Asatryan on 04.08.16.
 */
public interface WatchService {
    @GET("watches/popular")
    Call<WatchList> getWatchesList(@Query("nonce") String nonce, @Query("signature") String signature);

    @GET("banner")
    Call<BannerList> getBannersList(@Query("nonce") String nonce, @Query("signature") String signature);
}
