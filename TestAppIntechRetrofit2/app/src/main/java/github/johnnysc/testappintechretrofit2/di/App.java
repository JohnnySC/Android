package github.johnnysc.testappintechretrofit2.di;

import android.app.Application;

import github.johnnysc.testappintechretrofit2.main.data.bean.Song;
import github.johnnysc.testappintechretrofit2.main.di.MainActivityComponent;
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
    private Song mSong;

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
        if (mMainActivityComponent == null) {
            createMainActivityComponent();
        }
        return mMainActivityComponent;
    }

    public void clearMainActivityComponent() {
        mMainActivityComponent = null;
    }

    public void createPlayerActivityComponent(Song song) {
        mSong = song;
        mPlayerComponent = mMainActivityComponent.createPlayerActivityComponent(new PlayerModule(song));
    }

    public PlayerActivityComponent getPlayerComponent() {
        if (mPlayerComponent == null) {
            createPlayerActivityComponent(mSong);
        }
        return mPlayerComponent;
    }

    public void clearPlayerActivityComponent() {
        mPlayerComponent = null;
    }
}
