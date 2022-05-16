package com.soethan.citysearch.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.soethan.citysearch.R
import com.soethan.citysearch.databinding.FragmentCityListBinding
import com.soethan.citysearch.databinding.FragmentMapBinding
import com.soethan.citysearch.utils.toast


class MapFragment : Fragment(), OnMapReadyCallback {

    private var _binding: FragmentMapBinding? = null
    private val binding get() = _binding!!


    private var lat: Double? = null
    private var lon: Double? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync(this)

        lat = MapFragmentArgs.fromBundle(requireArguments()).lat.toDouble()
        lon = MapFragmentArgs.fromBundle(requireArguments()).lon.toDouble()

    }

    override fun onMapReady(map: GoogleMap) {
        if (lat == null && lon == null) return
        val marker = LatLng(lat!!, lon!!)
        map.animateCamera(CameraUpdateFactory.newLatLng(marker))

        map.addMarker(
            MarkerOptions()
                .position(marker)
        )


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}