package com.example.arquitectura_clean_mvvm.screen.maps

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.example.arquitectura_clean_mvvm.R
import com.example.domain.model.crokis.Crokis
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : FragmentActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    private lateinit var crokis: Crokis

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        crokis = intent?.extras?.getSerializable("maps") as Crokis
        println(crokis)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {

        mMap = googleMap
        crokis.Pins?.forEach {
            val position =
                LatLng(it.value.PinLatitude!!.toDouble(), it.value.PinLongitude!!.toDouble())
            mMap.addMarker(
                MarkerOptions().icon(
                    BitmapDescriptorFactory
                        .fromResource(R.drawable.pinselectedicon)
                ).anchor(0.0f, 1.0f)
                    .position(position).title(it.value.PinName)
            )
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 13F))
        }

    }
}