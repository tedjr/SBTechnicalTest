package com.example.sbtechincaltest.utils

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.sbtechincaltest.model.PhotoItem
import com.example.sbtechincaltest.model.Photos
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson

//extension function to load images from a given URL using glide
fun ImageView.loadImageFromUrl(thumbnailUrl: String) {
    Glide.with(context).load(thumbnailUrl).into(this)
}

fun getPhotoData(): PhotoItem {
    val sampleData = "{\n" +
            "  \"albumId\": 1,\n" +
            "  \"id\": 5,\n" +
            "  \"title\": \"natus nisi omnis corporis facere molestiae rerum in\",\n" +
            "  \"url\": \"https://via.placeholder.com/600/f66b97\",\n" +
            "  \"thumbnailUrl\": \"https://via.placeholder.com/150/f66b97\"\n" +
            "}"
    val gson = Gson()
    return gson.fromJson(sampleData, PhotoItem::class.java)
}

fun Photos.fromJson(json: String): Photos {
    val photoItems = Gson().fromJson(json, Array<PhotoItem>::class.java)
    return Photos().apply { addAll(photoItems) }
}


fun showErrorMsg(parent: View, msg: String) {
    val snack: Snackbar = Snackbar.make(
        parent, msg, Snackbar.LENGTH_LONG
    )
    snack.show()
}