package com.soethan.citysearch

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soethan.citysearch.repo.CitySearchRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val loadCities: CitySearchRepo):ViewModel(){
    init {
        loadCitiesa()
    }

    fun loadCitiesa(){
        viewModelScope.launch {

            try {
                val result = loadCities.loadAllCities()

                Log.i("MainViewModel","${result.size}")
            }catch (e:Exception){
                Log.i("MainViewModel","${e.localizedMessage}")

            }

        }
    }
}