package com.techforimpact.forfour.global.common.response

import org.springframework.http.HttpStatus

data class ApiResponse<T>(
    val code: Int,
    val message: String,
    val data: T?
){
    companion object {
        fun <T> response(httpStatus: HttpStatus, message: String, data: T): ApiResponse<T> {
            return ApiResponse(
                httpStatus.value(),
                message,
                data
            )
        }

        fun <T> response(httpStatus: HttpStatus, message: String): ApiResponse<T> {
            return ApiResponse(
                code = httpStatus.value(),
                message = message,
                data = null
            )
        }
    }
}