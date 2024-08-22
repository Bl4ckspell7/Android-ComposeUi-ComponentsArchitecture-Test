package com.example.uicomponentstest.ui.feature.testpage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.uicomponentstest.ui.feature.importproductpage.ImportProductPage
import com.example.uicomponentstest.ui.feature.importproductpage.ImportProductPageViewModel
import com.example.uicomponentstest.ui.feature.productpage.ProductPage
import com.example.uicomponentstest.ui.feature.settingspage.SettingsPage
import com.example.uicomponentstest.ui.platform.components.reusable.ListItemDemo
import com.example.uicomponentstest.ui.platform.components.specific.BottomSheetExample
import com.example.uicomponentstest.ui.platform.components.specific.CheckboxMinimalExample


@Composable
fun TestPageScreen(
    navController: NavController,
    importProductPageViewModel: ImportProductPageViewModel = hiltViewModel(),
) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            HorizontalDivider()
            Button(onClick = { navController.navigate(ImportProductPage) }) {
                Text(text = "Go to ImportProductPage")
            }
            HorizontalDivider()
            Button(onClick = { navController.navigate(ProductPage) }) {
                Text(text = "Go to ProductPage")
            }
            HorizontalDivider()
            Button(onClick = { navController.navigate(SettingsPage) }) {
                Text(text = "Go to SettingsPage")
            }

            HorizontalDivider()
            Button(onClick = { importProductPageViewModel.loadFromWeb("web") }) {
                Text(text = "Load Web Product")
            }
            HorizontalDivider()
            Button(onClick = { importProductPageViewModel.loadFromDatabase("database") }) {
                Text(text = "Load Database Product")
            }
            HorizontalDivider()

            Row(
                modifier = Modifier.height(IntrinsicSize.Min),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CheckboxMinimalExample()
            }
            HorizontalDivider()

            ListItemDemo()

            HorizontalDivider()

            BottomSheetExample()
        }
    }
}