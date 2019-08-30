package com.example.aditya.matchscheduller.detailmatch

import com.google.gson.annotations.SerializedName

data class evenDetailResponse (
    @SerializedName("events")
    val eventDetail: List<eventDetail> = ArrayList()
)