package com.android.app.sampleapplication.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.app.sampleapplication.network.APIInterface
import com.android.app.sampleapplication.network.NetworkRepository
import com.android.app.sampleapplication.network.Result
import com.android.app.sampleapplication.network.models.ApiResponseCallback
import com.android.app.sampleapplication.network.models.ResponseData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val networkRepository: NetworkRepository) :
    ViewModel() {
    private val screenState = MutableLiveData<Result>()

    fun searchData(city: String) {
        screenState.value = Result.Loading
        viewModelScope.launch(Dispatchers.IO) {

            networkRepository.searchWeather(city, object : ApiResponseCallback {
                override fun onSuccess(responseData: Result) {
                    screenState.postValue(responseData)
                }

                override fun onError(message: Result) {
                    screenState.postValue(message)
                }

            })
        }
    }

    fun getScreenStateLiveData(): LiveData<Result> {
        return screenState
    }


    fun loadData() {
        screenState.value = Result.Loading
        viewModelScope.launch(Dispatchers.IO) {
            networkRepository.loadData(object : ApiResponseCallback {
                override fun onSuccess(responseData: Result) {
                    screenState.postValue(responseData)
                }

                override fun onError(message: Result) {
                    screenState.postValue(message)
                }

            })
        }
    }
}