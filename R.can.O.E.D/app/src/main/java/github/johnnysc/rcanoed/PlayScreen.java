package github.johnnysc.rcanoed;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by johnny on 11.04.16.
 */
public class PlayScreen extends Activity {
    private ImageButton playButton;
    private ImageButton optionsButton;
    private ImageButton exitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playButton = (ImageButton) findViewById(R.id.playButton);
        optionsButton = (ImageButton) findViewById(R.id.optionsButton);
        exitButton = (ImageButton) findViewById(R.id.exitButton);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PlayScreen.this, MainActivity.class);
                startActivityForResult(i,0);
                rcanoedGame.loseCounts=0;
            }
        });

        optionsButton.setOnClickListener(new View.OnClickListener(){
            @Override
        public void onClick(View v){
                Intent i = new Intent(PlayScreen.this,optionsActivity.class);
                startActivityForResult(i,0);
            }
        });

        exitButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });
    }

}
