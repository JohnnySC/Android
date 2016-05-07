package github.johnnysc.mayakoffskij;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/*
 * Created by Hovhannes Asatryan (https://github.com/JohnnySC) on 05.05.16.
 */
public class FavoritePoems extends Activity {
    public static ArrayList<Integer> favIndexes;
    FavoritePoemsAdapter favPoemsAdapter = new FavoritePoemsAdapter();
    public static int favPosition;
    public static int favCode;
    Button clearFavorites;
    Button sureClearing;
    Button cancelClearing;
    public static Activity favoritePoemsActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorites);

        favoritePoemsActivity = this;
        ListView favoritesList = (ListView)findViewById(R.id.favoritesList);
        clearFavorites = (Button)findViewById(R.id.clearFavorites);
        sureClearing = (Button)findViewById(R.id.sureButton);
        cancelClearing = (Button)findViewById(R.id.cancelButton);
        favPosition = 0;
        favCode = 0;

        favoritesList.setAdapter(favPoemsAdapter);

        favoritesList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position,long id){
                favCode = 1;
                favPosition = favIndexes.get(position);
                Intent i = new Intent(FavoritePoems.this,PoemView.class);
                startActivityForResult(i,0);
            }
        });
    }

    public void clearFavorites(View view){
        sureClearing.setVisibility(View.VISIBLE);
        cancelClearing.setVisibility(View.VISIBLE);
        clearFavorites.setVisibility(View.GONE);
    }

    public void cancelClearing(View view){
        sureClearing.setVisibility(View.GONE);
        cancelClearing.setVisibility(View.GONE);
        clearFavorites.setVisibility(View.VISIBLE);
    }

    public void sureClearing(View view){
        FavoritePoemsAdapter.favoritePoems = new ArrayList<>();
        FavoritePoems.favIndexes = new ArrayList<>();
        Toast.makeText(getApplicationContext(),"Список избранных очищен",Toast.LENGTH_SHORT).show();
        try {
            FileIO.writeData(getApplicationContext(),openFileOutput(FileIO.file_name,MODE_PRIVATE));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finish();
    }

}