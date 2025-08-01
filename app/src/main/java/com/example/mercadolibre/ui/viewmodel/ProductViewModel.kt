package com.example.mercadolibre.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mercadolibre.data.model.sampleProducts
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor() : ViewModel() {

    private val allProducts = sampleProducts

    var searchQuery by mutableStateOf("")
        private set

    var filteredProducts by mutableStateOf(allProducts)
        private set

    init {
        viewModelScope.launch {
            while (true) {
                delay(300)
                filterProducts()
            }
        }
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