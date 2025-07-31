package com.example.mercadolibre.data.repositoryImpl

import com.example.mercadolibre.data.model.ResponseCategoryList
import com.example.mercadolibre.data.network.ApiMercadoLibre
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

class SearchCategoryListRepositoryImplTest {

    private lateinit var apiMock: ApiMercadoLibre
    private lateinit var repository: SearchCategoryListRepositoryImpl

    @Before
    fun setUp() {
        apiMock = mock(ApiMercadoLibre::class.java)
        repository = SearchCategoryListRepositoryImpl(apiMock)
    }

    @Test
    fun `getSearchCategoryList should return expected category list`(): Unit = runBlocking {
        // Given
        val query = "celulares"
        val expectedList = listOf(
            ResponseCategoryList(
                domain_id = "MLA-CELLPHONES",
                domain_name = "Celulares",
                category_id = "MLA1055",
                category_name = "Celulares y Smartphones",
                attributes = emptyList()
            )
        )

        `when`(apiMock.getSearchCategoryList(query)).thenReturn(expectedList)

        // When
        val result = repository.getSearchCategoryList(query)

        // Then
        assertEquals(expectedList, result)
        verify(apiMock).getSearchCategoryList(query)
    }

}