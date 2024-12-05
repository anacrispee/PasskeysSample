package com.example.passkeyssample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.passkeyssample.AppConstants.HOME_SCREEN
import com.example.passkeyssample.AppConstants.LOGIN_SCREEN
import com.example.passkeyssample.ui.home.HomeScreen
import com.example.passkeyssample.ui.login.LoginScreen
import com.example.passkeyssample.ui.theme.PasskeysSampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PasskeysSampleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        innerPadding = innerPadding
                    )
                }
            }
        }
    }

    @Composable
    private fun NavHost(
        navController: NavHostController,
        innerPadding: PaddingValues
    ) {
        NavHost(
            navController = navController,
            startDestination = LOGIN_SCREEN,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = LOGIN_SCREEN) {
                LoginScreen(
                    paddingValues = innerPadding,
                    navController = navController
                )
            }
            composable(route = HOME_SCREEN) {
                HomeScreen(
                    paddingValues = innerPadding,
                    navController = navController
                )
            }
        }
    }
}