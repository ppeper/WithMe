package com.bonobono.data.model.registration.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ImgResponse (
    val imgName : String,
    val imgUrl : String
) : Parcelable