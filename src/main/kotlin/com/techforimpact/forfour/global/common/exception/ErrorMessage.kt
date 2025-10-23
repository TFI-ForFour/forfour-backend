package com.techforimpact.forfour.global.common.exception

import org.springframework.http.HttpStatus

enum class ErrorMessage(
    val status: HttpStatus,
    val message: String
) {
    // 4XX Error
    METHOD_ARGUMENT_NOT_VALID(HttpStatus.BAD_REQUEST, "잘못된 [인자]입니다."),
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "요청한 [RESOURCE, URL]를 찾을 수 없습니다."),
    PARAMETER_NOT_FOUND(HttpStatus.BAD_REQUEST, "요청에 [Parameter]가 존재하지 않습니다."),

    // 5XX Error
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "[Server] 내부 에러가 발생했습니다.");
}