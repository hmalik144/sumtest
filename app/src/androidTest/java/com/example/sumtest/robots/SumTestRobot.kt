package com.example.sumtest.robots

import androidx.test.espresso.matcher.ViewMatchers.Visibility.*
import com.example.sumtest.R

fun sumTest(func: SumTestRobot.() -> Unit) = SumTestRobot().apply { func() }
class SumTestRobot: BaseTestRobot() {

    fun enterSumValues(firstNumber: String, secondNumber: String) {
        fillEditText(R.id.firstNumber, firstNumber)
        fillEditText(R.id.secondNumber, secondNumber)
    }

    fun submitEntries() {
        clickButton(R.id.cta)
    }

    fun submitValuesForSum(firstNumber: String, secondNumber: String){
        enterSumValues(firstNumber, secondNumber)
        submitEntries()
    }

    fun checkSumHasCalculated(result: String) {
        // Results is displayed and error is not
        checkVisibility(R.id.result, VISIBLE)
        checkVisibility(R.id.error, GONE)

        matchText(R.id.result, result)
        // Edit texts are empty
        matchText(R.id.firstNumber, "")
        matchText(R.id.secondNumber, "")
    }

    fun checkCalculationError(errorMessage: String) {
        checkVisibility(R.id.result, GONE)
        checkVisibility(R.id.error, VISIBLE)

        matchText(R.id.error, errorMessage)
    }
}