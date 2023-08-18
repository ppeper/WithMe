package com.bonobono.data.mapper

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

object Converter {

    fun createMultipartBodyPart(imagePath: String): MultipartBody.Part {
        val file = File(imagePath)
        val requestFile = file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
        return requestFile.let { MultipartBody.Part.createFormData("imageFiles", file.name, it) }
    }

    fun createMultipartBodyPartOnePhoto(imagePath: String): MultipartBody.Part {
        val file = File(imagePath)
        val requestFile = file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
        return requestFile.let { MultipartBody.Part.createFormData("image", file.name, it) }
    }
}