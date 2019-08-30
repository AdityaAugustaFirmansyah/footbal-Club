package com.example.aditya.matchscheduller.search

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchModel (
    @SerializedName("idEvent")
    var idEvent:String? = null,

    @SerializedName("strHomeTeam")
    var strHomeTeam:String? = null,

    @SerializedName("strAwayTeam")
    var strAwayTeam:String? = null,

    @SerializedName("idHomeTeam")
    var idHomeTeam:String?=null,

    @SerializedName("idAwayTeam")
    var idAwayTeam:String?=null,

    @SerializedName("intHomeScore")
    var intHomeScore:String?=null,

    @SerializedName("intAwayScore")
    var intAwayScore:String?=null,

    @SerializedName("dateEvent")
    var dateEvent:String?=null,

    @SerializedName("strTime")
    var time:String? = null
):Parcelable