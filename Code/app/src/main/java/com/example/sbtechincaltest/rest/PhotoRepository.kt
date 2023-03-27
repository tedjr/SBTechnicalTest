package com.example.sbtechincaltest.rest

import com.example.sbtechincaltest.utils.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface PhotoRepository {
    val photoResponseFlow: StateFlow<UIState>
    suspend fun getPhotos()
}

class PhotoRepositoryImpl(
    private val photosApi: PhotosApi
) : PhotoRepository {

    // mutable state flow initialized with loading state indicating data is being fetched
    private val _photoResponseFlow: MutableStateFlow<UIState> = MutableStateFlow(UIState.LOADING())

    //read-only state flow to external classes
    override val photoResponseFlow: StateFlow<UIState>
        get() = _photoResponseFlow


    //fetches list of photos and handles the success and error cases
    override suspend fun getPhotos() {
        try {
            val response = photosApi.photosList()

            if (response.isSuccessful) {
                response.body()?.let {
                    _photoResponseFlow.value = UIState.SUCCESS(it)
                } ?: run {
                    _photoResponseFlow.value =
                        UIState.ERROR(IllegalStateException("Photos are coming as null"))
                }
            } else {
                _photoResponseFlow.value = UIState.ERROR(Exception(response.errorBody()?.string()))
            }
        } catch (e: Exception) {
            _photoResponseFlow.value = UIState.ERROR(e)
        }
    }
}