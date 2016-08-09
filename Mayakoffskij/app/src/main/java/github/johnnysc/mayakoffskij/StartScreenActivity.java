package github.johnnysc.mayakoffskij;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/*
 * Created by Hovhannes Asatryan (https://github.com/JohnnySC) on 05.05.16.
 */
public class StartScreenActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_screen);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable(){
            public void run(){
                Intent intent = new Intent(StartScreenActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        },3000);
    }
}
