package com.github.johnnysc.yandextranslator.di;

import com.github.johnnysc.yandextranslator.mvp.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Asatryan on 23.06.18
 */
@Singleton
@Component(modules = {NetModule.class, OkHttpClientModule.class})
public interface NetComponent {

    void inject(MainActivity activity);
}