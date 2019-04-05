package com.example.aditya.matchscheduller.data

data class LeagueModel (
    var idLeague:String?,
    var strLeague:String?

) {
    override fun toString(): String {
        return strLeague.toString()
    }
}