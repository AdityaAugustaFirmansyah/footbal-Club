package com.example.aditya.matchscheduller.detailmatch

import com.google.gson.annotations.SerializedName

data class awayTeamResponse (
    @SerializedName("teams")
    val awayTeamDetail: List<AwayTeamDetail> = ArrayList()
)
