package com.github.johnnysc.yandextranslator.mvp;

import io.reactivex.Maybe;

/**
 * @author Asatryan on 24.06.18
 */
public class DefaultMainInteractor implements MainInteractor {

    private final MainRepository mMainRepository;

    public DefaultMainInteractor(MainRepository mainRepository) {
        mMainRepository = mainRepository;
    }

    @Override
    public Maybe<String> getTranslation(String key, String text, String lang, String format, String options) {
        return mMainRepository.getTranslation(key, text, lang, format, options)
                .filter(translationBean -> !translationBean.getText().isEmpty() && !translationBean.getText().get(0).isEmpty())
                .map(translationBean -> translationBean.getText().get(0));
    }
}