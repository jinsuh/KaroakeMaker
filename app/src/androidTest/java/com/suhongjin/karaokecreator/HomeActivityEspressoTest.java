package com.suhongjin.karaokecreator;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.google.common.truth.Truth.assertThat;

import android.content.Context;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

/**
 * Instrumented test, which will execute on an Android device.
 */
@RunWith(AndroidJUnit4.class)
public class HomeActivityEspressoTest {

  private final Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();

  @Rule
  public ActivityTestRule<HomeActivity> activityTestRule =
      new ActivityTestRule<>(HomeActivity.class);

  @Test
  public void useAppContext() throws Exception {
    assertThat(context.getPackageName()).matches("com.suhongjin.karaokecreator");
  }

  @Test
  public void recordButton() {
    onView(withId(R.id.record_button)).check(matches(isDisplayed()));
    onView(withId(R.id.record_button)).check(matches(withText(R.string.record_button)));

    onView(withId(R.id.record_button)).perform(click());
    onView(withId(R.id.record_button)).check(matches(withText(R.string.stop_recording_button)));

    onView(withId(R.id.record_button)).perform(click());
    onView(withId(R.id.record_button)).check(matches(withText(R.string.record_button)));
  }
}
