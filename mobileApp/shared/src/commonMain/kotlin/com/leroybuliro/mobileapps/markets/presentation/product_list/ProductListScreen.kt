package com.leroybuliro.mobileapps.markets.presentation.product_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ImageSearch
import androidx.compose.material.icons.outlined.PersonSearch
import androidx.compose.material.icons.outlined.SavedSearch
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.YoutubeSearchedFor
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import com.leroybuliro.mobileapps.markets.domain.Product
import com.leroybuliro.mobileapps.markets.presentation.product_list.components.ProductList
import com.leroybuliro.mobileapps.markets.presentation.theme.DarkColorPalette
import com.leroybuliro.mobileapps.markets.presentation.theme.LightColorPalette
import markets.shared.generated.resources.Res
import markets.shared.generated.resources.account_tab
import markets.shared.generated.resources.app_name
import markets.shared.generated.resources.cart_tab
import markets.shared.generated.resources.home_tab
import markets.shared.generated.resources.search_tab
import markets.shared.generated.resources.wishlist_tab
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListScreen(
    isDarkTheme: Boolean,
//    onAction: (ProductListAction) -> Unit,
//    toggleTheme: () -> Unit,
    products: List<Product>,
    onAction: () -> Unit,
) {

    var selectedItem by remember { mutableIntStateOf(0) }

    val items = listOf(
        stringResource(Res.string.home_tab),
        stringResource(Res.string.search_tab),
        stringResource(Res.string.account_tab),
        stringResource(Res.string.wishlist_tab),
        stringResource(Res.string.cart_tab)
    )

    val selectedIcons = listOf(
        Icons.Filled.Home,
        Icons.Filled.Search,
        Icons.Filled.AccountCircle,
        Icons.Filled.Star,
        Icons.Outlined.ShoppingCart

    )
    val unselectedIcons = listOf(
        Icons.Outlined.Home,
        Icons.Outlined.Search,
        Icons.Outlined.AccountCircle,
        Icons.Outlined.Star,
        Icons.Outlined.ShoppingCart
    )

    MaterialTheme( colorScheme = if (isDarkTheme) DarkColorPalette else LightColorPalette ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ){
            Scaffold(
                topBar = {
                    // TODO: Implement new AppBar Component
                    CenterAlignedTopAppBar(
                        title = {
                            Text(
                                text = stringResource(Res.string.app_name),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        },
                        navigationIcon = {
                            FilledIconButton(
                                onClick = { /* doSomething() */ }
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Menu,
                                    contentDescription = "Localized description"
                                )
                            }
                        },
                    )
                },
                bottomBar = {
                    // TODO: Implement new Toolbar Component
                    NavigationBar(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant,
                        contentColor = MaterialTheme.colorScheme.onSurfaceVariant
                    ) {
                        items.forEachIndexed { index, item ->
                            NavigationBarItem(
                                selected = selectedItem == index,
                                onClick = { selectedItem = index },
                                icon = {
                                    Icon(
                                        imageVector = if (selectedItem == index)
                                            selectedIcons[index]
                                        else
                                            unselectedIcons[index],
                                        contentDescription = item,
                                        tint = MaterialTheme.colorScheme.onSurface
                                    )
                                },
                                label = { Text(item) },
                            )
                        }
                    }
                },
                snackbarHost = {},
                floatingActionButton = {},
                floatingActionButtonPosition = FabPosition.End,
                contentWindowInsets = ScaffoldDefaults.contentWindowInsets,
            ) { innerPadding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                        .consumeWindowInsets(innerPadding)
                        .padding(innerPadding),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ProductList(
                        onProductClick = onAction,
                        product = products
                    )
                }
            }
        }
    }
}
