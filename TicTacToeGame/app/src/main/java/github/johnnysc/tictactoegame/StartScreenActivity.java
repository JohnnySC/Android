package github.johnnysc.tictactoegame;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class StartScreenActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_screen);

        ImageView logo = (ImageView)findViewById(R.id.logo);
        logo.setBackgroundResource(R.drawable.animation);

        final AnimationDrawable logoAnimated = (AnimationDrawable)logo.getBackground();

        logoAnimated.start();


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                logoAnimated.stop();
                Intent i = new Intent(StartScreenActivity.this, PlayScreen.class);
                startActivityForResult(i, 0);
                finish();
            }
        }, 5000);
    }
}
