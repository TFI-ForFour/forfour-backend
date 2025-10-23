package com.techforimpact.forfour.global.common.exception

import org.springframework.http.HttpStatus

abstract class BaseException(
    val status: HttpStatus,
    message: String
) : RuntimeException(message)