package com.example.sbtechincaltest.di

import com.example.sbtechincaltest.rest.PhotoRepository
import com.example.sbtechincaltest.rest.PhotoRepositoryImpl
import com.example.sbtechincaltest.rest.PhotosApi
import com.example.sbtechincaltest.viewmodel.PhotoViewModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {

    //provides the photoList repository implementation
    fun provideStackExchangeRepo(photosApi: PhotosApi): PhotoRepository =
        PhotoRepositoryImpl(photosApi)

    //provides Gson object
    fun provideGson() = GsonBuilder().create()

    //provides logging interceptor for http requests and responses
    fun provideLoggingInterceptor() =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    //provide okhttp client object for making http requests
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor) =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

    //provide retrofit builder for api requests
    fun provideStackExchangeApi(okHttpClient: OkHttpClient, gson: Gson): PhotosApi =
        Retrofit.Builder()
            .baseUrl(PhotosApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
            .create(PhotosApi::class.java)

    //provides single instances
    single { provideGson() }
    single { provideLoggingInterceptor() }
    single { provideOkHttpClient(get()) }
    single { provideStackExchangeApi(get(), get()) }
    single { provideStackExchangeRepo(get()) }
}

val viewModelModule = module {

    //provides single instance of viewModel
    viewModel {
        PhotoViewModel(get())
    }
}