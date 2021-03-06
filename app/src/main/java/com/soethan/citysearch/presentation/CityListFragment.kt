package com.soethan.citysearch.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.soethan.citysearch.databinding.FragmentCityListBinding
import com.soethan.citysearch.presentation.adapter.CityListAdapter
import com.soethan.citysearch.presentation.adapter.CityLoadStateAdapter
import com.soethan.citysearch.utils.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@FlowPreview
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class CityListFragment : Fragment() {

    private val cityListViewModel: CityListViewModel by viewModels()

    private var _binding: FragmentCityListBinding? = null
    private val binding get() = _binding!!

    private val cityAdapter: CityListAdapter by lazy {
        CityListAdapter(clickListener = CityListAdapter.OnCityItemClickListener { city ->
            val action =
                CityListFragmentDirections.toMapFragment(city.lat.toFloat(), city.long.toFloat())
            findNavController().navigate(action)
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        watchSearchTextField()
        observeData()

        binding.btnSearchClear.setOnClickListener {
            binding.etSearch.text.clear()
            cityListViewModel.loadAllCities(false)
        }
    }

    private fun setUpRecyclerView() {
        binding.apply {
            rvCities.layoutManager = LinearLayoutManager(requireContext())
            rvCities.adapter = cityAdapter.withLoadStateHeaderAndFooter(
                header = CityLoadStateAdapter { cityAdapter.retry() },
                footer = CityLoadStateAdapter { cityAdapter.retry() }
            )
        }
    }

    private fun observeData() {

        viewLifecycleOwner.lifecycleScope.launch {
            cityListViewModel.eventChannel.collectLatest {
                when (it) {
                    is CityListViewModel.Event.ShowErrorMessage -> requireContext().toast((it.error as ErrorBody).errMsg)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                cityListViewModel.citiesStateFlow.collectLatest { data ->
                    data?.let {
                        binding.progressCities.hide()
                        cityAdapter.submitData(data)
                    }
                }
            }

        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                cityListViewModel.dataLoadingStatus.collectLatest { isLoading ->
                    binding.progressCities.isVisible = isLoading
                    binding.rvCities.isVisible = !isLoading

                }
            }

        }
    }

    private fun watchSearchTextField() {
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (count > 0) binding.btnSearchClear.show() else binding.btnSearchClear.hide()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) =
                Unit

            override fun afterTextChanged(editable: Editable?) {
                if (!editable.isNullOrEmpty()) {
                    searchCity(editable.toString())
                }

            }
        })
    }

    private fun searchCity(cityName: String) {
        lifecycleScope.launch {
            cityListViewModel.apply {
                searchKeyWord.value = cityName
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCityListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}