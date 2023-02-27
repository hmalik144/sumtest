package com.example.sumtest.robots

import android.content.res.Resources
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*


open class BaseTestRobot {

    fun fillEditText(@IdRes resId: Int, text: String): ViewInteraction =
        onView(withId(resId)).perform(
            ViewActions.replaceText(text),
            ViewActions.closeSoftKeyboard()
        )

    fun clickButton(@IdRes resId: Int): ViewInteraction =
        onView((withId(resId))).perform(ViewActions.click())

    fun getViewInteraction(@IdRes resId: Int): ViewInteraction = onView(withId(resId))

    fun matchText(viewInteraction: ViewInteraction, text: String): ViewInteraction = viewInteraction
        .check(matches(withText(text)))

    fun matchText(@IdRes resId: Int, text: String): ViewInteraction =
        matchText(getViewInteraction(resId), text)

    fun getStringFromResource(@StringRes resId: Int): String =
        Resources.getSystem().getString(resId)

    fun checkVisibility(@IdRes resId: Int, visibility: Visibility) =
        getViewInteraction(resId).check(matches(withEffectiveVisibility(visibility)))

}