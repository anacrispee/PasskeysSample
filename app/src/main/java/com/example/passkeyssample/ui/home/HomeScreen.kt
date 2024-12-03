package com.example.passkeyssample.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun HomeScreen(
    paddingValues: PaddingValues,
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Home Screen")
        Spacer(modifier = Modifier.height(16.dp))
        Text("Você está logado!")
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                navController.navigate("loginScreen")
            }
        ) {
            Text("Voltar para o login")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen(
        paddingValues = PaddingValues(),
        navController = NavController(LocalContext.current)
    )
}