package com.example.sumtest.test

import io.cucumber.junit.CucumberOptions


@CucumberOptions(
    features = ["features"],
    tags = ["@calculator_test"],
    glue = ["com.example.sumtest.steps"]
)
class CucumberRunner