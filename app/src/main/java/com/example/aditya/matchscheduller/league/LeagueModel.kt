package com.example.aditya.matchscheduller.league

data class LeagueModel (
    var idLeague:String?,
    var strLeague:String?

) {
    override fun toString(): String {
        return strLeague.toString()
    }
}