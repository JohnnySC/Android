package github.johnnysc.testappintechretrofit2.player.di;

import dagger.Subcomponent;
import github.johnnysc.testappintechretrofit2.UI.PlayerActivity;

/**
 * @author Asatryan on 08.07.18
 */
@PlayerScope
@Subcomponent(modules = PlayerModule.class)
public interface PlayerActivityComponent {

    void inject(PlayerActivity activity);
}