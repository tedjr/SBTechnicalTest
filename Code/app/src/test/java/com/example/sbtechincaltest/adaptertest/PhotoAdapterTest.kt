package com.example.sbtechincaltest.adaptertest

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.sbtechincaltest.adapter.PhotoAdapter
import com.example.sbtechincaltest.adapter.PhotoViewHolder
import com.example.sbtechincaltest.model.PhotoItem
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

//@RunWith(AndroidJUnit4::class)
class PhotoAdapterTest {


    private lateinit var adapter: PhotoAdapter
    private val photoItemList = mutableListOf(
        PhotoItem(1, 1, "title1", "url1", "thumbnailUrl1"),
        PhotoItem(2, 2, "title2", "url2", "thumbnailUrl2"),
        PhotoItem(3, 3, "title3", "url3", "thumbnailUrl3")
    )

    @Before
    fun setUp() {
        adapter = PhotoAdapter()
    }

    @Test
    fun `onBindViewHolder should call bindData on the viewHolder with the correct photoItem`() {
        // Arrange
        val photoItem = PhotoItem(1, 1, "title1", "url1", "thumbnailUrl1")
        val viewHolder = mockk<PhotoViewHolder>(relaxed = true)
        adapter = PhotoAdapter(mutableListOf(photoItem))

        // Act
        adapter.onBindViewHolder(viewHolder, 0)

        // Assert
        verify { viewHolder.bindData(photoItem) }
    }


    /*
    The tests below work with more recent project configurations and by adding the @RunWith(AndroidJUnit4::class) annotation
     */
    @Test
    fun `loadData should update the list of photos`() {
        // Arrange
        val adapter = PhotoAdapter()
        // Act
        adapter.loadData(photoItemList)
        // Assert
        assertEquals(photoItemList, adapter.listOfPhotos)
    }

    @Test //WORKS
//    loadData_shouldCallNotifyDataSetChanged()
    fun `loadData should call notifyDataSetChanged`() {
        // Arrange
        val adapter = spyk(PhotoAdapter())

        // Act
        adapter.loadData(photoItemList)

        // Assert
        verify(exactly = 1) { adapter.notifyDataSetChanged() }
    }

    @Test
    fun testLoadData() {
        val adapter = PhotoAdapter()

        adapter.loadData(photoItemList)
        assertEquals(photoItemList.size, adapter.itemCount)
    }
}