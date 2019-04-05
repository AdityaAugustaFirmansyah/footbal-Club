package com.example.aditya.matchscheduller.data

import com.google.gson.annotations.SerializedName

data class LastMatchResponse(
    @SerializedName("events")
    val lastMatch : List<LastMatch> = ArrayList()
)
