package com.example.mercadolibre.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mercadolibre.data.model.ResponseCategoryItem
import com.example.mercadolibre.data.model.ResponseCategoryList
import com.mercadolibre.data.network.exception.ApiError
import com.mercadolibre.data.usecaseImpl.SearchCategoryItemUseCaseImpl
import com.mercadolibre.data.usecaseImpl.SearchCategoryListUseCaseImpl
import com.mercadolibre.domain.usecase.ServiceUseCaseResponse
import com.example.mercadolibre.ui.Product
import com.example.mercadolibre.ui.toProduct
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchCategoryListUseCaseImpl: SearchCategoryListUseCaseImpl,
    private val searchCategoryItemUseCaseImpl: SearchCategoryItemUseCaseImpl
)  : ViewModel() {

    private val _responseCategoryList = MutableStateFlow<List<Product>>(emptyList())
    val responseCategoryList: StateFlow<List<Product>> = _responseCategoryList.asStateFlow()

    private val _responseCategoryItem = MutableStateFlow<Product?>(null)
    val responseCategoryItem: StateFlow<Product?> = _responseCategoryItem.asStateFlow()

    private val _filteredProducts = MutableStateFlow<List<Product>>(emptyList())
    val filteredProducts: StateFlow<List<Product>> = _filteredProducts.asStateFlow()

    private val _showOrHideLoader = MutableStateFlow(false)
    val showOrHideLoader: StateFlow<Boolean> = _showOrHideLoader.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    var searchQuery by mutableStateOf("")
        private set

    init {
        snapshotFlow { searchQuery }
            .debounce(300)
            .onEach {
                getSearchCategoryList(it)
                filterProducts()
            }
            .launchIn(viewModelScope)
    }

    /**
     * Ejecuta la búsqueda de una lista de categorías en segundo plano a partir de un término de consulta
     * @param query Término de búsqueda ingresado por el usuario para filtrar las categorías
     */
    fun getSearchCategoryList(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _showOrHideLoader.value = true
            searchCategoryListUseCaseImpl.invoke(
                query,
                object : ServiceUseCaseResponse<List<ResponseCategoryList>> {
                    override fun onSuccess(result: List<ResponseCategoryList>) {
                        _showOrHideLoader.value = false
                        _responseCategoryList.value = result.map { it.toProduct() }
                        filterProducts()
                        Log.d("http ${this::class.java.simpleName}", "_responseCategoryList: ${_responseCategoryList.value}")
                    }

                    override fun onError(apiError: ApiError?) {
                        _showOrHideLoader.value = false
                        _errorMessage.value = apiError?.getErrorMessage()
                        Log.d("http ${this::class.java.simpleName}", "_errorMessage: ${_errorMessage.value}")
                    }
                }
            )
        }
    }

    /**
     * Ejecuta la búsqueda de ítems dentro de una categoría específica, según el ID proporcionado
     * @param query ID de la categoría cuyos ítems se desean buscar
     */
    fun getSearchItemCategory(query: String) {
        Log.d("http ${this::class.java.simpleName}", "getSearchItemCategory query: $query")

        viewModelScope.launch(Dispatchers.IO) {
            _showOrHideLoader.value = true
            searchCategoryItemUseCaseImpl.invoke(
                query,
                object : ServiceUseCaseResponse<ResponseCategoryItem> {
                    override fun onSuccess(result: ResponseCategoryItem) {
                        Log.d("http ${this::class.java.simpleName}", "getSearchItemCategory result: $result")
                        _showOrHideLoader.value = false
                        _responseCategoryItem.value = result.toProduct()
                        Log.d("http ${this::class.java.simpleName}", "getSearchItemCategory _responseCategoryItem: ${_responseCategoryItem.value}")
                    }

                    override fun onError(apiError: ApiError?) {
                        _showOrHideLoader.value = false
                        _errorMessage.value = apiError?.getErrorMessage()
                    }
                }
            )
        }
    }

    /**
     * Limpia los resultados actuales de búsqueda de categorías e ítems
     */
    fun clearResponseCategoryList() {
        _responseCategoryList.value = emptyList()
        _responseCategoryItem.value = null
    }

    private fun filterProducts() {
        _errorMessage.value = null
        _filteredProducts.value = if (searchQuery.isEmpty()) {
            _responseCategoryList.value
        } else {
            _responseCategoryList.value.filter { product ->
                product.title.contains(searchQuery, ignoreCase = true) ||
                        product.description.contains(searchQuery, ignoreCase = true) ||
                        product.category.contains(searchQuery, ignoreCase = true)
            }
        }
    }

    fun onSearchQueryChange(query: String) {
        searchQuery = query
        getSearchCategoryList(query)
    }
}