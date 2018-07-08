package github.johnnysc.testappintechretrofit2.REST;

import github.johnnysc.testappintechretrofit2.main.di.bean.SongList;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Hovhannes Asatryan on 12.08.16.
 */
public interface SongService {
    @GET("search")
    Observable<SongList> getSongsList(@Query("term") String term);
}