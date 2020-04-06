package com.emma.star

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.uiautomator.UiDevice
import com.emma.star.assertions.RecyclerViewItemCountAssertion
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
        Thread.sleep(1000) // TODO: IdlingResource

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
        Thread.sleep(1000) // TODO: IdlingResource

        // Check that results are loaded
        onView(withId(R.id.repos_recycler_view)).check(RecyclerViewItemCountAssertion(30))
    }

    @Test
    fun navigateToChromeTab() {
        val mDevice: UiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

        // Type text and then press the search.
        onView(withId(R.id.search_text))
            .perform(typeText(organization), closeSoftKeyboard())
        onView(withId(R.id.search_button)).perform(click())
        Thread.sleep(1000)

        // Navigate to Chrome tab
        onView(withId(R.id.repos_recycler_view))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()));

        mDevice.pressBack();
        onView(withId(R.id.repos_recycler_view)).check(RecyclerViewItemCountAssertion(30))
    }
}