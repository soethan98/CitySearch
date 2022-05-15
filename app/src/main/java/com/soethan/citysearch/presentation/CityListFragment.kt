package com.soethan.citysearch.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.soethan.citysearch.databinding.FragmentCityListBinding
import com.soethan.citysearch.presentation.adapter.CityListAdapter
import com.soethan.citysearch.presentation.adapter.CityLoadStateAdapter
import com.soethan.citysearch.utils.hide
import com.soethan.citysearch.utils.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class CityListFragment : Fragment() {

    private val cityListViewModel: CityListViewModel by viewModels()

    private var _binding: FragmentCityListBinding? = null
    private val binding get() = _binding!!

    private lateinit var cityAdapter: CityListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        watchSearchTextField()
        observeData()
    }

    private fun setUpRecyclerView(){
        cityAdapter = CityListAdapter()
        binding.apply {
            rvCities.layoutManager = LinearLayoutManager(requireContext())
            rvCities.adapter = cityAdapter.withLoadStateHeaderAndFooter(
                header = CityLoadStateAdapter { cityAdapter.retry() },
                footer = CityLoadStateAdapter { cityAdapter.retry() }
            )
        }
    }

    private fun observeData(){

        cityListViewModel.throwableLiveData.observe(viewLifecycleOwner, Observer {
            requireContext().toast(it.localizedMessage)
        })
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                cityListViewModel.citiesStateFlow.collectLatest { data ->
                    data?.let {
                        binding.progressCities.hide()
                        cityAdapter.submitData(data)
                    }
                }
            }

        }
    }

    private fun watchSearchTextField() {
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

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