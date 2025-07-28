package com.mercadolibre.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mercadolibre.ui.sampleProducts
import com.mercadolibre.ui.screen.ProductDetailScreen
import com.mercadolibre.ui.viewmodel.ProductViewModel
import com.mercadolibre.ui.screen.HomeScreen
import com.mercadolibre.ui.viewmodel.SearchViewModel

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
                }
            )
        }

        composable("product/{productId}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")
            val product = sampleProducts.find { it.id == productId } ?: sampleProducts[0]

            ProductDetailScreen(
                product = product,
                onBack = { navController.popBackStack() }
            )
        }
    }
}