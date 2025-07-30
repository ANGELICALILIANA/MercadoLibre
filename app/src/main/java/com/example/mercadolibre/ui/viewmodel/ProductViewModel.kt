package com.example.mercadolibre.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mercadolibre.ui.sampleProducts
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor() : ViewModel() {
    // Lista completa de productos (quemada)
    private val allProducts = sampleProducts

    // Estado para el texto de búsqueda
    var searchQuery by mutableStateOf("")
        private set

    // Estado para los productos filtrados
    var filteredProducts by mutableStateOf(allProducts)
        private set

    // Estado para mostrar carga (para futuro uso con API)
    var isLoading by mutableStateOf(false)
        private set

    init {
        viewModelScope.launch {
            while (true) {
                delay(300)
                filterProducts()
            }
        }
    }

    fun onSearchQueryChange(query: String) {
        searchQuery = query
    }

    private fun filterProducts() {
        filteredProducts = if (searchQuery.isEmpty()) {
            allProducts
        } else {
            allProducts.filter { product ->
                product.title.contains(searchQuery, ignoreCase = true) ||
                        product.description.contains(searchQuery, ignoreCase = true) ||
                        product.category.contains(searchQuery, ignoreCase = true)
            }
        }
    }
}