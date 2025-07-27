package com.mercadolibre.data.network.exception

import com.example.mercadolibre.data.model.AppConstants as error

data class ApiError(val message: String?, val code: Int?, var errorStatus: ErrorStatus) {

    constructor(message: String?, errorStatus: ErrorStatus) : this(message, null, errorStatus)

    fun getErrorMessage(): String {
        return when (errorStatus) {
            ErrorStatus.BAD_REQUEST -> error.BAD_REQUEST_ERROR_MESSAGE
            ErrorStatus.FORBIDDEN -> error.FORBIDDEN_ERROR_MESSAGE
            ErrorStatus.NOT_FOUND -> error.NOT_FOUND_ERROR_MESSAGE
            ErrorStatus.METHOD_NOT_ALLOWED -> error.METHOD_NOT_ALLOWED_ERROR_MESSAGE
            ErrorStatus.CONFLICT -> error.CONFLICT_ERROR_MESSAGE
            ErrorStatus.UNAUTHORIZED -> error.UNAUTHORIZED_ERROR_MESSAGE
            ErrorStatus.INTERNAL_SERVER_ERROR -> error.INTERNAL_SERVER_ERROR_MESSAGE
            ErrorStatus.NO_CONNECTION -> error.NO_CONNECTION_ERROR_MESSAGE
            ErrorStatus.TIMEOUT -> error.TIMEOUT_ERROR_MESSAGE
            ErrorStatus.UNKNOWN_ERROR -> error.UNKNOWN_ERROR_MESSAGE
            ErrorStatus.SERVICE_UNAVAILABLE -> error.SERVICE_UNAVAILABLE
            ErrorStatus.FAIL_CONNECTION -> error.FAIL_CONNECTION_ERROR_MESSAGE
            ErrorStatus.IOEXCEPTION_UNKNOWN_ERROR -> error.UNKNOWN_IOEXCEPTION_ERROR_MESSAGE
        }
    }

    enum class ErrorStatus {
        BAD_REQUEST,
        UNAUTHORIZED,
        FORBIDDEN,
        NOT_FOUND,
        METHOD_NOT_ALLOWED,
        CONFLICT,
        INTERNAL_SERVER_ERROR,
        TIMEOUT,
        NO_CONNECTION,
        UNKNOWN_ERROR,
        SERVICE_UNAVAILABLE,
        FAIL_CONNECTION,
        IOEXCEPTION_UNKNOWN_ERROR,
    }
}