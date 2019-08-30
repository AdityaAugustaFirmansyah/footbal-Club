package com.example.aditya.matchscheduller.detailmatch

import com.google.gson.annotations.SerializedName

data class homeTeamDetailResponse(
    @SerializedName("teams")
    val homeTeamDetail: List<homeTeamDetail> = ArrayList()
)