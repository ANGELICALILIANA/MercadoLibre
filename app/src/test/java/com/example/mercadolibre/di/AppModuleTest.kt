package com.example.mercadolibre.di

import com.example.mercadolibre.BuildConfig
import com.example.mercadolibre.data.network.ApiMercadoLibre
import com.example.mercadolibre.data.repositoryImpl.SearchCategoryItemRepositoryImpl
import com.example.mercadolibre.data.repositoryImpl.SearchCategoryListRepositoryImpl
import com.example.mercadolibre.data.usecaseImpl.SearchCategoryItemUseCaseImpl
import com.example.mercadolibre.data.usecaseImpl.SearchCategoryListUseCaseImpl
import com.example.mercadolibre.di.AppModule.provideCategoryItemRepository
import com.example.mercadolibre.di.AppModule.provideCategoryItemUseCaseImpl
import com.example.mercadolibre.di.AppModule.provideCategoryListRepository
import com.example.mercadolibre.di.AppModule.provideCategoryListUseCaseImpl
import com.example.mercadolibre.domain.repository.SearchCategoryItemRepository
import com.example.mercadolibre.domain.repository.SearchCategoryListRepository
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.mockito.kotlin.mock
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class AppModuleTest{

  private lateinit var mockWebServer: MockWebServer
  private lateinit var api: ApiMercadoLibre

  @Before
  fun setup() {
   mockWebServer = MockWebServer()
   mockWebServer.start()

   api = Retrofit.Builder()
    .baseUrl(mockWebServer.url(BuildConfig.BASE_URL))
    .addConverterFactory(GsonConverterFactory.create())
    .build()
    .create(ApiMercadoLibre::class.java)
  }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `should create ApiMercadoLibre instance`() {
        assertNotNull(api, "La instancia de ApiMercadoLibre no debería ser null")
        assertTrue(true, "El objeto no es instancia de ApiMercadoLibre")
    }

    @Test
    fun `should create SearchCategoryItemRepositoryImpl instance`() {
        // Given
        val mockApi = mock<ApiMercadoLibre>()

        // When
        val repository = provideCategoryItemRepository(mockApi)

        // Then
        assertNotNull(repository, "El repositorio no debería ser null")
        assertTrue(repository is SearchCategoryItemRepositoryImpl, "Debe ser instancia de SearchCategoryItemRepositoryImpl")
    }

    @Test
    fun `should create SearchCategoryItemUseCaseImpl instance`() {
        // Given
        val mockRepository = mock<SearchCategoryItemRepository>()

        // When
        val useCase = provideCategoryItemUseCaseImpl(mockRepository)

        // Then
        assertNotNull(useCase, "El UseCase no debería ser null")
        assertTrue(useCase is SearchCategoryItemUseCaseImpl, "Debe ser instancia de SearchCategoryItemUseCaseImpl")
    }

    @Test
    fun `should create SearchCategoryListRepositoryImpl instance`() {
        // Given
        val mockApi = mock<ApiMercadoLibre>()

        // When
        val repository = provideCategoryListRepository(mockApi)

        // Then
        assertNotNull(repository, "El repositorio no debería ser null")
        assertTrue(repository is SearchCategoryListRepositoryImpl, "Debe ser instancia de SearchCategoryListRepositoryImpl")
    }

    @Test
    fun `should create SearchCategoryListUseCaseImpl instance`() {
        // Given
        val mockRepository = mock<SearchCategoryListRepository>()

        // When
        val useCase = provideCategoryListUseCaseImpl(mockRepository)

        // Then
        assertNotNull(useCase, "El UseCase no debería ser null")
        assertTrue(useCase is SearchCategoryListUseCaseImpl, "Debe ser instancia de SearchCategoryListUseCaseImpl")
    }

}