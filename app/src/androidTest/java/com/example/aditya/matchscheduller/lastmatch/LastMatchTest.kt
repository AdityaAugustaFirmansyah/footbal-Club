package com.example.aditya.matchscheduller.lastmatch

import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.example.aditya.matchscheduller.LastMatchFragment
import com.example.aditya.matchscheduller.MainActivity
import com.example.aditya.matchscheduller.R
import com.example.aditya.matchscheduller.data.LastMatch
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)

class MainActivityTest {
    @Rule
    @JvmField var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testRecyclerViewBehaviour() {
        Thread.sleep(4000)
        onView(ViewMatchers.withId(R.id.list_view))
            .check(matches(isDisplayed()))
        onView(withId(R.id.list_view)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(15))

        onView(withText("Chelsea"))
            .check(matches(isDisplayed()))
        onView(withText("Chelsea")).perform(click())

        Thread.sleep(4000)
        onView(withId(R.id.addFavourite))
            .check(matches(isDisplayed()))
        onView(withId(R.id.addFavourite)).perform(click())
        pressBack()
    }


}