package com.github.johnnysc.yandextranslator.mvp;

import io.reactivex.Maybe;

/**
 * @author Asatryan on 24.06.18
 */
public interface MainInteractor {

    Maybe<String> getTranslation(String key,
                                 String text,
                                 String lang,
                                 String format,
                                 String options);
}