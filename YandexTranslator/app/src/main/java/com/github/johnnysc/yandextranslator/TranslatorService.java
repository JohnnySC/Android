package com.github.johnnysc.yandextranslator;

import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface TranslatorService {

    @FormUrlEncoded
    @POST("api/v1.5/tr.json/translate")
    Single<TranslatedText> getText(@Field("key") String key,
                                   @Field("text") String text,
                                   @Field("lang") String lang,
                                   @Field("format") String format,
                                   @Field("options") String options);
}