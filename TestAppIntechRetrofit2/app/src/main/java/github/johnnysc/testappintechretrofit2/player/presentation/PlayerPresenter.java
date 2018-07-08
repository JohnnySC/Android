package github.johnnysc.testappintechretrofit2.player.presentation;

import android.media.MediaPlayer;
import android.os.Handler;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import github.johnnysc.testappintechretrofit2.main.data.bean.Song;

/**
 * @author Asatryan on 08.07.18
 */
@InjectViewState
public class PlayerPresenter extends MvpPresenter<PlayerView> {

    private final Song mSong;

    private boolean mIsPlaying;
    private MediaPlayer mMediaPlayer;
    private Handler mHandler;
    private double mStartTime;
    private double mFinalTime;
    private int mOneTimeOnly;

    private final Runnable mUpdateSongTime;

    @Inject
    public PlayerPresenter(Song song) {
        mSong = song;
        mIsPlaying = false;
        mMediaPlayer = new MediaPlayer();
        mOneTimeOnly = 0;
        mStartTime = 0;
        mFinalTime = 0;
        mHandler = new Handler();
        mUpdateSongTime = new Runnable() {
            public void run() {
                mStartTime = mMediaPlayer.getCurrentPosition();
                getViewState().setSeekBarProgress((int) mStartTime);
                mHandler.postDelayed(this, 100);
            }
        };
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().setArtistText(mSong.getArtistName());
        getViewState().setTrackText(mSong.getTrackName());
        getViewState().setImageSource(mSong.getCoverUrl());
        getViewState().setPauseButtonEnabled(false);
    }

    public void play() {
        try {
            if (!mIsPlaying) {
                mIsPlaying = true;
                if (mOneTimeOnly == 0) {
                    mMediaPlayer.setDataSource(mSong.getPreviewUrl());
                    mMediaPlayer.prepare();
                }
                mMediaPlayer.start();
                mFinalTime = mMediaPlayer.getDuration();
                if (mStartTime == mFinalTime) {
                    mStartTime = 0;
                } else {
                    mStartTime = mMediaPlayer.getCurrentPosition();
                }
                if (mOneTimeOnly == 0) {
                    getViewState().setSeekBarMax((int) mFinalTime);
                    mOneTimeOnly = 1;
                }
                getViewState().setSeekBarProgress((int) mStartTime);
                mHandler.postDelayed(mUpdateSongTime, 100);
                getViewState().setPauseButtonEnabled(true);
                getViewState().setPlayButtonEnabled(false);
            } else {
                mIsPlaying = false;
                mMediaPlayer.release();
                mMediaPlayer = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pause() {
        mIsPlaying = false;
        mMediaPlayer.pause();
        getViewState().setPlayButtonEnabled(true);
        getViewState().setPauseButtonEnabled(false);
    }

    public void onBackPressed() {
        mHandler.removeCallbacks(mUpdateSongTime);
        if (mMediaPlayer != null)
            mMediaPlayer.release();
    }
}