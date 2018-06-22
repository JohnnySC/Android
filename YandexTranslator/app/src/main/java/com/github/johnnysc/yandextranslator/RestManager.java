package com.github.johnnysc.yandextranslator;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestManager {

    private static final String BASE_URL = "https://translate.yandex.net/";

    private TranslatorService mService;

    public TranslatorService getService() {
        if (mService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();

            mService = retrofit.create(TranslatorService.class);
        }
        return mService;
    }
}