package com.example.sbtechincaltest.viewmodeltest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.sbtechincaltest.rest.PhotoRepository
import com.example.sbtechincaltest.utils.UIState
import com.example.sbtechincaltest.utils.getPhotoData
import com.example.sbtechincaltest.viewmodel.PhotoViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PhotoViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val testCoroutineRule = TestCoroutinesRule()

    @Mock
    lateinit var getPhotosInfo: PhotoRepository
    private val photoData = getPhotoData()

    @Mock
    lateinit var observer: Observer<UIState>

    private lateinit var viewModel: PhotoViewModel
//    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = PhotoViewModel((getPhotosInfo))
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testCheckingNonNullResult() =
        testCoroutineRule.runBlockingTest {
            val expectedOutput = UIState.SUCCESS(photoData)
            viewModel.photosLiveData.observeForever(observer)
            Mockito.`when`(viewModel.photosLiveData.value).thenAnswer {
                flowOf(expectedOutput)
            }
            viewModel.subscribeToPhotosList()

            Assert.assertNotNull(viewModel.photosLiveData.value)
        }
}