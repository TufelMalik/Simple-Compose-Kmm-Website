package org.web.ecommerce

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.web.ecommerce.screens.ProductListScreen
import org.web.ecommerce.screens.ProductViewModel

@Composable
fun App() {
    val viewModel = ProductViewModel()
    LaunchedEffect(Unit) {
        viewModel.getProducts()
    }
    MaterialTheme {
        Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            ProductListScreen(
                viewModel.products.collectAsState(),
                onBackClick = { },
                onNextClick = { },
                onItemClick = {})
        }
    }
}