package com.example.arquitectura_clean_mvvm.screen.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.arquitectura_clean_mvvm.R
import com.example.arquitectura_clean_mvvm.firebase.FirebaseDataHelper
import com.example.arquitectura_clean_mvvm.screen.register.RegisterClActivity
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    @Inject
    lateinit var firebaseDataHelper: FirebaseDataHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        txtRegister.setOnClickListener {
            val intent = Intent(this,RegisterClActivity::class.java)
            startActivity(intent)
        }

        btnLogin.setOnClickListener {
            val intent = Intent(this,LoginClActivity::class.java)
            startActivity(intent)
        }

       /* btnLoginFacebook.setOnClickListener {
            //LoginManager.getInstance().logInWithPublishPermissions(this, listOf("email"))
            firebaseDataHelper.loginWithFacebook()
        }*/
    }


    override fun onBackPressed() {
        println()
    }
}