package com.example.sumtest.test

import io.cucumber.junit.CucumberOptions


@CucumberOptions(
    features = ["features"],
    tags = ["@sample_test"],
    glue = ["com.example.sumtest.steps"]
)
class CucumberRunner