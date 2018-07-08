package github.johnnysc.testappintechretrofit2.main.data.repository;

import javax.inject.Inject;

import github.johnnysc.testappintechretrofit2.main.data.bean.SongList;
import github.johnnysc.testappintechretrofit2.main.data.net.SongService;
import github.johnnysc.testappintechretrofit2.main.domain.MainActivityRepository;
import io.reactivex.Observable;

/**
 * @author Asatryan on 08.07.18
 */
public class MainActivityRepositoryImpl implements MainActivityRepository {

    private final SongService mService;

    @Inject
    public MainActivityRepositoryImpl(SongService service) {
        mService = service;
    }

    @Override
    public Observable<SongList> getSongsList(String term) {
        return mService.getSongsList(term);
    }
}