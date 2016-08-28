package github.johnnysc.mayakoffskij;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.keyboardsurfer.android.widget.crouton.Crouton;

/*
 * Created by Hovhannes Asatryan (https://github.com/JohnnySC) on 05.05.16.
 */

public class MainActivity extends Activity{
    PoemAdapter poemAdapter;
    public static int positionOfItem;
    public static Poems allPoems;
    public static int fontSize;
    public static Activity activity;
    @BindView(R.id.searchText) EditText searchText;
    @BindView(R.id.poems_list) ListView listView;
    @BindView(R.id.allPoemsCount) TextView allPoemsCount;
    @BindView(R.id.fontexample) TextView fontExample;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activity = this;
        ButterKnife.bind(this);
        FavoritePoems.favIndexes = new ArrayList<>();
        positionOfItem = 0;
        fontSize = 20;

        poemAdapter = new PoemAdapter();
        listView.setAdapter(poemAdapter);
        allPoems = new Poems();
        String text = "Стихи и поэмы: " + allPoems.getSize();
        allPoemsCount.setText(text);

        fontExample.setTextSize(fontSize);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent,View view,int position,long id){
                positionOfItem = position;
                FavoritePoems.favCode = 0;
                startNewActivity(PoemView.class,false);
            }
        }
        );

        try {
            FileIO.readData(this,getApplicationContext(), openFileInput(FileIO.file_name));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    @OnClick({
            R.id.searchButton,
            R.id.gotoFavorites,
            R.id.fontsizeminus,
            R.id.fontsizeplus
    })
    void onButtonClick(Button button){
        switch (button.getId()){
            case R.id.searchButton:
                if(searchText.getText().toString().length()>0)
                    startNewActivity(SearchResultsList.class, true);
                break;

            case R.id.gotoFavorites:
                startNewActivity(FavoritePoems.class, false);
                break;

            case R.id.fontsizeminus:
                if(fontSize>13) {
                    fontSize--;
                    fontExample.setTextSize(fontSize);
                }
                break;

            case R.id.fontsizeplus:
                if(fontSize<30) {
                    fontSize++;
                    fontExample.setTextSize(fontSize);
                }
                break;
        }
    }

    private void startNewActivity(Class another, boolean hasExtra){
        Intent intent = new Intent(MainActivity.this, another);
        if(hasExtra) intent.putExtra("searchText",searchText.getText().toString());
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Выход из приложения")
                .setMessage("Уверены, что хотите выйти из приложения?")
                .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                    @Override public void onClick(DialogInterface dialogInterface, int i) {
                    }
                })
                .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override public void onClick(DialogInterface dialogInterface, int i) {
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
