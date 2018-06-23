package com.github.johnnysc.yandextranslator.di;

import android.app.Application;

/**
 * @author Asatryan on 23.06.18
 */
public class TranslatorApplication extends Application{

    private static final String BASE_URL = "https://translate.yandex.net/";
    private static NetComponent sNetComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        sNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule(BASE_URL))
                .build();
    }

    public static NetComponent getNetComponent() {
        return sNetComponent;
    }
}