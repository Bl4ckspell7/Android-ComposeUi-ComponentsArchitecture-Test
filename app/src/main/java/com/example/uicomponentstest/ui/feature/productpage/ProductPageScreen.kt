package com.example.uicomponentstest.ui.feature.productpage

import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.uicomponentstest.R
import com.example.uicomponentstest.ui.feature.importproductpage.ImportProductPageViewModel
import com.example.uicomponentstest.ui.feature.testpage.TestPage

@Composable
fun ProductPageScreen(
    navController: NavHostController,
    importProductPageViewModel: ImportProductPageViewModel = hiltViewModel(),
    productPageViewModel: ProductPageViewModel = hiltViewModel(),
    onLoadFromWeb: () -> Unit = { importProductPageViewModel.loadFromWeb("web") },
    onLoadFromDatabase: () -> Unit = { importProductPageViewModel.loadFromDatabase("database") },
) {
    val product by productPageViewModel.product.collectAsStateWithLifecycle()

    Scaffold(floatingActionButton = { AddFab() }) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            item {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    HorizontalDivider()
                    Button(onClick = {
                        Log.d("ProductPageScreen3", "before: ${product?.name}")
                        onLoadFromWeb()
                        Log.d("ProductPageScreen3", "after: ${product?.name}")
                    }) {
                        Text(text = "Load Web Product")
                    }
                    HorizontalDivider()
                    Button(onClick = {
                        Log.d("ProductPageScreen3", "before: ${product?.name}")
                        onLoadFromDatabase()
                        Log.d("ProductPageScreen3", "after: ${product?.name}")
                    }) {
                        Text(text = "Load Database Product")
                    }
                    HorizontalDivider()
                    product?.let { it1 ->
                        Text(
                            text = it1.name
                        )
                    }
                    HorizontalDivider()
                }
            }
            item {
                SearchBar()
                RoundedImageCard(
                    imageResource = R.drawable.battery_stock_image,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .height(200.dp),
                    cornerRadius = 16.dp
                )
                Text(
                    text = "Important",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(16.dp)
                )
                ImportantInformationCard()
                Text(
                    text = "Other Information",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(16.dp)
                )
            }
            items(
                listOf(
                    "Battery Health",
                    "Durability",
                    "End of life collection information",
                    "Manufacturing Site",
                    "Carbon Footprint",
                    "Recycling"
                )
            ) { name ->
                ExpandableCard(name = name)
            }
            item {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(modifier = Modifier
                        .padding(16.dp)
                        .height(height = 56.dp),
                        onClick = { navController.navigate(TestPage) }) {
                        Text(text = "Go back")
                    }
                }
            }
        }
    }
}

@Composable
fun SearchBar() {
    var searchText by remember { mutableStateOf("") }
    var showMenu by remember { mutableStateOf(false) }

    Box(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = searchText,
            onValueChange = { searchText = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Search") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search Icon") },
            trailingIcon = {
                Box {
                    IconButton(onClick = { showMenu = true }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "More Options")
                    }

                    DropdownMenu(
                        expanded = showMenu,
                        onDismissRequest = { showMenu = false },
                        offset = DpOffset(x = (-16).dp, y = 0.dp)
                    ) {
                        DropdownMenuItem(text = { Text("Option 1") }, onClick = {
                            // Handle Option 1 click
                            showMenu = false
                        })
                        DropdownMenuItem(text = { Text("Option 2") }, onClick = {
                            // Handle Option 2 click
                            showMenu = false
                        })
                        DropdownMenuItem(text = { Text("Option 3") }, onClick = {
                            // Handle Option 3 click
                            showMenu = false
                        })
                    }
                }
            },
            singleLine = true,
            shape = RoundedCornerShape(16.dp)
        )
    }
}


@Composable
fun RoundedImageCard(
    imageResource: Int, modifier: Modifier = Modifier, cornerRadius: Dp = 16.dp
) {
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        Dialog(onDismissRequest = { showDialog = false }) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { showDialog = false },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = imageResource),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Fit
                )
            }
        }
    }

    Card(
        shape = RoundedCornerShape(corner = CornerSize(cornerRadius.value)),
        modifier = modifier.clickable { showDialog = true },
    ) {
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 200.dp),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun ImportantInformationCard() {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.padding(vertical = 8.dp)) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                ) {
                    Text(
                        "Manufacturer",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        "Product type",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        "Capacity(mAh)",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold
                    )
                }
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp)
                ) {
                    Text("VARTA", style = MaterialTheme.typography.bodyLarge)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("Rechargeable", style = MaterialTheme.typography.bodyLarge)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("5,000", style = MaterialTheme.typography.bodyLarge)
                }
            }
        }
    }
}

@Composable
fun ExpandableCard(name: String) {
    var expanded by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 16.dp)
            .fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .animateContentSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = !expanded },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.weight(1f)
                )
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        imageVector = if (expanded) {
                            Icons.Filled.ExpandLess
                        } else {
                            Icons.Filled.ExpandMore
                        }, contentDescription = if (expanded) "Show less" else "Show more"
                    )
                }
            }
            if (expanded) {
                Text(text = "Details about $name", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

@Composable
fun AddFab() {
    var showMenu by remember { mutableStateOf(false) }

    Box {
        FloatingActionButton(
            onClick = { showMenu = true }, containerColor = MaterialTheme.colorScheme.primary
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
        }

        DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false }) {
            DropdownMenuItem(text = { Text("Scan a product to compare") }, onClick = {
                // Handle scan action here
                showMenu = false
            })
        }
    }
}