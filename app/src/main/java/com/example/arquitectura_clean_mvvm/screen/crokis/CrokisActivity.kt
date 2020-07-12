package com.example.arquitectura_clean_mvvm.screen.crokis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.arquitectura_clean_mvvm.R
import com.example.arquitectura_clean_mvvm.firebase.FirebaseDataHelper
import com.example.arquitectura_clean_mvvm.screen.crokis.adapter.CrokisAdapter
import com.example.arquitectura_clean_mvvm.screen.maps.MapsActivity
import com.example.domain.model.crokis.Crokis
import com.example.domain.model.crokis.Pins
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_crokis.*
import javax.inject.Inject

@AndroidEntryPoint
class CrokisActivity : AppCompatActivity() {

    @Inject
    lateinit var firebaseDataHelper: FirebaseDataHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crokis)

        firebaseDataHelper.readCrokis("j9bCBFitSGQXIwSD6zhbWGtCYNQ2")
        getDataMaps()

    }

    private fun getDataMaps(){
        firebaseDataHelper.setOnCrokisCoordinates(object : FirebaseDataHelper.OnCrokisCoordinates {
            override fun onCrokisCoordinatesSuccess(crokis: List<Crokis>) {
                setUpRecyclerView(crokis)
            }

            override fun onCrokisCoordinatesFailed(message: String) {
                Toast.makeText(this@CrokisActivity,message,Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setUpRecyclerView(crokis: List<Crokis>){
        rcvCrokis.layoutManager = LinearLayoutManager(this)
        val crokisAdapter= CrokisAdapter(crokis)
        rcvCrokis.adapter = crokisAdapter
        crokisAdapter.setListener(object : CrokisAdapter.OnMaps {
            override fun onMapsCoordinates(coordinates: Crokis) {
                val intent = Intent(this@CrokisActivity, MapsActivity::class.java)
                val bundle = Bundle()
                bundle.putSerializable("maps",coordinates)
                intent.putExtras(bundle)
                startActivity(intent)
            }
        })
    }
}