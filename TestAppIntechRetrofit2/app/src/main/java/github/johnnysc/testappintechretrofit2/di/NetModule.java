package github.johnnysc.testappintechretrofit2.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import github.johnnysc.testappintechretrofit2.main.data.net.SongService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Asatryan on 08.07.18
 */
@Module
public class NetModule {

    private final String BASE_URL;

    public NetModule(String baseUrl) {
        BASE_URL = baseUrl;
    }

    @Provides
    @Singleton
    SongService provideSongService(Retrofit retrofit) {
        return retrofit.create(SongService.class);
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Converter.Factory converterFactory,
                             CallAdapter.Factory callAdapterFactory,
                             OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(callAdapterFactory)
                .client(client)
                .build();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(HttpLoggingInterceptor interceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
    }

    @Provides
    @Singleton
    HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    @Provides
    @Singleton
    Converter.Factory provideConverterFactory() {
        return GsonConverterFactory.create();
    }

    @Provides
    @Singleton
    CallAdapter.Factory provideCallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }
}