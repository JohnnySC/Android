package com.github.johnnysc.yandextranslator.di;

import com.github.johnnysc.yandextranslator.BuildConfig;
import com.github.johnnysc.yandextranslator.mvp.DefaultMainInteractor;
import com.github.johnnysc.yandextranslator.mvp.DefaultMainRepository;
import com.github.johnnysc.yandextranslator.mvp.MainInteractor;
import com.github.johnnysc.yandextranslator.mvp.MainRepository;
import com.github.johnnysc.yandextranslator.mvp.MocksMainRepository;
import com.github.johnnysc.yandextranslator.net.TranslatorService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Asatryan on 23.06.18
 */
@Module
public class NetModule {

    private final String mBaseUrl;

    public NetModule(String baseUrl) {
        mBaseUrl = baseUrl;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    TranslatorService provideTranslatorService(Retrofit retrofit) {
        return retrofit.create(TranslatorService.class);
    }

    @Provides
    @Singleton
    MainInteractor provideMainInteractor(MainRepository repository) {
        return new DefaultMainInteractor(repository);
    }

    @Provides
    @Singleton
    MainRepository provideMainRepository(TranslatorService service) {
        return BuildConfig.FLAVOR.contains("mocks")
                ? new MocksMainRepository()
                : new DefaultMainRepository(service);
    }
}