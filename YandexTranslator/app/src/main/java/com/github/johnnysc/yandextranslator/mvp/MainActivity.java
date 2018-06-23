package com.github.johnnysc.yandextranslator.mvp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.github.johnnysc.yandextranslator.R;
import com.github.johnnysc.yandextranslator.di.TranslatorApplication;
import com.github.johnnysc.yandextranslator.net.TranslatorService;

import javax.inject.Inject;

/**
 * @author Asatryan on 23.06.18
 */
public class MainActivity extends MvpActivity implements MainView {

    @InjectPresenter
    MainPresenter mMainPresenter;

    @Inject
    TranslatorService mTranslatorService;

    private EditText mEditText;
    private TextView mTextView;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TranslatorApplication.getNetComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditText = findViewById(R.id.input_edit_text);
        mTextView = findViewById(R.id.result_text_view);
        mButton = findViewById(R.id.translate_button);

        mMainPresenter.setTranslatorService(mTranslatorService);
        mButton.setOnClickListener(v -> mMainPresenter.translate(mEditText.getText().toString()));
    }

    @Override
    public void showMessage(int messageResId) {
        Toast.makeText(this, messageResId, Toast.LENGTH_LONG).show();
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