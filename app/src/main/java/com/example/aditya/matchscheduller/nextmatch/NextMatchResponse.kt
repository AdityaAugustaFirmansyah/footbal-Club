package com.example.aditya.matchscheduller.nextmatch

import com.google.gson.annotations.SerializedName

data class NextMatchResponse(
    @SerializedName("events")
    val ListMatch:List<NextMatch> = ArrayList()
)
