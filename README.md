# sumtest
====================

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

There are three ways to run the test, either with Gradle or directly with adb.
- `Using an Android Studio IDE`- it is simplest way.
- `gradlew`- can be run from the command line

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
