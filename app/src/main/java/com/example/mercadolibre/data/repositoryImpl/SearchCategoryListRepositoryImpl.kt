package com.example.mercadolibre.data.repositoryImpl

import com.example.mercadolibre.data.model.ResponseCategoryList
import com.example.mercadolibre.data.network.ApiMercadoLibre
import com.example.mercadolibre.domain.repository.SearchCategoryListRepository
import javax.inject.Inject

class SearchCategoryListRepositoryImpl @Inject constructor(
    private val retrofitInstance: ApiMercadoLibre,
): SearchCategoryListRepository {

    override suspend fun getSearchCategoryList(query: String): List<ResponseCategoryList> {
        return retrofitInstance.getSearchCategoryList(query)
    }
}