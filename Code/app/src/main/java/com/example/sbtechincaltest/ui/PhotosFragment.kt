package com.example.sbtechincaltest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sbtechincaltest.adapter.PhotoAdapter
import com.example.sbtechincaltest.databinding.FragmentPhotosBinding
import com.example.sbtechincaltest.model.Photos
import com.example.sbtechincaltest.utils.UIState
import com.example.sbtechincaltest.utils.showErrorMsg
import com.example.sbtechincaltest.viewmodel.PhotoViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PhotosFragment : Fragment() {

    //lazy initialization to provide better performance by delaying the view creation until it is actually required
    private val binding by lazy {
        FragmentPhotosBinding.inflate(layoutInflater)
    }

    private val photoViewModel: PhotoViewModel by viewModel()
    private lateinit var photoAdapter: PhotoAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        photoAdapter = PhotoAdapter()
        binding.photosList.adapter = photoAdapter


        //Observes the photo list data from the ViewModel and updates UI accordingly based on the state
        photoViewModel.photosLiveData.observe(viewLifecycleOwner) { uiState ->
            when (uiState) {
                is UIState.LOADING -> {}
                is UIState.SUCCESS -> {
                    val photosList = uiState.success as Photos
                    photoAdapter.loadData(photosList)
                }
                is UIState.ERROR -> {
                    showErrorMsg(binding.root, uiState.error.localizedMessage.toString())
//                    uiState.error.localizedMessage?.let { showErrorMsg(binding.root, it.toString()) }
                }
            }
        }

        //retrieves the photos data from the repository
        photoViewModel.subscribeToPhotosList()

        return binding.root
    }

}