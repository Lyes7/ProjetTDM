package com.example.quran.Activities

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.preference.PreferenceManager
import androidx.appcompat.app.AppCompatActivity
import com.example.quran.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activitysignin.*
import java.util.Calendar.getInstance


class SignInActivity: AppCompatActivity() {

/*
    private lateinit var mAuth:FirebaseAuth
    private lateinit var googleSignIn: GoogleSignInClient
*/
    override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activitysignin)
}
/*
// Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignIn = GoogleSignIn.getClient(this, gso)
        mAuth = FirebaseAuth.getInstance()

        signBtn.setOnClickListener{
            signIn()
            val currentUser = mAuth.currentUser
            print(currentUser)
        }


    }
    private fun signIn() {
        val signInIntent = googleSignIn.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val exception=task.exception
            if(task.isSuccessful)
            {  try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
               // Log.d("SignInActivity", "firebaseAuthWithGoogle:" + account.id)
               print( "COMPTE :" + account.id.toString())

                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
               // Log.w("SignInActivity", "Google sign in failed", e)
                print("SignInActivity "+ "GOOGLE MAFIHAACH"+ e.toString())
            }}else{
                //Log.w("SignInActivity", exception.toString())
                print("SignInActivity "+ exception.toString())

            }

        }
    }
    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    print("SignInActivity "+ "NAJA7NAA")

                } else {
                    // If sign in fails, display a message to the user.
                   // Log.w("SignInActivity", "signInWithCredential:failure", task.exception)
                    print("SignInActivity "+ "faaaaailllllerrr "+ task.exception.toString())

                }
            }
    }
*/
}
