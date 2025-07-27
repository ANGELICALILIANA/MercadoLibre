package com.mercadolibre.domain.usecase

import com.mercadolibre.data.network.exception.ApiError


interface ServiceUseCaseResponse<Type> {

    fun onSuccess(result: Type)

    fun onError(apiError: ApiError?)
}