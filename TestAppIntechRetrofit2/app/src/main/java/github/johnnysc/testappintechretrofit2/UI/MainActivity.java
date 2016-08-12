package github.johnnysc.testappintechretrofit2.UI;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.widget.Button;
import android.widget.EditText;

import com.jakewharton.rxbinding.widget.RxTextView;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import github.johnnysc.testappintechretrofit2.Adapter.SongRecycleAdapter;
import github.johnnysc.testappintechretrofit2.R;
import github.johnnysc.testappintechretrofit2.REST.*;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by Hovhannes Asatryan on 12.08.16.
 */
public class MainActivity extends Activity implements SongRecycleAdapter.SongClickListener{
    @BindView(R.id.goButton) Button goButton;
    @BindView(R.id.gridButton) Button gridButton;
    @BindView(R.id.editText) EditText editText;
    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    GridLayoutManager layoutManager;
    SongRecycleAdapter songAdapter;
    RestManager restManager;
    List<Song> songList;
    int columnCount;
    boolean gridActivated = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        makeObservable(editText,5);
    }

    @OnClick({
            R.id.goButton,
            R.id.gridButton
            })
    public void onClick(Button button) {
        switch (button.getId()) {
            case R.id.goButton:
                getSongData();
                break;
            case R.id.gridButton:
                gridActivated = true;
                makeGrid();
                break;
        }
    }

    void getSongData(){
        restManager = new RestManager();
        songList = new ArrayList<>();
        Call<SongList> songListCall = restManager.getSongService().getSongsList(editText.getText().toString());
        songListCall.enqueue(new Callback<SongList>() {
            @Override
            public void onResponse(Call<SongList> call, Response<SongList> response) {
                if(response.isSuccessful()){
                    SongList songsList = response.body();
                    songList = songsList.getSongsList();
                    for(Song song : songList)
                        Log.d("myLog","Seems to be successful, here is the list"+song.getTrackName());
                    initSongsView();
                }
            }

            @Override
            public void onFailure(Call<SongList> call, Throwable t) {
                Log.d("myLog","This is a failure! "+ t.getMessage());
            }
        });
    }

    private void initSongsView() {
        layoutManager = new GridLayoutManager(MainActivity.this,1);
        recyclerView.setLayoutManager(layoutManager);
        songAdapter = new SongRecycleAdapter(songList,this);
        recyclerView.setAdapter(songAdapter);
    }

    private void makeGrid(){
        if(gridActivated) {
            columnCount = (getScreenOrientation() == 1) ? 2 : 3;
            layoutManager = new GridLayoutManager(MainActivity.this, columnCount);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(songAdapter);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        makeGrid();
    }

    int getScreenOrientation(){
        Display getOrient = getWindowManager().getDefaultDisplay();
        int orientation;
        if(getOrient.getWidth() < getOrient.getHeight()){
            orientation = Configuration.ORIENTATION_PORTRAIT;
        }else {
            orientation = Configuration.ORIENTATION_LANDSCAPE;
        }
        return orientation;
    }

    private void makeObservable(EditText editText, final int charLength){
        Observable<CharSequence> observable = RxTextView.textChanges(editText);
        observable.map(new Func1<CharSequence, Boolean>() {
            @Override
            public Boolean call(CharSequence charSequence) {
                return charSequence.length() >= charLength;
            }
        }).subscribe(new Subscriber<Boolean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.d("myLog","Here is an error ! "+e.getMessage());
            }

            @Override
            public void onNext(Boolean aBoolean) {
                goButton.setEnabled(aBoolean);
                gridButton.setEnabled(aBoolean);
            }
        });
    }

    @Override
    public void onClick(int position) {
        Song currentSong = songAdapter.getSelectedSong(position);
        Intent intent = new Intent(MainActivity.this, PlayerActivity.class);
        intent.putExtra("song", Parcels.wrap(currentSong));
        startActivity(intent);
    }
}