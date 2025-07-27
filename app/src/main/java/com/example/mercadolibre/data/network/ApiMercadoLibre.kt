package com.example.mercadolibre.data.network

import com.example.mercadolibre.data.model.ResponseCategoryItem
import com.example.mercadolibre.data.model.ResponseCategoryList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiMercadoLibre {

    /**
     * Busca una lista de categorías asociadas a un término de búsqueda dado
     * @param query Término de búsqueda ingresado por el usuario (por ejemplo, "celulares", "zapatillas")
     * @return Una lista de [ResponseCategoryList] con las categorías sugeridas
     */
    @GET("sites/MLA/domain_discovery/search")
    suspend fun getSearchCategoryList(
        @Query("q") query: String
    ): List<ResponseCategoryList>

    /**
     * Obtiene los detalles de una categoría específica por su ID
     * @param categoryId El identificador único de la categoría a consultar
     * @return Un objeto [ResponseCategoryItem] que contiene la información de la categoría
     */
    @GET("categories/{categoryId}")
    suspend fun getSearchCategoryItem(
        @Path("categoryId") categoryId: String
    ): ResponseCategoryItem
}