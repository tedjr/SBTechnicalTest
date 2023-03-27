package com.example.sbtechincaltest.resttest

import com.example.sbtechincaltest.model.PhotoItem
import com.example.sbtechincaltest.model.Photos
import com.example.sbtechincaltest.rest.PhotoRepository
import com.example.sbtechincaltest.rest.PhotoRepositoryImpl
import com.example.sbtechincaltest.rest.PhotosApi
import com.example.sbtechincaltest.utils.UIState
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.ResponseBody
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import retrofit2.Response

class PhotoRepositoryTest {

    private lateinit var mockPhotosApi: PhotosApi
    private lateinit var photoRepository: PhotoRepository

    @Before
    fun setup() {
        mockPhotosApi = mock()
        photoRepository = PhotoRepositoryImpl(mockPhotosApi)
    }


    @Test
    fun `getPhotos should emit error state when response is unsuccessful`() = runBlocking {
        // Given
        whenever(mockPhotosApi.photosList()).thenReturn(
            Response.error(
                404,
                ResponseBody.create("application/json".toMediaTypeOrNull(), "")
            )
        )

        // When
        photoRepository.getPhotos()

        // Then
        val uiState = photoRepository.photoResponseFlow.first()
        Assert.assertTrue(uiState is UIState.ERROR)
    }

    @Test
    fun `getPhotos should emit error state when response is null`() = runBlocking {

        // Given
        whenever(mockPhotosApi.photosList()).thenReturn(Response.success(null))

        // When
        photoRepository.getPhotos()

        // Then
        val uiState = photoRepository.photoResponseFlow.first()
        Assert.assertTrue(uiState is UIState.ERROR)
    }

    @Test
    fun `getPhotos should emit error state when an exception is thrown`() = runBlocking {
        // Given
        val errorMessage = "Failed to fetch photos"
        whenever(mockPhotosApi.photosList()).thenThrow(RuntimeException(errorMessage))

        // When
        photoRepository.getPhotos()

        // Then
        val uiState = photoRepository.photoResponseFlow.first()
        Assert.assertTrue(uiState is UIState.ERROR && uiState.error?.message == errorMessage)
    }
}