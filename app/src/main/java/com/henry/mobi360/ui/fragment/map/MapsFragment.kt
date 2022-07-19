package com.henry.mobi360.ui.fragment.map

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.henry.mobi360.R
import com.henry.mobi360.databinding.FragmentMapsBinding
import com.henry.mobi360.local.model.map.Location
import com.henry.mobi360.local.remote.RetrofiteInstance
import com.henry.mobi360.ui.fragment.home.HomeViewModel
import com.henry.mobi360.utils.BitmapHelper
import com.henry.mobi360.utils.Constants.Companion.USER_ID_KEY
import java.util.*


class MapsFragment : Fragment() {

    private lateinit var binding: FragmentMapsBinding
    private lateinit var viewModel: HomeViewModel
    private var userId : Int? = null
    private var timer: Timer? = null
    private var location = arrayOf<Location>(

    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMapsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let { bundle ->
//               bundle.getInt(USER_ID_KEY).let {
//                   userId = it
//               }
            userId = bundle.getInt(USER_ID_KEY)
        }

        viewModel =  ViewModelProvider(this).get(HomeViewModel::class.java)
        viewModel.locationLiveData.observe(viewLifecycleOwner, Observer { locationObserver ->

            locationObserver?.let {
                location = it
            }

            val mapFragment = childFragmentManager.findFragmentById(R.id.mapsFragment) as SupportMapFragment?
            mapLoaded(mapFragment)
        })
        timerLocation()
    }

    private fun mapLoaded(mapFragment: SupportMapFragment?) {

        mapFragment?.getMapAsync { googleMap ->
            addMarkers(googleMap)
            googleMap.setOnMapLoadedCallback {
                val bounds = LatLngBounds.builder()

                location.forEach {
                    bounds.include(it.latLong())
                }
                googleMap.moveCamera(
                    CameraUpdateFactory.newLatLngBounds(
                        bounds.build(),
                        300
                    )
                )
            }
        }
    }

    private fun addMarkers(googleMap: GoogleMap) {
        location.forEach {
            googleMap.addMarker(
                MarkerOptions()
                    .title("Veículo : ${it.vehicleid}")
                    .position(it.latLong())
                    .icon(
                        BitmapHelper.vectorToBitmap(
                            requireContext(),
                            R.drawable.ic_car,
                            ContextCompat.getColor(requireContext(), R.color.purple_200)
                        )
                    )
            )
        }
    }

    fun timerLocation(){
        timer = Timer()
        timer?.schedule(object : TimerTask(){
            override fun run() {
                Log.i("Run","Test")
                userId?.let {
                    viewModel.getLocationUserId(it)
                }
            }
        },1000, 10000)
    }

    override fun onDestroy() {
        timer?.cancel()
        timer = null
        super.onDestroy()
    }

    override fun onStop() {
        timer?.cancel()
        timer = null
        super.onStop()
    }

    override fun onPause() {
        timer?.cancel()
        timer = null
        super.onPause()
    }
}