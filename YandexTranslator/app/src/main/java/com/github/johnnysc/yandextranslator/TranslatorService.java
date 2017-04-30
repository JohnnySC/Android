package com.github.johnnysc.yandextranslator;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @author Asatryan on 26.04.17.
 */

public interface TranslatorService {
    @FormUrlEncoded
    @POST("api/v1.5/tr.json/translate")
    Call<TranslatedText> getText(@Field("key") String key, @Field("text") String text, @Field("lang") String lang,
                                 @Field("format") String format, @Field("options") String options);
}
