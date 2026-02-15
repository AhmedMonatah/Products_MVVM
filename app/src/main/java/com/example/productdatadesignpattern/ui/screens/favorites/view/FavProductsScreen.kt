package com.example.productdatadesignpattern.ui.screens.favorites.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.example.productdatadesignpattern.model.Product
import com.example.productdatadesignpattern.ui.components.ProductItem

@Composable
fun FavProductsScreen(
    products: List<Product>,
    deleteFromFav: (Product) -> Unit,
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
                        isFavorite = true,
                        onToggleFavorite = { deleteFromFav(product) }
                    )
                }
            }
        } else {
             Text(
                text = "No favorites yet",
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}
