package com.leroybuliro.mobileapps.markets.presentation.sell

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Upload
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.leroybuliro.mobileapps.markets.presentation.sell.components.NumericInputField
import com.leroybuliro.mobileapps.markets.presentation.sell.components.PlatformImagePicker
import com.leroybuliro.mobileapps.markets.presentation.sell.components.PlatformImagePreview
import com.leroybuliro.mobileapps.markets.presentation.sell.components.SellDropdownField
import com.leroybuliro.mobileapps.markets.presentation.sell.components.TextInputField
import com.leroybuliro.mobileapps.markets.presentation.theme.DarkColorPalette
import com.leroybuliro.mobileapps.markets.presentation.theme.LightColorPalette
import markets.shared.generated.resources.Res
import markets.shared.generated.resources.sell_category
import markets.shared.generated.resources.sell_describe
import markets.shared.generated.resources.sell_header
import markets.shared.generated.resources.sell_material
import markets.shared.generated.resources.sell_photo_tip_1
import markets.shared.generated.resources.sell_photo_tip_2
import markets.shared.generated.resources.sell_price
import markets.shared.generated.resources.sell_stock
import markets.shared.generated.resources.sell_sub_category
import markets.shared.generated.resources.sell_upload
import org.jetbrains.compose.resources.stringResource

@Composable
fun SellScreen(
    onPostProduct: (com.leroybuliro.mobileapps.markets.domain.Product) -> Unit,
    isDarkTheme: Boolean
) {
    val carouselImageSize: Dp = 80.dp
    val (id, setId) = remember { mutableStateOf("") }
    val (name, setName) = remember { mutableStateOf("") }
    val (desc, setDesc) = remember { mutableStateOf("") }
    val (imageUris, setImageUris) = remember { mutableStateOf(listOf<String>()) }
    val (price, setPrice) = remember { mutableStateOf("") }
    val (offerPrice, setOfferPrice) = remember { mutableStateOf("") }
    val (stock, setStock) = remember { mutableStateOf("") }
    val (category, setCategoryRaw) = remember { mutableStateOf("") }
    val (subCategory, setSubCategory) = remember { mutableStateOf("") }
    val (material, setMaterial) = remember { mutableStateOf("") }
    val verticalScrollState = rememberScrollState()
    val carouselScrollState = rememberLazyListState()
    var validationError by remember { mutableStateOf<String?>(null) }

    // Wrap setCategory to auto-reset subCategory
    val setCategory: (String) -> Unit = { newCat ->
        setCategoryRaw(newCat)
        setSubCategory("")
    }

    val categoryOptions = categorySubcategoryList.map { it.name }
    val selectedCategoryObj = categorySubcategoryList.find { it.name == category }
    val subCategoryOptions = selectedCategoryObj?.subcategories ?: emptyList()
    val materialOptions = listOf(
        "Suede",
        "Leather",
        "Canvas",
        "Plastic",
        "Aluminium",
        "Other")

    MaterialTheme(colorScheme = if (isDarkTheme) DarkColorPalette else LightColorPalette) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = MaterialTheme.colorScheme.surface,
            floatingActionButton = {
                ExtendedFloatingActionButton(
                    onClick = {
                        val validId = id.toIntOrNull()
                        val validPrice = price.toDoubleOrNull()
                        val validOfferPrice = offerPrice.toDoubleOrNull()
                        val validStock = stock.toIntOrNull()
                        when {
                            name.isBlank() -> validationError = "Product name is required."
                            desc.isBlank() -> validationError = "Description is required."
                            imageUris.isEmpty() -> validationError = "At least one image is required."
                            category.isBlank() -> validationError = "Category is required."
                            subCategory.isBlank() -> validationError = "Subcategory is required."
                            material.isBlank() -> validationError = "Material is required."
                            validPrice == null || validPrice <= 0.0 -> validationError = "Price must be a positive number."
                            validStock == null || validStock < 0 -> validationError = "Stock must be 0 or more."
                            else -> {
                                validationError = null
                                val images = ArrayList<org.jetbrains.compose.resources.DrawableResource>()
                                val product = com.leroybuliro.mobileapps.markets.domain.Product(
                                    id = validId,
                                    image = images, // Simulated
                                    name = name,
                                    description = desc,
                                    category = category,
                                    subCategory = subCategory,
                                    material = material,
                                    price = validPrice,
                                    offerPrice = validOfferPrice,
                                    stock = validStock
                                )
                                onPostProduct(product)
                                setId("")
                                setName("")
                                setDesc("")
                                setImageUris(emptyList())
                                setPrice("")
                                setOfferPrice("")
                                setStock("")
                                setCategory("")
                                setMaterial("")
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .height(48.dp)
                        .background(
                            color = MaterialTheme.colorScheme.primaryContainer,
                            shape = RoundedCornerShape(8.dp)
                        ),
                    text = {
                        Text(
                            text = stringResource(Res.string.sell_upload)
                        )
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.Upload,
                            contentDescription = stringResource(Res.string.sell_upload)
                        )
                    },
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    expanded = true
                )
            },
            floatingActionButtonPosition = FabPosition.Center
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(verticalScrollState)
                    .padding(start = 0.dp, top = 0.dp, end = 0.dp, bottom = 100.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
// Image carousel
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp)
                        .background(MaterialTheme.colorScheme.tertiaryContainer),
                    contentAlignment = Alignment.Center,
                ) {
                    Column(
                        modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                            .matchParentSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = stringResource(Res.string.sell_photo_tip_1),
                            color = MaterialTheme.colorScheme.inversePrimary,
                            style = MaterialTheme.typography.bodySmall
                        )
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceAround
                        ) {
                            LazyRow(
                                state = carouselScrollState,
                                modifier = Modifier.height(carouselImageSize),
                                horizontalArrangement = Arrangement.spacedBy(16.dp),
                                verticalAlignment = Alignment.CenterVertically

                            ) {
                                itemsIndexed(imageUris) { index, uri ->
                                    Box(modifier = Modifier.size(carouselImageSize)) {
                                        // Placeholder for image preview (replace with real image loader if available)
                                        Box(
                                            modifier = Modifier
                                                .size(carouselImageSize)
                                                .clip(RoundedCornerShape(8.dp))
                                                .background(MaterialTheme.colorScheme.surfaceVariant)
                                                .border(
                                                    width = 1.dp,
                                                    color = MaterialTheme.colorScheme.outline,
                                                    shape = RoundedCornerShape(8.dp)),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            PlatformImagePreview(
                                                uri = uri,
                                                modifier = Modifier
                                            )
                                            IconButton(
                                                onClick = { setImageUris(imageUris.filterIndexed { i, _ -> i != index }) },
                                                modifier = Modifier
                                                    .align(Alignment.TopEnd)
                                                    .size(24.dp)
                                                    .background(
                                                        color = MaterialTheme.colorScheme.background,
                                                        shape = CircleShape
                                                    )
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Default.Close,
                                                    contentDescription = "Remove",
                                                    tint = MaterialTheme.colorScheme.error,
                                                    modifier = Modifier.size(16.dp)
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                            PlatformImagePicker(
                                onImagesPicked = { newUris -> setImageUris((imageUris + newUris).take(7)) },
                                maxImages = 7,
                                imageUris = imageUris.size < 7
                            )
                        }
                        Text(
                            text = stringResource(Res.string.sell_photo_tip_2),
                            color = MaterialTheme.colorScheme.inversePrimary,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
                Spacer(
                    modifier = Modifier
                        .height(8.dp)
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.surface)
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 0.dp)
                        .background(
                            color = MaterialTheme.colorScheme.surface,
                            shape = RoundedCornerShape(16.dp)
                        )
                ) {
// Name and Description text fields
                    Column(
                        modifier = Modifier
                            .background(
                                color = MaterialTheme.colorScheme.surfaceContainer,
                                shape = RoundedCornerShape(16.dp)
                            )
                            .padding(horizontal = 16.dp, vertical = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
// Name text field
                        TextInputField(
                            label = stringResource(Res.string.sell_header),
                            value = name,
                            onValueChange = setName,
                            minLines = 1,
                            maxLines = 1,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    color = MaterialTheme.colorScheme.background,
                                    shape = RoundedCornerShape(8.dp)
                                ),
                        )
                        // Description text field
                        TextInputField(
                            label = stringResource(Res.string.sell_describe),
                            value = desc,
                            onValueChange = setDesc,
                            minLines = 6,
                            maxLines = 10,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    color = MaterialTheme.colorScheme.background,
                                    shape = RoundedCornerShape(8.dp)
                                ),
                        )
                    }
                    Spacer(
                        modifier = Modifier
                            .height(8.dp)
                            .background(MaterialTheme.colorScheme.surface)
                    )
// Category and Material dropdowns in column
                    Column(
                        modifier = Modifier
                            .background(
                                color = MaterialTheme.colorScheme.surfaceContainer,
                                shape = RoundedCornerShape(16.dp)
                            )
                            .padding(horizontal = 16.dp, vertical = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        // Category
                        SellDropdownField(
                            label = stringResource(Res.string.sell_category),
                            options = categoryOptions,
                            selected = category,
                            onSelected = setCategory,
                            modifier = Modifier.fillMaxWidth(),
                            enabled = true
                        )
                        HorizontalDivider(
                            Modifier.fillMaxWidth(),
                            1.dp,
                            MaterialTheme.colorScheme.outline
                        )
                        // Sub-Category
                        SellDropdownField(
                            label = stringResource(Res.string.sell_sub_category),
                            options = subCategoryOptions,
                            selected = subCategory,
                            onSelected = setSubCategory,
                            modifier = Modifier.fillMaxWidth(),
                            enabled = category.isNotBlank()
                        )
                        HorizontalDivider(
                            modifier = Modifier.fillMaxWidth(),
                            thickness = 1.dp,
                            color = MaterialTheme.colorScheme.outline
                        )
                        // Material
                        SellDropdownField(
                            label = stringResource(Res.string.sell_material),
                            options = materialOptions,
                            selected = material,
                            onSelected = setMaterial,
                            modifier = Modifier.fillMaxWidth(),
                            enabled = true
                        )
                    }
                    Spacer(
                        modifier = Modifier
                            .height(8.dp)
                            .background(MaterialTheme.colorScheme.surface)
                    )
// Price and Stock numeric fields with inc/dec icons
                    Column(
                        modifier = Modifier
                            .background(
                                color = MaterialTheme.colorScheme.surfaceContainer,
                                shape = RoundedCornerShape(16.dp)
                            )
                            .padding(horizontal = 16.dp, vertical = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        // Price
                        NumericInputField(
                            label = stringResource(Res.string.sell_price),
                            value = price,
                            onValueChange = setPrice,
                            onIncrement = {
                                val current = price.toIntOrNull() ?: 0
                                setPrice((current + 1).toString())
                            },
                            onDecrement = {
                                val current = price.toIntOrNull() ?: 0
                                if (current > 0) setPrice((current - 1).toString())
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                        // Stock
                        NumericInputField(
                            label = stringResource(Res.string.sell_stock),
                            value = stock,
                            onValueChange = setStock,
                            onIncrement = {
                                val current = stock.toIntOrNull() ?: 0
                                setStock((current + 1).toString())
                            },
                            onDecrement = {
                                val current = stock.toIntOrNull() ?: 0
                                if (current > 0) setStock((current - 1).toString())
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}