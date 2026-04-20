package com.htmllearn.app.ui.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import com.htmllearn.app.ui.navigation.Screen
import com.htmllearn.app.ui.theme.*

data class NavItem(val screen: Screen, val label: String, val icon: ImageVector)

val navItems = listOf(
    NavItem(Screen.Dashboard, "হোম",      Icons.Filled.Home),
    NavItem(Screen.Chapters,  "পাঠ",      Icons.Filled.MenuBook),
    NavItem(Screen.Editor,    "এডিটর",   Icons.Filled.Code),
    NavItem(Screen.Reference, "রেফারেন্স",Icons.Filled.LibraryBooks),
    NavItem(Screen.Profile,   "প্রোফাইল", Icons.Filled.Person),
)

@Composable
fun BottomNavBar(currentRoute: String, onItemClick: (String) -> Unit) {
    NavigationBar(containerColor = BgSurface, tonalElevation = 0.dp) {
        navItems.forEach { item ->
            val selected = currentRoute == item.screen.route
            NavigationBarItem(
                selected = selected,
                onClick  = { onItemClick(item.screen.route) },
                icon     = { Icon(item.icon, contentDescription = item.label) },
                label    = { Text(item.label, style = MaterialTheme.typography.labelSmall) },
                colors   = NavigationBarItemDefaults.colors(
                    selectedIconColor   = Primary,
                    selectedTextColor   = Primary,
                    unselectedIconColor = TextHint,
                    unselectedTextColor = TextHint,
                    indicatorColor      = Primary.copy(alpha = 0.15f)
                )
            )
        }
    }
}
