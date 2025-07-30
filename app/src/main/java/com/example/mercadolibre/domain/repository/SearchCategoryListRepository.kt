package com.example.mercadolibre.domain.repository

import com.example.mercadolibre.data.model.ResponseCategoryList

interface SearchCategoryListRepository {

    suspend fun getSearchCategoryList(query: String): List<ResponseCategoryList>
}