package com.example.mercadolibre.data.repositoryImpl

import com.example.mercadolibre.data.model.ResponseCategoryItem
import com.example.mercadolibre.data.network.ApiMercadoLibre
import com.mercadolibre.domain.repository.SearchCategoryItemRepository
import javax.inject.Inject

class SearchCategoryItemRepositoryImpl @Inject constructor(
    private val retrofitInstance: ApiMercadoLibre,
): SearchCategoryItemRepository {

    override suspend fun getSearchCategoryItem(categoryId: String): ResponseCategoryItem {
        return retrofitInstance.getSearchCategoryItem(categoryId)
    }
}