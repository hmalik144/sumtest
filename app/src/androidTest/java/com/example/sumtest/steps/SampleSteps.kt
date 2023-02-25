package com.example.sumtest.steps

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.platform.app.InstrumentationRegistry
import com.example.sumtest.SumActivity
import io.cucumber.java.Before
import io.cucumber.java.en.Given
import org.junit.Assert

class SampleSteps {

    private lateinit var mActivityScenarioRule: ActivityScenario<*>

    @Before
    fun setup() {
        mActivityScenarioRule = ActivityScenario.launch(SumActivity::class.java)
    }

    @Given("I start the application")
    fun I_start_the_application() {
        mActivityScenarioRule.moveToState(Lifecycle.State.STARTED)

        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        Assert.assertEquals("com.example.sumtest", appContext.packageName)
    }

}