package com.example.arquitectura_clean_mvvm.screen.register

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.arquitectura_clean_mvvm.R
import com.example.arquitectura_clean_mvvm.firebase.FirebaseDataHelper
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_register_cl.*
import javax.inject.Inject

@AndroidEntryPoint
class RegisterClActivity : AppCompatActivity() {

    @Inject
    lateinit var firebaseDataHelper: FirebaseDataHelper
    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_cl)

        setOnClickListener()

         mAuth = FirebaseAuth.getInstance()
    }


    private fun setOnClickListener(){
        btnRegister.setOnClickListener {
            if(edtPassword.text.toString() == edtConfirmPassword.text.toString()){
                firebaseDataHelper.createAccount(edtEmail.text.toString(), edtPassword.text.toString())

                firebaseDataHelper.setListenerCreateAccount(object :
                    FirebaseDataHelper.OncreateAccount {
                    override fun createAccountSuccess() {
                        finish()
                    }

                    override fun createAccountFail(message: String) {
                        Toast.makeText(this@RegisterClActivity,message,Toast.LENGTH_SHORT).show()
                    }

                })
            }
        }

        imgBack.setOnClickListener {
            onBackPressed()
        }
    }
}