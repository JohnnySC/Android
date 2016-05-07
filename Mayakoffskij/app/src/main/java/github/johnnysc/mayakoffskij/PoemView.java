package github.johnnysc.mayakoffskij;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;

/*
 * Created by Hovhannes Asatryan (https://github.com/JohnnySC) on 05.05.16.
 */
public class PoemView extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poem_view);

        TextView aPoemView = (TextView)findViewById(R.id.aPoemTextView);
        if(FavoritePoems.favCode==1)
            aPoemView.setText(Poems.getPoem(FavoritePoems.favPosition));
        else
        aPoemView.setText(Poems.getPoem(MainActivity.positionOfItem));

        aPoemView.setTextSize(MainActivity.fontSize);

        final Button nextButton = (Button)findViewById(R.id.nextButton);
        final Button previousButton = (Button)findViewById(R.id.previousButton);

        final Button addToFavorites = (Button)findViewById(R.id.add_to_favorites);
        final Button removeFromFavorites = (Button)findViewById(R.id.remove_from_favorites);

        if(FavoritePoems.favCode==0) {
            removeFromFavorites.setVisibility(View.GONE);
            if(MainActivity.positionOfItem < MainActivity.allPoems.getSize() - 1){
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.positionOfItem++;
                Intent intent = new Intent(PoemView.this, PoemView.class);
                startActivityForResult(intent, 0);
                finish();
            }
        });
            }else
                nextButton.setVisibility(View.GONE);
        }else {
            removeFromFavorites.setVisibility(View.VISIBLE);
            removeFromFavorites.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FavoritePoemsAdapter.favoritePoems.remove(PoemAdapter.poems.get(FavoritePoems.favPosition));
                    for(int i=0;i<FavoritePoems.favIndexes.size();i++) {
                        if (FavoritePoems.favPosition == FavoritePoems.favIndexes.get(i)){
                            FavoritePoems.favIndexes.remove(i);
                        Toast.makeText(getApplicationContext(), "Удалено из избранных", Toast.LENGTH_SHORT).show();
                            try {
                                FileIO.writeData(getApplicationContext(),openFileOutput(FileIO.file_name,MODE_PRIVATE));
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    FavoritePoems.favoritePoemsActivity.finish();
                    Intent intent = new Intent(PoemView.this,FavoritePoems.class);
                    startActivityForResult(intent,0);
                    finish();
                }
            });
            addToFavorites.setVisibility(View.GONE);
            nextButton.setVisibility(View.GONE);
            previousButton.setVisibility(View.GONE);
        }

        if(MainActivity.positionOfItem>0) {
            previousButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity.positionOfItem--;
                    Intent intent = new Intent(PoemView.this, PoemView.class);
                    startActivityForResult(intent, 0);
                    finish();
                }
            });
        }else
        previousButton.setVisibility(View.GONE);

        addToFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!FavoritePoems.favIndexes.contains(MainActivity.positionOfItem)) {
                    FavoritePoems.favIndexes.add(MainActivity.positionOfItem);
                    FavoritePoemsAdapter.favoritePoems.add(PoemAdapter.poems.get(MainActivity.positionOfItem));
                    Toast.makeText(getApplicationContext(), "Добавлено в избранное", Toast.LENGTH_SHORT).show();
                    try {
                        FileIO.writeData(getApplicationContext(),openFileOutput(FileIO.file_name,MODE_PRIVATE));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }else
                    Toast.makeText(getApplicationContext(),"Уже содержится в избранных",Toast.LENGTH_LONG).show();
            }
        });
    }
}
