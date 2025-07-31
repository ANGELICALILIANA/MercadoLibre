package com.example.mercadolibre.data.network.exception

import com.example.mercadolibre.data.model.AppConstants
import junit.framework.TestCase.assertEquals
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException
import kotlin.test.Test

class ApiErrorHandlerKtTest {

    private fun createHttpException(code: Int, message: String): HttpException {
        val response = Response.error<Any>(
            code,
            ResponseBody.create("application/json".toMediaTypeOrNull(), message)
        )
        return HttpException(response)
    }

    @Test
    fun `test HttpException 400 returns BAD_REQUEST`() {
      //Given
        val exception = createHttpException(400, "Bad Request")

        //When
        val result = traceErrorException(exception)

        //Then
        assertEquals(ApiError.ErrorStatus.BAD_REQUEST, result.errorStatus)
    }

    @Test
    fun `test HttpException 500 returns INTERNAL_SERVER_ERROR`() {
        //Given
        val exception = createHttpException(500, "Internal Server Error")

        //When
        val result = traceErrorException(exception)

        //Then
        assertEquals(ApiError.ErrorStatus.INTERNAL_SERVER_ERROR, result.errorStatus)
    }

    @Test
    fun `test HttpException unknown code returns UNKNOWN_ERROR`() {
        //Given
        val exception = createHttpException(999, "Some weird error")

        //When
        val result = traceErrorException(exception)

        //Then
        assertEquals(ApiError.ErrorStatus.UNKNOWN_ERROR, result.errorStatus)
        assertEquals(AppConstants.UNKNOWN_ERROR_MESSAGE, result.message)
    }

    @Test
    fun `test SocketTimeoutException returns TIMEOUT`() {
        //Given
        val exception = SocketTimeoutException("timeout")

        //When
        val result = traceErrorException(exception)

        //Then
        assertEquals(ApiError.ErrorStatus.TIMEOUT, result.errorStatus)
    }

    @Test
    fun `test IOException Unable to resolve host returns NO_CONNECTION`() {
        //Given
        val exception = IOException("Unable to resolve host")

        //When
        val result = traceErrorException(exception)

        //Then
        assertEquals(ApiError.ErrorStatus.NO_CONNECTION, result.errorStatus)
    }

    @Test
    fun `test IOException Failed to connect returns FAIL_CONNECTION`() {
        //Given
        val exception = IOException("Failed to connect to server")

        //When
        val result = traceErrorException(exception)

        //Then
        assertEquals(ApiError.ErrorStatus.FAIL_CONNECTION, result.errorStatus)
    }

    @Test
    fun `test IOException with unknown reason returns IOEXCEPTION_UNKNOWN_ERROR`() {
        //Given
        val exception = IOException("Some IO issue")

        //When
        val result = traceErrorException(exception)

        //Then
        assertEquals(ApiError.ErrorStatus.IOEXCEPTION_UNKNOWN_ERROR, result.errorStatus)
    }

    @Test
    fun `test unknown Throwable returns UNKNOWN_ERROR`() {
        //Given
        val exception = Exception("Unhandled exception")

        //When
        val result = traceErrorException(exception)

        //Then
        assertEquals(ApiError.ErrorStatus.UNKNOWN_ERROR, result.errorStatus)
        assertEquals(AppConstants.UNKNOWN_ERROR_MESSAGE, result.message)
    }

}