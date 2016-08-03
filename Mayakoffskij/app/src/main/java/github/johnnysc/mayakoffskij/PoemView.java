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
public class PoemView extends Activity implements View.OnClickListener {
    TextView aPoemView;
    Button nextButton;
    Button previousButton;
    Button addToFavorites;
    Button removeFromFavorites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poem_view);

        initUI();
        if(FavoritePoems.favCode==1)
            aPoemView.setText(Poems.getPoem(FavoritePoems.favPosition));
        else
        aPoemView.setText(Poems.getPoem(MainActivity.positionOfItem));

        aPoemView.setTextSize(MainActivity.fontSize);

        if(FavoritePoems.favCode==0) {
            removeFromFavorites.setVisibility(View.GONE);
            if(MainActivity.positionOfItem < MainActivity.allPoems.getSize() - 1)
                nextButton.setOnClickListener(this);
            else
                nextButton.setVisibility(View.GONE);
        }else {
            removeFromFavorites.setVisibility(View.VISIBLE);
            removeFromFavorites.setOnClickListener(this);
            addToFavorites.setVisibility(View.GONE);
            nextButton.setVisibility(View.GONE);
            previousButton.setVisibility(View.GONE);
        }

        if(MainActivity.positionOfItem>0)
            previousButton.setOnClickListener(this);
        else
        previousButton.setVisibility(View.GONE);

        addToFavorites.setOnClickListener(this);
    }

    private void initUI() {
        aPoemView = (TextView)findViewById(R.id.aPoemTextView);
        nextButton = (Button)findViewById(R.id.nextButton);
        previousButton = (Button)findViewById(R.id.previousButton);
        addToFavorites = (Button)findViewById(R.id.add_to_favorites);
        removeFromFavorites = (Button)findViewById(R.id.remove_from_favorites);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.nextButton:
                MainActivity.positionOfItem++;
                startNewActivity(PoemView.class);
                break;

            case R.id.previousButton:
                MainActivity.positionOfItem--;
                startNewActivity(PoemView.class);
                break;

            case R.id.add_to_favorites:
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
                break;

            case R.id.remove_from_favorites:
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
                startNewActivity(FavoritePoems.class);
                break;
        }
    }

    private void startNewActivity(Class another){
        Intent intent = new Intent(PoemView.this, another);
        startActivity(intent);
        finish();
    }
}
