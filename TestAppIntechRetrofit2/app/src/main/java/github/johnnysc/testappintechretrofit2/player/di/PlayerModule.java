package github.johnnysc.testappintechretrofit2.player.di;

import dagger.Module;
import dagger.Provides;
import github.johnnysc.testappintechretrofit2.main.di.bean.Song;

/**
 * @author Asatryan on 08.07.18
 */
@PlayerScope
@Module
public class PlayerModule {

    private final Song mSong;

    public PlayerModule(Song song) {
        mSong = song;
    }

    @PlayerScope
    @Provides
    public Song provideSong() {
        return mSong;
    }
}