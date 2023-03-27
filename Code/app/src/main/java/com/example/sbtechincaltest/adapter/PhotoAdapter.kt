package com.example.sbtechincaltest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sbtechincaltest.databinding.LayoutPhotoitemBinding
import com.example.sbtechincaltest.model.PhotoItem
import com.example.sbtechincaltest.utils.loadImageFromUrl

class PhotoAdapter(
    val listOfPhotos: MutableList<PhotoItem> = mutableListOf()
) : RecyclerView.Adapter<PhotoViewHolder>() {

    //load list of photos to be displayed in the RecyclerView
    fun loadData(listOfAlerts: MutableList<PhotoItem>) {
        listOfPhotos.clear()
        listOfPhotos.addAll(listOfAlerts)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder(
            LayoutPhotoitemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bindData((listOfPhotos[position]))
    }

    override fun getItemCount(): Int {
        return listOfPhotos.size
    }

}

class PhotoViewHolder(itemView: LayoutPhotoitemBinding) : RecyclerView.ViewHolder(itemView.root) {

    val binding = itemView

    //bind data to the view holder
    fun bindData(photoItem: PhotoItem) {
        binding.photoTitle.text = photoItem.title
        binding.imageView.loadImageFromUrl(photoItem.thumbnailUrl)

    }
}