package com.example.mercadolibre.domain.repository.usecase

import com.example.mercadolibre.data.network.exception.ApiError


interface ServiceUseCaseResponse<Type> {

    fun onSuccess(result: Type)

    fun onError(apiError: ApiError?)
}