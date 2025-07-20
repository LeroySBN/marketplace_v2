package com.leroybuliro.mobileapps.markets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.DeliveryDining
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Store
import androidx.compose.material.icons.outlined.AccountBalanceWallet
import androidx.compose.material.icons.outlined.DeliveryDining
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material.icons.outlined.Store
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import com.leroybuliro.mobileapps.markets.domain.Cart
import com.leroybuliro.mobileapps.markets.domain.Product
import com.leroybuliro.mobileapps.markets.presentation.cart_list.CartListScreen
import com.leroybuliro.mobileapps.markets.presentation.product_detail.ProductDetailScreen
import com.leroybuliro.mobileapps.markets.presentation.product_list.ProductListScreen
import com.leroybuliro.mobileapps.markets.presentation.settings_list.SettingsListScreen
import markets.shared.generated.resources.Res
import markets.shared.generated.resources.account
import markets.shared.generated.resources.cart_tab
import markets.shared.generated.resources.led_steamdeck
import markets.shared.generated.resources.orders_tab
import markets.shared.generated.resources.paxton_three_seater_1
import markets.shared.generated.resources.paxton_three_seater_2
import markets.shared.generated.resources.settings
import markets.shared.generated.resources.shop_tab
import markets.shared.generated.resources.wallet_tab
import markets.shared.generated.resources.wishlist_tab
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

private val products = (1..10).map {
    Product(
        id = it,
        name = "Paxton Three Seater",
        description = "\n" +
                "\n" +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras gravida ante erat, a pretium velit consectetur ut. Aliquam et mollis erat. Interdum et malesuada fames ac ante ipsum primis in faucibus. Curabitur eget odio sem. Nullam rutrum euismod lacus et consectetur. Aenean non nisl lectus. Praesent ut lorem malesuada, porttitor orci at, luctus nibh. Nunc consequat velit ac sapien faucibus, quis facilisis odio feugiat. Nunc eu commodo felis. Vestibulum vitae ligula rhoncus, mollis libero sed, ultrices felis. Duis congue suscipit pharetra. Duis in est a tortor pharetra laoreet non vitae magna. Sed aliquam hendrerit est congue lobortis. Nam volutpat fermentum nulla, ac rhoncus justo sodales in. Vestibulum leo diam, imperdiet in arcu at, aliquet elementum augue. Mauris non convallis nulla, venenatis dignissim massa.\n" +
                "\n" +
                "Nulla elementum diam tortor, eu tincidunt enim tincidunt eu. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Proin nulla tortor, euismod ac risus vitae, facilisis suscipit mi. Quisque eget placerat dolor. Aliquam a mauris aliquam, elementum purus eu, pellentesque lorem. Curabitur lectus sem, blandit id sapien at, cursus hendrerit magna. Etiam pellentesque imperdiet massa. Aenean dui sapien, sagittis ut rutrum rhoncus, vestibulum non lacus.\n" +
                "\n" +
                "Proin vulputate eget massa nec porta. Donec eu ex elit. Duis bibendum ante eget orci pellentesque, ut porta ligula euismod. Morbi egestas lacinia pretium. Maecenas auctor, lectus at suscipit congue, dolor nisi vulputate justo, a auctor turpis lacus et nisl. Vestibulum justo eros, aliquet vestibulum mi vel, gravida aliquam ligula. Vestibulum enim eros, ultricies sit amet scelerisque at, egestas sit amet sapien. Cras a iaculis nunc, id euismod velit. Suspendisse accumsan velit eu turpis laoreet convallis. Duis et vulputate eros. ",
        image = arrayListOf(
            Res.drawable.paxton_three_seater_1,
            Res.drawable.paxton_three_seater_2),
        price = 75000.00,
        offerPrice = 0.0,
        stock = 20,
        category = "Home"
    )
}

private val carts = (1..7).map {
    Cart(
        id = it,
        name = "SteamDeck LED",
        image = Res.drawable.led_steamdeck,
        price = 42000.00,
        offerPrice = 0.0,
        stock = 20,
        quantity = 1,
    )
}

enum class Screen(
    val labelResource: StringResource,
    val selectedIcon: ImageVector?,
    val unselectedIcon: ImageVector?
) {
    ProductList(
        Res.string.shop_tab,
        Icons.Filled.Store,
        Icons.Outlined.Store
    ),
    Wishlist(
        Res.string.wishlist_tab,
        Icons.Filled.Favorite,
        Icons.Outlined.FavoriteBorder
    ),
    Wallet(
        Res.string.wallet_tab,
        Icons.Filled.AccountBalanceWallet,
        Icons.Outlined.AccountBalanceWallet
    ),
    CartList(
        Res.string.cart_tab,
        Icons.Filled.ShoppingCart,
        Icons.Outlined.ShoppingCart
    ),
    Orders(
        Res.string.orders_tab,
        Icons.Filled.DeliveryDining,
        Icons.Outlined.DeliveryDining
    ),
    ProductDetail(
        Res.string.shop_tab,
        null,
        null
    ),
    SettingsList(
        Res.string.settings,
        null,
        null
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    var currentScreen by remember { mutableStateOf(Screen.ProductList) }
    var isDarkMode by remember { mutableStateOf(false) }
    var isNavBarCompact by remember { mutableStateOf(false) }

    val backgroundColor = if (isDarkMode) Color(0xFFBBBBBB) else Color.White
    val toggleTheme = { isDarkMode = !isDarkMode }
    val toggleNavBar = { isNavBarCompact = !isNavBarCompact }
    val coroutineScope = rememberCoroutineScope()

    val navBarScreens = listOf(
        Screen.ProductList,
        Screen.Wishlist,
        Screen.Wallet,
        Screen.CartList,
        Screen.Orders
    )

    MaterialTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)
                .statusBarsPadding()
        ) {
            if (currentScreen == Screen.SettingsList)
                SettingsListScreen(
                    isDarkTheme = isDarkMode,
                    onToggleTheme = toggleTheme,
                    onToggleNavBar = toggleNavBar,
                    isNavBarCompact = isNavBarCompact,
                    onClose = { currentScreen = Screen.ProductList }
                )
            else
                Scaffold(
                    topBar = {
                        // TODO: Implement new AppBar Component
                        CenterAlignedTopAppBar(
                            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                                backgroundColor),
                            title = {
                                Text(
                                    text = when (currentScreen) {
                                        Screen.Wishlist -> stringResource(Res.string.wishlist_tab)
                                        Screen.Wallet -> stringResource(Res.string.wallet_tab)
                                        Screen.CartList -> stringResource(Res.string.cart_tab)
                                        Screen.Orders -> stringResource(Res.string.orders_tab)
                                        else -> stringResource(Res.string.shop_tab)
                                    },
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    style =  MaterialTheme.typography.displaySmall
                                )
                            },
                            navigationIcon = {
                                if (currentScreen == Screen.ProductList || currentScreen == Screen.ProductDetail) {
                                    // TODO: Implement currentScreen navigation and animations
                                    IconButton(
                                        onClick = {
                                            currentScreen =
                                                if (currentScreen == Screen.ProductDetail) Screen.ProductList
                                                else Screen.SettingsList
                                        },
                                        enabled = true,
                                    ) {
                                        Icon(
                                            imageVector = if (currentScreen == Screen.ProductList) Icons.Filled.Settings
                                            else Icons.Filled.ArrowBackIosNew,
                                            contentDescription = if (currentScreen == Screen.ProductList) stringResource(Res.string.settings)
                                            else stringResource(Res.string.shop_tab),
                                        )
                                    }
                                }
                            },
                            actions = {
                                if (currentScreen == Screen.ProductList) {
                                    IconButton(
                                        onClick = { /* doSomething() */ }
                                    ) {
                                        Icon(
                                            imageVector = Icons.Filled.AccountCircle,
                                            contentDescription = stringResource(Res.string.account)
                                        )
                                    }
                                }
                            },
                        )
                    },
                    bottomBar = {
                        // TODO: Implement new Toolbar Component
                        NavigationBar(
                            modifier = Modifier.background(backgroundColor),
                        ) {
                            navBarScreens.forEach { screenEnum ->
                                NavigationBarItem(
                                    selected = currentScreen == screenEnum,
                                    onClick = { currentScreen = screenEnum },
                                    icon = {
                                            Icon(
                                                imageVector = (if (currentScreen == screenEnum) screenEnum.selectedIcon
                                                else screenEnum.unselectedIcon)!!,
                                                contentDescription = stringResource(screenEnum.labelResource),
                                            )
                                    },
                                    label = { if (!isNavBarCompact) Text(stringResource(screenEnum.labelResource)) },
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
                            .background(backgroundColor)
                            .consumeWindowInsets(innerPadding)
                            .padding(innerPadding),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        when (currentScreen) {
                            Screen.ProductList -> {
                                ProductListScreen(
                                    isDarkTheme = isDarkMode,
                                    onAction = { currentScreen = Screen.ProductDetail },
                                    products = products,
                                )
                            }
                            Screen.ProductDetail -> {
                                ProductDetailScreen(
                                    isDarkTheme = isDarkMode,
                                    product = products[0],
                                )
                            }
                            Screen.CartList -> {
                                CartListScreen(
                                    isDarkTheme = isDarkMode,
                                    cart = carts,
                                    onAction = { currentScreen = Screen.ProductDetail }
                                )
                            }
                            Screen.Wishlist -> {
                                Column (
                                    modifier = Modifier.fillMaxSize(),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = "Looks like you got here early!",
                                        style = MaterialTheme.typography.titleLarge
                                    )
                                }
                            }
                            Screen.Wallet -> {
                                Column (
                                    modifier = Modifier.fillMaxSize(),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = "Looks like you got here early!",
                                        style = MaterialTheme.typography.titleLarge
                                    )
                                }
                            }
                            Screen.Orders -> {
                                Column (
                                    modifier = Modifier.fillMaxSize(),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = "Looks like you got here early!",
                                        style = MaterialTheme.typography.titleLarge
                                    )
                                }
                            }
                            else -> Text("Unhandled Screen: ${currentScreen.name}")
                        }
                    }
                }
        }
    }
}