package com.example.passkeyssample.ui.login

import android.content.Context
import android.content.Intent
import androidx.credentials.CreatePasswordRequest
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetPasswordOption
import androidx.credentials.PasswordCredential
import androidx.credentials.exceptions.CreateCredentialNoCreateOptionException
import androidx.lifecycle.ViewModel
import com.example.passkeyssample.MainActivity

class LoginViewModel : ViewModel() {

    suspend fun showSavedCredentials(
        context: Context
    ) {
        val credentialManager = CredentialManager.create(context = context)
        try {
            // Show pop-up to select saved credentials
            val response = credentialManager.getCredential(
                context = context,
                GetCredentialRequest(credentialOptions = listOf(GetPasswordOption()))
            )

            // If credentials exist, use them for login
            val credential = response.credential
            if (credential is PasswordCredential) {
                // User successfully logged in
                context.startActivity(Intent(context, MainActivity::class.java))
            }
        } catch (_: Exception) {
            // No credentials found or something went wrong
        }
    }

    suspend fun saveCredentials(
        context: Context, userId: String,
        password: String
    ) {
        val credentialManager = CredentialManager.create(context)
        try {
            // Save user credentials securely
            credentialManager.createCredential(
                request = CreatePasswordRequest(userId, password),
                context = context
            )
            println("Credentials saved successfully.")
        } catch (e: CreateCredentialNoCreateOptionException) {
            // Handle cases where saving credentials isn't possible
            throw Exception("No providers available for saving credentials. ${e.message}")
        } catch (e: Exception) {
            // Handle other errors
            throw Exception("Error: ${e.message}")
        }
    }
}