package github.johnnysc.mayakoffskij;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

/*
 * Created by Hovhannes Asatryan (https://github.com/JohnnySC) on 05.05.16.
 */
public class FavoritePoems extends Activity {
    public static ArrayList<Integer> favIndexes;
    public static Activity favoritePoemsActivity;
    FavoritePoemsAdapter favPoemsAdapter = new FavoritePoemsAdapter();
    public static int favPosition;
    public static int favCode;
    @BindView(R.id.favoritesList) ListView favoritesList;
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorites);

        activity = this;
        ButterKnife.bind(this);
        favoritePoemsActivity = this;
        favPosition = 0;
        favCode = 0;

        favoritesList.setAdapter(favPoemsAdapter);

        favoritesList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position,long id){
                favCode = 1;
                favPosition = favIndexes.get(position);
                Intent intent = new Intent(FavoritePoems.this,PoemView.class);
                startActivity(intent);
            }
        });
    }

    @OnClick(R.id.clearFavorites)
    void clearFavorites(){
        new AlertDialog.Builder(this)
                .setTitle("Oчистить список избранных")
                .setMessage("Уверены, что хотите очистить список?")
                .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                    @Override public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override public void onClick(DialogInterface dialogInterface, int i) {
                        FavoritePoemsAdapter.favoritePoems = new ArrayList<>();
                        FavoritePoems.favIndexes = new ArrayList<>();
                        Crouton.makeText(activity,"Список избранных очищен", Style.INFO).show();
                        try {
                            FileIO.writeData(activity,getApplicationContext(),openFileOutput(FileIO.file_name,MODE_PRIVATE));
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        finish();
                    }
                }).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Crouton.cancelAllCroutons();
    }
}