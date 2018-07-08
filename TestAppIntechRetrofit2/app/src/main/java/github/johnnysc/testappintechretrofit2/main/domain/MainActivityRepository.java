package github.johnnysc.testappintechretrofit2.main.domain;

import github.johnnysc.testappintechretrofit2.main.data.bean.SongList;
import io.reactivex.Observable;

/**
 * @author Asatryan on 08.07.18
 */
public interface MainActivityRepository {

    Observable<SongList> getSongsList(String term);
}