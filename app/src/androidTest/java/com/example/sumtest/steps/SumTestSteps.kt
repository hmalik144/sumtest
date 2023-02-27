package com.example.sumtest.steps

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Lifecycle.State.RESUMED
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import com.example.sumtest.SumActivity
import com.example.sumtest.constants.ErrorTypes
import com.example.sumtest.robots.sumTest
import io.cucumber.java.After
import io.cucumber.java.Before
import io.cucumber.java.en.And
import io.cucumber.java.en.Given
import io.cucumber.java.en.When

class SumTestSteps {
    private lateinit var mActivityScenarioRule: ActivityScenario<SumActivity>
    private lateinit var mIdlingResource: IdlingResource

    @Before
    fun setup() {
        mActivityScenarioRule = ActivityScenario.launch(SumActivity::class.java)
        mActivityScenarioRule.onActivity {
            mIdlingResource = it.getIdlingResource()!!
            IdlingRegistry.getInstance().register(mIdlingResource)
        }
    }

    @Given("I start the application")
    fun i_start_the_application() {
        mActivityScenarioRule.moveToState(RESUMED)
    }

    @When("^I run calculator sum for values \"([^\"]*)\" and \"([^\"]*)\"$")
    fun i_run_calculator_sum_for_values(firstValue: String, secondValue: String) {
        sumTest {
            submitValuesForSum(firstValue, secondValue)
        }
    }

    @And("^I assert the operation has run successfully with result \"([^\"]*)\"\$")
    fun i_assert_the_operation_has_run_successfully(result: String) {
        sumTest {
            checkSumHasCalculated(result)
        }
    }

    @And("^I assert the operation has failed with error message (Overflow|InvalidInput|EmptyInput)$")
    fun i_assert_the_operation_has_failed_with_error_message(result: ErrorTypes) {
        sumTest {
            checkCalculationError(result.message)
        }
    }

    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(mIdlingResource)
    }
}