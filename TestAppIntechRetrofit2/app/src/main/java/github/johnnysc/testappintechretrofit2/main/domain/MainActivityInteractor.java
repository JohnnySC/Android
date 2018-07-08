package github.johnnysc.testappintechretrofit2.main.domain;

import android.text.Editable;

import github.johnnysc.testappintechretrofit2.main.data.bean.SongList;
import io.reactivex.Observable;

/**
 * @author Asatryan on 08.07.18
 */
public interface MainActivityInteractor {

    Observable<SongList> getSongList();

    void afterTextChanged(Editable input);
}