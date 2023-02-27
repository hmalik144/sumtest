package com.example.sumtest.constants

import com.example.sumtest.Result

enum class ErrorTypes(override val message: String): Result {
    EmptyInput("One or more fields are empty"),
    InvalidInput("Only integers are allowed"),
    Overflow("Exception overflow error. NSOSStatusErrorDomain Code=-10817 \\\"(null)\\\" UserInfo={_LSFunction=_LSSchemaConfigureForStore, ExpectedSimulatorHash={length = 32, bytes = 0xa9298a34 dc614504 8992eb3c f65c237f ... ff5133c6 37c50886 }"),
}