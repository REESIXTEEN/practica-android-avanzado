package com.example.practica_android_avanzado.data.remote.response

import androidx.room.Entity
import com.squareup.moshi.Json


@Entity
data class GetHerosResponse(
    @Json(name = "id") val id: String,
    @Json(name = "name") val name: String,
    @Json(name = "description") val description: String,
    @Json(name = "favorite") val favorite: Boolean,
    @Json(name = "photo") val photo: String,
)
