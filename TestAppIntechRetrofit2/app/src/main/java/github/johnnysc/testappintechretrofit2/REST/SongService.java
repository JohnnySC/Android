package github.johnnysc.testappintechretrofit2.REST;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Hovhannes Asatryan on 12.08.16.
 */
public interface SongService {
 @GET("search")
    Call<SongList> getSongsList(@Query("term") String term);
}
