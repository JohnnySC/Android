package com.github.johnnysc.yandextranslator;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Asatryan on 26.04.17.
 */

public class RestManager {

    private static final String BASE_URL = "https://translate.yandex.net/";

    private TranslatorService mService;

    public TranslatorService getService() {
        if (mService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            mService = retrofit.create(TranslatorService.class);
        }
        return mService;
    }
}