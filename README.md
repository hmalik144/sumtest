# sumtest

This project is running Android instrumtation tests in a BDD format with the help of android espresso and Cucumber BDD.
Cucumber is BDD framework which works for BDD.
For Android, we are going to use cucumber-jvm, java port of cucumber.

BDD's behavior text is written in a business-readable domain-specific language.
It aims to communicate better between non-tech to tech over Software trueness and quality.  
The readable behavior also serves as documentation.

## Prequisites
Gherkin plugin works with Android Studio.
Install Plugin: Android Studio > Preferences > Plugins > Search "Gherkin" > Install & Restart Android Studio

## Running the tests

There are two ways to run the test, either with Gradle or directly with adb.
- `Using an Android Studio IDE`- it is simplest way.
- `gradlew`- can be run from the command line

**Tests cannot be run from .feature file directly - android-cucumber has its limitations**

To run the test using `Android Studio IDE`:

    Create a test run configuration:
    1. Run > Edit Configurations
    2. Click `+` button and select Android Instrumented Tests
    3. Specify test name: `AllTests`
    4. Select module: `sumtest`
    5. Click Ok
   
    Click the play icon to run all tests
    
To run the test using `gradlew`:

    ./gradlew connectedCheck
    
## Reports

There are two generated reports

- `Using an Android Studio IDE`- it is simplest way.
- `gradlew`- can be run from the command line

## An example

The app performs a very basic addition operation and displays the result to the user. The user enter will 2 integer values into the available text fields and after tapping the button either the result of the operation, or an error message will be displayed. The operation in the app may take a few seconds to complete, and while it is in progress a loading indicator will be shown.

* the user must be able to perform a cum on the calculator.

This translates to the following **Gherkin** syntax, which could be even written by the customer or project lead.

**sumtest.feature:**

```gherkin
  @calculator_test
  Scenario Outline: Run successful functions on the calculator
    Given I start the application
    When I run calculator sum for values "<First value>" and "<Second value>"
    And I assert the operation has run successfully with result "<Sum>"
    Examples:
      | First value | Second value | Sum   |
      | 5           | 5            | 10    |
      | -231        | -5           | -236  |
      | 999         | 1            | 1000  |
      | 1000        | 1000         | 2000  |
      | -1000       | -1000        | -2000 |
```

Simple and can easily be read and understood by non-programmers!

From that file, Cucumber pretty much autogenerates the Regular Expressions and Annotations needed to match the specific criterias. Then we only need to add the test logic and if we want, modify the method names.


**SumTestSteps.java (annotations and methods auto-generated):**

```kotlin
class SumTestSteps {
    ....

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

    ....
}
```
## Continuous Integration

As part of being an automation tester the project has been connected to CircleCI for CI/CD operations. 
We can see our test results in the pipeline to assess performance of testing.
<img src="/img/ci_pipeline.PNG" alt="CircleCI Pipeline" title="CircleCI Pipeline">

## Source Control

The project has many revisions for each piece of work completed to create a workable git tree.
<img src="/img/git_tree.PNG" alt="Github commit tree" title="Github commit tree">
