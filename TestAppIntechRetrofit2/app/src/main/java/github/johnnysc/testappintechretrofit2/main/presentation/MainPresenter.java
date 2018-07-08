package github.johnnysc.testappintechretrofit2.main.presentation;

import android.text.Editable;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.Collections;

import javax.inject.Inject;

import github.johnnysc.testappintechretrofit2.main.domain.MainActivityInteractor;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Asatryan on 08.07.18
 */
@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    private final CompositeDisposable mDisposables;
    private final MainActivityInteractor mInteractor;

    @Inject
    MainPresenter(MainActivityInteractor interactor) {
        mInteractor = interactor;
        mDisposables = new CompositeDisposable();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        mDisposables.add(mInteractor.getSongList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        response -> getViewState().showData(response.getSongsList()),
                        throwable -> getViewState().showData(Collections.emptyList())
                ));
    }

    public void afterTextChanged(Editable input) {
        mInteractor.afterTextChanged(input);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mDisposables.clear();
    }
}