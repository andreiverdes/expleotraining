package com.andreiverdes.training.expleo.stackoverflow;

import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.andreiverdes.training.expleo.arch.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mainActivityActivityScenarioRule
            = new ActivityScenarioRule<MainActivity>(MainActivity.class);

    @Test
    public void testEditText() {

        onView(withId(R.id.filter))
                .perform(typeText("Hello!"), ViewActions.closeSoftKeyboard());
    }

}