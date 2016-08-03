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

public class MainActivity extends Activity implements View.OnClickListener{
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

        initUI();
        poemAdapter = new PoemAdapter();
        listView.setAdapter(poemAdapter);
        allPoems = new Poems();
        String text = "Стихи и поэмы: " + Poems.poems.size();
        allPoemsCount.setText(text);

        fontExample.setTextSize(fontSize);

        setButtonsListener();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent,View view,int position,long id){
                positionOfItem = position;
                FavoritePoems.favCode = 0;
                startNewActivity(PoemView.class);
            }
        }
        );

        try {
            FileIO.readData(getApplicationContext(), openFileInput(FileIO.file_name));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void initUI() {
        listView = (ListView)findViewById(R.id.poems_list);
        allPoemsCount = (TextView)findViewById(R.id.allPoemsCount);
        searchButton = (Button)findViewById(R.id.searchButton);
        searchText = (EditText)findViewById(R.id.searchText);
        goToFavorites = (Button)findViewById(R.id.gotoFavorites);
        fontSizeMinus = (Button)findViewById(R.id.fontsizeminus);
        fontSizePlus = (Button)findViewById(R.id.fontsizeplus);
        fontExample = (TextView)findViewById(R.id.fontexample);
    }

    private void setButtonsListener() {
        searchButton.setOnClickListener(this);
        goToFavorites.setOnClickListener(this);
        fontSizeMinus.setOnClickListener(this);
        fontSizePlus.setOnClickListener(this);
    }

    private void startNewActivity(Class another){
        Intent intent = new Intent(MainActivity.this, another);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.searchButton:
                if(searchText.getText().toString().length()>0){
                    startNewActivity(SearchResultsList.class);
                }
                break;

            case R.id.gotoFavorites:
                startNewActivity(FavoritePoems.class);
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
}
