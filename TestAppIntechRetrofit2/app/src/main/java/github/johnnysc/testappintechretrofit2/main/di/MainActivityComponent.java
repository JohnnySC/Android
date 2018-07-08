package github.johnnysc.testappintechretrofit2.main.di;

import dagger.Subcomponent;
import github.johnnysc.testappintechretrofit2.main.presentation.MainActivity;
import github.johnnysc.testappintechretrofit2.player.di.PlayerActivityComponent;
import github.johnnysc.testappintechretrofit2.player.di.PlayerModule;

/**
 * @author Asatryan on 08.07.18
 */
//@ActivityScope
@Subcomponent(modules = MainActivityModule.class)
public interface MainActivityComponent {

    PlayerActivityComponent createPlayerActivityComponent(PlayerModule module);

    void inject(MainActivity activity);
}