package github.johnnysc.testappintech;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Created by Hovhannes Asatryan on 22.06.16.
 */

public class MainActivity extends AppCompatActivity {
    public static EditText editText;
    public static Button button;
    public static String url = "https://itunes.apple.com/search?term=";
    public static String text;
    public static ArrayList<Song> songArrayList;
    ListView listView;
    public static SongAdapter songAdapter;
    public static int positionOfItem;
    public static View myView;
    public static Button buttonGrid;
    GridView gridView;
    int numOfColumns;
    int available;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText)findViewById(R.id.editText);
        button = (Button)findViewById(R.id.button);
        buttonGrid = (Button)findViewById(R.id.buttonGrid);

        positionOfItem = 0;

        listView = (ListView)findViewById(R.id.listView);
        gridView = (GridView)findViewById(R.id.gridView);
        numOfColumns = 2;
        available = 0;


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText.getText().toString().length()>4) {
                    Thread thread = new Thread(new HtmlRunnable());
                    thread.start();
                    try {
                        thread.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    songArrayList = new ArrayList<>();
                    Thread thread2 = new Thread(new SongRunnable());
                    thread2.start();
                    try {
                        thread2.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    songAdapter = new SongAdapter();
                    listView.setAdapter(songAdapter);
                    listView.setVisibility(View.VISIBLE);
                    gridView.setVisibility(View.GONE);
                    available = 1;
                }
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                positionOfItem = position;
                myView = view;
                Intent intent = new Intent(MainActivity.this, PlayerActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        buttonGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if(available>0) {
                        gridView.setAdapter(songAdapter);
                        gridView.setVisibility(View.VISIBLE);
                        listView.setVisibility(View.GONE);
                        adjustGridView();
                    }
                }

        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                positionOfItem = position;
                myView = view;
                Intent intent = new Intent(MainActivity.this, PlayerActivity.class);
                startActivityForResult(intent, 0);
            }
        });

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                numOfColumns = 3;
           adjustGridView();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
                numOfColumns = 2;
             adjustGridView();
        }
    }

    private void adjustGridView() {
        gridView.setNumColumns(numOfColumns);
    }
    public class SongRunnable implements Runnable{
        public void run(){
            try{
                fillSongArrayList();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public class HtmlRunnable implements Runnable {
        public void run(){
            try {
                StringBuilder sb = new StringBuilder();
                URL pageURL = new URL(url+editText.getText().toString());
                URLConnection uc = pageURL.openConnection();
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(
                                uc.getInputStream(), "UTF-8"));
                String inputLine;
                while ((inputLine = br.readLine()) != null) {
                    sb.append(inputLine);
                }
                text = sb.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void fillSongArrayList() {
        ArrayList<String> artistName = GetContent.getArrayOfString("artistName");
        ArrayList<String> trackName = GetContent.getArrayOfString("trackName");
        ArrayList<String> coverURL = GetContent.getArrayOfString("coverURL");
        ArrayList<String> songURL = GetContent.getArrayOfString("songURL");

        for(int i=0;i<artistName.size();i++) {
            songArrayList.add(new Song(artistName.get(i), trackName.get(i), coverURL.get(i), songURL.get(i)));
        }
    }


}
