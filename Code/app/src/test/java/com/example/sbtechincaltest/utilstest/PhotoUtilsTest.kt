package com.example.sbtechincaltest.utilstest

import android.content.Context
import android.widget.ImageView
import com.example.sbtechincaltest.utils.getPhotoData
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class PhotoUtilsTest {

    private lateinit var context: Context
    private lateinit var imageView: ImageView

    @Before
    fun setup() {
        context = mockk(relaxed = true)
        imageView = mockk(relaxed = true)
    }

    @Test// WORKS
    fun `test getPhotoData`() {
        val photoItem = getPhotoData()
        assertEquals(photoItem.albumId, 1)
        assertEquals(photoItem.id, 5)
        assertEquals(photoItem.title, "natus nisi omnis corporis facere molestiae rerum in")
        assertEquals(photoItem.url, "https://via.placeholder.com/600/f66b97")
        assertEquals(photoItem.thumbnailUrl, "https://via.placeholder.com/150/f66b97")
    }
}