package com.example.gameofthroneshouses.ui


import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.gameofthroneshouses.R
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4ClassRunner::class)
class HousesListActivityTest {

    @get:Rule var activityTestRule = ActivityScenarioRule(HousesListActivity::class.java)


    @Test
    fun checkActivityVisibility(){
        onView(withId(R.id.activityHousesList))
            .check(matches(isDisplayed()))
    }

    @Test
    fun checkRecyclerViewVisibility() {
        onView(withId(R.id.housesRecyclerView))
            .check(matches(isDisplayed()))
    }

    @Test
    fun checkHouseTextOnFirstCard(){
        onView(
            allOf(
                withId(R.id.house_name), withText("House Algood"),
                withParent(withParent(withId(R.id.item_card))),
                isDisplayed()
            )
        ).check(matches(isDisplayed()))
    }

    @Test
    fun checkRegionTextOnFirstCard(){
        onView(
            allOf(
                withId(R.id.house_region), withText("The Westerlands"),
                withParent(withParent(withId(R.id.item_card))),
                isDisplayed()
            )
        ).check(matches(isDisplayed()))
    }

    @Test
    fun onClickFirstCard(){
        onView(
            allOf(
                withId(R.id.house_name), withText("House Algood"),
                withParent(withParent(withId(R.id.item_card))),
                isDisplayed()
            )
        ).perform(click())

        onView(withId(R.id.houseDetailsActivity))
            .check(matches(isDisplayed()))

    }

    @Test
    fun backPressToHousesListActivity() {
        onView(
            allOf(
                withId(R.id.house_name), withText("House Algood"),
                withParent(withParent(withId(R.id.item_card))),
                isDisplayed()
            )
        ).perform(click())

        onView(withId(R.id.houseDetailsActivity))
            .check(matches(isDisplayed()))

        Espresso.pressBack()

        onView(withId(R.id.activityHousesList))
            .check(matches(isDisplayed()))
    }

}
