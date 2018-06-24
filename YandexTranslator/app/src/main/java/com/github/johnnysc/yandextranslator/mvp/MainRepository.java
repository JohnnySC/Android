package com.github.johnnysc.yandextranslator.mvp;

import com.github.johnnysc.yandextranslator.bean.TranslationBean;

import io.reactivex.Single;

/**
 * @author Asatryan on 24.06.18
 */
public interface MainRepository {


    Single<TranslationBean> getTranslation(String key,
                                           String text,
                                           String lang,
                                           String format,
                                           String options);
}