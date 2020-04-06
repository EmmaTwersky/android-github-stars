package com.emma.star

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.emma.star.assertions.RecyclerViewItemCountAssertion
import org.hamcrest.CoreMatchers.anything
import org.hamcrest.CoreMatchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class OrganizationReposTest {

    private lateinit var organization: String

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity>
            = ActivityTestRule(MainActivity::class.java)

    @Before
    fun initOrganizationString() {
        organization = "artsy"
    }

    @Test
    fun searchOrganizationName() {
        // Type text and then press the search.
        onView(withId(R.id.search_text))
            .perform(typeText(organization), closeSoftKeyboard())
        onView(withId(R.id.search_button)).perform(click())

        // Check that the text persists searching
        onView(withId(R.id.search_text))
            .check(matches(withText(organization)))
    }

    @Test
    fun organizationResults() {
        // Type text and then press the search.
        onView(withId(R.id.search_text))
            .perform(typeText(organization), closeSoftKeyboard())
        onView(withId(R.id.search_button)).perform(click())

        // Check that results are loaded
        onView(withId(R.id.repos_recycler_view)).check(RecyclerViewItemCountAssertion(30))
    }

    @Test
    fun navigateToChromeTab() {
        // Type text and then press the search.
        onView(withId(R.id.search_text))
            .perform(typeText(organization), closeSoftKeyboard())
        onView(withId(R.id.search_button)).perform(click())

        // Navigate to Chrome tab
        onView(withId(R.id.repos_recycler_view)).check(RecyclerViewItemCountAssertion(30))
        onView(withId(R.id.repos_recycler_view))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()));
    }

    @Test
    fun loadingSpinner() {
        // Type text and then press the search.
        onView(withId(R.id.search_text))
            .perform(typeText(organization), closeSoftKeyboard())
        onView(withId(R.id.search_button)).perform(click())

        // Check that spinner is shown
        onView(withId(R.id.progress_bar)).check(matches(isDisplayed()))

        // Check that results are loaded and spinner is hidden
        onView(withId(R.id.repos_recycler_view)).check(RecyclerViewItemCountAssertion(30))
        onView(withId(R.id.progress_bar)).check(matches(not(isDisplayed())))
    }
}