package com.example.mercadolibre.data.repositoryImpl

import com.example.mercadolibre.data.model.ResponseCategoryItem
import com.example.mercadolibre.data.network.ApiMercadoLibre
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import kotlin.test.Test

class SearchCategoryItemRepositoryImplTest {

    private lateinit var apiMock: ApiMercadoLibre
    private lateinit var repository: SearchCategoryItemRepositoryImpl

    @Before
    fun setUp() {
        apiMock = mock(ApiMercadoLibre::class.java)
        repository = SearchCategoryItemRepositoryImpl(apiMock)
    }

    @Test
    fun `getSearchCategoryItem should return expected category item`(): Unit = runBlocking {
        // Given
        val categoryId = "MLA1055"
        val expectedItem = ResponseCategoryItem(
            id = categoryId,
            name = "Celulares",
            picture = "http://some-url.com/pic.jpg",
            permalink = "http://mercado.com/categorias",
            total_items_in_this_category = 1000L,
            path_from_root = listOf(),
            children_categories = listOf(),
            attribute_types = null,
            settings = null,
            channels_settings = listOf(),
            meta_categ_id = "",
            attributable = null,
            date_created = null
        )

        `when`(apiMock.getSearchCategoryItem(categoryId)).thenReturn(expectedItem)

        // When
        val result = repository.getSearchCategoryItem(categoryId)

        // Then
        assertEquals(expectedItem, result)
        verify(apiMock).getSearchCategoryItem(categoryId)
    }

}