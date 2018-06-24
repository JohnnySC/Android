package com.github.johnnysc.yandextranslator.mvp;

import com.github.johnnysc.yandextranslator.bean.TranslationBean;
import com.github.johnnysc.yandextranslator.net.TranslatorService;

import java.util.Collections;
import java.util.Map;

import io.reactivex.Single;

/**
 * @author Asatryan on 24.06.18
 */
public class DefaultMainRepository implements MainRepository {

    public static final Map<String, String> MOCKS = Collections.emptyMap();
    private final TranslatorService mTranslatorService;

    public DefaultMainRepository(TranslatorService translatorService) {
        mTranslatorService = translatorService;
    }

    @Override
    public Single<TranslationBean> getTranslation(String key, String text, String lang, String format, String options) {
        return mTranslatorService.getText(key, text, lang, format, options);
    }
}