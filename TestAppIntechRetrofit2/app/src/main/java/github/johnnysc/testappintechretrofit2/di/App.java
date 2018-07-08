package github.johnnysc.testappintechretrofit2.di;

import android.app.Application;

import github.johnnysc.testappintechretrofit2.main.di.MainActivityComponent;
import github.johnnysc.testappintechretrofit2.main.di.bean.Song;
import github.johnnysc.testappintechretrofit2.player.di.PlayerActivityComponent;
import github.johnnysc.testappintechretrofit2.player.di.PlayerModule;

/**
 * @author Asatryan on 08.07.18
 */
public class App extends Application {

    private static final String BASE_URL = "https://itunes.apple.com/";
    private static App sInstance;

    private AppComponent mAppComponent;
    private MainActivityComponent mMainActivityComponent;
    private PlayerActivityComponent mPlayerComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule(BASE_URL))
                .build();
    }

    public static App getInstance() {
        return sInstance;
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    public void createMainActivityComponent() {
        mMainActivityComponent = mAppComponent.createMainActivityComponent();
    }

    public MainActivityComponent getMainActivityComponent() {
        return mMainActivityComponent;
    }

    public void clearMainActivityComponent() {
        mMainActivityComponent = null;
    }

    public void createPlayerActivityComponent(Song song) {
        mPlayerComponent = mMainActivityComponent.createPlayerActivityComponent(new PlayerModule(song));
    }

    public PlayerActivityComponent getPlayerComponent() {
        return mPlayerComponent;
    }

    public void clearPlayerActivityComponent() {
        mPlayerComponent = null;
    }
}
