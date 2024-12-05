package com.example.passkeyssample.ui.login

import android.content.Context
import androidx.credentials.CreatePasswordRequest
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetPasswordOption
import androidx.credentials.PasswordCredential
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.passkeyssample.AppConstants.HOME_SCREEN

class LoginViewModel : ViewModel() {

    // Função que abre a bottom sheet para mostrar credenciais salvas
    suspend fun showSavedCredentials(
        context: Context,
        navController: NavController
    ) {
        val credentialManager = CredentialManager.create(context = context)
        val response = credentialManager.getCredential(
            context = context,
            GetCredentialRequest(
                credentialOptions = listOf(GetPasswordOption())
            )
        )
        // Verifica se há credenciais e usa elas para realizar login
        val credential = response.credential

        if (credential is PasswordCredential) {
            // Usuário logado com credenciais salvas navega para tela
            navController.navigate(HOME_SCREEN)
        }
    }

    // Função que salva novas credenciais
    suspend fun saveCredentials(
        context: Context, userId: String,
        password: String
    ) {
        val credentialManager = CredentialManager.create(context)

        // Cria nova credencial
        credentialManager.createCredential(
            request = CreatePasswordRequest(userId, password),
            context = context
        )
    }
}