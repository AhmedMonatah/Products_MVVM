package com.example.productdatadesignpattern.ui.screens.productList.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.example.productdatadesignpattern.model.Product
import com.example.productdatadesignpattern.ui.components.ProductItem

@Composable
fun AllProductsScreen(
    products: List<Product>,
    isLoading: Boolean,
    error: String?,
    addToFav: (Product) -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        if (products.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(products) { product ->
                    ProductItem(
                        product = product,
                        isFavorite = false,
                        onToggleFavorite = { addToFav(product) }
                    )
                }
            }
        }

        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }

        if (error != null) {
            Text(
                text = error,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}
