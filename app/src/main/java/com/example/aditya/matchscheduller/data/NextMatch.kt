package com.example.aditya.matchscheduller.data

import com.google.gson.annotations.SerializedName

data class NextMatch(
    @SerializedName("idEvent")
    var idEvent:String? = null,

    @SerializedName("strHomeTeam")
    var strHomeTeam:String? = null,

    @SerializedName("strAwayTeam")
    var strAwayTeam:String? = null,

    @SerializedName("idHomeTeam")
    var idHomeTeam:String? = null,

    @SerializedName("idAwayTeam")
    var idAwayTeam:String? = null,

    @SerializedName("dateEvent")
    var dateEvent:String?=null,

    @SerializedName("strTime")
    var time:String? = null,

    @SerializedName("strEvent")
    var nameMatch:String? = null

)
