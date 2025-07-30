package com.example.mercadolibre.data.usecaseImpl

import com.example.mercadolibre.data.model.ResponseCategoryItem
import com.example.mercadolibre.domain.repository.SearchCategoryItemRepository
import com.example.mercadolibre.domain.repository.usecase.ServiceUseCase
import javax.inject.Inject

class SearchCategoryItemUseCaseImpl @Inject constructor(
    private val searchCategoryItemRepository: SearchCategoryItemRepository
): ServiceUseCase<ResponseCategoryItem, Any?>() {
    override suspend fun run(request: Any?): ResponseCategoryItem = searchCategoryItemRepository.getSearchCategoryItem(
        request.toString()
    )
}