package com.htmllearn.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.htmllearn.app.ui.navigation.Screen
import com.htmllearn.app.ui.theme.*

data class NavItem(val screen: Screen, val label: String, val icon: ImageVector)

val navItems = listOf(
    NavItem(Screen.Dashboard, "হোম",      Icons.Filled.Home),
    NavItem(Screen.Chapters,  "পাঠ",      Icons.Filled.MenuBook),
    NavItem(Screen.Editor,    "এডিটর",   Icons.Filled.Code),
    NavItem(Screen.Reference, "Reference", Icons.Filled.LibraryBooks),
    NavItem(Screen.Profile,   "প্রোফাইল", Icons.Filled.Person),
)

@Composable
fun BottomNavBar(currentRoute: String, onItemClick: (String) -> Unit) {
    Surface(color = BgSurface, shadowElevation = 8.dp) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(horizontal = 4.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            navItems.forEach { item ->
                val selected = currentRoute == item.screen.route
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(12.dp))
                        .clickable { onItemClick(item.screen.route) },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    // Icon with background when selected
                    Box(
                        modifier = Modifier
                            .size(if (selected) 40.dp else 32.dp)
                            .background(
                                if (selected) Primary.copy(alpha = 0.18f) else Color.Transparent,
                                RoundedCornerShape(10.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.label,
                            tint = if (selected) Primary else TextHint,
                            modifier = Modifier.size(22.dp)
                        )
                    }
                    Spacer(Modifier.height(3.dp))
                    Text(
                        text  = item.label,
                        style = MaterialTheme.typography.labelSmall,
                        color = if (selected) Primary else TextHint
                    )
                    // Dot indicator at bottom
                    if (selected) {
                        Spacer(Modifier.height(2.dp))
                        Box(
                            Modifier
                                .size(4.dp)
                                .background(Primary, CircleShape)
                        )
                    } else {
                        Spacer(Modifier.height(6.dp))
                    }
                }
            }
        }
    }
}
