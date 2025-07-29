package com.leroybuliro.mobileapps.markets.presentation.settings_list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.ToggleOff
import androidx.compose.material.icons.filled.ToggleOn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults.iconButtonColors
import androidx.compose.material3.IconButtonDefaults.iconToggleButtonColors
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
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
import markets.shared.generated.resources.navbar_compact
import markets.shared.generated.resources.navbar_normal
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
    val verticalScrollState = rememberScrollState()

    MaterialTheme (
        colorScheme = if (isDarkTheme) DarkColorPalette else LightColorPalette
    ) {
        Column(
            modifier = modifier
                .verticalScroll(verticalScrollState)
                .background(color = MaterialTheme.colorScheme.surface)
                .padding(horizontal = 0.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = stringResource(Res.string.settings_title_app),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = modifier.padding(start = 8.dp)
            )
            Column(
                modifier = modifier.background(
                    color = MaterialTheme.colorScheme.surfaceContainer,
                )
            ) {
// LANGUAGE
                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(Res.string.settings_language),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = modifier.padding(start = 16.dp)
                    )
                    IconButton(
                        onClick = { onToggleTheme },
                        colors = iconButtonColors(
                            containerColor = MaterialTheme.colorScheme.surfaceContainer,
                            contentColor = MaterialTheme.colorScheme.primary,
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ChevronRight,
                            contentDescription = stringResource(Res.string.settings_language),
                        )
                    }
                }
                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.outline
                )
// DARK MODE
                Row(
                    modifier = modifier
                        .clickable { onToggleTheme() }
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(Res.string.settings_theme),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = modifier.padding(start = 16.dp)
                    )
                    IconButton(
                        onClick = { onToggleTheme() },
                        colors = iconButtonColors(
                            containerColor = MaterialTheme.colorScheme.surfaceContainer,
                            contentColor =
                                if (isDarkTheme) MaterialTheme.colorScheme.primary
                                else MaterialTheme.colorScheme.inversePrimary,
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
                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.outline
                )
// NAVBAR
                Row(
                    modifier = modifier
                        .clickable { onToggleNavBar() }
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(Res.string.settings_navbar),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = modifier.padding(start = 16.dp)
                    )
                    IconButton(
                        onClick = { onToggleNavBar() },
                        colors = iconButtonColors(
                            containerColor = MaterialTheme.colorScheme.surfaceContainer,
                            contentColor =
                                if (isNavBarCompact) MaterialTheme.colorScheme.primary
                                else MaterialTheme.colorScheme.inversePrimary,
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
            Text(
                text = stringResource(Res.string.settings_title_general),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = modifier.padding(start = 8.dp)
            )
            Column(
                modifier = modifier.background(
                    color = MaterialTheme.colorScheme.surfaceContainer,
                )
            ) {
                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(Res.string.settings_section_website),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = modifier.padding(start = 16.dp)
                    )
                    IconToggleButton(
                        checked = isDarkTheme,
                        onCheckedChange = {},
                        colors = iconToggleButtonColors(
                            containerColor = MaterialTheme.colorScheme.surfaceContainer,
                            contentColor = MaterialTheme.colorScheme.primary,
                            checkedContainerColor = MaterialTheme.colorScheme.surfaceContainer,
                            checkedContentColor = MaterialTheme.colorScheme.primary,
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ChevronRight,
                            contentDescription = stringResource(Res.string.settings_section_website),
                        )
                    }
                }
                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.outline
                )
                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(Res.string.settings_section_rating),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = modifier.padding(start = 16.dp)
                    )
                    IconToggleButton(
                        checked = isDarkTheme,
                        onCheckedChange = {},
                        colors = iconToggleButtonColors(
                            containerColor = MaterialTheme.colorScheme.surfaceContainer,
                            contentColor = MaterialTheme.colorScheme.primary,
                            checkedContainerColor = MaterialTheme.colorScheme.surfaceContainer,
                            checkedContentColor = MaterialTheme.colorScheme.primary,
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ChevronRight,
                            contentDescription = stringResource(Res.string.settings_section_rating),
                        )
                    }
                }
                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.outline
                )
                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(Res.string.settings_section_apps),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = modifier.padding(start = 16.dp)
                    )
                    IconToggleButton(
                        checked = isDarkTheme,
                        onCheckedChange = {},
                        colors = iconToggleButtonColors(
                            containerColor = MaterialTheme.colorScheme.surfaceContainer,
                            contentColor = MaterialTheme.colorScheme.primary,
                            checkedContainerColor = MaterialTheme.colorScheme.surfaceContainer,
                            checkedContentColor = MaterialTheme.colorScheme.primary,
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ChevronRight,
                            contentDescription = stringResource(Res.string.settings_section_apps),
                        )
                    }
                }
                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.outline
                )
                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(Res.string.settings_section_feedback),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = modifier.padding(start = 16.dp)
                    )
                    IconToggleButton(
                        checked = isDarkTheme,
                        onCheckedChange = {},
                        colors = iconToggleButtonColors(
                            containerColor = MaterialTheme.colorScheme.surfaceContainer,
                            contentColor = MaterialTheme.colorScheme.primary,
                            checkedContainerColor = MaterialTheme.colorScheme.surfaceContainer,
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
            Spacer(
                modifier = modifier.height(32.dp)
            )
// Credits
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