package com.openclassrooms.tajmahal;

import static android.os.Trace.isEnabled;

import android.content.Context;

import androidx.test.espresso.action.ViewActions;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;


import com.openclassrooms.tajmahal.ui.MainActivity;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class FullyAutoTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.openclassrooms.tajmahal", appContext.getPackageName());
    }

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);
    private void waitFor(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Test
    public void testAddReview() {
        waitFor(2000);
        onView(withId(R.id.buttonLetRating)).perform(click()); //verif fragment review affiché
        onView(withId(R.id.submitButton)).check(matches(not(isEnabled())));//verif bouton pas activé
        waitFor(1000);
        onView(withId(R.id.editTextReview)).perform(ViewActions.typeText("Super restaurant le message test est OK"), ViewActions.closeSoftKeyboard()); //type comment auto
        waitFor(1000);
        onView(withId(R.id.submitButton)).check(matches(not(isEnabled())));//verif bouton pas activé
        onView(withId(R.id.ratingBar)).perform(click()); //selection note
        waitFor(1000);
        onView(withId(R.id.submitButton)).perform(click()); //submit review
        waitFor(1000);
        onView(withId(R.id.reviewsRecyclerView)) //fait defiler le recycle jusqu'au comment test
                .perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Super restaurant le message test est OK"))));
        waitFor(1000);
        onView(allOf(withId(R.id.commentTextView), withText("Super restaurant le message test est OK"))) //check que le text de test soit présent
                .check(matches(isDisplayed()));
        waitFor(1000);
        onView(isRoot()).perform(ViewActions.pressBack());
        waitFor(1000);//retour arriere detailFrag
        onView(withId(R.id.numberTotalRating)).check(matches(withText("(6)"))); //total com maintenant 6
        onView(withId(R.id.numberRating)).check(matches(not(withText("4.0")))); //verif note changée 4.0 to 3.8 en l'occurence not ( elle n'est pas de 4.0 )
        waitFor(4000);
        onView(withId(R.id.buttonLetRating)).perform(click());//verif bouton laisser un avis
        waitFor(1000);
        onView(withId(R.id.submitButton)).check(matches(not(isEnabled())));//verif bouton pas activé
        waitFor(1000);
        onView(isRoot()).perform(ViewActions.pressBack());

    }
}