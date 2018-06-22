package com.github.johnnysc.yandextranslator.mvp;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.github.johnnysc.yandextranslator.R;
import com.github.johnnysc.yandextranslator.RestManager;
import com.github.johnnysc.yandextranslator.TranslatedText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    private static final String KEY = "trnsl.1.1.20170426T135336Z.48d648f8f882d563.d40bb959b17363f2bee9e4a816f36ca997ce55c2";//get your key and place it here
    private static final String LANG = "en-ru";
    private static final String FORMAT = "plain";
    private static final String OPTIONS = "1";
    private final RestManager mRestManager;

    MainPresenter() {
        mRestManager = new RestManager();
    }

    public void translate(String input) {
        if (input == null || input.isEmpty()) {
            return;
        }
        getViewState().setButtonEnabled(false);
        Call<TranslatedText> text = mRestManager.getService().getText(KEY, input, LANG, FORMAT, OPTIONS);
        text.enqueue(new Callback<TranslatedText>() {
            @Override
            public void onResponse(Call<TranslatedText> call, Response<TranslatedText> response) {
                getViewState().setButtonEnabled(true);
                if (response.isSuccessful()) {
                    TranslatedText finalText = response.body();
                    getViewState().showTranslation(finalText.getText().get(0));
                } else {
                    getViewState().showMessage(R.string.error);
                }
            }

            @Override
            public void onFailure(Call<TranslatedText> call, Throwable t) {
                getViewState().setButtonEnabled(true);
                getViewState().showMessage(R.string.error);
            }
        });
    }
}