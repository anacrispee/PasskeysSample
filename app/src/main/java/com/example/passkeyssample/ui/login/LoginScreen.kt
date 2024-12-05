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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.passkeyssample.AppConstants.HOME_SCREEN
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
        viewModel.showSavedCredentials(
            context = context,
            navController = navController
        )
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
        var isPasswordHide by remember { mutableStateOf(true) }

        TextField(
            value = username,
            onValueChange = {
                username = it
            },
            label = { Text(stringResource(R.string.username)) }
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = password,
            onValueChange = {
                password = it
            },
            label = { Text(stringResource(R.string.password)) },
            visualTransformation = if (isPasswordHide) PasswordVisualTransformation() else VisualTransformation.None,
            trailingIcon = {
                IconButton(
                    onClick = {
                        isPasswordHide = isPasswordHide.not()
                    }
                ) {
                    Icon(
                        painter = if (isPasswordHide) painterResource(id = R.drawable.ic_visibility_off)
                        else painterResource(id = R.drawable.ic_visibility),
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
                navController.navigate(HOME_SCREEN)
            },
            enabled = username.isNotBlank() && password.isNotBlank()
        ) {
            Text(stringResource(R.string.signin))
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