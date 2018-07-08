package github.johnnysc.testappintechretrofit2.main.presentation;

import android.text.Editable;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import github.johnnysc.testappintechretrofit2.REST.SongService;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;

/**
 * @author Asatryan on 08.07.18
 */
@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    private final List<Disposable> mDisposables;
    private final SongService mService;
    private final BehaviorSubject<String> mBehaviourSubject;

    @Inject
    MainPresenter(SongService service) {
        mService = service;
        mDisposables = new ArrayList<>();
        mBehaviourSubject = BehaviorSubject.create();
        mDisposables.add(mBehaviourSubject
                .filter(item -> item.length() > 2)
                .map(String::toLowerCase)
                .debounce(500, TimeUnit.MILLISECONDS)
                .flatMap(mService::getSongsList)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        response -> getViewState().showData(response.getSongsList()),
                        throwable -> getViewState().showData(Collections.emptyList())
                ));
    }

    public void afterTextChanged(Editable input) {
        mBehaviourSubject.onNext(input.toString());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mDisposables != null) {
            for (Disposable disposable : mDisposables) {
                if (!disposable.isDisposed()) {
                    disposable.dispose();
                }
            }
        }
    }
}
