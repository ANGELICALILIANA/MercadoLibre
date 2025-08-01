package com.example.mercadolibre.data.usecaseImpl

import com.example.mercadolibre.data.model.ResponseCategoryList
import com.example.mercadolibre.domain.repository.SearchCategoryListRepository
import com.example.mercadolibre.domain.usecase.ServiceUseCase
import javax.inject.Inject

class SearchCategoryListUseCaseImpl @Inject constructor(
    private val searchCategoryListRepository: SearchCategoryListRepository
): ServiceUseCase<List<ResponseCategoryList>, Any?>() {
    override suspend fun run(request: Any?): List<ResponseCategoryList> = searchCategoryListRepository.getSearchCategoryList(
        request.toString()
    )
}