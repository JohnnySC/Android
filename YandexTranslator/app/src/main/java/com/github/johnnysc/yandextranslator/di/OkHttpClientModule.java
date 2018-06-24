package com.github.johnnysc.yandextranslator.di;

import android.content.Context;

import java.io.File;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import timber.log.Timber;

/**
 * @author Asatryan on 24.06.18
 */
@Module(includes = ContextModule.class)
public class OkHttpClientModule {

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Cache cache, HttpLoggingInterceptor interceptor) {
        return new OkHttpClient()
                .newBuilder()
                .cache(cache)
                .addInterceptor(interceptor)
                .build();
    }

    @Provides
    @Singleton
    public HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(message -> Timber.d(message));
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return logging;
    }

    @Provides
    @Singleton
    public Cache provideCache(File cacheFile) {
        return new Cache(cacheFile, 10 * 1000 * 1000);
    }

    @Provides
    @Singleton
    public File provideFile(@ApplicationContext Context context) {
        File file = new File(context.getCacheDir(), "HttpCache");
        file.mkdirs();
        return file;
    }
}