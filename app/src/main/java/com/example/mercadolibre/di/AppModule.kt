package com.example.mercadolibre.di

import com.example.mercadolibre.BuildConfig
import com.example.mercadolibre.data.network.ApiMercadoLibre
import com.example.mercadolibre.data.repositoryImpl.SearchCategoryItemRepositoryImpl
import com.example.mercadolibre.data.repositoryImpl.SearchCategoryListRepositoryImpl
import com.mercadolibre.data.usecaseImpl.SearchCategoryItemUseCaseImpl
import com.mercadolibre.data.usecaseImpl.SearchCategoryListUseCaseImpl
import com.mercadolibre.domain.repository.SearchCategoryItemRepository
import com.mercadolibre.domain.repository.SearchCategoryListRepository
import com.mercadolibre.ui.viewmodel.SearchViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(): ApiMercadoLibre {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiMercadoLibre::class.java)
    }

    @Provides
    @Singleton
    fun provideCategoryItemRepository(mercadoApi: ApiMercadoLibre): SearchCategoryItemRepository {
        return SearchCategoryItemRepositoryImpl(mercadoApi)
    }

    @Provides
    @Singleton
    fun provideCategoryItemUseCaseImpl(searchCategoryItemRepository: SearchCategoryItemRepository): SearchCategoryItemUseCaseImpl {
        return SearchCategoryItemUseCaseImpl(searchCategoryItemRepository)
    }

    @Provides
    @Singleton
    fun provideCategoryListRepository(mercadoApi: ApiMercadoLibre): SearchCategoryListRepository {
        return SearchCategoryListRepositoryImpl(mercadoApi)
    }

    @Provides
    @Singleton
    fun provideCategoryListUseCaseImpl(searchCategoryListRepository: SearchCategoryListRepository): SearchCategoryListUseCaseImpl {
        return SearchCategoryListUseCaseImpl(searchCategoryListRepository)
    }

    @Provides
    fun provideSearchViewModel(
        searchCategoryListUseCaseImpl: SearchCategoryListUseCaseImpl,
        searchCategoryItemUseCaseImpl: SearchCategoryItemUseCaseImpl,
    ): SearchViewModel {
        return SearchViewModel(searchCategoryListUseCaseImpl, searchCategoryItemUseCaseImpl)
    }
}