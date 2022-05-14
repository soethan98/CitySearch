package com.soethan.citysearch.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.soethan.citysearch.MainViewModel
import com.soethan.citysearch.R
import com.soethan.citysearch.databinding.FragmentCityListBinding
import com.soethan.citysearch.presentation.adapter.CityListAdapter
import com.soethan.citysearch.presentation.model.MockClass


class CityListFragment : Fragment() {

    private val mainViewModel: MainViewModel by viewModels()

    private var _binding: FragmentCityListBinding? = null
    private val binding get() = _binding!!

    private val cityAdapter: CityListAdapter by lazy { CityListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvCities.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = cityAdapter
        }

        cityAdapter.submitList(MockClass.generate())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCityListBinding.inflate(inflater, container, false)
        return binding.root
    }




}