package com.example.kataloox.auth

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.tasks.await

class GoogleAuthUiClient(private val context: Context) {

    private val auth = FirebaseAuth.getInstance()

    private val googleSignInClient: GoogleSignInClient by lazy {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("185539624006-1iml09258mtbamj52phm2qc7b3042eap.apps.googleusercontent.com")
            .requestEmail()
            .build()
        GoogleSignIn.getClient(context, gso)
    }

    fun signInIntent(): Intent = googleSignInClient.signInIntent

    fun signOut() {
        auth.signOut()
        googleSignInClient.signOut()
    }

    fun getSignedInUser(): String? {
        return auth.currentUser?.displayName
    }

    fun getCorreoUsuario(): String? {
        return FirebaseAuth.getInstance().currentUser?.email
    }

    fun getNombreUsuario(): String? {
        return FirebaseAuth.getInstance().currentUser?.displayName
    }


    suspend fun signInWithIntent(data: Intent): Boolean {
        val task = GoogleSignIn.getSignedInAccountFromIntent(data)
        val account: GoogleSignInAccount = task.result
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        val result = auth.signInWithCredential(credential).await()
        return result.user != null
    }
}
