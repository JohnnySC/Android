package github.johnnysc.testappintechretrofit2.player.presentation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.arellomobile.mvp.MvpActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import github.johnnysc.testappintechretrofit2.R;
import github.johnnysc.testappintechretrofit2.di.App;

/**
 * @author Hovhannes Asatryan on 08.07.18.
 */
public class PlayerActivity extends MvpActivity implements PlayerView {

    private ImageView mCoverImageView;
    private TextView mTrackTextView;
    private TextView mArtistTextView;
    private Button mPauseButton;
    private Button mPlayButton;
    private SeekBar mSeekBar;

    @Inject
    @InjectPresenter
    PlayerPresenter mPresenter;

    public static Intent newIntent(Context context) {
        return new Intent(context, PlayerActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        App.getInstance().getPlayerComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        initUI();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.getInstance().clearPlayerActivityComponent();
    }

    @ProvidePresenter
    PlayerPresenter getPresenter() {
        return mPresenter;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mPresenter.onBackPressed();
    }

    @Override
    public void setTrackText(String text) {
        mTrackTextView.setText(text);
    }

    @Override
    public void setArtistText(String text) {
        mArtistTextView.setText(text);
    }

    @Override
    public void setImageSource(String source) {
        Picasso.with(this)
                .load(source)
                .error(R.drawable.ic_error)
                .placeholder(R.drawable.progress_animation)
                .into(mCoverImageView);
    }

    @Override
    public void setPlayButtonEnabled(boolean enabled) {
        mPlayButton.setEnabled(enabled);
    }

    @Override
    public void setPauseButtonEnabled(boolean enabled) {
        mPauseButton.setEnabled(enabled);
    }

    @Override
    public void setSeekBarProgress(int time) {
        mSeekBar.setProgress(time);
    }

    @Override
    public void setSeekBarMax(int max) {
        mSeekBar.setMax(max);
    }

    private void initUI() {
        mCoverImageView = findViewById(R.id.cover_image_view);
        mTrackTextView = findViewById(R.id.track_text_view);
        mArtistTextView = findViewById(R.id.artist_name_text_view);
        mSeekBar = findViewById(R.id.seek_bar);
        mPauseButton = findViewById(R.id.pause_button);
        mPlayButton = findViewById(R.id.play_button);
        mPauseButton.setOnClickListener(v -> mPresenter.pause());
        mPlayButton.setOnClickListener(v -> mPresenter.play());
    }
}