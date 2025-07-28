package com.leroybuliro.mobileapps.markets.presentation.settings_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ToggleOff
import androidx.compose.material.icons.filled.ToggleOn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults.iconToggleButtonColors
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.leroybuliro.mobileapps.markets.presentation.theme.DarkColorPalette
import com.leroybuliro.mobileapps.markets.presentation.theme.LightColorPalette
import markets.shared.generated.resources.Res
import markets.shared.generated.resources.app_build
import markets.shared.generated.resources.app_creator_credits
import markets.shared.generated.resources.app_title
import markets.shared.generated.resources.app_version
import markets.shared.generated.resources.close
import markets.shared.generated.resources.navbar_compact
import markets.shared.generated.resources.navbar_normal
import markets.shared.generated.resources.settings
import markets.shared.generated.resources.settings_language
import markets.shared.generated.resources.settings_navbar
import markets.shared.generated.resources.settings_section_apps
import markets.shared.generated.resources.settings_section_feedback
import markets.shared.generated.resources.settings_section_rating
import markets.shared.generated.resources.settings_section_website
import markets.shared.generated.resources.settings_theme
import markets.shared.generated.resources.settings_title_app
import markets.shared.generated.resources.settings_title_general
import markets.shared.generated.resources.theme_off
import markets.shared.generated.resources.theme_on
import org.jetbrains.compose.resources.stringResource

@Composable
fun SettingsListScreen(
    modifier: Modifier = Modifier,
    isDarkTheme: Boolean,
    isNavBarCompact: Boolean,
    onToggleTheme: () -> Unit,
    onToggleNavBar: () -> Unit,
//    onAction: (SettingsListAction) -> Unit,
){
    var verticalScrollState = rememberScrollState()

    MaterialTheme (
        colorScheme = if (isDarkTheme) DarkColorPalette else LightColorPalette
    ) {
        Column(
            modifier = modifier
                .verticalScroll(verticalScrollState)
                .background(color = MaterialTheme.colorScheme.surface)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(32.dp),
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text(
                    text = stringResource(Res.string.settings_title_app),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.inversePrimary
                )
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = MaterialTheme.colorScheme.onSurface,
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(0.dp),
                    ) {

                        // LANGUAGE
                        Row(
                            modifier = modifier
                                .padding(8.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = stringResource(Res.string.settings_language),
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.primary
                            )
                            IconToggleButton(
                                checked = isDarkTheme,
                                onCheckedChange = { onToggleTheme },
                                colors = iconToggleButtonColors(
                                    containerColor = MaterialTheme.colorScheme.onSurface,
                                    contentColor = MaterialTheme.colorScheme.primary,
                                    checkedContainerColor = MaterialTheme.colorScheme.onSurface,
                                    checkedContentColor = MaterialTheme.colorScheme.primary,
                                )
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.ChevronRight,
                                    contentDescription = stringResource(Res.string.settings_language),
                                )
                            }
                        }

                        // DARK MODE
                        Row(
                            modifier = modifier
                                .padding(8.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = stringResource(Res.string.settings_theme),
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.primary
                            )
                            IconToggleButton(
                                checked = isDarkTheme,
                                onCheckedChange = { onToggleTheme() },
                                colors = iconToggleButtonColors(
                                    containerColor = MaterialTheme.colorScheme.onSurface,
                                    contentColor = MaterialTheme.colorScheme.primary,
                                    checkedContainerColor = MaterialTheme.colorScheme.onSurface,
                                    checkedContentColor = MaterialTheme.colorScheme.primary,
                                )
                            ) {
                                Icon(
                                    imageVector = if (isDarkTheme) Icons.Filled.ToggleOn
                                    else Icons.Filled.ToggleOff,
                                    contentDescription = if (isDarkTheme) stringResource(
                                        Res.string.theme_off
                                    )
                                    else stringResource(Res.string.theme_on),
                                )
                            }
                        }

                        // NAVBAR
                        Row(
                            modifier = modifier
                                .padding(8.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = stringResource(Res.string.settings_navbar),
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.primary
                            )
                            IconToggleButton(
                                checked = isNavBarCompact,
                                onCheckedChange = { onToggleNavBar() },
                                colors = iconToggleButtonColors(
                                    containerColor = MaterialTheme.colorScheme.onSurface,
                                    contentColor = MaterialTheme.colorScheme.primary,
                                    checkedContainerColor = MaterialTheme.colorScheme.onSurface,
                                    checkedContentColor = MaterialTheme.colorScheme.primary,
                                )
                            ) {
                                Icon(
                                    imageVector = if (isNavBarCompact) Icons.Filled.ToggleOn
                                    else Icons.Filled.ToggleOff,
                                    contentDescription = if (isNavBarCompact) stringResource(
                                        Res.string.navbar_normal
                                    )
                                    else stringResource(Res.string.navbar_compact),
                                )
                            }
                        }
                    }
                }
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text(
                    text = stringResource(Res.string.settings_title_general),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.inversePrimary
                )
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = MaterialTheme.colorScheme.onSurface
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(0.dp),
                    ) {
                        Row(
                            modifier = modifier
                                .padding(8.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = stringResource(Res.string.settings_section_website),
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.primary
                            )
                            IconToggleButton(
                                checked = isDarkTheme,
                                onCheckedChange = {},
                                colors = iconToggleButtonColors(
                                    containerColor = MaterialTheme.colorScheme.onSurface,
                                    contentColor = MaterialTheme.colorScheme.primary,
                                    checkedContainerColor = MaterialTheme.colorScheme.onSurface,
                                    checkedContentColor = MaterialTheme.colorScheme.primary,
                                )
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.ChevronRight,
                                    contentDescription = stringResource(Res.string.settings_section_website),
                                )
                            }
                        }
                        Row(
                            modifier = modifier
                                .padding(8.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = stringResource(Res.string.settings_section_rating),
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.primary
                            )
                            IconToggleButton(
                                checked = isDarkTheme,
                                onCheckedChange = {},
                                colors = iconToggleButtonColors(
                                    containerColor = MaterialTheme.colorScheme.onSurface,
                                    contentColor = MaterialTheme.colorScheme.primary,
                                    checkedContainerColor = MaterialTheme.colorScheme.onSurface,
                                    checkedContentColor = MaterialTheme.colorScheme.primary,
                                )
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.ChevronRight,
                                    contentDescription = stringResource(Res.string.settings_section_rating),
                                )
                            }
                        }
                        Row(
                            modifier = modifier
                                .padding(8.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = stringResource(Res.string.settings_section_apps),
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.primary
                            )
                            IconToggleButton(
                                checked = isDarkTheme,
                                onCheckedChange = {},
                                colors = iconToggleButtonColors(
                                    containerColor = MaterialTheme.colorScheme.onSurface,
                                    contentColor = MaterialTheme.colorScheme.primary,
                                    checkedContainerColor = MaterialTheme.colorScheme.onSurface,
                                    checkedContentColor = MaterialTheme.colorScheme.primary,
                                )
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.ChevronRight,
                                    contentDescription = stringResource(Res.string.settings_section_apps),
                                )
                            }
                        }
                        Row(
                            modifier = modifier
                                .padding(8.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = stringResource(Res.string.settings_section_feedback),
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.primary
                            )
                            IconToggleButton(
                                checked = isDarkTheme,
                                onCheckedChange = {},
                                colors = iconToggleButtonColors(
                                    containerColor = MaterialTheme.colorScheme.onSurface,
                                    contentColor = MaterialTheme.colorScheme.primary,
                                    checkedContainerColor = MaterialTheme.colorScheme.onSurface,
                                    checkedContentColor = MaterialTheme.colorScheme.primary,
                                )
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.ChevronRight,
                                    contentDescription = stringResource(Res.string.settings_section_feedback),
                                )
                            }
                        }
                    }
                }
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(0.dp),
            ) {
                Text(
                    text = stringResource(Res.string.app_title) + " " +
                            stringResource(Res.string.app_version) + " (" +
                            stringResource(Res.string.app_build) + ")",
                    modifier = modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = stringResource(Res.string.app_creator_credits),
                    modifier = modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}