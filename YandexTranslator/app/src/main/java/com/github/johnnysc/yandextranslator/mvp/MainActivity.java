package com.github.johnnysc.yandextranslator.mvp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.github.johnnysc.yandextranslator.R;
import com.github.johnnysc.yandextranslator.net.RestManager;

public class MainActivity extends MvpActivity implements MainView {

    @InjectPresenter
    MainPresenter mMainPresenter;

    private EditText mEditText;
    private TextView mTextView;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMainPresenter.setRestManager(new RestManager());
        mEditText = findViewById(R.id.input_edit_text);
        mTextView = findViewById(R.id.result_text_view);
        mButton = findViewById(R.id.translate_button);
        mButton.setOnClickListener(v -> mMainPresenter.translate(mEditText.getText().toString()));

    }

    @Override
    public void showMessage(int messsageResId) {
        Toast.makeText(this, messsageResId, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showTranslation(String text) {
        mTextView.setText(text);
    }

    @Override
    public void setButtonEnabled(boolean enabled) {
        mButton.setEnabled(enabled);
    }
}