package github.johnnysc.testappintech;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Display;
import android.view.View;

import android.view.WindowManager;
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

public class MainActivity extends Activity {
    EditText editText;
    Button button;
    String url = "https://itunes.apple.com/search?term=";
    public static String text;
    public static ArrayList<Song> songArrayList;
    ListView listView;
    SongAdapter songAdapter;
    public static int positionOfItem;
    Button buttonGrid;
    GridView gridView;
    int numOfColumns;
    int available;
    int currentOrientation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        editText = (EditText)findViewById(R.id.editText);
        button = (Button)findViewById(R.id.button);
        buttonGrid = (Button)findViewById(R.id.buttonGrid);
        listView = (ListView)findViewById(R.id.listView);
        gridView = (GridView)findViewById(R.id.gridView);

        positionOfItem = 0;
        currentOrientation = getScreenOrientation();
        numOfColumns =(currentOrientation==1)? 2:3;
        available = 0;

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText.getText().length()>4) {
                    Thread thread = new Thread(new HtmlRunnable());
                    runTheThread(thread);
                    songArrayList = new ArrayList<>();
                    Thread thread2 = new Thread(new SongRunnable());
                    runTheThread(thread2);
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

    class HtmlRunnable implements Runnable {
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

    class SongRunnable implements Runnable{
        public void run(){
            try{
                fillSongArrayList();
            }catch (Exception e){
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

    public static void runTheThread(Thread thread){
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    int getScreenOrientation()
    {
        Display getOrient = getWindowManager().getDefaultDisplay();
        int orientation;
            if(getOrient.getWidth() < getOrient.getHeight()){
                orientation = Configuration.ORIENTATION_PORTRAIT;
            }else {
                orientation = Configuration.ORIENTATION_LANDSCAPE;
            }
        return orientation;
    }

}
