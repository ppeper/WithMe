package com.bonobono.presentation.viewmodel

import androidx.compose.runtime.mutableStateListOf
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

    fun removePhoto(photo: Photo) {
        _selectedPhoto.remove(photo)
    }
}