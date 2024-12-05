package com.example.passkeyssample.ui.login

import android.content.Context
import android.util.Log
import androidx.credentials.CreatePasswordRequest
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetPasswordOption
import androidx.credentials.PasswordCredential
import androidx.credentials.exceptions.CreateCredentialNoCreateOptionException
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

        try {
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
        } catch (e: Exception) {
            Log.d("Erro:", "Erro ao buscar credenciais salvas", e)
        }
    }

    // Função que salva novas credenciais
    suspend fun saveCredentials(
        context: Context, userId: String,
        password: String
    ) {
        val credentialManager = CredentialManager.create(context)

        // Cria nova credencial
        try {
            credentialManager.createCredential(
                request = CreatePasswordRequest(userId, password),
                context = context
            )
        } catch (e: CreateCredentialNoCreateOptionException) {
            // Lida com cados onde não há opções de criação de credenciais
            throw Exception("No providers available for saving credentials. ${e.message}")
        } catch (e: Exception) {
            // Lida com outros erros
            throw Exception("Error: ${e.message}")
        }
    }
}