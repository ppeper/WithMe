package com.bonobono.data.model.community.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ImageResponse(
    val imageName: String?,
    val imageUrl: String
): Parcelable