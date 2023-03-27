package com.example.sbtechincaltest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sbtechincaltest.rest.PhotoRepository
import com.example.sbtechincaltest.utils.UIState
import kotlinx.coroutines.*

class PhotoViewModel(
    private val photoRepository: PhotoRepository,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val coroutineScope: CoroutineScope = CoroutineScope(SupervisorJob() + ioDispatcher)
) : CoroutineScope by coroutineScope, ViewModel() {

    private val _photosLiveData: MutableLiveData<UIState> = MutableLiveData(UIState.LOADING())
    val photosLiveData: LiveData<UIState> get() = _photosLiveData

    fun subscribeToPhotosList() {
        _photosLiveData.postValue(UIState.LOADING())
        collectPhotosList()
        launch {
            photoRepository.getPhotos()
        }
    }

    private fun collectPhotosList() {
        launch {

//            _photosLiveData.postValue(UIState.SUCCESS(getOutputData()))

            photoRepository.photoResponseFlow.collect() { uiState ->
                when (uiState) {
                    is UIState.LOADING -> {
                        _photosLiveData.postValue(uiState)
                    }
                    is UIState.SUCCESS -> {
                        _photosLiveData.postValue(uiState)
                    }
                    is UIState.ERROR -> {
                        _photosLiveData.postValue(uiState)
                    }
                }

            }
        }
    }

}