package com.bonobono.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.bonobono.presentation.ui.community.Photo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PhotoViewModel @Inject constructor(): ViewModel() {
    private val _selectedPhoto: SnapshotStateList<Photo> = mutableStateListOf()
    val selectedPhoto: SnapshotStateList<Photo>
        get() = _selectedPhoto

    private val _selectedOnePhoto = mutableStateOf(Photo("",false,true))
    val selectedOnePhoto : MutableState<Photo>
        get() = _selectedOnePhoto

    fun setOnePhoto(photo : Photo) {
        _selectedOnePhoto.value = photo
    }

    fun setPhotoList(photoList: List<Photo>) {
        _selectedPhoto.addAll(photoList)
    }

    fun removePhoto(photo: Photo) {
        _selectedPhoto.remove(photo)
    }
}