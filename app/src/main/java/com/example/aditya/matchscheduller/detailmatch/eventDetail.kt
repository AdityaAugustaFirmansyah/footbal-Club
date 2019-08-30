package com.example.aditya.matchscheduller.detailmatch

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class eventDetail (

    @SerializedName("idEvent")
    var idEvent: String? = null,

    @SerializedName("idHomeTeam")
    var idHome:String?=null,

    @SerializedName("idAwayTeam")
    var idAway:String?=null,

    @SerializedName("strHomeTeam")
    var strHomeTeam: String? = null,

    @SerializedName("strAwayTeam")
    var strAwayTeam: String? = null,

    @SerializedName("intHomeScore")
    var intHomeScore: String? = null,

    @SerializedName("intAwayScore")
    var intAwayScore: String? = null,

    @SerializedName("dateEvent")
    var dateEvent: String? = null,

    @SerializedName("strHomeGoalDetails")
    var strHomeGoalDetails: String? = null,

    @SerializedName("strAwayGoalDetails")
    var strAwayGoalDetails: String? = null,

    @SerializedName("strAwayLineupGoalkeeper")
    var strAwayLineupGoalkeeper:String? = null,

    @SerializedName("strHomeLineupGoalkeeper")
    var strHomeLineupGoalkeeper:String? = null,

    @SerializedName("strHomeLineupDefense")
    var strHomeLineupDefense:String? = null,

    @SerializedName("strAwayLineupDefense")
    var strAwayLineupDefense:String? = null,

    @SerializedName("strHomeLineupMidfield")
    var strHomeLineupMidfield:String?=null,

    @SerializedName("strAwayLineupMidfield")
    var strAwayLineupMidfield:String?=null,

    @SerializedName("strHomeLineupForward")
    var strHomeLineupForward:String?=null,

    @SerializedName("strAwayLineupForward")
    var strAwayLineupForward:String?=null,

    @SerializedName("strHomeLineupSubstitutes")
    var strHomeSubstitutes:String?=null,

    @SerializedName("strAwayLineupSubstitutes")
    var strAwayLineupSubstitutes:String?=null


)