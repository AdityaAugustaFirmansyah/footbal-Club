package com.example.aditya.matchscheduller.data

import com.google.gson.annotations.SerializedName

data class PlayerResponse (
    @SerializedName("player")
    val players:List<PlayerModel>
)