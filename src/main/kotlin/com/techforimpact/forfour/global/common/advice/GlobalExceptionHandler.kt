package com.techforimpact.forfour.global.common.advice

import com.techforimpact.forfour.global.common.exception.BaseException
import com.techforimpact.forfour.global.common.exception.ErrorDetail
import com.techforimpact.forfour.global.common.exception.ErrorMessage
import com.techforimpact.forfour.global.common.response.ApiResponse
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.resource.NoResourceFoundException


@RestControllerAdvice
class GlobalExceptionHandler {

    companion object {
        private val log = KotlinLogging.logger {}
        private const val LOG_FORMAT = "Class : {}, Code : {}, Message : {}"
    }

    @ExceptionHandler(BaseException::class)
    fun handleBaseException(e: BaseException): ResponseEntity<ApiResponse<Unit?>> {
        logWarning(e, e.status.value())
        return responseException(e.status, e.message, null)
    }

    @ExceptionHandler(MissingServletRequestParameterException::class)
    fun handleMissingServletRequestParameterException(e: MissingServletRequestParameterException): ResponseEntity<ApiResponse<Unit?>> {
        val errorCode = ErrorMessage.PARAMETER_NOT_FOUND
        logWarning(e, HttpStatus.BAD_REQUEST.value())
        return responseException(errorCode.status, errorCode.message, null)
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleHttpMessageNotReadableException(e: HttpMessageNotReadableException): ResponseEntity<ApiResponse<Unit?>> {
        logWarning(e, HttpStatus.BAD_REQUEST.value())
        return responseException(HttpStatus.BAD_REQUEST, e.message, null)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentValidation(e: MethodArgumentNotValidException): ResponseEntity<ApiResponse<List<ErrorDetail>?>> {
        val errorCode = ErrorMessage.METHOD_ARGUMENT_NOT_VALID

        val errors = e.bindingResult
            .fieldErrors
            .map { fe ->
                ErrorDetail(
                    errorField = fe.field,
                    errorMessage = fe.defaultMessage ?: ErrorMessage.METHOD_ARGUMENT_NOT_VALID.message,
                    inputValue = fe.rejectedValue
                )
            }
            .toList()

        logWarning(e, errorCode.status.value())
        return responseException(errorCode.status, errorCode.message, errors)
    }

    @ExceptionHandler(NoResourceFoundException::class)
    fun handleNoResourceFound(e: NoResourceFoundException): ResponseEntity<ApiResponse<Unit?>> {
        val errorCode = ErrorMessage.RESOURCE_NOT_FOUND
        logWarning(e, errorCode.status.value())
        return responseException(errorCode.status, errorCode.message, null)
    }

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResponseEntity<ApiResponse<Unit?>> {
        val errorCode = ErrorMessage.INTERNAL_SERVER_ERROR
        logError(e, errorCode.status.value())
        return responseException(errorCode.status, e.message ?: ErrorMessage.INTERNAL_SERVER_ERROR.message, null)
    }

    private fun <T> responseException(status: HttpStatus, message: String?, data: T?): ResponseEntity<ApiResponse<T?>> {
        val response = ApiResponse.response(status, message ?: ErrorMessage.INTERNAL_SERVER_ERROR.message, data)

        return ResponseEntity
            .status(status)
            .body(response)
    }

    private fun logWarning(e: Exception, errorCode: Int) {
        log.warn(e.message, e)
        log.warn(LOG_FORMAT, e.javaClass.simpleName, errorCode, e.message)
    }

    private fun logError(e: Exception, errorCode: Int) {
        log.error(e.message, e)
        log.error(LOG_FORMAT, e.javaClass.simpleName, errorCode, e.message)
    }
}