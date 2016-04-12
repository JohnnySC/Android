package github.johnnysc.rcanoed;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * Created by johnny on 11.04.16.
 */
public class optionsActivity extends Activity {
    private ImageButton instantTouch;
    private ImageButton arrows;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options);

        instantTouch = (ImageButton) findViewById(R.id.instantTouch);
        arrows = (ImageButton) findViewById(R.id.arrowsButton);

        instantTouch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rcanoedGame.optionInput=1;
                instantTouch.setImageResource(R.drawable.instant_touch_selected);
                arrows.setImageResource(R.drawable.arrows);
            }
        });
        arrows.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rcanoedGame.optionInput=-2;
                arrows.setImageResource(R.drawable.arrows_selected);
                instantTouch.setImageResource(R.drawable.instant_touch);
            }
        });
    }
}
