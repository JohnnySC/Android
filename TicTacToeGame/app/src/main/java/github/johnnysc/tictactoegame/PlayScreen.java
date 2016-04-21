package github.johnnysc.tictactoegame;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

/**
 * Created by johnny on 21.04.16.
 */
public class PlayScreen extends Activity {

    ImageButton twoPlayersButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_screen);

        twoPlayersButton = (ImageButton)findViewById(R.id.two_players_button);
        twoPlayersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PlayScreen.this, MainActivity.class);
                startActivityForResult(i, 0);
                finish();
            }
        });

       ImageView clouds = (ImageView)findViewById(R.id.clouds);
        clouds.setBackgroundResource(R.drawable.animation2);

        final AnimationDrawable cloudsMoving = (AnimationDrawable)clouds.getBackground();

        cloudsMoving.start();



    }
}
