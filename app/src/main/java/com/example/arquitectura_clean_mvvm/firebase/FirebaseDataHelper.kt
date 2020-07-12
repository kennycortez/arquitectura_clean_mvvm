package com.example.arquitectura_clean_mvvm.firebase

import com.example.domain.model.crokis.Crokis
import com.example.domain.model.users.Users
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*


class FirebaseDataHelper {


    private var listGeneric: ArrayList<Crokis> = arrayListOf()


    fun readUsers() {
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val myRefUser: DatabaseReference = database.getReference("Users")
        myRefUser.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                println()
            }

            override fun onDataChange(success: DataSnapshot) {
                listGeneric.clear()
                success.children.forEach {
                    it.let { _ ->
                        val users: Users = it.getValue(Users::class.java)!!
                        //listGeneric.add(users)

                    }
                }
                println(listGeneric)
            }
        })
    }

    fun registerSaveUser(users: Users) {
        val myRefUser: DatabaseReference = FirebaseDatabase.getInstance().getReference("Users")
        myRefUser.push().setValue(users)
    }


    fun readCrokis(id:String) {
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val myRefCrokis: Query = database.getReference("Crokis").orderByChild("CreatorId").equalTo(id)
        myRefCrokis.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(message: DatabaseError) {
                onCrokisCoordinates?.onCrokisCoordinatesFailed(message.message)
            }

            override fun onDataChange(success: DataSnapshot) {
                listGeneric.clear()
                success.children.forEach {
                    it.let {
                        val crokis = it.getValue(Crokis::class.java)!!
                        listGeneric.add(crokis)
                    }
                }

                onCrokisCoordinates?.onCrokisCoordinatesSuccess(listGeneric)
                //println(listGeneric)
            }
        })
    }

    fun writeMap() {
        val mAuth = FirebaseAuth.getInstance()
        val currentUser: FirebaseUser? = mAuth.currentUser
        val users = Users(currentUser?.uid, currentUser?.email)
    }

    fun createAccount(email: String, pwd: String) {
        val mAuth = FirebaseAuth.getInstance()
        mAuth.createUserWithEmailAndPassword(email, pwd)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    val currentUser: FirebaseUser? = mAuth.currentUser
                    val users = Users(currentUser?.uid, currentUser?.email)
                    registerSaveUser(users)
                    oncreateAccount?.createAccountSuccess()
                } else {
                    oncreateAccount?.createAccountFail(it.exception?.message.toString())
                }
            }
    }

    fun signInWithEmailAndPassword(email: String, pwd: String) {
        val mAuth = FirebaseAuth.getInstance()
        mAuth.signInWithEmailAndPassword(email, pwd)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    onSignInWithEmailAndPassword?.signInWithEmailSuccess()
                } else {
                    onSignInWithEmailAndPassword?.signInWithEmailFail(it.exception?.localizedMessage.toString())
                }
            }
    }

    private var onSignInWithEmailAndPassword: OnsignInWithEmailAndPassword? = null
    private var onCrokisCoordinates:OnCrokisCoordinates ?= null
    private var oncreateAccount: OncreateAccount? = null


    fun setListenerSignIn(onSignInWithEmailAndPassword: OnsignInWithEmailAndPassword) {
        this.onSignInWithEmailAndPassword = onSignInWithEmailAndPassword
    }

    fun setOnCrokisCoordinates(onCrokisCoordinates:OnCrokisCoordinates){
        this.onCrokisCoordinates = onCrokisCoordinates
    }

    fun setListenerCreateAccount(oncreateAccount: OncreateAccount) {
        this.oncreateAccount = oncreateAccount
    }

    interface OncreateAccount {
        fun createAccountSuccess()
        fun createAccountFail(message: String)
    }

    interface OnsignInWithEmailAndPassword {
        fun signInWithEmailFail(message: String)
        fun signInWithEmailSuccess()
    }

    interface OnCrokisCoordinates {
        fun onCrokisCoordinatesSuccess(crokis: List<Crokis>)
        fun onCrokisCoordinatesFailed(message: String)
    }


  /*  fun loginWithFacebook() {
        val firebaseAuth = FirebaseAuth.getInstance()
        val callbackManager = CallbackManager.Factory.create()
        LoginManager.getInstance().registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult?) {
                //handleFacebookAccessToken(result!!.accessToken,firebaseAuth)
                result?.let { it ->
                    val token = it.accessToken
                    val credential = FacebookAuthProvider.getCredential(token.token)
                    firebaseAuth.signInWithCredential(credential).addOnCompleteListener { ita->
                        if(ita.isSuccessful){
                            println(ita.result?.user?.email)
                        }else{
                            println("error")
                        }
                    }
                }

            }

            override fun onCancel() {
                println("hola")
            }

            override fun onError(error: FacebookException?) {
                println("hola")
            }
        })
    } */
}