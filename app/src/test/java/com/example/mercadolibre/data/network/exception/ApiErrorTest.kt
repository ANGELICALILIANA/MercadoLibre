package com.example.mercadolibre.data.network.exception

import com.example.mercadolibre.data.model.AppConstants
import junit.framework.TestCase.assertEquals
import kotlin.test.Test

class ApiErrorTest{

 @Test
 fun `getErrorMessage should return correct messages for all statuses`() {
  // Given
  val statusToExpectedMessage = mapOf(
   ApiError.ErrorStatus.BAD_REQUEST to AppConstants.BAD_REQUEST_ERROR_MESSAGE,
   ApiError.ErrorStatus.FORBIDDEN to AppConstants.FORBIDDEN_ERROR_MESSAGE,
   ApiError.ErrorStatus.NOT_FOUND to AppConstants.NOT_FOUND_ERROR_MESSAGE,
   ApiError.ErrorStatus.METHOD_NOT_ALLOWED to AppConstants.METHOD_NOT_ALLOWED_ERROR_MESSAGE,
   ApiError.ErrorStatus.CONFLICT to AppConstants.CONFLICT_ERROR_MESSAGE,
   ApiError.ErrorStatus.UNAUTHORIZED to AppConstants.UNAUTHORIZED_ERROR_MESSAGE,
   ApiError.ErrorStatus.INTERNAL_SERVER_ERROR to AppConstants.INTERNAL_SERVER_ERROR_MESSAGE,
   ApiError.ErrorStatus.NO_CONNECTION to AppConstants.NO_CONNECTION_ERROR_MESSAGE,
   ApiError.ErrorStatus.TIMEOUT to AppConstants.TIMEOUT_ERROR_MESSAGE,
   ApiError.ErrorStatus.UNKNOWN_ERROR to AppConstants.UNKNOWN_ERROR_MESSAGE,
   ApiError.ErrorStatus.SERVICE_UNAVAILABLE to AppConstants.SERVICE_UNAVAILABLE,
   ApiError.ErrorStatus.FAIL_CONNECTION to AppConstants.FAIL_CONNECTION_ERROR_MESSAGE,
   ApiError.ErrorStatus.IOEXCEPTION_UNKNOWN_ERROR to AppConstants.UNKNOWN_IOEXCEPTION_ERROR_MESSAGE,
  )

  //When
  statusToExpectedMessage.forEach { (status, expectedMessage) ->
   val apiError = ApiError("Error", status)
   val actualMessage = apiError.getErrorMessage()

   //Then
   assertEquals("Error", expectedMessage, actualMessage)
  }
 }

 }