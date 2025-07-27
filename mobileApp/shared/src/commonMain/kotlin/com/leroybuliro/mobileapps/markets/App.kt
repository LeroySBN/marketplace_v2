package com.leroybuliro.mobileapps.markets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DeliveryDining
import androidx.compose.material.icons.filled.Sell
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Store
import androidx.compose.material.icons.outlined.AccountBalanceWallet
import androidx.compose.material.icons.outlined.DeliveryDining
import androidx.compose.material.icons.outlined.Sell
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material.icons.outlined.Store
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.leroybuliro.mobileapps.markets.domain.Cart
import com.leroybuliro.mobileapps.markets.domain.Product
import com.leroybuliro.mobileapps.markets.presentation.cart_list.CartListScreen
import com.leroybuliro.mobileapps.markets.presentation.product_detail.ProductDetailScreen
import com.leroybuliro.mobileapps.markets.presentation.product_list.ProductListScreen
import com.leroybuliro.mobileapps.markets.presentation.sell.SellScreen
import com.leroybuliro.mobileapps.markets.presentation.settings_list.SettingsListScreen
import com.leroybuliro.mobileapps.markets.presentation.theme.DarkColorPalette
import com.leroybuliro.mobileapps.markets.presentation.theme.LightColorPalette
import com.leroybuliro.mobileapps.markets.presentation.wallet.WalletScreen
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import markets.shared.generated.resources.Res
import markets.shared.generated.resources.account
import markets.shared.generated.resources.cart_tab
import markets.shared.generated.resources.led_steamdeck
import markets.shared.generated.resources.orders_tab
import markets.shared.generated.resources.paxton_three_seater_1
import markets.shared.generated.resources.paxton_three_seater_2
import markets.shared.generated.resources.sell_tab
import markets.shared.generated.resources.sell_title
import markets.shared.generated.resources.settings
import markets.shared.generated.resources.shop_tab
import markets.shared.generated.resources.wallet_tab
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

private val products = (1..40).map {
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
        category = "Home",
        subCategory = "Living Room",
        material = "Leather"
    )
}

private val carts = (1..20).map {
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
    Sell(
        Res.string.sell_tab,
        Icons.Filled.Sell,
        Icons.Outlined.Sell
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
    var currentScreen by remember { mutableStateOf(Screen.Sell) }
    var isDarkMode by remember { mutableStateOf(false) }
    var isNavBarCompact by remember { mutableStateOf(false) }
    var isNavBarVisible by remember { mutableStateOf(true) }
    val lazyListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    val backgroundColor = if (isDarkMode) Color(0xFFBBBBBB) else Color.White
    val toggleTheme = { isDarkMode = !isDarkMode }
    val toggleNavBar = { isNavBarCompact = !isNavBarCompact }
//    val isNavBarVisible by remember {
//        derivedStateOf {
//            !lazyListState.isScrollInProgress
//        }
//    }

    val navBarScreens = listOf(
        Screen.ProductList,
        Screen.Sell,
        Screen.Wallet,
        Screen.CartList,
        Screen.Orders
    )

    LaunchedEffect(lazyListState) {
        // Store the previous scroll position.
        // Initialize with a value that ensures the first scroll event is processed correctly.
        // Using 0 for both assumes that any initial scroll will be either "down" from the top
        // or the list is already not at the exact top.
        var previousFirstVisibleItemIndex = lazyListState.firstVisibleItemIndex
        var previousFirstVisibleItemScrollOffset = lazyListState.firstVisibleItemScrollOffset

        snapshotFlow { lazyListState.firstVisibleItemIndex to lazyListState.firstVisibleItemScrollOffset }
            .map { (currentIndex, currentOffset) ->
                // Determine scroll direction
                val scrolledDown: Boolean? = if (currentIndex > previousFirstVisibleItemIndex) {
                    true  // Content scrolled DOWN (user's finger moved UP)
                } else if (currentIndex < previousFirstVisibleItemIndex) {
                    false // Content scrolled UP (user's finger moved DOWN)
                } else {
                    // Same item, check offset for more granular direction
                    if (currentOffset > previousFirstVisibleItemScrollOffset) {
                        true // Content scrolled DOWN within the same item
                    } else if (currentOffset < previousFirstVisibleItemScrollOffset) {
                        false // Content scrolled UP within the same item
                    } else {
                        null // No significant scroll, or change is too small to determine direction
                    }
                }

                // Update previous positions *before* deciding visibility for the next emission.
                // This makes the logic simpler as we compare the *current* scroll to the *previous* one.
                previousFirstVisibleItemIndex = currentIndex
                previousFirstVisibleItemScrollOffset = currentOffset

                // Visibility logic based on direction:
                when (scrolledDown) {
                    true -> false // Scrolled DOWN, so HIDE NavBar
                    false -> true  // Scrolled UP, so SHOW NavBar
                    null -> isNavBarVisible // No change in determined direction, keep current visibility
                }
            }
            .distinctUntilChanged() // Only update if the visibility decision actually changes
            .collect { shouldBeVisible ->
                isNavBarVisible = shouldBeVisible
            }
    }

    MaterialTheme (
        colorScheme = if (isDarkMode) DarkColorPalette else LightColorPalette
    ) {
        Surface (
            modifier = Modifier.fillMaxSize(),
        ) {
            Scaffold (
                topBar = {
                    // TODO: Implement new AppBar Component
                    CenterAlignedTopAppBar(
                        title = {
                            Text (
                                text = when (currentScreen) {
                                    Screen.Sell -> stringResource(Res.string.sell_title)
                                    Screen.Wallet -> stringResource(Res.string.wallet_tab)
                                    Screen.CartList -> stringResource(Res.string.cart_tab)
                                    Screen.Orders -> stringResource(Res.string.orders_tab)
                                    Screen.SettingsList -> stringResource(Res.string.settings)
                                    else -> stringResource(Res.string.shop_tab)
                                },
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                style =  MaterialTheme.typography.titleLarge
                            )
                        },
                        navigationIcon = {
                            if (currentScreen in listOf(Screen.ProductList, Screen.ProductDetail, Screen.SettingsList)) {
                                // TODO: Implement currentScreen navigation and animations
                                IconButton (
                                    onClick = {
                                        currentScreen =
                                            if (currentScreen != Screen.ProductList) Screen.ProductList
                                            else Screen.SettingsList
                                    },
                                    enabled = true,
                                ) {
                                    Icon (
                                        imageVector = when (currentScreen) {
                                            Screen.ProductList -> Icons.Filled.Settings
                                            Screen.SettingsList -> Icons.Filled.Close
                                            else -> Icons.Filled.ArrowBackIosNew
                                        },
                                        contentDescription = if (currentScreen == Screen.ProductList) stringResource(Res.string.settings)
                                        else stringResource(Res.string.shop_tab),
                                    )
                                }
                            }
                        },
                        actions = {
                            if (currentScreen == Screen.ProductList) {
                                IconButton (
                                    onClick = { /* doSomething() */ }
                                ) {
                                    Icon (
                                        imageVector = Icons.Filled.AccountCircle,
                                        contentDescription = stringResource(Res.string.account)
                                    )
                                }
                            }
                        },
                        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                            containerColor = MaterialTheme.colorScheme.background,
                            navigationIconContentColor = MaterialTheme.colorScheme.onBackground,
                            titleContentColor = MaterialTheme.colorScheme.onBackground,
                            actionIconContentColor = MaterialTheme.colorScheme.onBackground,
                        ),
                        scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
                    )
                },
                bottomBar = {
                    // TODO: Implement new Toolbar Component
                    AnimatedVisibility (
                        visible = when (currentScreen) {
                            Screen.ProductList, Screen.Sell, Screen.Wallet -> true
                            Screen.ProductDetail, Screen.SettingsList -> false
                            else -> isNavBarVisible
                        },
                        enter = slideInVertically(
                            animationSpec = tween(500),
                            initialOffsetY = { it }),
                        exit = slideOutVertically(
                            animationSpec = tween(250),
                            targetOffsetY = { it }),
                    ) {
                        NavigationBar (
                            containerColor = MaterialTheme.colorScheme.surfaceVariant,
                            contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            tonalElevation = 16.dp,
                            windowInsets = WindowInsets(0.dp, 0.dp, 0.dp, 0.dp)
                        ) {
                            navBarScreens.forEach { screenEnum ->
                                NavigationBarItem (
                                    selected = currentScreen == screenEnum,
                                    onClick = { currentScreen = screenEnum },
                                    icon = {
                                        Icon (
                                            imageVector = (if (currentScreen == screenEnum) screenEnum.selectedIcon
                                            else screenEnum.unselectedIcon)!!,
                                            contentDescription = stringResource(screenEnum.labelResource),
                                        )
                                    },
                                    label = {
                                        if (!isNavBarCompact)
                                            Text (
                                                text = stringResource(screenEnum.labelResource),
                                            )
                                    },
                                    colors = NavigationBarItemDefaults.colors(
                                        indicatorColor = MaterialTheme.colorScheme.surfaceVariant,
                                        selectedIconColor = MaterialTheme.colorScheme.onBackground,
                                        selectedTextColor = MaterialTheme.colorScheme.onBackground
                                    ),
                                )
                            }
                        }
                    }
                },
                snackbarHost = {},
                contentWindowInsets = ScaffoldDefaults.contentWindowInsets,
            ) { innerPadding ->
                Column (
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.surface)
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
                                state = lazyListState,
                                isDarkTheme = isDarkMode,
                                product = products[0],
                            )
                        }

                        Screen.CartList -> {
                            CartListScreen(
                                state = lazyListState,
                                isDarkTheme = isDarkMode,
                                cart = carts,
                                onAction = { currentScreen = Screen.ProductDetail }
                            )
                        }

                        Screen.Sell -> {
                            val sellViewModel =
                                remember { com.leroybuliro.mobileapps.markets.presentation.sell.SellViewModel() }
                            Column {
                                SellScreen(
                                    onPostProduct = { product ->
                                        sellViewModel.onAction(
                                            com.leroybuliro.mobileapps.markets.presentation.sell.SellAction.PostProduct(
                                                product
                                            )
                                        )
                                    },
                                    isDarkTheme = isDarkMode,
                                )
//                                sellViewModel.state.lastPosted?.let { product ->
//                                    Spacer(modifier = Modifier.height(16.dp))
//                                }
                                sellViewModel.state.lastPosted?.let { product ->
                                    Spacer(modifier = Modifier.height(16.dp))
                                    Text("Last posted: ${product.name} (ID: ${product.id})")
                                }
                            }
                        }

                        Screen.Wallet -> {
                            val walletViewModel =
                                remember { com.leroybuliro.mobileapps.markets.presentation.wallet.WalletViewModel() }
                            WalletScreen(
                                balance = walletViewModel.state.balance,
                                onAddFunds = { amount ->
                                    walletViewModel.onAction(
                                        com.leroybuliro.mobileapps.markets.presentation.wallet.WalletAction.AddFunds(
                                            amount
                                        )
                                    )
                                }
                            )
                        }

                        Screen.Orders -> {
                            val orderViewModel =
                                remember { com.leroybuliro.mobileapps.markets.presentation.order.OrderViewModel() }
                            // Simulate loading orders for demo
                            LaunchedEffect(Unit) {
                                orderViewModel.onAction(
                                    com.leroybuliro.mobileapps.markets.presentation.order.OrderAction.LoadOrders(
                                        "demoUser"
                                    )
                                )
                            }
                            com.leroybuliro.mobileapps.markets.presentation.order.OrderScreen(orders = orderViewModel.state.orders)
                        }

                        Screen.SettingsList -> {
                            SettingsListScreen(
                                isDarkTheme = isDarkMode,
                                onToggleTheme = toggleTheme,
                                onToggleNavBar = toggleNavBar,
                                isNavBarCompact = isNavBarCompact,
                            )
                        }
                    }
                }
            }
        }
    }
}