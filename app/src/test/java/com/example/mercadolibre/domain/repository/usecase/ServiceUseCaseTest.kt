package com.example.mercadolibre.domain.repository.usecase

import com.mercadolibre.domain.usecase.ServiceUseCaseResponse
import kotlinx.coroutines.test.runTest
import org.junit.Test
import io.mockk.*
import java.util.concurrent.CancellationException

class ServiceUseCaseTest {

    @Test
    fun `should call onSuccess when run completes successfully`() = runTest {
        // Given
        val request = "request"
        val response = mockk<ServiceUseCaseResponse<String>>(relaxed = true)
        val useCase = object : ServiceUseCase<String, String>() {
            override suspend fun run(request: String?): String {
                return "Success for $request"
            }
        }

        // When
        useCase.invoke(request, response)

        // Then
        verify { response.onSuccess("Success for request") }
        confirmVerified(response)
    }


    @Test
    fun `should call onError when run throws exception`() = runTest {
        // Given
        val useCase = object : ServiceUseCase<String, String>() {
            override suspend fun run(request: String?): String {
                throw RuntimeException("Error")
            }
        }
        val response = mockk<ServiceUseCaseResponse<String>>(relaxed = true)

        // When
        useCase.invoke("fail", response)

        // Then
        verify { response.onError(any()) }
        confirmVerified(response)
    }


    @Test
    fun `Should call onError with cancellationException`() = runTest {
        // Given
        val cancellationUseCase = object : ServiceUseCase<String, String>() {
            override suspend fun run(request: String?): String {
                throw CancellationException("Cancel")
            }
        }
        val responseMock = mockk<ServiceUseCaseResponse<String>>(relaxed = true)

        // When
        cancellationUseCase.invoke("", responseMock)

        // Then
        verify { responseMock.onError(any()) }
    }

}

