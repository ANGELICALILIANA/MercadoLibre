package com.example.mercadolibre.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mercadolibre.ui.sampleProducts
import com.mercadolibre.ui.screen.HomeScreen
import com.mercadolibre.ui.screen.ProductDetailScreen
import com.example.mercadolibre.ui.viewmodel.SearchViewModel

@Composable
fun NavigationWrapper(
    navController: NavHostController = rememberNavController(),
    viewModel: SearchViewModel
) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(
                viewModel = viewModel,
                onProductClick = { productId ->
                    navController.navigate("product/$productId")
                    viewModel.getSearchItemCategory(productId)
                }
            )
        }

        composable("product/{productId}") {
            val product = viewModel.responseCategoryItem.collectAsState()
            ProductDetailScreen(
                product = product.value ?: sampleProducts[0],
                onBack = { navController.popBackStack() }
            )
        }
    }
}