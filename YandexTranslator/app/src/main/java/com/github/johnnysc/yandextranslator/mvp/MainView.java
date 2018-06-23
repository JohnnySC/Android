package com.github.johnnysc.yandextranslator.mvp;


import android.support.annotation.StringRes;

import com.arellomobile.mvp.MvpView;

/**
 * @author Asatryan on 22.06.18
 */
public interface MainView extends MvpView {

    void showMessage(@StringRes int messageResId);

    void showTranslation(String text);

    void setButtonEnabled(boolean enabled);
}