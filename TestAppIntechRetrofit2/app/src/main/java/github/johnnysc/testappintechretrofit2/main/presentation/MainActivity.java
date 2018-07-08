package github.johnnysc.testappintechretrofit2.main.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;

import com.arellomobile.mvp.MvpActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import java.util.List;

import javax.inject.Inject;

import github.johnnysc.testappintechretrofit2.Adapter.SongRecycleAdapter;
import github.johnnysc.testappintechretrofit2.R;
import github.johnnysc.testappintechretrofit2.UI.PlayerActivity;
import github.johnnysc.testappintechretrofit2.di.App;
import github.johnnysc.testappintechretrofit2.main.di.bean.Song;

/**
 * @author Hovhannes Asatryan on 08.07.2018
 */
public class MainActivity extends MvpActivity implements SongRecycleAdapter.SongClickListener, MainView {

    private RecyclerView mRecyclerView;
    private SongRecycleAdapter mSongRecycleAdapter;

    @Inject
    @InjectPresenter
    MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        App.getInstance().createMainActivityComponent();
        App.getInstance().getMainActivityComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUi();
    }

    private void initUi() {
        TextInputEditText editText = findViewById(R.id.text_input_edit_text);
        mRecyclerView = findViewById(R.id.recycler_view);

        editText.addTextChangedListener(new InputTextWatcher(input -> {
            mPresenter.afterTextChanged(input);
        }));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mSongRecycleAdapter = new SongRecycleAdapter(this);
        mRecyclerView.setAdapter(mSongRecycleAdapter);
    }

    @Override
    public void onClick(int position) {
        Song currentSong = mSongRecycleAdapter.getSelectedSong(position);
        Intent intent = new Intent(MainActivity.this, PlayerActivity.class);
        intent.putExtra("song", currentSong);
        startActivity(intent);
    }

    @Override
    public void showData(List<Song> songList) {
        mSongRecycleAdapter.setData(songList);
        mRecyclerView.setAdapter(mSongRecycleAdapter);
    }

    @ProvidePresenter
    MainPresenter getPresenter() {
        return mPresenter;
    }

    private class InputTextWatcher implements TextWatcher {

        private final AfterTextChangedListener mListener;

        InputTextWatcher(AfterTextChangedListener listener) {
            mListener = listener;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            //do nothing
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //do nothing
        }

        @Override
        public void afterTextChanged(Editable s) {
            mListener.afterTextChanged(s);
        }
    }

    private interface AfterTextChangedListener {
        void afterTextChanged(Editable input);
    }
}