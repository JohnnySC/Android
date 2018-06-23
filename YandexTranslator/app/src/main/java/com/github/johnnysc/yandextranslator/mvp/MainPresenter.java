package com.github.johnnysc.yandextranslator.mvp;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.github.johnnysc.yandextranslator.R;
import com.github.johnnysc.yandextranslator.net.RestManager;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Asatryan on 22.06.18
 */
@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    private static final String KEY = "trnsl.1.1.20170426T135336Z.48d648f8f882d563.d40bb959b17363f2bee9e4a816f36ca997ce55c2";
    private static final String LANG = "en-ru";
    private static final String FORMAT = "plain";
    private static final String OPTIONS = "1";

    private RestManager mRestManager;
    private Disposable mDisposable;

    MainPresenter() {
    }

    public void setRestManager(RestManager restManager) {
        mRestManager = restManager;
    }

    public void translate(String input) {
        if (input.isEmpty()) return;
        getViewState().setButtonEnabled(false);
        mDisposable = mRestManager.getService().getText(KEY, input, LANG, FORMAT, OPTIONS)
                .subscribeOn(Schedulers.io())
                .filter(translationBean -> !translationBean.getText().isEmpty() && !translationBean.getText().get(0).isEmpty())
                .map(translationBean -> translationBean.getText().get(0))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(translatedText -> {
                            getViewState().setButtonEnabled(true);
                            getViewState().showTranslation(translatedText);
                        },
                        throwable -> {
                            getViewState().setButtonEnabled(true);
                            getViewState().showMessage(R.string.error);
                        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mDisposable.dispose();
    }
}