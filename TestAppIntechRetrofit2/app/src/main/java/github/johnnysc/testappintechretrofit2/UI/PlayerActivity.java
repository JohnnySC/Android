package github.johnnysc.testappintechretrofit2.UI;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import github.johnnysc.testappintechretrofit2.R;
import github.johnnysc.testappintechretrofit2.main.di.bean.Song;

/**
 * Created by Hovhannes Asatryan on 12.08.16.
 */
public class PlayerActivity extends Activity {
    ImageView albumCoverView;
    TextView songView;
    TextView artistNameView;
    Button pauseButton;
    Button playButton;
    boolean isPlaying = false;
    MediaPlayer mp = null;
    SeekBar seekBar;
    Handler myHandler = new Handler();
    double startTime = 0;
    double finalTime = 0;
    int oneTimeOnly;
    Song song;

    private void initUI() {
        albumCoverView = findViewById(R.id.playerAlbumCoverView);
        songView = findViewById(R.id.playerSongTextView);
        artistNameView = findViewById(R.id.playerArtistNameView);
        pauseButton = findViewById(R.id.pauseButton);
        playButton = findViewById(R.id.playButton);

        pauseButton.setOnClickListener(v -> {
            isPlaying = false;
            mp.pause();
            playButton.setEnabled(true);
            pauseButton.setEnabled(false);
        });

        playButton.setOnClickListener(v -> {
            try {
                if (!isPlaying) {
                    isPlaying = true;
                    if (oneTimeOnly == 0) {
                        mp.setDataSource(song.getPreviewUrl());
                        mp.prepare();
                    }
                    mp.start();
                    finalTime = mp.getDuration();
                    if (startTime == finalTime)
                        startTime = 0;
                    else
                        startTime = mp.getCurrentPosition();
                    if (oneTimeOnly == 0) {
                        seekBar.setMax((int) finalTime);
                        oneTimeOnly = 1;
                    }
                    seekBar.setProgress((int) startTime);
                    myHandler.postDelayed(UpdateSongTime, 100);
                    pauseButton.setEnabled(true);
                    playButton.setEnabled(false);

                } else {
                    isPlaying = false;
                    mp.release();
                    mp = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        initUI();
        song = (getIntent().getParcelableExtra("song"));

        Picasso.with(this)
                .load(song.getCoverUrl())
                .error(R.drawable.ic_error)
                .placeholder(R.drawable.progress_animation)
                .into(albumCoverView);
        songView.setText(song.getTrackName());
        artistNameView.setText(song.getArtistName());

        seekBar = findViewById(R.id.seekBar);
        seekBar.setClickable(false);
        pauseButton.setEnabled(false);
        mp = new MediaPlayer();
        oneTimeOnly = 0;

    }

    private Runnable UpdateSongTime = new Runnable() {
        public void run() {
            startTime = mp.getCurrentPosition();
            seekBar.setProgress((int) startTime);
            myHandler.postDelayed(this, 100);
        }
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        myHandler.removeCallbacks(UpdateSongTime);
        if (mp != null)
            mp.release();
    }
}
