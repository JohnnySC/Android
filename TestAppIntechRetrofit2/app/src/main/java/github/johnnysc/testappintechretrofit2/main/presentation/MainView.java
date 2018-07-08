package github.johnnysc.testappintechretrofit2.main.presentation;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import github.johnnysc.testappintechretrofit2.main.data.bean.Song;

/**
 * @author Asatryan on 08.07.18
 */
public interface MainView extends MvpView {

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showData(List<Song> songList);
}