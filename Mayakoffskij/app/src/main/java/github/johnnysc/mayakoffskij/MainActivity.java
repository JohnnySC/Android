package github.johnnysc.mayakoffskij;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/*
 * Created by Hovhannes Asatryan (https://github.com/JohnnySC) on 05.05.16.
 */

public class MainActivity extends Activity {
    public static PoemAdapter poemAdapter;
    public static int positionOfItem;
    Button searchButton;
    public static EditText searchText;
    ListView listView;
    public static Poems allPoems;
    Button goToFavorites;
    TextView allPoemsCount;
    Button fontSizeMinus;
    Button fontSizePlus;
    public static int fontSize;
    TextView fontExample;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FavoritePoems.favIndexes = new ArrayList<>();
        positionOfItem = 0;
        fontSize = 20;

        listView = (ListView)findViewById(R.id.poems_list);
        poemAdapter = new PoemAdapter();
        listView.setAdapter(poemAdapter);
        allPoems = new Poems();
        allPoemsCount = (TextView)findViewById(R.id.allPoemsCount);
        String text = "Стихи и поэмы: " + Poems.poems.size();
        allPoemsCount.setText(text);

        searchButton = (Button)findViewById(R.id.searchButton);
        searchText = (EditText)findViewById(R.id.searchText);
        goToFavorites = (Button)findViewById(R.id.gotoFavorites);
        fontSizeMinus = (Button)findViewById(R.id.fontsizeminus);
        fontSizePlus = (Button)findViewById(R.id.fontsizeplus);
        fontExample = (TextView)findViewById(R.id.fontexample);
        fontExample.setTextSize(fontSize);

        try {
            FileIO.readData(getApplicationContext(), openFileInput(FileIO.file_name));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchText.getText().toString().length()>0){
                    Intent intent = new Intent(MainActivity.this, SearchResultsList.class);
                    startActivityForResult(intent,0);
                }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent,View view,int position,long id){
                positionOfItem = position;
                FavoritePoems.favCode = 0;
                Intent intent = new Intent(MainActivity.this, PoemView.class);
                startActivityForResult(intent,0);
            }
        }
        );

        goToFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, FavoritePoems.class);
                startActivityForResult(i,0);
            }
        });

        fontSizeMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fontSize>13) {
                    fontSize--;
                    fontExample.setTextSize(fontSize);
                }

            }
        });

        fontSizePlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fontSize<30) {
                    fontSize++;
                    fontExample.setTextSize(fontSize);
                }
            }
        });

    }

    public void clearEditText(View view){
        searchText.setText("");
    }

}
