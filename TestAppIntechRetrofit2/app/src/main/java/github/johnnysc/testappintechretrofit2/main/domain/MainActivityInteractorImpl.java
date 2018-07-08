package github.johnnysc.testappintechretrofit2.main.domain;

import android.text.Editable;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import github.johnnysc.testappintechretrofit2.main.data.bean.SongList;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

/**
 * @author Asatryan on 08.07.18
 */
public class MainActivityInteractorImpl implements MainActivityInteractor {

    private static final int DEBOUNCE_MS = 300;
    private static final int MIN_INPUT_LENGTH = 3;

    private final BehaviorSubject<String> mBehaviourSubject;
    private final MainActivityRepository mRepository;

    @Inject
    public MainActivityInteractorImpl(MainActivityRepository repository) {
        mRepository = repository;
        mBehaviourSubject = BehaviorSubject.create();
    }

    @Override
    public Observable<SongList> getSongList() {
        return mBehaviourSubject
                .filter(item -> item.length() >= MIN_INPUT_LENGTH)
                .map(String::toLowerCase)
                .debounce(DEBOUNCE_MS, TimeUnit.MILLISECONDS)
                .flatMap(mRepository::getSongsList);
    }

    @Override
    public void afterTextChanged(Editable input) {
        mBehaviourSubject.onNext(input.toString());
    }
}