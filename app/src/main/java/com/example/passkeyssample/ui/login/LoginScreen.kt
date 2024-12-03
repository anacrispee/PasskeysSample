package com.example.passkeyssample.ui.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.passkeyssample.R
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    paddingValues: PaddingValues,
    navController: NavController
) {
    val viewModel = LoginViewModel()
    val context = LocalContext.current

    LaunchedEffect(true) {
        viewModel.showSavedCredentials(context)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var username by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var passwordVisible by remember { mutableStateOf(false) }

        TextField(
            value = username,
            onValueChange = {
                username = it
            },
            label = { Text("Username") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = password,
            onValueChange = {
                password = it
            },
            label = { Text("Password") },
            visualTransformation = if (passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
            trailingIcon = {
                IconButton(
                    onClick = {
                        passwordVisible = passwordVisible.not()
                    }
                ) {
                    Icon(
                        painter = if (passwordVisible) painterResource(id = R.drawable.ic_visibility)
                        else painterResource(id = R.drawable.ic_visibility_off),
                        contentDescription = null
                    )
                }
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                viewModel.viewModelScope.launch {
                    viewModel.saveCredentials(
                        context = context,
                        userId = username,
                        password = password
                    )
                }
            }
        ) {
            Text("Login")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    LoginScreen(
        paddingValues = PaddingValues(),
        navController = NavController(LocalContext.current)
    )
}