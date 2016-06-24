package github.johnnysc.testappintech;

import android.app.Activity;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import java.io.IOException;

/**
 * Created by Hovhannes Asatryan on 23.06.16.
 */
public class PlayerActivity extends Activity {
    public static ImageView albumCoverView;
    public static TextView songView;
    public static TextView artistNameView;
    public static Bitmap bitmap=null;
    private Song song = null;
    private Button playButton;
    private Button pauseButton;
    boolean isPlaying = false;
    private  MediaPlayer mp=null;
    private SeekBar seekBar;
    private Handler myHandler = new Handler();
    private double startTime = 0;
    private double finalTime = 0;
    public static int oneTimeOnly;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_activity);

        albumCoverView = (ImageView)findViewById(R.id.playerAlbumCoverView);
        songView = (TextView)findViewById(R.id.playerSongTextView);
        artistNameView = (TextView)findViewById(R.id.playerArtistNameView);
        playButton = (Button)findViewById(R.id.playButton);
        pauseButton = (Button)findViewById(R.id.pauseButton);

        song = SongAdapter.allSongs.get(MainActivity.positionOfItem);

        Thread thread = new Thread(new bitmapRunnable());
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        albumCoverView.setImageBitmap(bitmap);

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

                if (!isPlaying) {
                    isPlaying = true;

                    try {
                        if(oneTimeOnly==0) {
                            mp.setDataSource(song.getSongURL());
                            mp.prepare();
                        }
                        mp.start();
                        finalTime = mp.getDuration();

                        if(startTime==finalTime)
                            startTime=0;
                        else
                        startTime = mp.getCurrentPosition();

                        if (oneTimeOnly == 0) {
                            seekBar.setMax((int) finalTime);
                            oneTimeOnly = 1;
                        }
                        seekBar.setProgress((int)startTime);
                        myHandler.postDelayed(UpdateSongTime,100);
                        pauseButton.setEnabled(true);
                        playButton.setEnabled(false);
                    } catch (IOException e) {
                        Log.e("Log_Tag","prepare() failed");
                    }
                } else {
                    isPlaying = false;
                    mp.release();
                    mp = null;
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

    private class bitmapRunnable implements Runnable{
        public void run(){
            bitmap = SongAdapter.getImageBitmap(song.getCoverURL());
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        myHandler.removeCallbacks(UpdateSongTime);
        mp.release();
    }
}
