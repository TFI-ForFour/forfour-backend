package com.techforimpact.forfour.global.common.exception

data class ErrorDetail(
    val errorField: String,
    val errorMessage: String,
    val inputValue: Any?
)