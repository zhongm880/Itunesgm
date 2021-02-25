package com.example.itunesk.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.itunesk.model.ItunesResponse
import com.example.itunesk.model.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.properties.Delegates

private const val TAG = "ItunesViewModel"
class ItunesViewModel(private val repository: Repository): ViewModel() {

    private val mutableLiveData: MutableLiveData<ItunesResponse> = MutableLiveData()
    //view model

    fun getLiveDataResponse(): LiveData<ItunesResponse>{ //view can not update the livedata due to mvvm
        return mutableLiveData
    }

    fun getArtistByName(inputName: String){ //called by view
        val scope = CoroutineScope(EmptyCoroutineContext) //dont have scope so creating scope
        scope.launch {
            val response = repository.getArtist(inputName)
            Log.d(TAG, "getArtistByName: $response")
            mutableLiveData.postValue(response)
        }
    }

    class ItunesViewModelFactory(private val repository: Repository):
            ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return ItunesViewModel(repository) as T
        }
    }
}