package com.mercadolibre.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mercadolibre.data.model.ResponseCategoryItem
import com.example.mercadolibre.data.model.ResponseCategoryList
import com.mercadolibre.data.network.exception.ApiError
import com.mercadolibre.data.usecaseImpl.SearchCategoryItemUseCaseImpl
import com.mercadolibre.data.usecaseImpl.SearchCategoryListUseCaseImpl
import com.mercadolibre.domain.usecase.ServiceUseCaseResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchCategoryListUseCaseImpl: SearchCategoryListUseCaseImpl,
    private val searchCategoryItemUseCaseImpl: SearchCategoryItemUseCaseImpl
)  : ViewModel() {

    private val _responseCategoryList = MutableStateFlow<List<ResponseCategoryList>>(emptyList())
    val responseCategoryList: MutableStateFlow<List<ResponseCategoryList>>
        get() = _responseCategoryList

    private val _responseCategoryItem = MutableStateFlow<ResponseCategoryItem?>(null)
    val responseCategoryItem: MutableStateFlow<ResponseCategoryItem?>
        get() = _responseCategoryItem

    private val _showOrHideLoader = MutableStateFlow<Boolean>(false)
    val showOrHideLoader: MutableStateFlow<Boolean>
        get() = _showOrHideLoader

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: MutableStateFlow<String?>
        get() = _errorMessage

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
                        _responseCategoryList.value = result
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
     * Ejecuta la búsqueda de ítems dentro de una categoría específica, según el ID proporcionado
     * @param query ID de la categoría cuyos ítems se desean buscar
     */
    fun getSearchItemCategory(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _showOrHideLoader.value = true
            searchCategoryItemUseCaseImpl.invoke(
                query,
                object : ServiceUseCaseResponse<ResponseCategoryItem> {
                    override fun onSuccess(result: ResponseCategoryItem) {
                        _showOrHideLoader.value = false
                        _responseCategoryItem.value = result
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

}