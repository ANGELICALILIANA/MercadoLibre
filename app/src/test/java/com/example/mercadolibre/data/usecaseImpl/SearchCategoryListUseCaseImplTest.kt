package com.example.mercadolibre.data.usecaseImpl

import com.example.mercadolibre.data.model.ResponseCategoryList
import com.example.mercadolibre.domain.repository.SearchCategoryListRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

class SearchCategoryListUseCaseImplTest {


    @Test
    fun `when run is executed it should return the list of categories`() = runTest {
        // When
        val repository = mockk<SearchCategoryListRepository>()
        val useCase = SearchCategoryListUseCaseImpl(repository)

        val query = "celulares"
        val expectedList = listOf(
            ResponseCategoryList(
                domain_name = "",
                domain_id = "",
                category_id = "",
                category_name = "",
                attributes = listOf()
            ),
            ResponseCategoryList(
                domain_name = "",
                domain_id = "",
                category_id = "",
                category_name = "",
                attributes = listOf()
            )
        )
        coEvery { repository.getSearchCategoryList(query) } returns expectedList

        //When
        val result = useCase.run(query)

        //Then
        assertEquals(expectedList, result)
        coVerify(exactly = 1) { repository.getSearchCategoryList(query) }
    }

    @Test
    fun `when the response is empty it should return empty list`() = runTest {
        //When
        val repository = mockk<SearchCategoryListRepository>()
        val useCase = SearchCategoryListUseCaseImpl(repository)
        val query = "sin resultados"
        coEvery { repository.getSearchCategoryList(query) } returns emptyList()

        //When
        val result = useCase.run(query)

        //Then
        assertEquals(emptyList<ResponseCategoryList>(), result)
        coVerify { repository.getSearchCategoryList(query) }
    }

}