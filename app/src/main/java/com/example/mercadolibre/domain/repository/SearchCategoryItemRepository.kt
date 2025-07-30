package com.example.mercadolibre.domain.repository

import com.example.mercadolibre.data.model.ResponseCategoryItem

interface SearchCategoryItemRepository {

    suspend fun getSearchCategoryItem(categoryId: String): ResponseCategoryItem

}