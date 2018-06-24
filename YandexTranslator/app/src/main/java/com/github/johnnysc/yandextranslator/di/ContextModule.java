package com.github.johnnysc.yandextranslator.di;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * @author Asatryan on 24.06.18
 */
@Module
public class ContextModule {

    Context mContext;

    public ContextModule(Context context) {
        mContext = context;
    }

    @ApplicationContext
    @Provides
    public Context provideContext() {
        return mContext.getApplicationContext();
    }
}