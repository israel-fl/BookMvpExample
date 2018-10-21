package flores.israel.androidcodingchallenge;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import flores.israel.androidcodingchallenge.ui.books.BooksActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Screen Tests for the {@link BooksActivity}
 */
@RunWith(AndroidJUnit4.class)
public class BooksActivityScreenTest {

    @Rule
    public ActivityTestRule<BooksActivity> mActivityTestRule =
            new ActivityTestRule<>(BooksActivity.class);

    // This test doesn't do anything! Just for demonstration
    @Test
    public void test_swipeRefresh_success() {
        // Action
        onView(withId(R.id.lt_swipe_refresh)).perform(swipeDown());

        // Assert - note: it would probably be better to create a custom class that checks if the
        // RecyclerView is non-empty or similar
        onView(withId(R.id.rv_books)).check(matches(isDisplayed()));
    }

}