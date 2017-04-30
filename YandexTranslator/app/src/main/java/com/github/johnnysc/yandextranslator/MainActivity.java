package com.github.johnnysc.yandextranslator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String KEY = "";//get your key and place it here
    private static final String LANG = "en-ru";
    private static final String FORMAT = "plain";
    private static final String OPTIONS = "1";

    private EditText mEditText;
    private TextView mTextView;
    private Button mButton;
    private RestManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = new RestManager();
        mEditText = (EditText) findViewById(R.id.input_edit_text);
        mTextView = (TextView) findViewById(R.id.result_text_view);
        mButton = (Button) findViewById(R.id.button);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEditText.getText().toString().length() > 0) {
                    getTranslatedText(mEditText.getText().toString());
                }
            }
        });

    }

    private void getTranslatedText(String input) {
        Call<TranslatedText> text = manager.getService().getText(KEY, input, LANG, FORMAT, OPTIONS);
        text.enqueue(new Callback<TranslatedText>() {
            @Override
            public void onResponse(Call<TranslatedText> call, Response<TranslatedText> response) {
                if (response.isSuccessful()) {
                    TranslatedText finalText = response.body();
                    mTextView.setText(finalText.getText().get(0));
                } else {
                    Toast.makeText(getApplicationContext(), "ошибка", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<TranslatedText> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "ошибка " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }
}
