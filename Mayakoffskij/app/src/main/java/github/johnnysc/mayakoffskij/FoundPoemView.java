package github.johnnysc.mayakoffskij;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/*
 * Created by Hovhannes Asatryan (https://github.com/JohnnySC) on 05.05.16.
 */
public class FoundPoemView extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poem_view);

        TextView aPoemView = (TextView)findViewById(R.id.aPoemTextView);
        aPoemView.setText(Poems.getPoem(SearchResultsList.foundPosition));
        aPoemView.setTextSize(MainActivity.fontSize);

        final Button nextButton = (Button)findViewById(R.id.nextButton);
        nextButton.setVisibility(View.GONE);

        final Button previousButton = (Button)findViewById(R.id.previousButton);
        previousButton.setVisibility(View.GONE);

        final Button addToFavorites = (Button)findViewById(R.id.add_to_favorites);

        addToFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if(!FavoritePoems.favIndexes.contains(SearchResultsList.foundPosition)) {
                        FavoritePoems.favIndexes.add(SearchResultsList.foundPosition);
                        FavoritePoemsAdapter.favoritePoems.add(PoemAdapter.poems.get(SearchResultsList.foundPosition));
                        Toast.makeText(getApplicationContext(),"Добавлено в избранное",Toast.LENGTH_SHORT).show();
                    }else
                        Toast.makeText(getApplicationContext(),"Уже содержится в избранных",Toast.LENGTH_LONG).show();


            }
        });
    }
}
