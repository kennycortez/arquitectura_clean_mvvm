package com.example.arquitectura_clean_mvvm.screen.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.arquitectura_clean_mvvm.R
import com.example.arquitectura_clean_mvvm.firebase.FirebaseDataHelper
import com.example.arquitectura_clean_mvvm.screen.crokis.CrokisActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_login_cl.*
import javax.inject.Inject

@AndroidEntryPoint
class LoginClActivity : AppCompatActivity(){

    @Inject
    lateinit var firebaseDataHelper: FirebaseDataHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_cl)

        setOnClickListener()
    }


    private fun setOnClickListener(){
        btnLogin.setOnClickListener {
            firebaseDataHelper.signInWithEmailAndPassword(edtEmail.text.toString(),edtPassword.text.toString())
            firebaseDataHelper.setListenerSignIn(object :
                FirebaseDataHelper.OnsignInWithEmailAndPassword {
                override fun signInWithEmailFail(message: String) {
                    Toast.makeText(this@LoginClActivity,message,Toast.LENGTH_SHORT).show()
                }

                override fun signInWithEmailSuccess() {
                    val intent = Intent(this@LoginClActivity,CrokisActivity::class.java)
                    startActivity(intent)
                }

            })
        }

        imgBack.setOnClickListener {
            onBackPressed()
        }

    }

}