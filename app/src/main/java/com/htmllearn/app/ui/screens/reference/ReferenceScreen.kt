package com.htmllearn.app.ui.screens.reference

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.htmllearn.app.ui.AppViewModel
import com.htmllearn.app.ui.theme.*

private val categories = listOf("সব","Document","Text","Link","Media","List","Table","Form","Semantic","Layout","Advanced")
private val categoryColors = mapOf(
    "Document" to Color(0xFF6C63FF),"Text" to Color(0xFF00BCD4),"Link" to Color(0xFF4CAF50),
    "Media" to Color(0xFFFF9800),"List" to Color(0xFFE91E63),"Table" to Color(0xFF9C27B0),
    "Form" to Color(0xFF009688),"Semantic" to Color(0xFFFF6B6B),"Layout" to Color(0xFFFFD700),
    "Advanced" to Color(0xFF795548)
)

@Composable
fun ReferenceScreen(vm: AppViewModel = viewModel()) {
    val allTags = vm.tags
    var search by remember { mutableStateOf("") }
    var selectedCat by remember { mutableStateOf("সব") }
    var expanded by remember { mutableStateOf<String?>(null) }

    val filtered = allTags.filter { tag ->
        val matchSearch = search.isEmpty() || tag.tag.contains(search, true) || tag.description.contains(search, true)
        val matchCat = selectedCat == "সব" || tag.category == selectedCat
        matchSearch && matchCat
    }

    Column(Modifier.fillMaxSize().background(BgDark)) {
        Surface(color = BgSurface, tonalElevation = 2.dp) {
            Column(Modifier.fillMaxWidth().padding(16.dp, 48.dp, 16.dp, 8.dp)) {
                Text("HTML Tag Reference", style = MaterialTheme.typography.headlineMedium)
                Text("${allTags.size}টি tag এর বাংলা ব্যাখ্যা", style = MaterialTheme.typography.bodySmall, color = TextSecondary)
                Spacer(Modifier.height(12.dp))
                // Search
                OutlinedTextField(
                    value = search, onValueChange = { search = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("Tag খুঁজুন...") },
                    leadingIcon = { Icon(Icons.Filled.Search, null, tint = TextHint) },
                    trailingIcon = { if (search.isNotEmpty()) IconButton({ search = "" }) { Icon(Icons.Filled.Clear, null) } },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Primary, unfocusedBorderColor = Divider,
                        focusedContainerColor = BgCard, unfocusedContainerColor = BgCard
                    ),
                    shape = RoundedCornerShape(12.dp), singleLine = true
                )
            }
        }

        // Category Tabs
        LazyRow(contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(categories) { cat ->
                val isSelected = selectedCat == cat
                val catColor = categoryColors[cat] ?: Primary
                Surface(shape = RoundedCornerShape(20.dp),
                    color = if (isSelected) catColor else BgCard,
                    border = if (!isSelected) BorderStroke(1.dp, Divider) else null,
                    modifier = Modifier.clickable { selectedCat = cat }) {
                    Text(cat, modifier = Modifier.padding(14.dp, 7.dp),
                        color = if (isSelected) TextPrimary else TextSecondary,
                        style = MaterialTheme.typography.labelLarge)
                }
            }
        }

        // Tags List
        LazyColumn(contentPadding = PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            item {
                Text("${filtered.size}টি result", style = MaterialTheme.typography.bodySmall, color = TextHint)
                Spacer(Modifier.height(4.dp))
            }
            items(filtered) { tag ->
                val isExpanded = expanded == tag.tag
                val catColor = categoryColors[tag.category] ?: Primary

                Card(colors = CardDefaults.cardColors(containerColor = BgCard),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()) {
                    Column {
                        Row(Modifier.fillMaxWidth().clickable { expanded = if (isExpanded) null else tag.tag }
                            .padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
                            Surface(shape = RoundedCornerShape(6.dp), color = catColor.copy(0.15f)) {
                                Text(tag.tag, modifier = Modifier.padding(8.dp, 4.dp),
                                    fontFamily = FontFamily.Monospace, color = catColor,
                                    style = MaterialTheme.typography.titleMedium)
                            }
                            Spacer(Modifier.width(12.dp))
                            Text(tag.description, style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.weight(1f), maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                                overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis)
                            Icon(if (isExpanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                                null, tint = TextHint, modifier = Modifier.size(18.dp))
                        }
                        if (isExpanded) {
                            Divider(color = Divider)
                            Surface(color = Color(0xFF0D1117), modifier = Modifier.fillMaxWidth()) {
                                Text(tag.example, modifier = Modifier.padding(14.dp),
                                    fontFamily = FontFamily.Monospace, fontSize = 12.sp,
                                    color = Color(0xFF79C0FF), lineHeight = 20.sp)
                            }
                        }
                    }
                }
            }
        }
    }
}
