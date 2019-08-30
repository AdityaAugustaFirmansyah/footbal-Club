package com.example.aditya.matchscheduller.lastmatch

import com.google.gson.annotations.SerializedName

data class LastMatchResponse(
    @SerializedName("events")
    val lastMatch : List<LastMatch> = ArrayList()
)
