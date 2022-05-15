package com.soethan.citysearch.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.soethan.citysearch.domain.CitySearchRepo
import com.soethan.citysearch.domain.model.City
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CityListViewModel @Inject constructor(private val cityRepo: CitySearchRepo) : ViewModel() {


    private val _citiesStateFlow = MutableStateFlow<PagingData<City>?>(null)
    val citiesStateFlow  = _citiesStateFlow.asStateFlow()


    private val _throwableLiveData = MutableLiveData<Throwable>()
    val throwableLiveData:LiveData<Throwable> = _throwableLiveData

    val searchKeyWord = MutableStateFlow<String>("")


    init {
        loadAllCities()
        searchCities()
    }


     fun loadAllCities()  {

        viewModelScope.launch {
            cityRepo.loadAllCities().cachedIn(this).catch { e->
                _throwableLiveData.postValue(e)
            }.collectLatest {
                _citiesStateFlow.emit(it)
            }
        }
    }

     private fun searchCities() {
        viewModelScope.launch {
            searchKeyWord.debounce(1000L)
                .distinctUntilChanged()
                .filter { it.trim().isNotEmpty() }
                .mapLatest { query ->
                    cityRepo.searchCities(query).cachedIn(this)
                }.catch { e ->
                    Log.i("MainViewModel",e.message ?: e.localizedMessage)
                _throwableLiveData.postValue(e)
                }.flowOn(Dispatchers.IO).collectLatest {
                    _citiesStateFlow.emitAll(it)
                }
        }



    }
}