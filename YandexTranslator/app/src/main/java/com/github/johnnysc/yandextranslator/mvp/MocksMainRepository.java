package com.github.johnnysc.yandextranslator.mvp;

import com.github.johnnysc.yandextranslator.bean.TranslationBean;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Single;

/**
 * @author Asatryan on 24.06.18
 */
public class MocksMainRepository implements MainRepository {

    public static final Map<String, String> MOCKS = new HashMap<>();

    static {
        MOCKS.put("hello", "привет");
        MOCKS.put("good", "хорошо");
        MOCKS.put("bad", "плохо");
        MOCKS.put("thing", "вещь");
        MOCKS.put("not found", "не найдено");
    }

    @Override
    public Single<TranslationBean> getTranslation(String key, String text, String lang, String format, String options) {
        String translation = MOCKS.containsKey(text) ? MOCKS.get(text) : MOCKS.get("not found");
        return Single.just(new TranslationBean(200, "en", Collections.singletonList(translation)));
    }
}