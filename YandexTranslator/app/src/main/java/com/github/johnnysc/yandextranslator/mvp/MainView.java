package com.github.johnnysc.yandextranslator.mvp;

import android.support.annotation.StringRes;

import com.arellomobile.mvp.MvpView;

public interface MainView extends MvpView {

    void showMessage(@StringRes int messsageResId);

    void showTranslation(String text);

    void setButtonEnabled(boolean enabled);
}