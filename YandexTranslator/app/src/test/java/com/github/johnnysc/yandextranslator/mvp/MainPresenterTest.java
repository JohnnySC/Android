package com.github.johnnysc.yandextranslator.mvp;

import com.github.johnnysc.yandextranslator.R;
import com.github.johnnysc.yandextranslator.rule.RxImmediateSchedulerRule;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Maybe;

import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Asatryan on 23.06.18
 */
@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {

    @ClassRule
    public static final RxImmediateSchedulerRule SCHEDULER_RULE = new RxImmediateSchedulerRule();

    private static final String ENG_MOCK = "hi";
    private static final String RU_MOCK = "привет";

    @Mock
    MainView$$State mMainView;

    @Mock
    MainInteractor mMainInteractor;

    private MainPresenter mPresenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mPresenter = new MainPresenter(mMainInteractor);
        mPresenter.setViewState(mMainView);
    }

    public MainPresenterTest() {
    }

    @Test
    public void testTranslatePositive() {
        when(mMainInteractor.getTranslation(anyString(), anyString(), anyString(), anyString(), anyString())).thenReturn(getTranslationBean());
        mPresenter.translate(ENG_MOCK);
        verify(mMainView).setButtonEnabled(false);
        verify(mMainView).setButtonEnabled(true);
        verify(mMainView).showTranslation(RU_MOCK);
        verify(mMainView, never()).showMessage(anyInt());
    }

    @Test
    public void testTranslateError() {
        when(mMainInteractor.getTranslation(anyString(), anyString(), anyString(), anyString(), anyString())).thenReturn(getError());
        mPresenter.translate(ENG_MOCK);
        verify(mMainView).setButtonEnabled(false);
        verify(mMainView).setButtonEnabled(true);
        verify(mMainView).showMessage(R.string.error);
        verify(mMainView, never()).showTranslation(anyString());
    }

    @Test
    public void testTranslateVoidCase() {
        mPresenter.translate("");
        verify(mMainView, never()).setButtonEnabled(anyBoolean());
        verify(mMainView, never()).showMessage(R.string.error);
        verify(mMainView, never()).showTranslation(anyString());
    }

    private Maybe<String> getError() {
        return Maybe.error(new RuntimeException());
    }

    private Maybe<String> getTranslationBean() {
        return Maybe.just(RU_MOCK);
    }
}