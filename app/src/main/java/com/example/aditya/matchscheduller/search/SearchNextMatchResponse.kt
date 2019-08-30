package com.example.aditya.matchscheduller.search

import com.example.aditya.matchscheduller.lastmatch.LastMatch
import com.google.gson.annotations.SerializedName

data class SearchNextMatchResponse (
    @SerializedName("event")
    var events:MutableList<LastMatch>
)