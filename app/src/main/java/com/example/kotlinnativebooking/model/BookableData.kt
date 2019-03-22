package com.example.kotlinnativebooking.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class BookableData(
    var id: Int? = null,
    var hotel: HotelData? = null,
   @SerializedName("room_count")
    var roomCount: Int? = null,
    var dtFrom: Date? = null,
    var dtTo: Date? = null
)