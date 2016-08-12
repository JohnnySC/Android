package github.johnnysc.testappintechretrofit2.UI;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;
import github.johnnysc.testappintechretrofit2.R;
import github.johnnysc.testappintechretrofit2.REST.Song;

/**
 * Created by Hovhannes Asatryan on 12.08.16.
 */
public class PlayerActivity extends Activity {
    @BindView(R.id.playerAlbumCoverView) ImageView albumCoverView;
    @BindView(R.id.playerSongTextView) TextView songView;
    @BindView(R.id.playerArtistNameView) TextView artistNameView;
    @BindView(R.id.pauseButton) Button pauseButton;
    @BindView(R.id.playButton) Button playButton;
    boolean isPlaying = false;
    MediaPlayer mp=null;
    SeekBar seekBar;
    Handler myHandler = new Handler();
    double startTime = 0;
    double finalTime = 0;
    int oneTimeOnly;
    Song song;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        ButterKnife.bind(this);

        song = Parcels.unwrap(getIntent().getParcelableExtra("song"));

        Picasso.with(this)
                .load(song.getArtWorkUrl100())
                .error(R.drawable.ic_error)
                .placeholder(R.drawable.progress_animation)
                .into(albumCoverView);
        songView.setText(song.getTrackName());
        artistNameView.setText(song.getArtistName());

        seekBar = (SeekBar)findViewById(R.id.seekBar);
        seekBar.setClickable(false);
        pauseButton.setEnabled(false);
        mp = new MediaPlayer();
        oneTimeOnly =0;

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isPlaying = false;
                mp.pause();
                playButton.setEnabled(true);
                pauseButton.setEnabled(false);
            }
        });
    }

    private Runnable UpdateSongTime = new Runnable(){
        public void run(){
            startTime = mp.getCurrentPosition();
            seekBar.setProgress((int)startTime);
            myHandler.postDelayed(this,100);
        }
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        myHandler.removeCallbacks(UpdateSongTime);
        if(mp!=null)
            mp.release();
    }
}
