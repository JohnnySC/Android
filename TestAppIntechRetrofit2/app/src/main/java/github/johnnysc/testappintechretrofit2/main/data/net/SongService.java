package github.johnnysc.testappintechretrofit2.main.data.net;

import github.johnnysc.testappintechretrofit2.main.data.bean.SongList;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author Hovhannes Asatryan on 08.07.18.
 */
public interface SongService {

    @GET("search")
    Observable<SongList> getSongsList(@Query("term") String term);
}