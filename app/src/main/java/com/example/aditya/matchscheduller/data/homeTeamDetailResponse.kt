package com.example.aditya.matchscheduller.data

import com.google.gson.annotations.SerializedName

data class homeTeamDetailResponse(
    @SerializedName("teams")
    val homeTeamDetail: List<homeTeamDetail> = ArrayList()
)