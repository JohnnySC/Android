package com.github.johnnysc.yandextranslator.net;

import com.github.johnnysc.yandextranslator.bean.TranslationBean;

import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @author Asatryan on 22.06.18
 */
public interface TranslatorService {

    @FormUrlEncoded
    @POST("api/v1.5/tr.json/translate")
    Single<TranslationBean> getText(@Field("key") String key,
                                    @Field("text") String text,
                                    @Field("lang") String lang,
                                    @Field("format") String format,
                                    @Field("options") String options);
}