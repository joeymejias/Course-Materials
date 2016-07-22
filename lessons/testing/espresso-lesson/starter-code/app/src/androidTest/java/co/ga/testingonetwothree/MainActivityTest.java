package co.ga.testingonetwothree;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static android.support.test.espresso.Espresso.*;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.action.ViewActions.typeTextIntoFocusedView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by joey on 7/21/16.
 */

@RunWith(AndroidJUnit4.class)

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mMainActivityActivityTestRule =
            new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void testAddButton() throws
            Exception{
        String value1 = "1.23";
        String value2 = "4.56";
        String expectedAnswer = "$5.79";

        onView(withId(R.id.editText1))
                .perform((typeText(value1)), closeSoftKeyboard());
        onView(withId(R.id.editText2))
                .perform(typeText(value2), closeSoftKeyboard());

        onView(withId(R.id.addButton))
                .perform(click());

        onView(withId(R.id.answerTextView))
                .check(matches(withText(expectedAnswer)));
    }
}
