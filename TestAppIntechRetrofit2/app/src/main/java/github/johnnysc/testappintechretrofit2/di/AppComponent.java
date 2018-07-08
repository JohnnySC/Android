package github.johnnysc.testappintechretrofit2.di;

import javax.inject.Singleton;

import dagger.Component;
import github.johnnysc.testappintechretrofit2.main.di.MainActivityComponent;

/**
 * @author Asatryan on 08.07.18
 */
@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface AppComponent {

    MainActivityComponent createMainActivityComponent();
}