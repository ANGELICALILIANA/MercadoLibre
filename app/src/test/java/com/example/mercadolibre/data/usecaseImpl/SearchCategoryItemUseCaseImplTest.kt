package com.example.mercadolibre.data.usecaseImpl

import com.example.mercadolibre.data.model.ResponseCategoryItem
import com.example.mercadolibre.domain.repository.SearchCategoryItemRepository
import com.example.mercadolibre.domain.usecase.ServiceUseCaseResponse
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import kotlin.test.Test

class SearchCategoryItemUseCaseImplTest {

    private lateinit var repository: SearchCategoryItemRepository
    private lateinit var useCase: SearchCategoryItemUseCaseImpl

    @Before
    fun setup() {
        repository = mockk()
        useCase = SearchCategoryItemUseCaseImpl(repository)
    }

    @Test
    fun `should call repository and return ResponseCategoryItem`() = runTest {
        // Given
        val categoryId = "123"
        val expected = ResponseCategoryItem(
            id = "123",
            name = "Categoría Ejemplo",
            picture = "https://example.com/image.jpg",
            permalink = null,
            total_items_in_this_category = 100,
            path_from_root = emptyList(),
            children_categories = emptyList(),
            attribute_types = null,
            settings = null,
            channels_settings = emptyList(),
            meta_categ_id = null,
            attributable = null,
            date_created = null
        )

        coEvery { repository.getSearchCategoryItem(categoryId) } returns expected

        // When
        val result = useCase.run(categoryId)

        // Then
        assertEquals(expected, result)
        coVerify(exactly = 1) { repository.getSearchCategoryItem(categoryId) }
    }
}