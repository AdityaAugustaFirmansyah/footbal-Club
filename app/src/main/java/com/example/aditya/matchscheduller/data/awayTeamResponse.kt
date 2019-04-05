package com.example.aditya.matchscheduller.data

import com.google.gson.annotations.SerializedName

data class awayTeamResponse (
    @SerializedName("teams")
    val awayTeamDetail: List<AwayTeamDetail> = ArrayList()
)
