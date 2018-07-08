package github.johnnysc.testappintechretrofit2.player.presentation;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * @author Asatryan on 08.07.18
 */
public interface PlayerView extends MvpView {

    @StateStrategyType(AddToEndSingleStrategy.class)
    void setTrackText(String text);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void setArtistText(String text);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void setImageSource(String source);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void setPlayButtonEnabled(boolean enabled);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void setPauseButtonEnabled(boolean enabled);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void setSeekBarProgress(int time);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void setSeekBarMax(int max);
}