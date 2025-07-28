package com.example.mercadolibre

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.mercadolibre.ui.theme.LightGrayML
import com.example.mercadolibre.ui.theme.MercadoLibreTheme
import com.example.mercadolibre.ui.theme.TextDarkML
import com.example.mercadolibre.ui.theme.YellowML
import com.mercadolibre.navigation.NavigationWrapper
import com.mercadolibre.ui.viewmodel.ProductViewModel
import com.mercadolibre.ui.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MercadoLibreTheme {
                val viewModel: SearchViewModel = hiltViewModel()
                ScaffoldWithSearchBar(viewModel)
            }
        }
    }
}

@Composable
fun ScaffoldWithSearchBar(viewModel: SearchViewModel) {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
        topBar = {
            Column(
                modifier = Modifier
                    .padding(WindowInsets.statusBars.asPaddingValues())
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                YellowML,
                                LightGrayML
                            ),
                            startY = 0f,
                            endY = 350f
                        )
                    )
                    .padding(16.dp)
            ) {
                SearchBarVisual(viewModel = viewModel)
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier
            .fillMaxSize()
            .background(YellowML)
            .padding(paddingValues)) {
            NavigationWrapper(navController, viewModel)
        }
    }
}

@Composable
fun SearchBarVisual(
    viewModel: SearchViewModel,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = viewModel.searchQuery,
            onValueChange = viewModel::onSearchQueryChange,
            placeholder = {
                Text(
                    "Buscar productos...",
                    color = TextDarkML
                )
            },
            leadingIcon = {
                Icon(
                    Icons.Default.Search,
                    contentDescription = "Buscar",
                    tint = TextDarkML
                )
            },
            singleLine = true,
            modifier = Modifier
                .weight(1f)
                .background(
                    MaterialTheme.colorScheme.surface,
                    MaterialTheme.shapes.extraLarge
                ),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White,
                unfocusedTextColor = TextDarkML,
                focusedTextColor = TextDarkML,

                unfocusedPlaceholderColor = Color.Gray,
                focusedPlaceholderColor = Color.Gray,
                unfocusedLeadingIconColor = TextDarkML,
                focusedLeadingIconColor = TextDarkML
            ),
            shape = MaterialTheme.shapes.extraLarge
        )
    }
}