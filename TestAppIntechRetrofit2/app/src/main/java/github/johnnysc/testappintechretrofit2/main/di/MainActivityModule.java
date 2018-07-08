package github.johnnysc.testappintechretrofit2.main.di;

import dagger.Module;
import dagger.Provides;
import github.johnnysc.testappintechretrofit2.main.data.net.SongService;
import github.johnnysc.testappintechretrofit2.main.data.repository.MainActivityRepositoryImpl;
import github.johnnysc.testappintechretrofit2.main.domain.MainActivityInteractor;
import github.johnnysc.testappintechretrofit2.main.domain.MainActivityInteractorImpl;
import github.johnnysc.testappintechretrofit2.main.domain.MainActivityRepository;

/**
 * @author Asatryan on 08.07.18
 */
@Module
public class MainActivityModule {

    @ActivityScope
    @Provides
    MainActivityInteractor provideMainActivityInteractor(MainActivityRepository repository) {
        return new MainActivityInteractorImpl(repository);
    }

    @ActivityScope
    @Provides
    MainActivityRepository provideMainActivityRepository(SongService service) {
        return new MainActivityRepositoryImpl(service);
    }
}