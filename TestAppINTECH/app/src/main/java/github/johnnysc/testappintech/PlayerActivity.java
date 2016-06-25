package github.johnnysc.testappintech;

import android.app.Activity;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by Hovhannes Asatryan on 23.06.16.
 */
public class PlayerActivity extends Activity {
    ImageView albumCoverView;
    TextView songView;
    TextView artistNameView;
    Bitmap bitmap=null;
    Song song = null;
    Button playButton;
    Button pauseButton;
    boolean isPlaying = false;
    MediaPlayer mp=null;
    SeekBar seekBar;
    Handler myHandler = new Handler();
    double startTime = 0;
    double finalTime = 0;
    int oneTimeOnly;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_activity);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        albumCoverView = (ImageView)findViewById(R.id.playerAlbumCoverView);
        songView = (TextView)findViewById(R.id.playerSongTextView);
        artistNameView = (TextView)findViewById(R.id.playerArtistNameView);
        playButton = (Button)findViewById(R.id.playButton);
        pauseButton = (Button)findViewById(R.id.pauseButton);

        song = SongAdapter.allSongs.get(MainActivity.positionOfItem);

        Thread thread = new Thread(new bitmapRunnable());
        MainActivity.runTheThread(thread);

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
                try {
                    if (!isPlaying) {
                        isPlaying = true;
                        if (oneTimeOnly == 0) {
                            mp.setDataSource(song.getSongURL());
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

    private class bitmapRunnable implements Runnable{
        public void run(){
            bitmap = SongAdapter.getImageBitmap(song.getCoverURL());
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        myHandler.removeCallbacks(UpdateSongTime);
        if(mp!=null)
        mp.release();
    }
}
