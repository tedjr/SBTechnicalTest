package com.example.sbtechincaltest.rest

import com.example.sbtechincaltest.model.Photos
import retrofit2.Response
import retrofit2.http.GET

interface PhotosApi {

    //fetching data from the api
    @GET(PHOTOS)
    suspend fun photosList(): Response<Photos>

    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com/"
        private const val PHOTOS = "photos"
    }
}