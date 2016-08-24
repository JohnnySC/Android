package github.johnnysc.mayakoffskij;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.FileNotFoundException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

/*
 * Created by Hovhannes Asatryan (https://github.com/JohnnySC) on 05.05.16.
 */
public class FoundPoemView extends Activity {
    @BindView(R.id.aPoemTextView) TextView aPoemView;
    @BindView(R.id.nextButton) Button nextButton;
    @BindView(R.id.previousButton) Button previousButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poem_view);

        ButterKnife.bind(this);
        aPoemView.setText(Poems.getPoem(SearchResultsList.foundPosition));
        aPoemView.setTextSize(MainActivity.fontSize);

        nextButton.setVisibility(View.GONE);
        previousButton.setVisibility(View.GONE);
    }

    @OnClick(R.id.add_to_favorites)
    void addToFavorites(){
        if(!FavoritePoems.favIndexes.contains(SearchResultsList.foundPosition)) {
            FavoritePoems.favIndexes.add(SearchResultsList.foundPosition);
            FavoritePoemsAdapter.favoritePoems.add(PoemAdapter.poems.get(SearchResultsList.foundPosition));
            Crouton.makeText(this,"Добавлено в избранное", Style.INFO).show();
            try {
                FileIO.writeData(this,getApplicationContext(),openFileOutput(FileIO.file_name,MODE_PRIVATE));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }else
            Crouton.makeText(this,"Уже содержится в избранных",Style.ALERT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Crouton.cancelAllCroutons();
    }
}
