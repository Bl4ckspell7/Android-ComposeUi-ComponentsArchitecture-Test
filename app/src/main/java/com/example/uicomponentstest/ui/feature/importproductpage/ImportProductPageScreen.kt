package com.example.uicomponentstest.ui.feature.importproductpage

import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.uicomponentstest.ui.feature.testpage.TestPage

@Composable
fun ImportProductPageScreen(
    navController: NavController,
    importProductPageViewModel: ImportProductPageViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val qrCodeScanResult by importProductPageViewModel.qrCodeScanResult.collectAsState()
    val coroutineScope = rememberCoroutineScope()

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
            Text(text = qrCodeScanResult, modifier = Modifier.basicMarquee())
            Button(content = { Text(text = "Scan") }, onClick = {
                importProductPageViewModel.startScan(context, coroutineScope)
            })
            HorizontalDivider()
            Button(onClick = { navController.navigate(TestPage) }) {
                Text(text = "Go back")
            }
            HorizontalDivider()
        }
    }
}
