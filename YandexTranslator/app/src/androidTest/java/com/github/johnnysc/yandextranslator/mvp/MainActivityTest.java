package com.github.johnnysc.yandextranslator.mvp;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.github.johnnysc.yandextranslator.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Map;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Change product flavor to mocksDebug to run this test
 *
 * @author Asatryan on 23.06.18
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testPositiveScenario() {
        if (DefaultMainRepository.MOCKS.isEmpty()) {
            throw new IllegalStateException("Choose mocks flavor to run the test");
        }
        for (Map.Entry<String, String> entry : DefaultMainRepository.MOCKS.entrySet()) {
            onView(withId(R.id.input_edit_text)).perform(clearText(), typeText(entry.getKey()));
            onView(withId(R.id.translate_button)).perform(click());
            onView(withId(R.id.result_text_view)).check(matches(withText(entry.getValue())));
        }
    }
}