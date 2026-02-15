import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val route: String,
    val label: String,
    val icon: ImageVector
) {
    object AllProducts : Screen(
        route = "all_products",
        label = "Products",
        icon = Icons.Default.Home
    )

    object Favorites : Screen(
        route = "favorites",
        label = "Favorites",
        icon = Icons.Default.Favorite
    )
}
