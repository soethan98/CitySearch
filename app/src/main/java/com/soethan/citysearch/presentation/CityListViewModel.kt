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
import com.soethan.citysearch.utils.Lce
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
@HiltViewModel
class CityListViewModel @Inject constructor(private val cityRepo: CitySearchRepo) : ViewModel() {


    private val _citiesStateFlow = MutableStateFlow<PagingData<City>?>(null)
    val citiesStateFlow  = _citiesStateFlow.asStateFlow()

    private val _dataLoadingStatus = MutableStateFlow<Boolean>(false)
    val dataLoadingStatus  = _dataLoadingStatus.asStateFlow()

    private val _eventChannel = Channel<Event>()
    val eventChannel = _eventChannel.receiveAsFlow()


    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _eventChannel.trySend(Event.ShowErrorMessage(throwable))
    }



    val searchKeyWord = MutableStateFlow<String>("")


    init {
        loadAllCities()
        searchCities()
    }


     fun loadAllCities(fetch:Boolean = true)  {
        viewModelScope.launch(exceptionHandler){
           _dataLoadingStatus.value = fetch

            cityRepo.loadAllCities(fetch).catch { e->
                _eventChannel.send(Event.ShowErrorMessage(e))

            }.cachedIn(this).collectLatest {
                _dataLoadingStatus.value = false
                _citiesStateFlow.emit(it)
            }
        }
    }

     private fun searchCities() {
        viewModelScope.launch(exceptionHandler){
            searchKeyWord.debounce(1000L)
                .distinctUntilChanged()
                .filter { it.trim().isNotEmpty() }
                .mapLatest { query ->
                    _dataLoadingStatus.value = true
                    cityRepo.searchCities(query).cachedIn(this)
                }.catch { e ->
                    Log.i("MainViewModel",e.message ?: e.localizedMessage)
                    _eventChannel.send(Event.ShowErrorMessage(e))
                }.flowOn(Dispatchers.IO).collectLatest {
                    _dataLoadingStatus.value = false
                    _citiesStateFlow.emitAll(it)
                }
        }
     }


    sealed class Event{
        data class ShowErrorMessage(val error: Throwable) : Event()
    }
}