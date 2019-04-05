package com.example.aditya.matchscheduller.sqlite

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


data class Favourite(val id:Long?,val idEvent:String?,
                     val idHome:String?,
                     val idAway:String?,
                     val strHomeTeam:String?,
                     val strAwayName:String?,
                     val intHomeScore:String?,
                     val intAwayScore:String?,
                     val dateEvent :String?,
                     val time :String?){

    companion object {
        const val TABLE_NAME:String = "TABLE_FAVORITE"
        const val ID:String = "ID_"
        const val ID_EVENT:String = "ID_EVENT"
        const val HOME_ID:String = "HOME_ID"
        const val AWAY_ID:String = "AWAY_ID"
        const val HOME_NAME:String = "HOME_NAME"
        const val AWAY_NAME:String = "AWAY_NAME"
        const val HOME_SCORE:String = "HOME_SCORE"
        const val AWAY_SCORE:String = "AWAY_SCORE"
        const val DATE:String = "DATE"
        const val TIME:String = "TIME"

    }
}
