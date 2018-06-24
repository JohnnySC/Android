package com.github.johnnysc.yandextranslator.di;

import android.app.Application;

import timber.log.Timber;

/**
 * @author Asatryan on 23.06.18
 */
public class TranslatorApplication extends Application {

    private static final String BASE_URL = "https://translate.yandex.net/";
    private static NetComponent sNetComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
        sNetComponent = DaggerNetComponent.builder()
                .contextModule(new ContextModule(this))
                .netModule(new NetModule(BASE_URL))
                .build();
    }

    public static NetComponent getNetComponent() {
        return sNetComponent;
    }
}