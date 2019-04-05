package com.example.aditya.matchscheduller.data

import com.google.gson.annotations.SerializedName

data class SearchNextMatchResponse (
    @SerializedName("event")
    var events:MutableList<LastMatch>
)