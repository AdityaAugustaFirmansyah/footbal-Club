package com.example.aditya.matchscheduller.data

import com.google.gson.annotations.SerializedName

data class evenDetailResponse (
    @SerializedName("events")
    val eventDetail: List<eventDetail> = ArrayList()
)