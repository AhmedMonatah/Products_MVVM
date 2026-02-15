package com.example.productdatadesignpattern

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.productdatadesignpattern.ui.screens.favorites.view.FavProductsScreen
import com.example.productdatadesignpattern.ui.screens.favorites.viewmodel.FavProductFactory
import com.example.productdatadesignpattern.ui.screens.favorites.viewmodel.FavProductViewModel
import com.example.productdatadesignpattern.ui.screens.productList.view.AllProductsScreen
import com.example.productdatadesignpattern.ui.screens.productList.viewmodel.AllProductFactory
import com.example.productdatadesignpattern.ui.screens.productList.viewmodel.AllProductViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val allProductsFactory = AllProductFactory(application)
        val allProductsViewModel = ViewModelProvider(this, allProductsFactory).get(AllProductViewModel::class.java)

        val favProductsFactory = FavProductFactory(application)
        val favProductsViewModel = ViewModelProvider(this, favProductsFactory).get(FavProductViewModel::class.java)

        setContent {
            MainScreen(
                allProductsViewModel = allProductsViewModel,
                favProductsViewModel = favProductsViewModel
            )
        }
    }
}

@Composable
fun MainScreen(
    allProductsViewModel: AllProductViewModel,
    favProductsViewModel: FavProductViewModel
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            NavigationBar {
                val screens = listOf(Screen.AllProducts, Screen.Favorites)

                NavigationBar {
                    screens.forEach { screen ->
                        NavigationBarItem(
                            icon = { Icon(screen.icon, contentDescription = screen.label) },
                            label = { Text(screen.label) },
                            selected = currentRoute == screen.route,
                            onClick = {
                                navController.navigate(screen.route) {
                                    launchSingleTop = true
                                    if (screen == Screen.AllProducts) {
                                        popUpTo(screen.route) { inclusive = true }
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.AllProducts.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screen.AllProducts.route) {
                val products by allProductsViewModel.products.collectAsState()
                val isLoading by allProductsViewModel.isLoading.collectAsState()
                val error by allProductsViewModel.errorMessage.collectAsState()

                AllProductsScreen(
                    products = products,
                    isLoading = isLoading,
                    error = error,
                    addToFav = { allProductsViewModel.addProductToFav(it) }
                )
            }

            composable(Screen.Favorites.route) {
                val favorites by favProductsViewModel.favoriteProducts.observeAsState(emptyList())

                FavProductsScreen(
                    products = favorites,
                    deleteFromFav = { favProductsViewModel.removeProductFromFav(it) }
                )
            }
        }

    }
}
